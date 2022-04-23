from .driveMotorGroup import DriveMotorGroup
from ..controlers.driverControl import DriverControl
from ..limelight import Limelight


class DriveBase():
    def __init__(self, right: DriveMotorGroup,
                 left: DriveMotorGroup,
                 driver: DriverControl):
        
        self.right = right
        self.left = left
        self.driver = driver
        self.limelight = Limelight(self, self.driver)
        self.speed = 1
        self.highSpeed = 1
        self.normalSpeed = .66
        self.lowSpeed = .5
        self.lowestSpeed = .20

    def update(self):
        if (self.limelight.update()):
            return
   
        if (self.driver.getSpeedUp() and self.driver.getSpeedDown()):
            self.speed = self.lowestSpeed
        elif (self.driver.getSpeedUp()):
            self.speed = self.normalSpeed
        elif (self.driver.getSpeedDown()):
            self.speed = self.lowSpeed
        else:
            self.speed = self.highSpeed

        left = (self.driver.getLeftY() ** 3) * self.speed
        right = (self.driver.getRightY() ** 3) * self.speed

        if (self.driver.getFullForward()):
            right = self.speed
            left = self.speed
        elif (self.driver.getFullBackward()):
            right = -self.speed
            left = -self.speed
        
        self.right.set(right)
        self.left.set(left)
        
    def setRaw(self, right, left):
        self.right.set(right)
        self.left.set(left)
