from ctre import WPI_TalonFX, TalonFXControlMode
from ..controlers.operatorControl import OperatorControl


class Lifter():
    def __init__(self, operator: OperatorControl):
        self.operator = operator
        self.motor = WPI_TalonFX(6)
        self.speed = .5

    def update(self):
        power = 0
        if (self.operator.getLifterUp()):
            power += self.speed

        self.motor.set(TalonFXControlMode.PercentOutput, power)
