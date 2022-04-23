#include <Subsystems/Camera.h>
#include "../RobotMap.h"

Camera::Camera() :
		Subsystem("Camera")
{
	tilt = new Servo(CAMERA_SERVO_TILT);
	rotate = new Servo(CAMERA_SERVO_ROTATE);

	//Set a default 'home' position
	MoveHome();
}

void Camera::InitDefaultCommand()
{
	// Set the default command for a subsystem here.
	//SetDefaultCommand(new MySpecialCommand());
	SetDefaultCommand(new CameraMoveCommand());
}

// Put methods for controlling this subsystem
// here. Call these from Commands.
void Camera::MoveWithPOV(Joystick* stick)
{
	int angle = (int)stick->GetPOV();
	SmartDashboard::PutNumber("POV Angle:", angle);
	SmartDashboard::PutNumber("Tilt servo: ",tilt->Get());
	SmartDashboard::PutNumber("Rotate servo: ",rotate->Get());

	switch(angle) {
	case 0:
		tilt->Set(tilt->Get()-.01);
		break;
	case 45:
		tilt->Set(tilt->Get()-.01);
		rotate->Set(rotate->Get()+.01);
		break;
	case 90:
		rotate->Set(rotate->Get()+.01);
		break;
	case 135:
		tilt->Set(tilt->Get()+.01);
		rotate->Set(rotate->Get()+.01);
		break;
	case 180:
		tilt->Set(tilt->Get()+.01);
		break;
	case 225:
		tilt->Set(tilt->Get()+.01);
		rotate->Set(rotate->Get()-.01);
		break;
	case 270:
		rotate->Set(rotate->Get()-.01);
		break;
	case 315:
		tilt->Set(tilt->Get()-.01);
		rotate->Set(rotate->Get()-.01);
		break;
	default:
		//nothing
		break;
	}

}

void Camera::MoveHome()
{
	//Set a default 'home' position
	tilt->Set(0.85);
	rotate->Set(0.5);
}
