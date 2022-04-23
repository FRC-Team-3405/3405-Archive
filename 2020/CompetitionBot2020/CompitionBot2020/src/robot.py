from wpilib import TimedRobot, run, Timer
from networktables import NetworkTables

from components.drive.driveMotor import DriveMotor
from components.drive.driveMotorGroup import DriveMotorGroup
from components.drive.driveBase import DriveBase
from components.controlers.driverControl import DriverControl
from components.controlers.operatorControl import OperatorControl
from components.shifter import Shifter
from components.shooter.indexer import Indexer
from components.climber.arm import Arm
from components.climber.lifter import Lifter
from components.shooter.intake import Intake


class Robot(TimedRobot):
    def robotInit(self):
        self.driver = DriverControl(0)
        self.operator = OperatorControl(1)

        self.dashboard = NetworkTables.getTable("SmartDashboard")

        self.right1 = DriveMotor(3, True)
        self.right2 = DriveMotor(4, True)
        self.right = DriveMotorGroup([self.right1, self.right2])

        self.left1 = DriveMotor(1, False)
        self.left2 = DriveMotor(2, False)
        self.left = DriveMotorGroup([self.left1, self.left2])

        self.drive = DriveBase(self.right, self.left, self.driver)
        self.driveMotors = [self.right1, self.right2, self.left1, self.left2]

        self.shifter = Shifter(0, 1, self.driver)

        self.indexer = Indexer(self.operator)

        self.arm = Arm(self.operator)
        self.lifter = Lifter(self.operator)
        self.intake = Intake(self.operator)

    def disabledInit(self):
        pass

    def autonomousInit(self):
        self.autonomousTimer = Timer()
        self.autonomousTimer.start()

    def teleopInit(self):
        pass

    def testInit(self):
        pass

    def robotPeriodic(self):
        pass

    def disabledPeriodic(self):
        pass

    def autonomousPeriodic(self):
        if (self.autonomousTimer.get() < 2):
            self.drive.setRaw(.5, .5)
        else:
            self.drive.setRaw(0, 0)

    def teleopPeriodic(self):
        self.indexer.update()
        self.shifter.update()
        self.drive.update()
        self.arm.update()
        self.lifter.update()
        self.intake.update()

    def testPeriodic(self):
        pass


if __name__ == "__main__":
    run(Robot)
