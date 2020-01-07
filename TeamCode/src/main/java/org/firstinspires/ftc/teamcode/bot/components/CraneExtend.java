package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.Subsystem;

import org.firstinspires.ftc.teamcode.HOMAR.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.SystemConfig;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CraneExtend implements Subsystem {
    private HardwareMap hmp;

    public CraneExtend(HardwareMap hardwareMap) {
        this.hmp = hardwareMap;
    }

    @Override
    public void initHardware() {
    }

    @Override
    public void periodic() {

    }
}
