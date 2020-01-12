package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class CraneLift {
    private DcMotor left, right;
    private final double SPEED = 0.4;

    // put nine different heights for now
    public CraneLift(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotor.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotor.class, SystemConfig.right_lift);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void extend() {
        left.setPower(SPEED);
        right.setPower(-SPEED);
    }

    public void retract() {
        left.setPower(-SPEED);
        right.setPower(SPEED);
    }

    public void stop() {
        left.setPower(0);
        right.setPower(0);
    }
}
