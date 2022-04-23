#ifndef ELEVATOR_H
#define ELEVATOR_H

#include "Commands/Subsystem.h"
#include "WPILib.h"
#include <iostream>

#include "CommandList.h"

class Elevator: public Subsystem
{
private:
	// It's desirable that everything possible under private except
	// for methods that implement subsystem capabilities
	Encoder* enc_1;
	Encoder* enc_2;

	DigitalInput* bottomLimitSwitch;
	DigitalInput* topLimitSwitch;

	Talon* leftTalon;
	Talon* rightTalon;
	Victor* wormgear;
public:
	typedef enum
	{
		k1, k2
	}EncoderIndex;

	int DESTINATION_LEVEL;
	bool DESTINATION_REACHED;
	bool MANUALLY_CONTROLLED;

	Elevator();
	void InitDefaultCommand();
	int GetEncoder(EncoderIndex);
	void ResetEncoders();

	bool BottomLimitReached();
	bool TopLimitReached();

	bool GoToLevel(int);
	void MoveUp();
	void MoveDown();

	void MoveUpWithSpeed(float);
	void MoveDownWithSpeed(float);

	void Hold();
};

#endif
