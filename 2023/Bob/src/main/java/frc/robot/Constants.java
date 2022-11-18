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
    /**
     * NOTE: Please use the following naming conventions when giving constant values a name:
     * - Items that are motors: "M_NAME_HERE". The "M_" indicates a motor value.
     * - Items that are part of the CAN Bus (that aren't motors): "C_NAME_HERE"
     * - Items that are connected to the RoboRIO directly: "R_NAME_HERE"
     * - Items that are connected to the Voltage Regulator Module: "V_NAME_HERE"
     * - Items that are external hardware (Raspberry Pi, LimeLight, etc.): "E_NAME_HERE"
     * Please list items in their respective categories in the order presented here (M, C, R, E).
    */
    // ROBOT //
    // - DriveTrain - //
    public static final int M_LEFT = 1; // Left Motor
    public static final int M_RIGHT = 2; // Right Motor

    // UI //
    // - Main Controller - //
    public static final int CONTROLLER = 0; // Primary Controller Port

}
