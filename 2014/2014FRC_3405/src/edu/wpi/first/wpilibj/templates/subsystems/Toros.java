/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author RobsLaptop
 */
public class Toros extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
//    private Jaguar left;
//    private Jaguar right;
    public Talon left,right;
    public Toros() {
        left = new Talon(9);
        right = new Talon(10);
	   
    }
    
    public Talon getLeftTalon()
    {
        return left;
    }
    
    public Talon getRightTalon()
    {
        return right;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}