#include "MoveToLevel.h"
#include "../RobotMap.h"

MoveToLevel::MoveToLevel()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::elevator);
}


// Called just before this Command runs the first time
void MoveToLevel::Initialize()
{
}

// Called repeatedly when this Command is scheduled to run
void MoveToLevel::Execute()
{
	SmartDashboard::PutNumber("Encoder 1",Robot::elevator->GetEncoder(Elevator::k1));
	SmartDashboard::PutNumber("Encoder 2",Robot::elevator->GetEncoder(Elevator::k2));

	//Reset our encoders if we hit the bottom limit, so we have a reference point for the higher levels
	if(Robot::elevator->BottomLimitReached())
		Robot::elevator->ResetEncoders();

	//DESTINATION_LEVEL and DESTINATION_REACHED are set in the SetDestinationLevel command, which is triggered by a button press

	//If we haven't got to our goal level, then move towards that level
	if (!Robot::elevator->DESTINATION_REACHED) {
		Robot::elevator->DESTINATION_REACHED = Robot::elevator->GoToLevel(Robot::elevator->DESTINATION_LEVEL);
		std::cout << "Attempting to reach level " << Robot::elevator->DESTINATION_LEVEL << "\n";
	} else {
		Robot::elevator->Hold();
	}

	//Light the green or red LED depending if we've reached the destination level
	if (Robot::elevator->DESTINATION_REACHED) {
		Robot::oi->GetGamepad()->SetOutput(GREEN_LED,true);
		Robot::oi->GetGamepad()->SetOutput(RED_LED,false);
	}
	else {
		Robot::oi->GetGamepad()->SetOutput(GREEN_LED,false);
		Robot::oi->GetGamepad()->SetOutput(RED_LED,true);
	}

}

// Make this return true when this Command no longer needs to run execute()
bool MoveToLevel::IsFinished()
{
	return false;
}

// Called once after isFinished returns true
void MoveToLevel::End()
{
	Robot::elevator->Hold();
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void MoveToLevel::Interrupted()
{
	Robot::elevator->Hold();
}
