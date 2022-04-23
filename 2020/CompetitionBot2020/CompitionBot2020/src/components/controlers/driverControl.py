from wpilib.interfaces import GenericHID


class DriverControl(GenericHID):
    def __init__(self, id):
        super().__init__(id)
        self.ButtonMap = {"ShiftUp": 5,
                          "ShiftDown": 6,
                          "SpeedUp": 7,
                          "SpeedDown": 8,
                          "FullForward": 4,
                          "FullBackward": 1}
        self.AxisMap = {"RightY": 2,
                        "RightX": 3,
                        "LeftY": 1,
                        "LeftX": 0}
        self.rumbleTime = 0

    def update(self):
        self.rumble(self.rumbleTime > 0)
        self.rumbleTime -= 1

    def getLeftX(self) -> float:
        return self.getRawAxis(self.AxisMap["LeftX"])

    def getLeftY(self) -> float:
        return -self.getRawAxis(self.AxisMap["LeftY"])

    def getRightX(self) -> float:
        return self.getRawAxis(self.AxisMap["RightX"])

    def getRightY(self) -> float:
        return -self.getRawAxis(self.AxisMap["RightY"])

    def getShiftUp(self) -> bool:
        return self.getRawButton(self.ButtonMap["ShiftUp"])

    def getShiftDown(self) -> bool:
        return self.getRawButton(self.ButtonMap["ShiftDown"])

    def getSpeedUp(self) -> bool:
        return self.getRawButton(self.ButtonMap["SpeedUp"])

    def getSpeedDown(self) -> bool:
        return self.getRawButton(self.ButtonMap["SpeedDown"])

    def getFullForward(self) -> bool:
        return self.getRawButton(self.ButtonMap["FullForward"])
    
    def getFullBackward(self) -> bool:
        return self.getRawButton(self.ButtonMap["FullBackward"])

    def rumble(self, _bool):
        if (_bool):
            self.setRumble(self.RumbleType.kLeftRumble, 1)
            self.setRumble(self.RumbleType.kRightRumble, 1)
            print("rumble")
        else:
            self.setRumble(self.RumbleType.kLeftRumble, 0)
            self.setRumble(self.RumbleType.kLeftRumble, 0)

    def setRumbleTime(self, _float):
        self.rumbleTime = _float
