using ShibeBot.Commands;
using WPILib;
using WPILib.Commands;
using WPILib.Interfaces;

namespace ShibeBot.Subsystems.Lifter
{
    public class Lifter : Subsystem
    {
		// Change this thing later
		private static TalonSRX ClimberOne = new TalonSRX(0);
		private static TalonSRX ClimberTwo = new TalonSRX(1);
        protected override void InitDefaultCommand()
        {
            SetDefaultCommand(new ClimbCommand());
        }

		public void Climb(Joystick stick) {

			double max = 1 - stick.GetRawAxis(XboxMap.RightTrigger);
			ClimberOne.Set((max) * stick.GetRawAxis(XboxMap.LeftTrigger));
			ClimberTwo.Set((-max) * stick.GetRawAxis(XboxMap.LeftTrigger));

		}
    }
}