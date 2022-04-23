using System;
using WPILib;
using WPILib.Commands;

namespace ShibeBot.Commands
{
    public class TankEnable : Command
    {
        public TankEnable()
        {
        }

        protected override void Initialize()
        {

        }

        protected override void Execute()
        {
            Oi.DriveStyle = DriveStyle.Tank;
            Console.WriteLine(Oi.DriveStyle);
        }

        protected override bool IsFinished()
        {
            return true;
        }

        protected override void End()
        {
        }

        protected override void Interrupted()
        {
        }
    }
}
