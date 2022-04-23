using WPILib;

namespace ShibeBot
{
    public class RobotMap
    {
        //Encoders
        public static int DriveTrainLeftQuadratureA = 0;
        public static int DriveTrainLeftQuadratureB = 1;
        public static int ThrowerLeftQuadratureA = 3;
        public static int ThrowerLeftQuadratureB = 4;
        public static int ThrowerRightQuadratureA = 5;
        public static int ThrowerRightQuadratureB = 6;

        //Motors
        public static int DriveTrainLeftPrimary = 0;
        public static int DriveTrainLeftSecondary = 1;
        public static int DriveTrainRightPrimary = 2;
        public static int DriveTrainRightSecondary = 3;
        public static int TowerCollector = 0;
        public static int HopperMotor = 1;
        public static int ThrowerLoader = 2;
        public static int ThrowerLeft = 3;
        public static int ThrowerRight = 4;
        public static int LifterMotor = 5;

        //Drive Systems

        //Pneumatics
        public const int PrimaryPcm = 0;
        public const bool ClosedLoopControl = true;
        public const int ShifterExtend = 0;
        public const int ShifterRetract = 1;
        public const int GearDoorsExtend = 2;
        public const int GearDoorsRetract = 3;
        public const int GearGrabberMechExtend = 4;
        public const int GearGrabberMechRetract = 5;
        public const int GearClampExtend = 6;
        public const int GearClampRetract = 7;

        //Reporting
        public const int PrimaryPdp = 0;

        //Analog
        public const int ThrowerAngle = 0;
        public const int Pressure = 1;

        //Environment
        public static SPI.Port Gyro = SPI.Port.OnboardCS0;
    }
}
