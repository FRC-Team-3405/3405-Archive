from .driveMotor import DriveMotor
from statistics import mean
from typing import List


class DriveMotorGroup():
    def __init__(self, _motors: List[DriveMotor]):
        self.motors = _motors

    def set(self, value: float):
        for i in self.motors:
            i.set(value)

    def getPosition(self) -> float:
        values = []
        for i in self.motors:
            values.append(i.getPosition())
        return mean(values)

    def getVelocity(self) -> float:
        values = []
        for i in self.motors:
            values.append(i.getVelocity())
        return mean(values)
