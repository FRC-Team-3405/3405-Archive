#include "CameraHomeCommand.h"

CameraHomeCommand::CameraHomeCommand()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::camera);
}

// Called just before this Command runs the first time
void CameraHomeCommand::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void CameraHomeCommand::Execute()
{
	Robot::camera->MoveHome();
}

// Make this return true when this Command no longer needs to run execute()
bool CameraHomeCommand::IsFinished()
{
	return true;
}

// Called once after isFinished returns true
void CameraHomeCommand::End()
{

}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void CameraHomeCommand::Interrupted()
{

}
