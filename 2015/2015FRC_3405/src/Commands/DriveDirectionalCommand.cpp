#include "DriveDirectionalCommand.h"

DriveDirectionalCommand::DriveDirectionalCommand(float _magnitudeX, float _magnitudeY, float _rotation)
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::chassis);
	magnitudeX = _magnitudeX;
	magnitudeY = _magnitudeY;
	rotation = _rotation;
}

// Called just before this Command runs the first time
void DriveDirectionalCommand::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void DriveDirectionalCommand::Execute()
{
	Robot::chassis->DriveDirectional(magnitudeX, magnitudeY, rotation);
}

// Make this return true when this Command no longer needs to run execute()
bool DriveDirectionalCommand::IsFinished()
{
	return false;
}
//
// Called once after isFinished returns true
void DriveDirectionalCommand::End()
{

}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void DriveDirectionalCommand::Interrupted()
{

}
