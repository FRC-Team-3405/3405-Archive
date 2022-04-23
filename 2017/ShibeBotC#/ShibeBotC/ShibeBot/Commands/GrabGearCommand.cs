using ShibeBot.Subsystems.Pneumatics;
using WPILib;
using WPILib.Commands;

namespace ShibeBot.Commands
{
    public class GrabGearCommand : Command
    {
        public GrabGearCommand()
        {
            // Use requires() here to declare subsystem dependencies
            Requires(ShibeBot.Pnuematics);
        }

        // Called just before this Command runs the first time
        protected override void Initialize()
        {

        }

        // Called repeatedly when this Command is scheduled to run
        protected override void Execute()
        {
            switch (Oi.Pilot.GetPOV(0))
            {
                case 0: 
                    ShibeBot.Pnuematics.StepThroughPnuematics(4);
                    break;
                case 90:
                    ShibeBot.Pnuematics.StepThroughPnuematics(1);
                    break;
                case 180:
                    ShibeBot.Pnuematics.StepThroughPnuematics(2);
                    break;
                case 270:
                    ShibeBot.Pnuematics.StepThroughPnuematics(3);
                    break;
            }
        }

        // Make this return true when this Command no longer needs to run execute()
        protected override bool IsFinished()
        {
            return false;
        }

        // Called once after isFinished returns true
        protected override void End()
        {
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected override void Interrupted()
        {
        }
    }
}
