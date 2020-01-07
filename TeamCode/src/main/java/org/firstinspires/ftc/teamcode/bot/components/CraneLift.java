package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.Subsystem;

import org.firstinspires.ftc.teamcode.SystemConfig;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CraneLift implements Subsystem {
    private HardwareMap hmp;

    // put nine different heights for now
    public CraneLift(HardwareMap hardwareMap) {
        this.hmp = hardwareMap;
    }

    @Override
    public void initHardware() {
    }

    @Override
    public void periodic() {;

    }
}
