/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.FollowPathCommand
import frc.robot.commands.buttons.*
import frc.robot.maps.JoystickMap
import frc.robot.maps.RobotMap
import frc.robot.maps.RobotMap.LEFT_LIGHT_SENSOR_PORT
import frc.robot.maps.RobotMap.RIGHT_LIGHT_SENSOR_PORT
import frc.robot.maps.XboxMap
import frc.robot.subsystems.*
import frc.robot.utilties.LightSensor
import frc.robot.utilties.onPressed
import frc.robot.utilties.whileHeld

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : TimedRobot() {
    private var currentPathCommand: FollowPathCommand? = null

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    override fun robotInit() {
        Robot.gyroscope.initGryo()
        Robot.gyroscope.calibrate()

        //Register permanent button commands
        joystick.RightBumperButton.onPressed(ShiftHighGearCommand())
        joystick.LeftBumperButton.onPressed(ShiftLowGearCommand())
        joystick.NineButton.onPressed(GrabHatchPanelCommand())
        joystick.TenButton.onPressed(ReleaseHatchPanelCommand())
        joystick.AButton.onPressed(SwitchDirectionCommand())
        joystick.BButton.onPressed(ToggleFeederCommand())
        //RESERVED: Reverse feeder motors/belt: joystick.RightLowerBumperButton
        //RESERVED: Brake on line follower detect line: joystick.LeftLowerBumperButton
        //RESERVED: POV for specialized drive commands

        joystick.XButton.onPressed {
//            val path = SmartDashboard.getString("path", "")
            val path = "G-R"
            if (path.isNotEmpty() && currentPathCommand?.isRunning != true) {
                currentPathCommand = FollowPathCommand(path)
                currentPathCommand?.start()
                println("Started following path $path")
                SmartDashboard.putString("current_path", path)
            } else if (currentPathCommand?.isRunning == true) {
                currentPathCommand?.cancel()
                println("Cancelled path $path")
                SmartDashboard.putString("current_path", null)
            }
        }

        joystick.YButton.whileHeld {
            //TODO point at camera target
        }

        secondaryJoystick.triggerButton.onPressed(ReleaseHatchPanelCommand())
    }

    /**
     * This function is called every robot packet, no matter the mode.
     */
    override fun robotPeriodic() {
        SmartDashboard.updateValues()
        Scheduler.getInstance().run()
    }

    /**
     * This function is called at the beginning of the sandstorm period.
     */
    override fun autonomousInit() {
        println("Sandstorm has begun")
    }

    /**
     * This function is called periodically during the sandstorm period.
     */
    override fun autonomousPeriodic() {

    }

    /**
     * This function is called at the end of the sandstorm period.
     */
    override fun teleopInit() {
        println("Tele-Op has begun")
    }

    /**
     * This function is called periodically during operator control.
     */
    override fun teleopPeriodic() {

    }

    /**
     * This function is run when test is first started.
     */
    override fun testInit() {
        println("Test initialized")
    }

    /**
     * This function is called periodically during test mode.
     */
    override fun testPeriodic() {

    }

    /**
     * This function is run when robot is first disabled.
     */
    override fun disabledInit() {
        println("Robot disabled")
    }

    /**
     * This function is called periodically while disabled.
     */
    override fun disabledPeriodic() {

    }

    /**
     * Static members of the robot (Subsystems)
     */
    companion object {
        //Control Joysticks
        val joystick = XboxMap.Controller(Joystick(RobotMap.MAIN_JOYSTICK_PORT))
        val secondaryJoystick = JoystickMap.Controller(Joystick(RobotMap.SECONDARY_JOYSTICK_PORT))

        //Drive subsystems
        val driveTrain = DriveTrain()

        //Sensor subsystems
        val builtInAccelerometer = Accelerometer()
        val gyroscope = Gyroscope()

        //Pneumatics subsystems
        val pneumatics = Pneumatics()

        //Elevator subsystems
        val elevator = Elevator()

        //Box subsystems
        val box = Box()

        //Feeder subsystems
        val feeder = Feeder()

        val leftLightSensor = LightSensor(LEFT_LIGHT_SENSOR_PORT)
        val rightLightSensor = LightSensor(RIGHT_LIGHT_SENSOR_PORT)

        //Power
//        val pdp = PowerDistributionPanel(0)
    }
}
