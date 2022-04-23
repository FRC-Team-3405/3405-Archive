#ifndef MOVE_TO_LEVEL_H
#define MOVE_TO_LEVEL_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class MoveToLevel: public Command
{
private:
//	int level;
//	bool levelReached;
public:
	MoveToLevel();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
