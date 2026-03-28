package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private final TalonFX leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    private double leftPower, rightPower;
    


    public Drive() {
        leftFrontMotor = new TalonFX(0, new CANBus("rio"));
        leftBackMotor = new TalonFX(1, new CANBus("rio"));
        rightFrontMotor = new TalonFX(2, new CANBus("rio"));
        rightBackMotor = new TalonFX(3, new CANBus("rio"));
        leftPower = 0.0;
        rightPower = 0.0;
    }

    public void setLeftPower(double leftPower) {
        this.leftPower = leftPower;
    }

    public void setRightPower(double rightPower) {
        this.rightPower = rightPower;
    }

    @Override 
    public void periodic() {
        leftFrontMotor.set(leftPower);
        leftBackMotor.set(leftPower);
        rightBackMotor.set(-rightPower);
        rightFrontMotor.set(-rightPower);
    }

}
