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
        drive.setLeftPower(0);
        drive.setRightPower(0);
    }

    @Override
    public void execute() {
        if (gamepad.getRightX() > 0.05) {
            drive.setLeftPower(-gamepad.getRightX());
            drive.setRightPower(gamepad.getRightX());
        }
        else {
            drive.setLeftPower(gamepad.getLeftY());
            drive.setRightPower(gamepad.getLeftY());
        }
    }




    
}
