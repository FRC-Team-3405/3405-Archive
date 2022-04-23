from components.solenoid import Solenoid
from components.controlers.driverControl import DriverControl


class Shifter():
    def __init__(self, lowId, highId, driver: DriverControl):
        self.solenoid = Solenoid(lowId, highId)
        self.driver = driver
        self.high = False

    def update(self):
        if self.driver.getShiftUp():
            self.high = True
        elif self.driver.getShiftDown():
            self.high = False
        
        self.solenoid.set(self.high)
