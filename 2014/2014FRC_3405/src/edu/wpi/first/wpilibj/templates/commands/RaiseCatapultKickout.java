/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author RobsLaptop
 */
public class RaiseCatapultKickout extends CommandBase {
    
    double kickSeconds = 1.3;
    double kickPower = .25;
    
    public RaiseCatapultKickout() {
	   // Use requires() here to declare subsystem dependencies
	   // eg. requires(chassis);
	   requires(catapult);
	   setTimeout(kickSeconds);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	   catapult.setJaguarSpeed(kickPower);
	   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	   return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
	   catapult.setJaguarSpeed(0); //stop the catapult arm
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	   catapult.setJaguarSpeed(0); //stop catapult
    }
}