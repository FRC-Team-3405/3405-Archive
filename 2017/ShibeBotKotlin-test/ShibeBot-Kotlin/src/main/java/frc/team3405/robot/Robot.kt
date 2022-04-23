package frc.team3405.robot
import edu.wpi.first.wpilibj.IterativeRobot
import lib.BaseRobot
import lib.command
import lib.subsystem

class Robot : BaseRobot({



    val driveCommand = command {
        fun x() {}
    }

    val mySubsystem = subsystem(driveCommand) {  }



    onInit {

    }

    disabled {

    }
})