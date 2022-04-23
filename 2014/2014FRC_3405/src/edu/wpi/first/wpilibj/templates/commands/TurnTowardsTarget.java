/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.camera.AxisCamera.ResolutionT;
import edu.wpi.first.wpilibj.templates.subsystems.Camera.TargetReport;

/**
 *
 * @author RobsLaptop
 */
public class TurnTowardsTarget extends CommandBase {
    
    private boolean isFinished;
    public TurnTowardsTarget() {
        // Use requires() here to declare subsystem dependencies
        requires(chassis);
        //requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        /*final double boundPercent = .1;
        final double speed = 1;
        //Figure where targets are
        TargetReport target = camera.locateTarget();
        ResolutionT cameraResolution = camera.getResolution();
        //Turn until target is centered  
        double leftBound = .5*cameraResolution.width-boundPercent*cameraResolution.width;
        double rightBound = .5*cameraResolution.width+boundPercent*cameraResolution.width;
        int targetX = target.horizontalIndex;
        if (targetX > leftBound && targetX < rightBound) {
            //Middle section
            chassis.turn(0);
            isFinished = true;
        } else if (targetX > rightBound) {
            //Right section
            chassis.turn(speed);
        } else {
            //Left section
            chassis.turn(-1*speed);
        }*/
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}