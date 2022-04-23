from networktables import NetworkTables
from .controlers.driverControl import DriverControl


class Limelight():
    def __init__(self, driveTrain, driver: DriverControl):
        self.table = NetworkTables.getTable("limelight")
        self.drive = driveTrain
        self.driver = driver
        self.kp = 1

    def update(self):
        pov = self.driver.getPOV()
        if (pov == 325 or pov == 0 or pov == 35):
            if (self.table.getNumber("tv", 0) == 1):
                offset = self.table.getNumber("tx")
                self.drive.setRaw(offset*self.kp, -offset*self.kp)
                return True

        return False
