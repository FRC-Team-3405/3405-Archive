#include <Subsystems/Elevator.h>
#include "../RobotMap.h"



Elevator::Elevator() :
		Subsystem("Elevator")
{
	DESTINATION_LEVEL = 0;
	DESTINATION_REACHED = true;
	MANUALLY_CONTROLLED = false;

	enc_1 = new Encoder(ELEVATOR_ENC_1_A,ELEVATOR_ENC_1_B);
	enc_2 = new Encoder(ELEVATOR_ENC_2_A,ELEVATOR_ENC_2_B);

	bottomLimitSwitch = new DigitalInput(ELEVATOR_BOTTOM_SWITCH);
	topLimitSwitch = new DigitalInput(ELEVATOR_TOP_SWITCH);

	leftTalon = new Talon(TALON_ELEVATOR_LEFT);
	rightTalon = new Talon(TALON_ELEVATOR_RIGHT);
	wormgear = new Victor(VICTOR_WORMGEAR);
}

void Elevator::InitDefaultCommand()
{
	// Set the default command for a subsystem here.
	//SetDefaultCommand(new MySpecialCommand());
	//SetDefaultCommand(new HoldElevator());
	SetDefaultCommand(new MoveToLevel());
}

// Put methods for controlling this subsystem
// here. Call these from Commands.
int Elevator::GetEncoder(EncoderIndex e = k1)
{
	if(e == k1)
		return enc_1->Get();
	else if(e == k2)
		return enc_2->Get();
	else
		throw 0;
}

void Elevator::ResetEncoders()
{
	enc_1->Reset();
	enc_2->Reset();
}

bool Elevator::BottomLimitReached()
{
	SmartDashboard::PutBoolean("Bottom Limit Switch",!bottomLimitSwitch->Get());
	// Switch reads at 0 when it is pressed so reverse the bool returned
	return !bottomLimitSwitch->Get();

}

bool Elevator::TopLimitReached()
{
	SmartDashboard::PutBoolean("Top Limit Switch",!topLimitSwitch->Get());
	// Switch reads 0 when it is pressed, so reverse the bool returned
	return !topLimitSwitch->Get();
}

//Move the elevator up or down to reach a given level (based on encoder values)
bool Elevator::GoToLevel(int _level)
{

	//Special case: level 0 is the ground level, so we want to go down until we hit our bottom limit.
	//When the bottom limit is hit, the encoders are also reset so we can have a reference point for our higher levels.
	if(_level == 0)
	{
		MoveDownWithSpeed(ELEVATOR_DOWNWARD_SPEED*.7);
		return BottomLimitReached();
	}
	else
	{
		//Get our current encoder value, and our goal encoder value (specified in RobotMap)
		int encoderCurrent = enc_1->Get();
		int encoderGoal = LEVELS[_level];

		//If we're below our goal and outside of our bilateral deadzone, then move up
		if(encoderCurrent < encoderGoal && abs(encoderCurrent - encoderGoal) > LEVEL_DEADZONE)
		{
			SmartDashboard::PutString("Level", "less than");
			//Implement a zone around the goal in which the elevator will move slower, so we get closer to our goal
			if (abs(encoderCurrent - encoderGoal) < LEVEL_SLOWZONE) {
				MoveUpWithSpeed(ELEVATOR_UPWARD_SPEED*0.3);
			} else {
				MoveUpWithSpeed(ELEVATOR_UPWARD_SPEED);
			}
			//We haven't reached our level yet, so return false
			return false;
		}
		//If we're above our goal and outside of our bilateral deadzone, then move down
		else if (encoderCurrent > encoderGoal && abs(encoderCurrent - encoderGoal) > LEVEL_DEADZONE)
		{
			SmartDashboard::PutString("Level", "greater than");
			//Implement a zone around the goal in which the elevator will move slower, so we get closer to our goal
			if (abs(encoderCurrent - encoderGoal) < LEVEL_SLOWZONE) {
				MoveDownWithSpeed(ELEVATOR_DOWNWARD_SPEED*0.3);
			} else {
				MoveDownWithSpeed(ELEVATOR_DOWNWARD_SPEED);
			}
			return false;
		}
		//If we're within our deadzone, we reached the level so hold the elevator and return true
		else if(abs(encoderCurrent - encoderGoal) < LEVEL_DEADZONE)
		{
			SmartDashboard::PutString("Level", "reached");
			Hold();
			return true;
		}
		//I dunno what's going on if this happens...
		else
		{
			SmartDashboard::PutString("Level", "error");
			return true;
		}
	}
}

void Elevator::MoveUp() {
//	if (!TopLimitReached()) {
//		leftTalon->Set(-ELEVATOR_UPWARD_SPEED);
//		rightTalon->Set(ELEVATOR_UPWARD_SPEED);
//		wormgear->Set(WORMGEAR_UP_SPEED);
//	}
	leftTalon->Set(-ELEVATOR_UPWARD_SPEED);
	rightTalon->Set(ELEVATOR_UPWARD_SPEED);
	wormgear->Set(WORMGEAR_UP_SPEED);
}

void Elevator::MoveDown() {
//	if (!BottomLimitReached()) {
//		leftTalon->Set(ELEVATOR_DOWNWARD_SPEED);
//		rightTalon->Set(-ELEVATOR_DOWNWARD_SPEED);
//		wormgear->Set(-WORMGEAR_DOWN_SPEED);
//	}
	leftTalon->Set(ELEVATOR_DOWNWARD_SPEED);
	rightTalon->Set(-ELEVATOR_DOWNWARD_SPEED);
	wormgear->Set(-WORMGEAR_DOWN_SPEED);
}

void Elevator::MoveUpWithSpeed(float _speed)
{
//	if (!TopLimitReached()) {
//		leftTalon->Set(-_speed);
//		rightTalon->Set(_speed);
//		wormgear->Set(WORMGEAR_UP_SPEED);
//	}
	leftTalon->Set(-_speed);
	rightTalon->Set(_speed);
	wormgear->Set(WORMGEAR_UP_SPEED);
}

void Elevator::MoveDownWithSpeed(float _speed)
{
//	if (!BottomLimitReached()) {
//		leftTalon->Set(_speed);
//		rightTalon->Set(-_speed);
//		wormgear->Set(-_speed);
//	}
	leftTalon->Set(_speed);
	rightTalon->Set(-_speed);
	wormgear->Set(-WORMGEAR_DOWN_SPEED);
}

void Elevator::Hold()
{
	leftTalon->Set(0);
	rightTalon->Set(0);
	wormgear->Set(0);
}
