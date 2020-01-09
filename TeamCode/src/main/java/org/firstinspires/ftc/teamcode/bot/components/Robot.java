package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SystemConfig;

public class Robot {
    public SystemConfig sys;

    public Intake intake;
    public ServoLatch latch;
    public DriveTrain drive;

    public Robot(DogeOpMode opmode, HardwareMap hmp, Telemetry t) {
        sys = new SystemConfig();

        //intake = new Intake(hmp);
        //latch = new ServoLatch(hmp);
        drive = new DriveTrain(hmp);
        drive.drive.setTelemetry(t);
    }

    public void xbox_control(double x, double y, double turn) { drive.xbox_drive(x, y, turn); }
    // TODO: implement intake and latch functionality

}
