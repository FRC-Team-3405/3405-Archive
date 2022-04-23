/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author RobsLaptop
 */
public class Catapult extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Jaguar jaguar; //CHANGE TO PRIVATE!
    public Jaguar jaguar2;
    public Jaguar jaguar3;
    public Jaguar jaguar4;
    private Encoder encoder;
    private Encoder encoder2;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;
    public final double maxAngle = 90;
    public final double minAngle = 0;

    public Catapult() {
        jaguar = new Jaguar(5);
        jaguar2 = new Jaguar(6);//check for real port
	   jaguar3 = new Jaguar(7);
	   jaguar4 = new Jaguar(8);
        encoder = new Encoder(9,10);
        encoder.setDistancePerPulse(1);
        encoder.start();
        encoder2 = new Encoder(11, 12);//check for real port
        encoder2.setDistancePerPulse(1);
        encoder2.start();
        topLimitSwitch = new DigitalInput(13);
        bottomLimitSwitch = new DigitalInput(14);
    }
    
    public void setJaguarSpeed(double speed) {
        jaguar.set(-speed);
        jaguar2.set(speed);
	   jaguar3.set(speed);
	   jaguar4.set(-speed);
    }
    
    
    
    public double getEncoderAngle()
    {
        return encoder.getDistance();
    }
    public double getEncoder2Angle()
    {
        return encoder2.getDistance();
    }
    public boolean isEncoderMax()
    {
        return (getEncoderAngle() >= maxAngle) || (getEncoder2Angle() >= maxAngle);
    }
    public boolean isEncoderMin()
    {
        return (getEncoderAngle() <= minAngle) || (getEncoder2Angle() <= minAngle);
    }
    
    public boolean isTopLimitSwitchTriggered()
    {
        return topLimitSwitch.get();
    }
    
    public boolean isBottomLimitSwitchTriggered()
    {
        return bottomLimitSwitch.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}