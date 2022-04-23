#include "ClampArm.h"

ClampArm::ClampArm()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::arms);
	SetTimeout(1);
}

// Called just before this Command runs the first time
void ClampArm::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void ClampArm::Execute()
{
	Robot::arms->Clamp();
}

// Make this return true when this Command no longer needs to run execute()
bool ClampArm::IsFinished()
{
	return IsTimedOut();
}

// Called once after isFinished returns true
void ClampArm::End()
{
	Robot::arms->Stop();
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void ClampArm::Interrupted()
{
	Robot::arms->Stop();
}

