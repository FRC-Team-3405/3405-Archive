#include "OpenArm.h"

OpenArm::OpenArm()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::arms);
	SetTimeout(1);
}

// Called just before this Command runs the first time
void OpenArm::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void OpenArm::Execute()
{
	Robot::arms->Open();
}

// Make this return true when this Command no longer needs to run execute()
bool OpenArm::IsFinished()
{
	return IsTimedOut();
}

// Called once after isFinished returns true
void OpenArm::End()
{
	Robot::arms->Stop();
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void OpenArm::Interrupted()
{
	Robot::arms->Stop();
}
