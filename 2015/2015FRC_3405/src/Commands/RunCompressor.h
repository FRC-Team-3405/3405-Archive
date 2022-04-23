#ifndef RUN_COMPRESSOR_H
#define RUN_COMPRESSOR_H

#include "WPILib.h"
#include "Robot.h"

class RunCompressor: public Command
{
public:
	RunCompressor();
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
