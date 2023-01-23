package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SelfDestruct extends CommandBase {
    Timer t;
    public SelfDestruct() {
        addRequirements(RobotContainer.m_drive);
    }

    @Override
    public void initialize() {
        t = new Timer();
        t.start();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      RobotContainer.m_drive.arcadeDrive(0, 4);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        t.stop();
        RobotContainer.m_drive.arcadeDrive(0, 0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return t.hasElapsed(1.5);
    }
}