#include "Robot.h"

Pneumatics* Robot::pneumatics = NULL;
Chassis* Robot::chassis = NULL;
Arms* Robot::arms = NULL;
Elevator* Robot::elevator = NULL;
Camera* Robot::camera = NULL;

OI* Robot::oi = NULL;

void Robot::RobotInit()
{
	CommandBase::init();

	chassis = new Chassis();
	arms = new Arms();
	elevator = new Elevator();
	pneumatics = new Pneumatics();
	camera = new Camera();

	oi = new OI();
	autonomousCommand = new AutonomousCommandGroup();
	lw = LiveWindow::GetInstance();
	//Display currently running commands on the dashboard
	SmartDashboard::PutData(Scheduler::GetInstance());
	//Adds buttons to the dashboard to run particular commands
	SmartDashboard::PutData("Clamp Arms", new ClampArm());
	SmartDashboard::PutData("Open Arms", new OpenArm());
	SmartDashboard::PutData("Move Camera Home", new CameraHomeCommand());
	//SmartDashboard::PutData("Level 1", new MoveToLevel(1));
//	SmartDashboard::PutData("Level 2", new MoveToLevel(2));
//	SmartDashboard::PutData("Level 3", new MoveToLevel(3));
//	SmartDashboard::PutData("Level 4", new MoveToLevel(4));
//	SmartDashboard::PutData("Level 5", new MoveToLevel(5));
}

void Robot::DisabledPeriodic()
{
	Scheduler::GetInstance()->Run();
}

void Robot::AutonomousInit()
{
	if (autonomousCommand != NULL)
		autonomousCommand->Start();
}

void Robot::AutonomousPeriodic()
{
	Scheduler::GetInstance()->Run();
}

void Robot::TeleopInit()
{
	// This makes sure that the autonomous stops running when
	// teleop starts running. If you want the autonomous to
	// continue until interrupted by another command, remove
	// this line or comment it out.
	if (autonomousCommand != NULL)
		autonomousCommand->Cancel();

	Robot::elevator->ResetEncoders();

}

void Robot::TeleopPeriodic()
{
	Scheduler::GetInstance()->Run();
	SmartDashboard::PutData(Scheduler::GetInstance());
}

void Robot::TestPeriodic()
{
	lw->Run();
}

START_ROBOT_CLASS(Robot);

