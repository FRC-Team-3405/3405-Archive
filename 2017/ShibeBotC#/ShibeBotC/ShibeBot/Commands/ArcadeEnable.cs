using System;
using WPILib;
using WPILib.Commands;

namespace ShibeBot.Commands
{
    public class ArcadeEnable : Command
    {
        public ArcadeEnable()
        {
        }

        protected override void Initialize()
        {

        }

        protected override void Execute()
        {
            Oi.DriveStyle = DriveStyle.Arcade;
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
