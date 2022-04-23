#include "HoldElevator.h"

HoldElevator::HoldElevator()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::elevator);
}

// Called just before this Command runs the first time
void HoldElevator::Initialize()
{

}

// Called repeatedly when this Command is scheduled to run
void HoldElevator::Execute()
{
	std::cout << "Running HoldElevator\n";
	Robot::elevator->Hold();
	SmartDashboard::PutNumber("Encoder 1",Robot::elevator->GetEncoder(Elevator::k1));
	SmartDashboard::PutNumber("Encoder 2",Robot::elevator->GetEncoder(Elevator::k2));
}

// Make this return true when this Command no longer needs to run execute()
bool HoldElevator::IsFinished()
{
	return false;
}

// Called once after isFinished returns true
void HoldElevator::End()
{

}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void HoldElevator::Interrupted()
{

}
