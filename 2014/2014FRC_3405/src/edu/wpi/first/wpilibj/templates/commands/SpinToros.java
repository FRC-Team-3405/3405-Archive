/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author RobsLaptop
 */
public class SpinToros extends CommandBase {
    private boolean spinIn;
    public SpinToros(boolean spinIn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(toros);
	   requires(catapult); //REMOVE
        this.spinIn = spinIn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        final double toroSpeed = 0.5;
        if (spinIn == true) {
            toros.getLeftTalon().set(toroSpeed);
            toros.getRightTalon().set(-1*toroSpeed);
           
        } else {
		  toros.getLeftTalon().set(0);
            toros.getRightTalon().set(0);
            //toros.getLeftTalon().set(-1*toroSpeed);
            //toros.getRightTalon().set(toroSpeed);   
        }
//	   if (spinIn == true) {
//		  catapult.jaguar.set(-.2);
//		  catapult.jaguar2.set(0);
//	   } else {
//		  catapult.jaguar2.set(.2);
//		  catapult.jaguar.set(0);
//	   }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//	   catapult.jaguar.set(0);
//	   catapult.jaguar2.set(0);
	   toros.left.set(0);
	   toros.right.set(0);
    }
}