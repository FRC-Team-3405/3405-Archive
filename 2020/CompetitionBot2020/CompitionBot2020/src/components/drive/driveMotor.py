from ctre import (WPI_TalonFX,
                  SensorCollection,
                  TalonFXControlMode,
                  NeutralMode)


class DriveMotor:
    def __init__(self, _id: int, _invert: bool):
        self.motor = WPI_TalonFX(_id)
        self.motor.setInverted(_invert)
        self.motor.setSensorPhase(_invert)
        self.motor.setNeutralMode(NeutralMode.Brake)
        self.sensor = SensorCollection(self.motor)

    def set(self, value: float):
        self.motor.set(TalonFXControlMode.PercentOutput, value)

    def getPostion(self) -> float:
        return self.sensor.getQuadraturePosition()

    def getVelocity(self) -> float:
        return self.sensor.getQuadratureVelocity()
