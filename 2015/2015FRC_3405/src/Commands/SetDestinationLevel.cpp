#include "SetDestinationLevel.h"
#include "../RobotMap.h"

SetDestinationLevel::SetDestinationLevel(int _level)
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::elevator);
	level = _level;
	levelReached = false;
}


// Called just before this Command runs the first time
void SetDestinationLevel::Initialize()
{
}

// Called repeatedly when this Command is scheduled to run
void SetDestinationLevel::Execute()
{
	Robot::elevator->DESTINATION_LEVEL = level;
	Robot::elevator->DESTINATION_REACHED = false;

}

// Make this return true when this Command no longer needs to run execute()
bool SetDestinationLevel::IsFinished()
{
	//return levelReached;
	return true;
}

// Called once after isFinished returns true
void SetDestinationLevel::End()
{
	//Robot::elevator->Hold();
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void SetDestinationLevel::Interrupted()
{
	//Robot::elevator->Hold();
}
