#ifndef ANALOG_ELEVATOR_MOVE_H
#define ANALOG_ELEVATOR_MOVE_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class AnalogElevatorMove: public Command
{
private:

public:
	AnalogElevatorMove();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
