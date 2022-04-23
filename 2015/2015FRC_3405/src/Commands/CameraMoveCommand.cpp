#include "CameraMoveCommand.h"

CameraMoveCommand::CameraMoveCommand()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::camera);
}

// Called just before this Command runs the first time
void CameraMoveCommand::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void CameraMoveCommand::Execute()
{
	Robot::camera->MoveWithPOV(Robot::oi->GetJoystick());
}

// Make this return true when this Command no longer needs to run execute()
bool CameraMoveCommand::IsFinished()
{
	return false;
}

// Called once after isFinished returns true
void CameraMoveCommand::End()
{

}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void CameraMoveCommand::Interrupted()
{

}
