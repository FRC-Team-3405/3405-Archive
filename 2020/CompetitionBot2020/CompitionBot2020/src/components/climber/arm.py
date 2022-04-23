from ctre import WPI_TalonSRX, ControlMode
from ..controlers.operatorControl import OperatorControl


class Arm():
    def __init__(self, operator: OperatorControl):
        self.operator = operator
        self.motor = WPI_TalonSRX(10)
        self.speed = 1

    def update(self):
        power = 0
        if (self.operator.getArmUp()):
            power += self.speed
        if (self.operator.getArmDown()):
            power -= self.speed

        self.motor.set(ControlMode.PercentOutput, power)
