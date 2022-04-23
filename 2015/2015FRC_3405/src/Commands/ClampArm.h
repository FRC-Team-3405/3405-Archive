#ifndef CLAMP_ARM_H
#define CLAMP_ARM_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class ClampArm: public Command
{
public:
	ClampArm();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
