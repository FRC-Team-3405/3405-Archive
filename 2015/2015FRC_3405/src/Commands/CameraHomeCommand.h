#ifndef HOME_CAMERA_COMMAND_H
#define HOME_CAMERA_COMMAND_H

#include "../CommandBase.h"
#include "../OI.h"
#include "WPILib.h"

class CameraHomeCommand : public Command
{
public:
	CameraHomeCommand();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif

