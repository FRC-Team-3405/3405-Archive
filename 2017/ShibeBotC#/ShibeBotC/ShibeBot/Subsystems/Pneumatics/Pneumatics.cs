using System.Diagnostics;
using ShibeBot.Commands;
using WPILib;
using WPILib.Commands;
using WPILib.SmartDashboard;

namespace ShibeBot.Subsystems.Pneumatics
{

    public class Pneumatics : Subsystem
    {
        public Compressor Compressor = new Compressor(RobotMap.PrimaryPcm);
        public static readonly DoubleSolenoid Shifter = new DoubleSolenoid(RobotMap.ShifterExtend, RobotMap.ShifterRetract);
        public static readonly DoubleSolenoid GearMech = new DoubleSolenoid(RobotMap.GearGrabberMechExtend, RobotMap.GearGrabberMechRetract);
        public static readonly DoubleSolenoid GearClamp = new DoubleSolenoid(RobotMap.GearClampExtend, RobotMap.GearClampRetract);


        protected override void InitDefaultCommand()
        {
            SetDefaultCommand(new GrabGearCommand());
            Compressor.Enabled();
            Compressor.ClosedLoopControl = RobotMap.ClosedLoopControl;
        }

        public void CompressorOn()
        {
            Compressor.Start();
        }

        public void CompressorOff()
        {
            Compressor.Stop();
        }

        public void StepThroughPnuematics(int step)
        {
            switch (step)
            {
                case 1:
                    StepOne();
                    break;
                case 2:
                    StepTwo();
                    break;
                case 3:
                    StepThree();
                    break;
                case 4:
                    StepFour();
                    break;
            }
        }

		public void ShiftUp()
		{
			if (Oi.ShifterGear != Gear.High) 
			{
				Shifter.Set(DoubleSolenoid.Value.Forward);
				Oi.ShifterGear = Gear.High;
			}
		}

		public void ShiftDown() 
		{
			if (Oi.ShifterGear != Gear.Low) 
			{
				Shifter.Set(DoubleSolenoid.Value.Reverse);
				Oi.ShifterGear = Gear.Low;
			}
		
		}
        private void StepOne()
        {
            GearClamp.Set(DoubleSolenoid.Value.Reverse);
        }

        private void StepTwo()
        {
			GearMech.Set(DoubleSolenoid.Value.Forward);
        }

        private void StepThree()
        {
			GearClamp.Set(DoubleSolenoid.Value.Forward);
        }

        private void StepFour()
        {
			GearMech.Set(DoubleSolenoid.Value.Reverse);
        }
    }
}