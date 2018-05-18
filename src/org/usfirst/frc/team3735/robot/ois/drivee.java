package org.usfirst.frc.team3735.robot.ois;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class drivee
{
	
	XboxController main = new XboxController(0); 
	WPI_TalonSRX Piv;
	WPI_TalonSRX arm;
	WPI_TalonSRX armm;

	Timer t;
	
	public drivee()
	{
	Piv = new WPI_TalonSRX(8);
	 arm =  new WPI_TalonSRX(2);
	 armm =  new WPI_TalonSRX(1);
	
	
	//Pivot();
	}
	
	public void Pivot() //Cube intake
	{
		if(main.getAButton() == true)
		{
			Piv.set(.5);
			t.delay(.2);
			Piv.set(0);
			
		}
		
		
		if(main.getBButton() == true)
		{
			Piv.set(-.5);
			t.delay(.2);
			Piv.set(0);
		}
		
	}
	
	public void Arm() //Cube intake
	{
		if(main.getYButton())
		{
			arm.set(-1);
			armm.set(.8);
			
		}
		else if(main.getXButton())
		{
			arm.set(1);
			armm.set(-.8);
		}
		else
		{
		arm.set(0);
		armm.set(0);
		}
		
		
	}
	public void ArmReverse() //shoot
	{

	}

	
}