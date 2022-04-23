using System;
using WPILib;
using WPILib.Commands;

namespace ShibeBot.Commands
{
	public class ShiftDownCommand : Command
	{
		public ShiftDownCommand()
		{
			Requires(ShibeBot.Pnuematics);
		}

		protected override void Initialize()
		{
		}

		protected override void Execute()
		{
			ShibeBot.Pnuematics.ShiftDown();
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
