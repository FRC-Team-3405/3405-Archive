#ifndef DRIVE_DIRECTIONAL_H
#define DRIVE_DIRECTIONAL_H

#include "../CommandBase.h"
#include "WPILib.h"
#include "Robot.h"

class DriveDirectionalCommand: public Command
{
private:
	float magnitudeX;
	float magnitudeY;
	float rotation;
public:
	DriveDirectionalCommand(float, float, float);
	void Initialize();
	void Execute();
	bool IsFinished();
	void End();
	void Interrupted();
};

#endif
