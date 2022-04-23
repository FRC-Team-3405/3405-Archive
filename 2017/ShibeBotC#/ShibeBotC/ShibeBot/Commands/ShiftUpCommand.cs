using System;
using WPILib;
using WPILib.Commands;

namespace ShibeBot.Commands
{
	public class ShiftUpCommand : Command
	{
		public ShiftUpCommand()
		{
			Requires(ShibeBot.Pnuematics);
		}

		protected override void Initialize()
		{
		}

		protected override void Execute()
		{
			ShibeBot.Pnuematics.ShiftUp();
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
