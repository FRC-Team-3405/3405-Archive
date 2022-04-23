#ifndef HOLD_ELEVATOR_H
#define HOLD_ELEVATOR_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class HoldElevator: public Command
{
public:
	HoldElevator();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
