using System.Media;
using ShibeBot.Commands;
using ShibeBot.OperatorInteface;
using CTRE;
using WPILib;
using WPILib.Commands;
using WPILib.SmartDashboard;
using System.Threading;
using System;

namespace ShibeBot.Subsystems.DriveTrain
{
	public class DriveTrain : Subsystem
	{

		private static readonly CANTalon LeftPrimary = new CANTalon(0);
		private static readonly CANTalon RightPrimary = new CANTalon(1);
		private static readonly CANTalon LeftSecondary = new CANTalon(2);
		private static readonly CANTalon RightSecondary = new CANTalon(3);

		public bool InvertedControls = false;

		private Drive _drive = new Drive(RightPrimary, LeftPrimary, RightSecondary, LeftSecondary);

		protected override void InitDefaultCommand()
		{
			SetDefaultCommand(new DriveCommand());
		}

		private double leftX = 0;
		private double leftY = 0;
		private double rightX = 0;
		private double rightY = 0;

		public void TankDrive(Joystick stick)
		{
			_drive.MaxOutput = 1 - stick.GetRawAxis(XboxMap.RightTrigger);
			if (InvertedControls)
			{
				_drive.TankDrive(stick.GetRawAxis(XboxMap.RightY), stick.GetRawAxis(XboxMap.LeftY));
			}
			else 
			{
				_drive.ArcadeDrive(-stick.GetRawAxis(XboxMap.RightY), -stick.GetRawAxis(XboxMap.LeftY));
			}
		}

		public void ArcadeDrive(Joystick stick)
		{
			_drive.MaxOutput = 1 - stick.GetRawAxis(XboxMap.RightTrigger);
			if (InvertedControls)
			{
				_drive.ArcadeDrive(-stick.GetRawAxis(XboxMap.LeftY), stick.GetRawAxis(XboxMap.LeftX), true);
			}
			else 
			{ 
				_drive.ArcadeDrive(stick.GetRawAxis(XboxMap.LeftY), -stick.GetRawAxis(XboxMap.LeftX));
			}
		}

		public void DriveDistance(double distance)
		{
			_drive.DriveDistance(distance);
		}


		public void DriveManually(double leftSpeed, double rightSpeed)
		{
			_drive.TankDrive(leftSpeed, rightSpeed);
		}

		private void AutoLerp(Joystick stick, double amount)
		{
			leftX = Lerp(leftX, stick.GetRawAxis(XboxMap.LeftX), amount);
			leftY = Lerp(leftY, stick.GetRawAxis(XboxMap.LeftY), amount);
			rightX = Lerp(rightX, stick.GetRawAxis(XboxMap.RightX), amount);
			rightY = Lerp(rightY, stick.GetRawAxis(XboxMap.RightY), amount);
			SmartDashboard.PutNumber("Left X", leftX);
			SmartDashboard.PutNumber("Left Y", leftY);
			SmartDashboard.PutNumber("Right X", rightX);
			SmartDashboard.PutNumber("Right Y", rightY);
		}

		private double Lerp(double first, double second, double amount)
		{
			return (1 - amount) * first + amount * second;
		}
	}
}


public class Drive
{

    private const int WheelDiameter = 4;
    private const int InchesToFeet = 12;


    private CANTalon RightPrimary;
    private CANTalon LeftPrimary;
    private CANTalon RightSecondary;
    private CANTalon LeftSecondary;

    public double MaxOutput = 1;

    public Drive(CANTalon _rightPrimary, CANTalon _leftPrimary, CANTalon _rightSecondary, CANTalon _leftSecondary)
    {
        RightPrimary = _rightPrimary;
        LeftPrimary = _leftPrimary;
        LeftSecondary = _leftSecondary;
        RightSecondary = _rightSecondary;
    }

    public void ArcadeDrive(double x, double y, bool isInverted = false)
    {
        double left;
        double right;
        if (isInverted)
        {
            left = (y - x) * MaxOutput;
            right = (y + x) * MaxOutput;
        }
        else
        {
            left = (y + x) * MaxOutput;
            right = (y - x) * MaxOutput;
        }
        if (right <= .2 && right >= -.2)
        {
            right = 0;
        }

        if (left <= .2 && left >= -.2)
        {
            left = 0;
        }

        RightPrimary.Set(right);
        RightSecondary.Set(right);

        LeftPrimary.Set(left);
        LeftSecondary.Set(left);

    }

    public void TankDrive(double y1, double y2)
    {
        RightPrimary.Set(y1 * MaxOutput);
        RightSecondary.Set(y1 * MaxOutput);

        LeftPrimary.Set(y2 * MaxOutput);
        LeftSecondary.Set(y2 * MaxOutput);
    }

    public void DriveDistance(double distance)
    {
        //double position = (distance * 12) / (4 * Math.PI);

        //RightPrimary.SetPosition(position);
        //RightSecondary.SetPosition(position);

        //LeftPrimary.SetPosition(position);
        //LeftSecondary.SetPosition(position);



        WPILib.Timer timer = new WPILib.Timer();
        timer.Reset();
        timer.Start();

        while (timer.Get() < distance)
        {
            RightPrimary.Set(.5);
            RightSecondary.Set(.5);

            LeftPrimary.Set(-.5);
            LeftSecondary.Set(-.5);
        }

		timer.Stop();


        RightPrimary.Set(0);
        RightSecondary.Set(0);

        LeftPrimary.Set(0);
        LeftSecondary.Set(0);

    }

};