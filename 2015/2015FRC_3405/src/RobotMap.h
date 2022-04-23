#ifndef ROBOTMAP_H
#define ROBOTMAP_H

#include "WPILib.h"
#include "XboxMap.h"

const float PI = 3.14f;

// PWM Channels for the Talon motor controllers for chassis
const int TALON_FRONTRIGHT = 0;
const int TALON_FRONTLEFT = 1;
const int TALON_BACKRIGHT = 2;
const int TALON_BACKLEFT = 3;

// PWM channels for elevator talon controllers
const int TALON_ELEVATOR_LEFT = 4;
const int TALON_ELEVATOR_RIGHT = 5;
// PWM channel for wormgear
const int VICTOR_WORMGEAR = 6;


// Digital IO channels for elevator quadrature encoders
const int ELEVATOR_ENC_1_A = 0;
const int ELEVATOR_ENC_1_B = 1;
const int ELEVATOR_ENC_2_A = 2;
const int ELEVATOR_ENC_2_B = 3;

// Digital IO channels for elevator limit switches
const int ELEVATOR_BOTTOM_SWITCH = 4;
const int ELEVATOR_TOP_SWITCH = 5;

// PWM Channels for the Camera's Servos
const int CAMERA_SERVO_TILT = 7;
const int CAMERA_SERVO_ROTATE = 8;

const float JOYSTICK_THRESHOLD = .20f;
const float TRIGGER_THRESHOLD = .001f;

const float DRIVE_POWER = .5f;

// Encoder values for elevator levels
// Bottom level is LEVELS[0], Sixth level is LEVELS[5]
const int LEVELS[] = {0, 1100, 2200, 3500, 4600, 5000};
const int LEVEL_DEADZONE = 10;
const int LEVEL_SLOWZONE = 50;

// Speed values for talons (-1 to 1) for elevator
const float ELEVATOR_UPWARD_SPEED = 0.3;
const float ELEVATOR_DOWNWARD_SPEED = 0.1;
// Speed values for talons (-1 to 1) for wormgear
const float WORMGEAR_UP_SPEED = 1;
const float WORMGEAR_DOWN_SPEED = 1;
// Power multiplier for elevator
const float ELEVATOR_POWER = .5;

// Outputs on operator launchpad for LED indicators
const int GREEN_LED = 1;
const int RED_LED = 2;
#endif
