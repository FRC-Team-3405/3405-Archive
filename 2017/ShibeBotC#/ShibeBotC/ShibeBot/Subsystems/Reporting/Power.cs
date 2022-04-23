using System;
using System.Collections.Generic;
using ShibeBot.Maps;
using WPILib;
using WPILib.Commands;
using WPILib.Interfaces;
using ShibeBot.Subsystems.Reporting.PowerDistribution;
using WPILib.SmartDashboard;

namespace ShibeBot.Subsystems.Reporting
{
    public class Power : Subsystem
    {
        public static PowerDistributionPanel Panel = new PowerDistributionPanel(RobotMap.PrimaryPdp);
        public static List<PowerChannel> Channels;

        private static Compressor Compressor;

        protected override void InitDefaultCommand()
        {
            Compressor = ShibeBot.Pnuematics.Compressor;

            Channels = new List<PowerChannel> {
                new PowerChannel(Panel, PowerMap.DriveTrainLeftPrimaryPowerPort, PowerMap.DriveTrainLeftPrimaryPowerName),
                new PowerChannel(Panel, PowerMap.DriveTrainLeftSecondaryPowerPort, PowerMap.DriveTrainLeftSecondaryPowerName),
                new PowerChannel(Panel, PowerMap.DriveTrainRightPrimaryPowerPort, PowerMap.DriveTrainRightPrimaryPowerName),
                new PowerChannel(Panel, PowerMap.DriveTrainRightSecondaryPowerPort, PowerMap.DriveTrainRightSecondaryPowerName)
            };
            Panel.ResetTotalEnergy();
        }

        public void Update()
        {
            double totalVoltage = Panel.GetVoltage();
            SmartDashboard.PutNumber("Total Voltage", totalVoltage);
            double totalCurrent = Panel.GetTotalCurrent();
            SmartDashboard.PutNumber("Total Current", totalCurrent);
            double totalPower = Panel.GetTotalPower();
            SmartDashboard.PutNumber("Total Power", totalPower);
            double totalEnergy = Panel.GetTotalEnergy();
            SmartDashboard.PutNumber("Total Energy", totalEnergy);

            double compressorCurrent = Compressor.GetCompressorCurrent();
            SmartDashboard.PutNumber("Compressor Current", compressorCurrent);

            foreach (PowerChannel channel in Channels)
            {
                int port = channel.Channel;
                string name = channel.ChannelName;
                double current = Panel.GetCurrent(port);

                string ID = $"#{port} - {name}";

                SmartDashboard.PutNumber(ID, current);
            }
        }
    }
}
