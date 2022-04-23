#ifndef OI_H
#define OI_H

#include "WPILib.h"

#include "CommandList.h"

class OI
{
private:
	Joystick* xbox;
	Joystick* gamepad;
	Joystick* flightstick;

	// Xbox buttons
	JoystickButton* xboxA;
	JoystickButton* xboxB;
	JoystickButton* xboxX;
	JoystickButton* xboxY;
	JoystickButton* xboxBumperLeft;
	JoystickButton* xboxBumperRight;
	JoystickButton* xboxBack;
	JoystickButton* xboxStart;
	// Operator pad buttons
	JoystickButton* gamepad1;
	JoystickButton* gamepad2;
	JoystickButton* gamepad3;
	JoystickButton* gamepad4;
	JoystickButton* gamepad5;
	JoystickButton* gamepad6;
	JoystickButton* gamepad7;
	JoystickButton* gamepad8;
	JoystickButton* gamepad9;
	JoystickButton* gamepad10;
	JoystickButton* gamepad11;
	JoystickButton* gamepad12;
	// Flightstick buttons
	JoystickButton* flightstickTrigger;
	JoystickButton* flightstick2;
	JoystickButton* flightstick3;
	JoystickButton* flightstick4;
	JoystickButton* flightstick5;
	JoystickButton* flightstick6;
	JoystickButton* flightstick7;
	JoystickButton* flightstick8;
	JoystickButton* flightstick9;
	JoystickButton* flightstick10;
	JoystickButton* flightstick11;


public:
	OI();
	Joystick* GetJoystick();
	Joystick* GetGamepad();
	Joystick* GetFlightstick();
};

#endif
