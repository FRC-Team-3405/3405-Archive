#include <Subsystems/Pneumatics.h>
#include "../RobotMap.h"

Pneumatics::Pneumatics() :
		Subsystem("Pneumatics")
{
	compressor = new Compressor(0);
	compressor->SetClosedLoopControl(false);
}

void Pneumatics::InitDefaultCommand()
{
	// Set the default command for a subsystem here.
	//SetDefaultCommand(new MySpecialCommand());
	SetDefaultCommand(new RunCompressor());
}

// Put methods for controlling this subsystem
// here. Call these from Commands.

void Pneumatics::StartCompressor()
{
	//compressor->Start();
}

void Pneumatics::StopCompressor()
{
	compressor->Stop();
}

void Pneumatics::SetCompressorClosedLoopControl(bool on)
{
	//compressor->SetClosedLoopControl(on);
}

bool Pneumatics::PressureSwitchState()
{
	return compressor->GetPressureSwitchValue();
}
