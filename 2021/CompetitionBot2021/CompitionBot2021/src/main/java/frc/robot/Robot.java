/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {

  //  private static final String HSB = "HB";
  //  private static final String HSF = "HF";
  //  private static final String FSB = "FB";
  //  private static final String no_speed = "NO";
  //  private static final String FSF = "FF";
  private final SendableChooser<String> m_speed = new SendableChooser<>();

  private final WPI_TalonFX m_leftMotor1 = new WPI_TalonFX(1);
  private final WPI_TalonFX m_throwing = new WPI_TalonFX(7);
  private final WPI_TalonFX m_rightMotor1 = new WPI_TalonFX(3);
  private final WPI_TalonFX m_leftMotor2 = new WPI_TalonFX(2);
  private final WPI_TalonFX m_rightMotor2 = new WPI_TalonFX(4);
  private final WPI_VictorSPX m_intakeMotor1 = new WPI_VictorSPX(8);
  private final WPI_VictorSPX m_intakeMotor2 = new WPI_VictorSPX(9);
  private final WPI_VictorSPX m_Index = new WPI_VictorSPX(7);
  private final DifferentialDrive m_intake = new DifferentialDrive(m_intakeMotor1, m_intakeMotor2);
  private final SpeedControllerGroup shoot_ball = new SpeedControllerGroup(m_throwing);
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftMotor1, m_leftMotor2);
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightMotor1, m_rightMotor2);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  private final XboxController m_Controller = new XboxController(0);
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  private double m_LimelightIntakeCommand = 0.0;
  private double m_LimelightIntakeSteer = 0.0;
  //  private final int startPos = m_rightMotor1.getSelectedSensorPosition();

  @Override
  public void robotInit() {
        m_speed.addOption("FSF", "FF");
        m_speed.addOption("HSF", "HF");
        m_speed.setDefaultOption("no speed", "NO");
        m_speed.addOption("HSB", "HB");
        m_speed.addOption("FSB", "FB");
        SmartDashboard.putData("Speed", m_speed);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
      // m_rightMotor1.setSensorPhase(false);
      // m_rightMotor1.configClearPositionOnLimitF(true, 2048);
      // m_rightMotor1.configClearPositionOnLimitR(true, 2048);
      // m_rightMotor1.configClearPositionOnQuadIdx(true, 2048);
      // startPos = m_rightMotor1.getSelectedSensorPosition();
  }

  @Override
  public void autonomousPeriodic() {
    // if(m_speed.getSelected() == no_speed){
    //   m_robotDrive.arcadeDrive(0.0, 0.0);
    // }
    // if(m_speed.getSelected() == FSF){
    //  22222 m_robotDrive.arcadeDrive(1.0, 0.0);
    // }
    // if(m_speed.getSelected() == HSF){
    //   m_robotDrive.arcadeDrive(0.5, 0.0);
    // }
    // if(m_speed.getSelected() == FSB){
    //   m_robotDrive.arcadeDrive(-1.0, 0.0);
    // }
    // if(m_speed.getSelected() == HSB){
    //   m_robotDrive.arcadeDrive(-0.5, 0.0);
    // }

  
    // if (m_rightMotor1.getSelectedSensorPosition() < 10000 + startPos){
    //   m_robotDrive.arcadeDrive(-.5,0);
    // } if(m_rightMotor1.getSelectedSensorPosition() > 10000 + startPos) {
    //   m_robotDrive.arcadeDrive(0,.5);
    // }
  }

  

  @Override
  public void teleopInit(){
    Set_pipeline(0);
  }
  
  @Override
  public void teleopPeriodic() {
    Update_Limelight_Tracking();

    double steer = m_Controller.getX(Hand.kRight);
    double drive = -m_Controller.getY(Hand.kLeft);
    boolean auto = m_Controller.getAButton();
    boolean intake = m_Controller.getBButton();
    boolean index = m_Controller.getXButton();
    boolean throwing = m_Controller.getYButton();

    steer *= 0.70;
    drive *= 0.70;

    if(throwing){
      shoot_ball.set(1.0);
    } else{
      shoot_ball.set(0.0);
    }

    if (intake){
      m_intake.arcadeDrive(1.0, 1);
    }

    if (auto)
    {
      m_robotDrive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
      m_intake.arcadeDrive(m_LimelightIntakeCommand, m_LimelightIntakeSteer);
    }
    
    else
    {
      m_robotDrive.arcadeDrive(drive,steer);
    }

    if (index){
      m_Index.set(0.5);
    } else{
      m_Index.set(0.0);
    }
  }

  public void Update_Limelight_Tracking()
  {
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.03;                    // how hard to turn toward the target
        final double DRIVE_K = 0.20;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 10.0;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast


        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        double pipe = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);

        if (pipe == 0){
          m_LimelightIntakeCommand = 0.0;
          m_LimelightIntakeSteer = 0.0;

          if (ta < 0.1)
          {
            m_LimelightDriveCommand = 0.2;
            m_LimelightIntakeSteer = 0.5;
            return;
          }

          if (ty < -16 && ta < 1){
            Set_pipeline(1);
            return;
          }

        // Start with proportional steering
          m_LimelightSteerCommand = tx * STEER_K;

        // try to drive forward until the target area reaches our desired area
          double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

        // don't let the robot drive too fast into the goal
          if (drive_cmd > MAX_DRIVE)
          {
            drive_cmd = MAX_DRIVE;
          }
          m_LimelightDriveCommand = drive_cmd;

          if (drive_cmd == 0){
            m_LimelightSteerCommand = 0.2;
            m_LimelightDriveCommand = 0.2;
          }

        }

        if (pipe == 1){
          if (ta < 0.1) {
            m_LimelightDriveCommand = -0.2;
            m_LimelightSteerCommand = 0.0;
            m_LimelightIntakeCommand = 0.0;
            m_LimelightIntakeSteer = 0.0;
            return;
          }

          if (ty < -18 && ta > 1){
            Set_pipeline(0);
            m_LimelightDriveCommand = 0.0;
            m_LimelightSteerCommand = 0.0;
            m_LimelightIntakeCommand = 0.0;
            m_LimelightIntakeSteer = 0.0;
            return;
          }

          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          m_LimelightIntakeCommand = 1.0;
          m_LimelightIntakeSteer = -0.5;
        }
  }

  void Set_pipeline(int pipeline){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
  }
}