from ctre import (WPI_TalonFX,
                  FeedbackDevice,
                  TalonFXControlMode,
                  SensorCollection)


class LauncherMotor():
    def __init__(self, id, driver):
        self.motor = WPI_TalonFX(id)
        self.motor.configFactoryDefault()
        self.sensor = SensorCollection(self.motor)
        self.driver = driver
    
    def update(self):
        speed = 0
        if (self.driver.getIndexUp()):
            speed = -1
        self.motor.set(TalonFXControlMode.PercentOutput, speed)
    
    def getPostion(self) -> float:
        return self.sensor.getQuadraturePosition()

    def getVelocity(self) -> float:
        return self.sensor.getQuadratureVelocity()
    
    def getAmperage(self) -> float:
        return self.motor.getOutputCurrent()
    

