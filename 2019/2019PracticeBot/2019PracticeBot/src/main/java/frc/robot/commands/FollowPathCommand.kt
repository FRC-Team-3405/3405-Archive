package frc.robot.commands

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.Notifier
import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot
import frc.robot.maps.ENCODER_UNITS_PER_REVOLUTION
import frc.robot.maps.MAX_ROBOT_VELOCITY
import frc.robot.maps.WHEEL_DIAMETER
import frc.robot.subsystems.Direction
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.PathfinderFRC
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower
import java.lang.Exception
import kotlin.math.PI

class FollowPathCommand(val pathName: String): Command() {

    init {
        requires(Robot.driveTrain)
        requires(Robot.gyroscope)
    }

    private lateinit var leftFollower: EncoderFollowerThatIsMineAndMineOnlyBwaHaHaHaHaHa
    private lateinit var rightFollower: EncoderFollowerThatIsMineAndMineOnlyBwaHaHaHaHaHa

    private lateinit var followerNotifier: Notifier

    private var finished = false

    override fun initialize() {
        super.initialize()

        Robot.driveTrain.resetEncoderCounts()
        Robot.gyroscope.reset()

        val leftTrajectory = PathfinderFRC.getTrajectory("$pathName.left")
        val rightTrajectory = PathfinderFRC.getTrajectory("$pathName.right")

        leftFollower = EncoderFollowerThatIsMineAndMineOnlyBwaHaHaHaHaHa(leftTrajectory)
        rightFollower = EncoderFollowerThatIsMineAndMineOnlyBwaHaHaHaHaHa(rightTrajectory)

        leftFollower.configureEncoder(Robot.driveTrain.getLeftEncoder(), ENCODER_UNITS_PER_REVOLUTION.toInt(), WHEEL_DIAMETER)
        //TODO tune the PID values on the following line!
        leftFollower.configurePIDVA(0.4, 0.0, 0.5, 1 / MAX_ROBOT_VELOCITY, 0.0)

        rightFollower.configureEncoder(Robot.driveTrain.getRightEncoder(), ENCODER_UNITS_PER_REVOLUTION.toInt(), WHEEL_DIAMETER)
        //TODO tune the PID values on the following line!
        rightFollower.configurePIDVA(0.4, 0.0, 0.5, 1 / MAX_ROBOT_VELOCITY, 0.0)

        followerNotifier = Notifier(::followPath)
        followerNotifier.startPeriodic(0.2) //TODO create constant
    }

    private fun followPath() {
        try {
            if (leftFollower.isFinished || rightFollower.isFinished) {
                println("Path complete!")
                followerNotifier.stop()
                finished = true
            } else {
                val leftSpeed = leftFollower.calculate(Robot.driveTrain.getLeftEncoder())
                val rightSpeed = rightFollower.calculate(Robot.driveTrain.getRightEncoder())

                println("Left speed: $leftSpeed")
                println("Right speed: $rightSpeed")

                println("Left encoder: ${Robot.driveTrain.getLeftEncoder()}")
                println("Right encoder: ${Robot.driveTrain.getRightEncoder()}")
                val heading = Robot.gyroscope.getAngle()
                println("Heading: $heading")
                val desiredHeading = Pathfinder.r2d(leftFollower.heading)
                println("Desired Heading: $desiredHeading")
                val headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading)
                println("Heading difference: $headingDifference")
                val turn = 1.0 * (-1.0 / 80.0) * headingDifference
                println("Turning: $turn")
                Robot.driveTrain.driveSide((leftSpeed + turn), (rightSpeed - turn), Direction.HATCH_FORWARD)
            }
        } catch (e: Exception) {
            Robot.driveTrain.driveSide(0.0, 0.0)
        }
    }

    override fun interrupted() {
        followerNotifier.stop()
        finished = true
        Robot.driveTrain.driveSide(0.0, 0.0)
    }

    override fun isFinished() = finished

}

class EncoderFollowerThatIsMineAndMineOnlyBwaHaHaHaHaHa {

