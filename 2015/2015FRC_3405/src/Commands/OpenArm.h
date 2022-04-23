#ifndef OPEN_ARM_H
#define OPEN_ARM_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class OpenArm: public Command
{
public:
	OpenArm();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
