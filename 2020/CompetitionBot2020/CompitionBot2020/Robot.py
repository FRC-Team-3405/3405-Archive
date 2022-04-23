from wpilib import TimedRobot, run
from ctre import WPI_TalonFX


class Robot(TimedRobot):
    def robotInit(self):
        pass

    def disabledInit(self):
        pass

    def autonomusInit(self):
        pass

    def teleopInit(self):
        pass

    def testInit(self):
        pass

    def robotPeriodic(self):
        pass

    def disabledPeriodic(self):
        pass

    def autonomusPeriodic(self):
        pass

    def teleopPeriodic(self):
        pass

    def testPeriodic(self):
        pass


if __name__ == "__main__":
    run(Robot)
