#include "OI.h"
#include "XboxMap.h"

OI::OI()
{
	// Define Joysticks here
	xbox = new Joystick(0);
	gamepad = new Joystick(1);
	flightstick = new Joystick(2);

	// Define Xbox Buttons here
	xboxA = new JoystickButton(xbox, XBOX_A);
	xboxB = new JoystickButton(xbox, XBOX_B);
	xboxX = new JoystickButton(xbox, XBOX_X);
	xboxY = new JoystickButton(xbox, XBOX_Y);
	xboxBumperLeft = new JoystickButton(xbox, XBOX_BUMPER_LEFT);
	xboxBumperRight = new JoystickButton(xbox, XBOX_BUMPER_RIGHT);
	xboxBack = new JoystickButton(xbox,XBOX_BACK);
	xboxStart = new JoystickButton(xbox, XBOX_START);

	// Assign xbox actions here
//	xboxA->WhenPressed(new ClampArm());
//	xboxB->WhenPressed(new OpenArm());
//	xboxX->WhenPressed(new MoveToLevel());
//	xboxY->WhenPressed(new MoveToLevel());
//	xboxStart->WhenPressed(new RunCompressor());

	//Define gamepad buttons here
	gamepad1 = new JoystickButton(gamepad, 1);
	gamepad2 = new JoystickButton(gamepad, 2);
	gamepad3 = new JoystickButton(gamepad, 3);
	gamepad4 = new JoystickButton(gamepad, 4);
	gamepad5 = new JoystickButton(gamepad, 5);
	gamepad6 = new JoystickButton(gamepad, 6);
	gamepad7 = new JoystickButton(gamepad, 7);
	gamepad8 = new JoystickButton(gamepad, 8);
	gamepad9 = new JoystickButton(gamepad, 9);
	gamepad10 = new JoystickButton(gamepad, 10);
	gamepad11 = new JoystickButton(gamepad, 11);
	gamepad12 = new JoystickButton(gamepad, 12);

	//Assign gamepad actions here
	gamepad1->WhenPressed(new SetDestinationLevel(0));
	gamepad2->WhenPressed(new SetDestinationLevel(1));
	gamepad3->WhenPressed(new SetDestinationLevel(2));
	gamepad4->WhenPressed(new SetDestinationLevel(3));
	gamepad5->WhenPressed(new SetDestinationLevel(4));
	gamepad6->WhenPressed(new SetDestinationLevel(5));
	gamepad7->WhenPressed(new OpenArm());
	gamepad8->WhenPressed(new ClampArm());
	gamepad9->WhenPressed(new ReleaseDriveReverse());
	gamepad10->WhenPressed(new ReleaseGround());
	gamepad11->WhenPressed(new CameraHomeCommand());
	gamepad12->WhenPressed(new CameraHomeCommand());

	// Define flightstick buttons here
	flightstickTrigger = new JoystickButton(flightstick, 1);
	flightstick2 = new JoystickButton(flightstick, 2);
	flightstick3 = new JoystickButton(flightstick, 3);
	flightstick4 = new JoystickButton(flightstick, 4);
	flightstick5 = new JoystickButton(flightstick, 5);
	flightstick6 = new JoystickButton(flightstick, 6);
	flightstick7 = new JoystickButton(flightstick, 7);
	flightstick8 = new JoystickButton(flightstick, 8);
	flightstick9 = new JoystickButton(flightstick, 9);
	flightstick10 = new JoystickButton(flightstick, 10);
	flightstick11 = new JoystickButton(flightstick, 11);

	// Assign flightstick button actions here
	flightstickTrigger->WhileHeld(new AnalogElevatorMove());
	flightstick2->WhenPressed(new ClampArm());
	flightstick3->WhenPressed(new OpenArm());

	// Have flightstick buttons 6-11 go to levels 0-5
	flightstick6->WhenPressed(new SetDestinationLevel(0));
	flightstick7->WhenPressed(new SetDestinationLevel(1));
	flightstick8->WhenPressed(new SetDestinationLevel(2));
	flightstick9->WhenPressed(new SetDestinationLevel(3));
	flightstick10->WhenPressed(new SetDestinationLevel(4));
	flightstick11->WhenPressed(new SetDestinationLevel(5));

}

Joystick* OI::GetJoystick()
{
	return xbox;
}

Joystick* OI::GetGamepad()
{
	return gamepad;
}

Joystick* OI::GetFlightstick()
{
	return flightstick;
}
