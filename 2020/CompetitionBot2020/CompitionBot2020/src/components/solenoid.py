from wpilib import DoubleSolenoid


class Solenoid():
    def __init__(self, _low: int, _high: int):
        self.ds = DoubleSolenoid(_low, _high)

    def set(self, _bool):
        self.ds.set(self.ds.Value.kForward if _bool else self.ds.Value.kReverse)
