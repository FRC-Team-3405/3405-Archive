// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //** DriveTrain */
    // Left Motors
    public static final int FL_TALONSRX = 1;
    public static final int BL_TALONSRX = 2;
    // Right Motors
    public static final int FR_TALONSRX = 3;
    public static final int BR_TALONSRX = 4;

    //** Pneumatics */
    // DoubleSolenoid
    public static final int HIGHGEAR = 4;
    public static final int LOWGEAR = 5;

    //** OI */
    // Driver Controller
    public static final int SHIFT_BUTTON = 6; // Right Bumper

    //** LimeLight Camera */
    public static final double KNOWN_DISTANCE = 3.048; // 10 Feet (120 inches in meters)
    public static final int PIXEL_WIDTH_KNOWN = 60;
    public static final double KNOWN_TAPE_BOUND_WIDTH = 0.3937; // Width of the tape (15.5 inches converted to meters)
    public static final double FOCAL_LENGTH = (KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH; //** This is an equation of some kind pulled from this repo: https://github.com/Yeti-Robotics/Y3T1-java-2021/blob/master/src/main/java/frc/robot/RobotContainer.java */
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;
    public static final double FOV_SUB_H = 59.6;
    public static final double FOV_SUB_V = 45.7;

}
