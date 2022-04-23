#include "AutonomousDrive.h"

AutonomousDrive::AutonomousDrive()
{
	// Use Requires() here to declare subsystem dependencies
	// eg. Requires(chassis);
	Requires(Robot::chassis);
	timer = new Timer();
}

// Called just before this Command runs the first time
void AutonomousDrive::Initialize()
{
	timer->Reset();
	timer->Start();
}

// Called repeatedly when this Command is scheduled to run
void AutonomousDrive::Execute()
{

	/*if (!step1) {
		//drive forward
		//step1 = timer has period passed
	} else if (!step2) {

	}*/

	if (timer->Get() < 1.0) {
		//Drive forward for 1 second
		Robot::chassis->DriveDirectional(0,1,0);
		std::cout << "Driving forward!\n";
	} else if (timer->Get() < 1.5) {
		//Drive to the right for .5 seconds
		Robot::chassis->DriveDirectional(1,0,0);
		std::cout << "Driving right :(\n";
	}
}

// Make this return true when this Command no longer needs to run execute()
bool AutonomousDrive::IsFinished()
{
	return false;
}
//
// Called once after isFinished returns true
void AutonomousDrive::End()
{

}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void AutonomousDrive::Interrupted()
{

}
