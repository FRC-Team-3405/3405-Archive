#ifndef SET_DESTINATION_LEVEL_H
#define SET_DESTINATION_LEVEL_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class SetDestinationLevel: public Command
{
private:
	int level;
	bool levelReached;
public:
	SetDestinationLevel(int);
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
