#include <Subsystems/Arms.h>
#include "../RobotMap.h"

Arms::Arms() :
		Subsystem("Arms")
{
	armSolenoid = new DoubleSolenoid(0,1);
	armSolenoid->Set(armSolenoid->kOff);
}

void Arms::InitDefaultCommand()
{
	// Set the default command for a subsystem here.
	//SetDefaultCommand(new MySpecialCommand());
}

// Put methods for controlling this subsystem
// here. Call these from Commands.
void Arms::Clamp()
{
	armSolenoid->Set(armSolenoid->kForward);
}

void Arms::Open()
{
	armSolenoid->Set(armSolenoid->kReverse);
}

void Arms::Stop()
{
	armSolenoid->Set(armSolenoid->kOff);
}
