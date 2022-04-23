using WPILib;
using WPILib.Buttons;
using ShibeBot.Commands;
using ShibeBot.OperatorInteface;

namespace ShibeBot
{
    enum Controller
    {
        Pilot,
        CoPilot
    };

    public enum DriveStyle
    {
        Arcade,
        Tank
    }

    public enum Gear
    {
        Low,
        High
    }

    public enum ToggleField
    {
        DriveStyle
    }

    public class Oi
    {

		public static Joystick Pilot = new Joystick(HidMap.PilotXbox);

        //Pilot Systems
        public static JoystickButton ArcadeToggle = new JoystickButton(Pilot, XboxMap.AButton);
        public static JoystickButton TankToggle = new JoystickButton(Pilot, XboxMap.XButton);
		public static JoystickButton HigherGear = new JoystickButton(Pilot, XboxMap.RightBumper);
        public static JoystickButton LowerGear = new JoystickButton(Pilot, XboxMap.LeftBumper);
        public static JoystickStick LeftStick = new JoystickStick(Pilot, XboxMap.LeftX, XboxMap.LeftY);
        public static JoystickStick RightStick = new JoystickStick(Pilot, XboxMap.RightX, XboxMap.RightY);

        //Pilot Variables
        public static DriveStyle DriveStyle = DriveStyle.Arcade;
        public static Gear ShifterGear = Gear.Low;


		public Oi()
		{
			//Pilot togglables
			ArcadeToggle.WhenPressed(new ArcadeEnable());
			TankToggle.WhenPressed(new TankEnable());

			HigherGear.WhenPressed(new ShiftUpCommand());
			LowerGear.WhenPressed(new ShiftDownCommand());

		}
	}
}
