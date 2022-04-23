using WPILib;
using WPILib.Buttons;
using ShibeBot.Commands;
using WPILib.Commands;

namespace ShibeBot.OperatorInteface
{
    public class JoystickStick
    {
        private Joystick master;
        private int x;
        private int y;

        public JoystickStick(Joystick stick, int stickX, int stickY)
        {
            master = stick;
            x = stickX;
            y = stickY;
        }

        public double GetX => master.GetRawAxis(x);
        public double GetY => master.GetRawAxis(y);
        private double Magnitude => 1;
    }
}
