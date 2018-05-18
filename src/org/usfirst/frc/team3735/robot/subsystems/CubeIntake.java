package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CubeIntake extends Subsystem {
	WPI_TalonSRX left;
	WPI_TalonSRX right;
	WPI_TalonSRX pivot;

	private boolean isDown = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CubeIntake(){
		pivot = new WPI_TalonSRX(RobotMap.CubeIntake.pivotMotor);
		left = new WPI_TalonSRX(RobotMap.CubeIntake.leftMotor);
		right = new WPI_TalonSRX(RobotMap.CubeIntake.rightMotor);
		
		pivot.setNeutralMode(NeutralMode.Brake);
		pivot.setInverted(true);
		left.setInverted(true);
	}

    public void initDefaultCommand() {
    }
    
    //positive is out, negative is in
    //actually flip that
    public void setPivotSpeed(double speed){
    	pivot.set(speed);
    }

    public void setRollersSpeed(double speed){
    	left.set(speed);
    	right.set(speed);
    }
    
    public void log(){
    }

	public void debugLog() {

		
	}
}

