
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.LaunchBall;
import edu.wpi.first.wpilibj.templates.commands.RaiseCatapult;
import edu.wpi.first.wpilibj.templates.commands.RetractCatapult;
import edu.wpi.first.wpilibj.templates.commands.SpinToros;
import edu.wpi.first.wpilibj.templates.commands.StopCatapult;
import edu.wpi.first.wpilibj.templates.commands.Kickout;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public static final int JOYSTICK_PORT = 1;
    public static final int TORO_STICK_PORT = 2;
    private Joystick stick;
    private Joystick toroStick;
    
    private JoystickButton toroLeftButton;
    private JoystickButton toroRightButton;
    private JoystickButton shotButton;
    private JoystickButton retractButton;
    private JoystickButton launchBallButton;
    private JoystickButton kickoutButton;
    //private JoystickButton //
    
    private final int XboxAButton = 1;
    private final int XboxBButton = 2;
    private final int XboxXButton = 3;
    private final int XboxYButton = 4;
    private final int XboxLeftBumper = 5;
    private final int XboxRightBumper = 6;
    private final int XboxBackButton = 7;
    private final int XboxStartButton = 8;
    private final int XboxLeftJoystickButton = 9;
    private final int XboxRightJoystickButton = 10;
    
    public OI() {
        stick = new Joystick(JOYSTICK_PORT);
	   toroStick = new Joystick(TORO_STICK_PORT);
	   //b is shoot, a is retractXboxBButton
        retractButton = new JoystickButton(stick,XboxAButton);
        shotButton = new JoystickButton(stick,XboxBButton);
	   toroRightButton = new JoystickButton(toroStick,XboxRightBumper);
	   retractButton = new JoystickButton(toroStick, XboxLeftBumper);
	   kickoutButton = new JoystickButton(stick, XboxYButton);
	  // slowRaiseButton = new JoystickButton(stick, XboxBButton);
	   //toroRightButton.
        //toroRightButton.whileHeld(new SpinToros(true));
	   //toroRightButton.whenReleased(new SpinToros(false));
	   //toroInButton.whileHeld(new SpinToros(true));
        //toroOutButton.whileHeld(new SpinToros(false));
	   retractButton.whenPressed(new RetractCatapult());
	   //toroInButton.whenPressed(new RetractCatapult());
	   //toroInButton.whenReleased(new StopCatapult());

	   shotButton.whenPressed(new RaiseCatapult());
	   //toroOutButton.whenReleased(new StopCatapult());
	   kickoutButton.whenPressed(new Kickout());
	   
        launchBallButton = new JoystickButton(stick,XboxRightBumper);
        launchBallButton.whenPressed(new LaunchBall());
    }
    
    public Joystick getJoystick() {
        return stick;
    }
}

