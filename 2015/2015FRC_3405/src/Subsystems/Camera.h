#ifndef CAMERA_H
#define CAMERA_H

#include "Commands/Subsystem.h"
#include "WPILib.h"
#include "Commands/CameraMoveCommand.h"

class Camera: public Subsystem
{
private:
	// It's desirable that everything possible under private except
	// for methods that implement subsystem capabilities
	Servo* tilt;
	Servo* rotate;
public:
	Camera();
	void InitDefaultCommand();
	void MoveWithPOV(Joystick* stick);
	void MoveHome();
};

#endif
