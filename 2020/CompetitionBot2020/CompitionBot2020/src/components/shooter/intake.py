from ..controlers.operatorControl import OperatorControl
from .intakeMotor import IntakeMotor
from ..solenoid import Solenoid


class Intake():
    def __init__(self, operator: OperatorControl):
        self.operator = operator
        self.motor1 = IntakeMotor(8, False)
        self.motor2 = IntakeMotor(9, False)
        self.solenoid = Solenoid(2, 3)
        self.deployed = False
        self.speed = .5

    def update(self):
        if (self.operator.getPickupDeploy()):
            self.deployed = not self.deployed

        self.solenoid.set(self.deployed)
        motorOut = 0
        if (self.deployed):
            motorOut += self.speed * self.operator.getPickupIn()
            motorOut -= self.speed * self.operator.getPickupOut()

        self.motor1.set(motorOut)
        self.motor2.set(motorOut)
