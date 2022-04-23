from wpilib.interfaces import GenericHID


class OperatorControl(GenericHID):
    def __init__(self, id):
        super().__init__(id)
        self.ButtonMap = {"IndexDown": 5, "IndexUp": 6, "LifterDown": 1,
                          "LifterUp": 4, "PickupOut": 2, "PickupIn": 5, "Deploy": 2}
        self.AxisMap = {}

    def getIndexDown(self) -> bool:
        return self.getRawButton(self.ButtonMap["IndexDown"])

    def getIndexUp(self) -> bool:
        return self.getRawButton(self.ButtonMap["IndexUp"])

    def getPickupDeploy(self) -> bool:
        return self.getRawButtonPressed(self.ButtonMap["Deploy"])

    def getPickupIn(self) -> float:
        return self.getRawAxis(self.ButtonMap["PickupIn"])

    def getPickupOut(self) -> float:
        return self.getRawAxis(self.ButtonMap["PickupOut"])

    def getLifterUp(self) -> bool:
        return self.getRawButton(self.ButtonMap["LifterUp"])

    def getLifterDown(self) -> bool:
        return self.getRawButton(self.ButtonMap["LifterDown"])

    def getArmUp(self) -> bool:
        pov = self.getPOV()
        return pov == 0 or pov == 45 or pov == 315

    def getArmDown(self) -> bool:
        pov = self.getPOV()
        return pov == 135 or pov == 180 or pov == 225
