    /*
	* To change this template, choose Tools | Templates
	* and open the template in the editor.
	*/
    package edu.wpi.first.wpilibj.templates.commands;

    /**
	*
	* @author RobsLaptop
	*/
    public class RetractCatapult extends CommandBase {

	   public RetractCatapult() {
		  // Use requires() here to declare subsystem dependencies
		  // eg. requires(chassis);
		  requires(catapult);
		  setTimeout(0.1);
	   }

	   // Called just before this Command runs the first time
	   protected void initialize() {
	   }

	   // Called repeatedly when this Command is scheduled to run
	   protected void execute() {
		  catapult.setJaguarSpeed(-0.5);
		  System.out.println("Raising");
	   }

	   // Make this return true when this Command no longer needs to run execute()
	   protected boolean isFinished() {
		  //return catapult.isBottomLimitSwitchTriggered() || catapult.isEncoderMin();
		  //return true;
		  return isTimedOut();
	   }

	   // Called once after isFinished returns true
	   protected void end() {
		  catapult.setJaguarSpeed(0);
	   }

	   // Called when another command which requires one or more of the same
	   // subsystems is scheduled to run
	   protected void interrupted() {
		  catapult.setJaguarSpeed(0);
	   }
    }