#include <Subsystems/Chassis.h>
#include "../RobotMap.h"

Chassis::Chassis() :
		Subsystem("Chassis")
{
	frontLeft = new Talon(TALON_FRONTLEFT);
	backLeft = new Talon(TALON_BACKLEFT);
	frontRight = new Talon(TALON_FRONTRIGHT);
	backRight = new Talon(TALON_BACKRIGHT);


	//drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
}

void Chassis::InitDefaultCommand()
{
	// Set the default command for a subsystem here.
	//SetDefaultCommand(new MySpecialCommand());
	std::cout << "Setting default command";
	SetDefaultCommand(new DriveCommand());
}

void Chassis::DriveWithJoystick(Joystick* stick)
{
	// Initialize the power to constant defined in RobotMap.h
	float power = DRIVE_POWER;

	float trigger = stick->GetRawAxis(XBOX_TRIGGERS);

	float x = stick->GetX(GenericHID::kLeftHand);
	float y = stick->GetY(GenericHID::kLeftHand);
	float rotation = stick->GetRawAxis(XBOX_RIGHT_XAXIS);

	SmartDashboard::PutNumber("X",x);
	SmartDashboard::PutNumber("Y",y);
	SmartDashboard::PutNumber("R",rotation);

	if(fabs(x) < JOYSTICK_THRESHOLD)
		x = 0.0;
	if(fabs(y) < JOYSTICK_THRESHOLD)
		y = 0.0;
	if(fabs(rotation) < JOYSTICK_THRESHOLD)
		rotation = 0.0;

	if(trigger < -TRIGGER_THRESHOLD)
		power *= (1 + (trigger * -1));

	DriveDirectional(x, y, rotation, power);
}

void Chassis::DriveDirectional(float magnitudeX, float magnitudeY, float rotation)
{
	DriveDirectional(magnitudeX, magnitudeY, rotation, DRIVE_POWER);
}

void Chassis::DriveDirectional(float magnitudeX, float magnitudeY, float rotation, float power)
{
	float FR = (magnitudeY + rotation + magnitudeX) * power;
	float BR = (magnitudeY + rotation - magnitudeX) * power;
	float FL = (-1.0 * (magnitudeY - rotation - magnitudeX)) * power;
	float BL = (-1.0 * (magnitudeY - rotation + magnitudeX)) * power;

	Robot::chassis->frontRight->Set(FR);
	Robot::chassis->frontLeft->Set(FL);
	Robot::chassis->backRight->Set(BR);
	Robot::chassis->backLeft->Set(BL);
}
// Put methods for controlling this subsystem
// here. Call these from Commands.