    internal var encoder_offset: Int = 0
    internal var encoder_tick_count: Int = 0
    internal var wheel_circumference: Double = 0.toDouble()

    internal var kp: Double = 0.toDouble()
    internal var ki: Double = 0.toDouble()
    internal var kd: Double = 0.toDouble()
    internal var kv: Double = 0.toDouble()
    internal var ka: Double = 0.toDouble()

    internal var last_error: Double = 0.toDouble()
    /**
     * @return the desired heading of the current point in the trajectory
     */
    var heading: Double = 0.toDouble()
        internal set

    internal var segment: Int = 0
    internal lateinit var trajectory: Trajectory

    /**
     * @return whether we have finished tracking this trajectory or not.
     */
    val isFinished: Boolean
        get() = segment >= trajectory.length()

    constructor(traj: Trajectory) {
        this.trajectory = traj
    }

    constructor() {}

    /**
     * Set a new trajectory to follow, and reset the cumulative errors and segment counts
     * @param traj a previously generated trajectory
     */
    fun setTrajectory(traj: Trajectory) {
        this.trajectory = traj
        reset()
    }

    /**
     * Configure the PID/VA Variables for the Follower
     * @param kp The proportional term. This is usually quite high (0.8 - 1.0 are common values)
     * @param ki The integral term. Currently unused.
     * @param kd The derivative term. Adjust this if you are unhappy with the tracking of the follower. 0.0 is the default
     * @param kv The velocity ratio. This should be 1 over your maximum velocity @ 100% throttle.
     * This converts m/s given by the algorithm to a scale of -1..1 to be used by your
     * motor controllers
     * @param ka The acceleration term. Adjust this if you want to reach higher or lower speeds faster. 0.0 is the default
     */
    fun configurePIDVA(kp: Double, ki: Double, kd: Double, kv: Double, ka: Double) {
        this.kp = kp
        this.ki = ki
        this.kd = kd
        this.kv = kv
        this.ka = ka
    }

    /**
     * Configure the Encoders being used in the follower.
     * @param initial_position      The initial 'offset' of your encoder. This should be set to the encoder value just
     * before you start to track
     * @param ticks_per_revolution  How many ticks per revolution the encoder has
     * @param wheel_diameter        The diameter of your wheels (or pulleys for track systems) in meters
     */
    fun configureEncoder(initial_position: Int, ticks_per_revolution: Int, wheel_diameter: Double) {
        encoder_offset = initial_position
        encoder_tick_count = ticks_per_revolution
        wheel_circumference = Math.PI * wheel_diameter
    }

    /**
     * Reset the follower to start again. Encoders must be reconfigured.
     */
    fun reset() {
        last_error = 0.0
        segment = 0
    }

    /**
     * Calculate the desired output for the motors, based on the amount of ticks the encoder has gone through.
     * This does not account for heading of the robot. To account for heading, add some extra terms in your control
     * loop for realignment based on gyroscope input and the desired heading given by this object.
     * @param encoder_tick The amount of ticks the encoder has currently measured.
     * @return             The desired output for your motor controller
     */
    fun calculate(encoder_tick: Int): Double {
        // Number of Revolutions * Wheel Circumference
        val distance_covered = Math.abs((encoder_tick - encoder_offset).toDouble() / encoder_tick_count * wheel_circumference)
        println("Covered distance: $distance_covered")
        if (segment < trajectory.length()) {
            val seg = trajectory.get(segment)
            val error = seg.position - distance_covered
            println("Erro: $error")
            val calculated_value = kp * error +                                    // Proportional

                    kd * ((error - last_error) / seg.dt) +          // Derivative

                    (kv * seg.velocity + ka * seg.acceleration)    // V and A Terms
            last_error = error
            heading = seg.heading
            segment++

            return calculated_value
        } else
            return 0.0
    }

    /**
     * @return the current segment being operated on
     */
    fun getSegment(): Trajectory.Segment {
        return trajectory.get(segment)
    }

}
