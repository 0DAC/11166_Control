package org.firstinspires.ftc.teamcode.bot.actions;

import com.disnodeteam.dogecommander.Command;

import org.firstinspires.ftc.teamcode.bot.components.DriveTrain;
import org.firstinspires.ftc.teamcode.bot.components.ServoLatch;

public class SetDrive implements Command {
    DriveTrain drive;
    double dir;
    double vel;

    public SetDrive(DriveTrain robot_drive, double angle, double velocity) {
        drive = robot_drive;
        dir = angle;
        vel = velocity;
    }

    @Override
    public void start() {
        drive.power_drive(dir, vel);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}

