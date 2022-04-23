#ifndef COMMAND_BASE_H
#define COMMAND_BASE_H

#include "Subsystems/Pneumatics.h"
#include "Subsystems/Chassis.h"
#include "Subsystems/Elevator.h"
#include "Subsystems/Arms.h"
#include "Subsystems/Camera.h"

#include <string>
#include "Commands/Command.h"
#include "OI.h"
#include "WPILib.h"

class Chassis;
class OI;
class Arms;
class Elevator;
class Pneumatics;


/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.examplesubsystem
 */
class CommandBase: public Command
{
public:
	CommandBase(char const *name);
	CommandBase();
	static void init();
	// Create a single static instance of all of your subsystems
	static Pneumatics *pneumatics;
	static OI* oi;
};

#endif
