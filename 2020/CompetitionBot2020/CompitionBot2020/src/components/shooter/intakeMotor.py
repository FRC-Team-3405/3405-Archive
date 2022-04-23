from ctre import (WPI_VictorSPX, ControlMode)


class IntakeMotor:
    def __init__(self, _id: int, _invert: bool):
        self.motor = WPI_VictorSPX(_id)
        self.motor.setInverted(_invert)

    def set(self, value: float):
        self.motor.set(ControlMode.PercentOutput, value)