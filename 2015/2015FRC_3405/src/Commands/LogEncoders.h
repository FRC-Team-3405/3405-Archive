#ifndef LOG_ENCODERS_H
#define LOG_ENCODERS_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class LogEncoders: public Command
{
public:
	LogEncoders();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
