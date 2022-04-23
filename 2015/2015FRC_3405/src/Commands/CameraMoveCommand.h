#ifndef MOVE_CAMERA_COMMAND_H
#define MOVE_CAMERA_COMMAND_H

#include "../CommandBase.h"
#include "../OI.h"
#include "WPILib.h"

class CameraMoveCommand : public Command
{
public:
	CameraMoveCommand();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif

