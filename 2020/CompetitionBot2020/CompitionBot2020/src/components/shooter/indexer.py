from wpilib import DigitalInput, Encoder
from networktables import NetworkTables
from ctre import WPI_VictorSPX, ControlMode
from ..controlers.operatorControl import OperatorControl


class Indexer():
    def __init__(self, operator: OperatorControl):
        self.operator = operator
        self.motor = WPI_VictorSPX(7)
        self.encoder = Encoder(0, 1, False, Encoder.EncodingType.k4X)
        self.encoder.setIndexSource(2)
        self.limit = DigitalInput(4)
        self.dashboard = NetworkTables.getTable("SmartDashboard")

        self.totalValues = 2048  # * self.encoder.getEncodingScale() - 1
        self.targetValue = 10
        self.realValue = 0
        self.allowedError = 10
        self.hole = 4
        self.holeOffset = 36 - 15
        self.maxspeed = .25
        self.minspeed = .1
        self.speedmult = 1/300
        self.speed = .1

    def update(self):
        self.realValue = self.encoder.get()

        offset = (self.targetValue - self.realValue) % self.totalValues - (self.totalValues / 2)

        self.speed = clamp(abs(offset) * self.speedmult, self.minspeed, self.maxspeed)
        self.dashboard.putString("Indexer", "{} offset".format(offset))

        if (offset > self.allowedError):
            self.motor.set(ControlMode.PercentOutput, -self.speed)
        elif (offset < -self.allowedError):
            self.motor.set(ControlMode.PercentOutput, self.speed)
        else:
            self.motor.set(ControlMode.PercentOutput, 0)

        if (abs(offset) < 15):
            if (self.operator.getIndexUp()):
                self.hole = (self.hole + 1) % 5
            elif (self.operator.getIndexDown()):
                self.hole = (self.hole + 4) % 5
            self.setRotation(self.hole * 72 + self.holeOffset)

    def getRotation(self) -> float:
        return self.realValue / self.totalValues * 360

    def setRotation(self, degrees):
        self.targetValue = clamp(degrees/360*self.totalValues,
                                 0, self.totalValues)


def clamp(_value, _min, _max) -> float:
    return min(max(_value, _min), _max)
