package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.Subsystem;

import org.firstinspires.ftc.teamcode.HOMAR.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.SystemConfig;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class StoneGrabber implements Subsystem {
    private HardwareMap hmp;

    private MecanumDrivetrain drive;

    public StoneGrabber(HardwareMap hardwareMap) {
        this.hmp = hardwareMap;
    }

    @Override
    public void initHardware() {
    }

    @Override
    public void periodic() {

    }
}
