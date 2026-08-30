package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystems.Drive;

public class DriveCommand extends Command {
    private final Drive drive;
    private final CommandPS5Controller gamepad;

    public DriveCommand(Drive drive, CommandPS5Controller gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.stop();
    }

    @Override
    public void execute() {
        double throttle = applyDeadband(gamepad.getLeftY());
        double turn = applyDeadband(gamepad.getRightX());

        drive.setLeftPower(-throttle + turn);
        drive.setRightPower(-throttle - turn);
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }

    private static double applyDeadband(double value) {
        return Math.abs(value) < 0.1 ? 0.0 : value;
    }
}
