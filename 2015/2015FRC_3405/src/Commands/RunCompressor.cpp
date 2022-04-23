#include "RunCompressor.h"

RunCompressor::RunCompressor()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::pneumatics);
}

// Called just before this Command runs the first time
void RunCompressor::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void RunCompressor::Execute()
{
	//Robot::pneumatics->StartCompressor();
	//SmartDashboard::PutBoolean("Pressure Switch",Robot::pneumatics->PressureSwitchState());
}

// Make this return true when this Command no longer needs to run execute()
bool RunCompressor::IsFinished()
{
	return false;
}

// Called once after isFinished returns true
void RunCompressor::End()
{
	Robot::pneumatics->StopCompressor();
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void RunCompressor::Interrupted()
{
	Robot::pneumatics->StopCompressor();
}
