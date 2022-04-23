using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPILib;

namespace ShibeBot.Subsystems.Reporting.PowerDistribution
{
    public class PowerChannel
    {
        private PowerDistributionPanel Panel;
        private int Port;
        private string Name;

        public PowerChannel(PowerDistributionPanel panel, int port, string name)
        {
            Panel = panel;
            Port = port;
            Name = name;
        }

        public double GetCurrent()
        {
            return Panel.GetCurrent(Port);
        }

        public int Channel => Port;
        public string ChannelName => Name;
    }
}
