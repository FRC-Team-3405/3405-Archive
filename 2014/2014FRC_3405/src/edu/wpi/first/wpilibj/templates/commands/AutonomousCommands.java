/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.AutonomousDrive;

/**
 *
 * @author RobsLaptop
 */
public class AutonomousCommands extends CommandGroup {
    
    public AutonomousCommands() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        //addSequential(new TurnTowardsTarget());
        //addSequential(new LaunchBall());
	   
	   //addSequential(new InitToroPosition(),1.0);
	   addSequential(new AutonomousDrive(), 2.1);
	   //addSequential(new Pause(),1.5);
	   //addSequential(new LaunchBall());
        
	   // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}