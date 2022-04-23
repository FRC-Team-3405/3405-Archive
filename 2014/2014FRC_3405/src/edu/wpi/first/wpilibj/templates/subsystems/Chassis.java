/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.templates.commands.DriveCommand;
import edu.wpi.first.wpilibj.templates.commands.PrintEncoders;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author RobsLaptop
 */
public class Chassis extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //RobotDrive drive;
    Jaguar frontLeft;
    Jaguar frontRight;
    Jaguar backLeft;
    Jaguar backRight;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private Encoder frontLeftEncoder;
    private Encoder frontRightEncoder;
    private Encoder backLeftEncoder;
    private Encoder backRightEncoder;

    public Chassis() {
        /*frontLeftEncoder = new Encoder(1, 2);
        frontRightEncoder = new Encoder(3, 4);
        backLeftEncoder = new Encoder(5, 6);
        backRightEncoder = new Encoder(7, 8);
        //drive = new RobotDrive(1, 2, 3, 4);
        //drive.setSafetyEnabled(false);
        frontLeftEncoder.setDistancePerPulse(Math.PI * 2.0 / 180.0);
        frontRightEncoder.setDistancePerPulse(Math.PI * 2.0 / 180.0);
        backLeftEncoder.setDistancePerPulse(Math.PI * 2.0 / 180.0);
        backRightEncoder.setDistancePerPulse(Math.PI * 2.0 / 180.0);
        frontLeftEncoder.start();
        frontRightEncoder.start();
        backLeftEncoder.start();
        backRightEncoder.start();*/
        frontLeft = new Jaguar(1);
        frontRight = new Jaguar(3);
        backLeft = new Jaguar(2);
        backRight = new Jaguar(4);
    }

    public void printEncoders() {
        System.out.println("Front Left: " + this.frontLeftEncoder.getDistance() + " Front Right: " + this.frontRightEncoder.getDistance() + " Back Left: " + this.backLeftEncoder.getDistance() + " Back Right: " + this.backRightEncoder.getDistance());
    }
    public void autoDrive(double speed){
	   frontRight.set(-speed);
        frontLeft.set(speed);
        backRight.set(-speed);
        backLeft.set(speed);
    }
    public void turn(double speed) {
        double direction;
        if (speed > 0) {
            direction = 1;
        } else {
            direction = -1;
        }
        //drive.drive(Math.abs(speed), direction);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveCommand());
    }

    public void driveWithJoystick(Joystick stick) {
//        drive.arcadeDrive(stick);
        double threshold = .25;
        double x = stick.getX();
        double y = stick.getY();
        double rotation = stick.getAxis(Joystick.AxisType.kThrottle);
//        drive.mecanumDrive_Cartesian(x, y, rotation, 0);
        if (x > -threshold && x < threshold) {
            x = 0;
		  System.out.println("X Received: ");
        }
        if (y > -threshold && y < threshold) {
            y = 0;
		  System.out.println("Y Received: ");
        }
        if (rotation > threshold && rotation < threshold) {
            rotation = 0;
		  System.out.println("Rot Received: ");
        }

        double FR = y + rotation + x;
        double BR = y + rotation - x;
        double FL = -1 * (y - rotation - x);
        double BL = -1 * (y - rotation + x);

        frontRight.set(FR);
        frontLeft.set(FL);
        backRight.set(BR);
        backLeft.set(BL);
//        double magnitude = Math.sqrt(x * x + y * y);
//        double direction = Math.toDegrees(MathUtils.atan2(y, x));
//        
//        System.out.println("Magnitude: " + magnitude + " Direction: " + direction + " Rotation"  + rotation);
//        
//        drive.mecanumDrive_Polar(magnitude, direction, rotation);

    }
}