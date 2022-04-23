using WPILib;
using WPILib.Commands;
using WPILib.Interfaces;
using WPILib.SmartDashboard;

namespace ShibeBot.Subsystems.Reporting
{
    public class Air : Subsystem
    {
        protected override void InitDefaultCommand()
        {

        }

        private AnalogInput Pressure = new AnalogInput(RobotMap.Pressure);

        public void Update()
        {
            SmartDashboard.PutNumber("Analog Pressure Voltage", Pressure.GetVoltage());
            SmartDashboard.PutNumber("~Pressure (PSI)", AnalogToUnitPSI(Pressure.GetVoltage()));
        }

        private const double SENSOR_VOLTAGE = 5.0;
        private double AnalogToUnitPSI(double voltage)
        {
            return ((250 * (voltage / SENSOR_VOLTAGE)) - 25);
        }
    }
}
