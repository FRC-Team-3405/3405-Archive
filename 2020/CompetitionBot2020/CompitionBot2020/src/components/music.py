from ctre import Orchestra


class Music(Orchestra):
    def __init__(self, motors: list, name: str):
        super().__init__()
        for i in motors:
            self.addInstrument(i.motor)
        self.loadMusic("/home/lvuser/py/music/"+name+".chrp")
    
    def start(self):
        if self.isPlaying():
            self.stop()
        self.play()
