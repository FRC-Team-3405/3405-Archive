package frc.robot.maps

/**
 * Maximum motor controller output percentage
 */
const val MAX_MOTOR_SPEED = 0.85

/**
 * Maximum Robot velocity when following paths (ft/s)
 */
const val MAX_ROBOT_VELOCITY = 10.0

/**
 * Maximum speed of the elevator motor
 */
const val MAX_ELEVATOR_SPEED = 0.5

/**
 * Whether or not the left side of the robot should be inverted
 */
const val INVERT_LEFT = false

/**
 * Whether or not the right side of the robot should be inverted.
 */
const val INVERT_RIGHT = true

/**
 * Error if action exceeds this timeout period
 */
const val TIMEOUT_MS = 30

/**
 * Motor neutral dead-band, set to the minimum 0.1%. Currently set to 0.5%.
 */
const val NEUTRAL_DEADBAND = 0.005

/**
 * Primary and auxiliary PID Index
 */
const val PID_PRIMARY = 0
const val PID_TURN = 1

/**
 * Remote device index 0 (Can have up to 2 remote devices [0, 1])
 */
const val REMOTE_0 = 0

/**
 * Using the configSelectedFeedbackCoefficient() function, scale units to 3600 per rotation.
 * This is nice as it keeps 0.1 degrees of resolution, and is fairly intuitive.
 */
const val TURN_TRAVEL_UNITS_PER_ROTATION = 3600.0

//TODO adjust this constant
/**
 * Encoder units per one robot rotation
 *
 * Empirically measure what the difference between encoders per 360'
 * Drive the robot in clockwise rotations and measure the units per rotation.
 * Drive the robot in counter clockwise rotations and measure the units per rotation.
 * Take the average of the two.
 */
const val ENCODER_UNITS_PER_ROTATION = 9500 //Demo was 51711 WAS 10960 THEN 9500

/**
 * Encoder units per one shaft rotation
 */
const val ENCODER_UNITS_PER_REVOLUTION = 1440.0
/** 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
const val CLOSED_LOOP_TIME_MS = 1

/** configAuxPIDPolarity(boolean invert, int timeoutMs)
        * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
        * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
        */
const val AUX_PID_POLARITY = true

/**
 * Diameter of the robot's wheels IN METERS (Typically 0.15 or 0.102, which is 6 in and 4 in respectively)
 */
const val WHEEL_DIAMETER = 0.15 // (6 inches)

/**
 * Turning slot (Not entirely sure)
 */
const val SLOT_TURNING = 1

/**
 * The name of the main robot gyro reported to the Smart Dashboard
 */
const val MAIN_GYRO_NAME = "Main"

//TODO tune this PID
/**
 * PID values for turning. May need to be adjusted. (kP should really be ~2, but our encoders are off and the motors so consistent, we're ignoring that for now)
 */
val GAINS_TURNING = Gains(4.0, 0.0, 0.0, 0.0, 200, 1.00, 0)

data class Gains(val kP: Double, val kI: Double, val kD: Double, val kF: Double, val kIZone: Int, val kPeakOutput: Double, val allowableClosedLoopError: Int)
