#ifndef AUTONOMOUS_DRIVE_H
#define AUTONOMOUS_DRIVE_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class AutonomousDrive: public Command
{
private:
	Timer* timer;
public:
	AutonomousDrive();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
