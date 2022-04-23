from wpilib import AnalogInput


class PressureSensor():
    def __init__(self, id):
        self.input = AnalogInput(id)

    def read(self):
        return (self.input.getVoltage() - .5) * 50
