from ctre import (WPI_VictorSPX, ControlMode)


class PickupMotor:
    def __init__(self, _id: int, _invert: bool):
        self.motor = WPI_VictorSPX(_id)
        self.motor.setInverted(_invert)
        self.motor.setSensorPhase(_invert)

    def set(self, value: float):
        self.motor.set(ControlMode.PercentOutput, value)
