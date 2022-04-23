from .pickupMotor import PickupMotor
from .driver import driver
from typing import List


class PickupMotors():
    def __init__(self, _motors: List[PickupMotor],
                 _driver: driver, _maxOutput: int):

        self.motors = _motors
        self.driver = _driver
        self.maxOutput = _maxOutput

    def update(self):
        if self.driver.getPickupForward():
            for i in self.motors:
                i.set(self.maxOutput)
        elif self.driver.getPickupBackward():
            for i in self.motors:
                i.set(-self.maxOutput)
        else:
            for i in self.motors:
                i.set(0)
            
