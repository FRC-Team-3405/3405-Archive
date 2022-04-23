#ifndef CHASSIS_H
#define CHASSIS_H

#include <math.h>

#include "Commands/Subsystem.h"
#include "Commands/DriveCommand.h"
#include "WPILib.h"

class Chassis: public Subsystem
{
private:
	// It's desirable that everything possible under private except
	// for methods that implement subsystem capabilities

public:
	Talon* frontLeft;
	Talon* frontRight;
	Talon* backLeft;
	Talon* backRight;

	//RobotDrive* drive;

	Chassis();
	void DriveWithJoystick(Joystick* stick);
	void DriveDirectional(float magnitudeX, float magnitudeY, float rotation, float power);
	void DriveDirectional(float magnitudeX, float magnitudeY, float rotation);

	void InitDefaultCommand();
};

#endif
