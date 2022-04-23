using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPILib;
using WPILib.Commands;

namespace ShibeBot.OperatorInterface.Controllers
{
    class NykoAirFloEX : Joystick
    {
        private Joystick stick;

        public NykoAirFloEX(int port) : base(port)
        {
            stick = new Joystick(port);
        }

        public NykoAirFloEX(int port, int numAxisTypes, int numButtonTypes) : base(port, numAxisTypes, numButtonTypes)
        {
            stick = new Joystick(port, numAxisTypes, numButtonTypes);
        }

        public POV
    }
}
