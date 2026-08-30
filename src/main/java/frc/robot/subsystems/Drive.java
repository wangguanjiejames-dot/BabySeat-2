package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
    private final TalonFX leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    private final SlewRateLimiter leftLimiter =
            new SlewRateLimiter(DriveConstants.kPowerSlewRate);
    private final SlewRateLimiter rightLimiter =
            new SlewRateLimiter(DriveConstants.kPowerSlewRate);
    private double leftPower, rightPower;

    public Drive() {
        leftFrontMotor = new TalonFX(0, new CANBus("rio"));
        leftBackMotor = new TalonFX(1, new CANBus("rio"));
        rightFrontMotor = new TalonFX(2, new CANBus("rio"));
        rightBackMotor = new TalonFX(3, new CANBus("rio"));
        configureMotor(leftFrontMotor);
        configureMotor(leftBackMotor);
        configureMotor(rightFrontMotor);
        configureMotor(rightBackMotor);
        leftPower = 0.0;
        rightPower = 0.0;
    }

    private static void configureMotor(TalonFX motor) {
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setLeftPower(double leftPower) {
        this.leftPower = leftPower;
    }

    public void setRightPower(double rightPower) {
        this.rightPower = rightPower;
    }

    public void stop() {
        leftPower = 0.0;
        rightPower = 0.0;
        leftLimiter.reset(0.0);
        rightLimiter.reset(0.0);
    }

    @Override
    public void periodic() {
        double limitedLeft = applySlew(leftLimiter, leftPower);
        double limitedRight = applySlew(rightLimiter, rightPower);
        leftFrontMotor.set(limitedLeft);
        leftBackMotor.set(limitedLeft);
        rightBackMotor.set(-limitedRight);
        rightFrontMotor.set(-limitedRight);
    }

    private static double applySlew(SlewRateLimiter limiter, double target) {
        if (target == 0.0) {
            limiter.reset(0.0);
            return 0.0;
        }
        return limiter.calculate(target);
    }
}
