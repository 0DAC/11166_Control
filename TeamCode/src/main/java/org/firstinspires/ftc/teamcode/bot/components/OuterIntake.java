package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class OuterIntake {
    private DcMotor left, right;
    private double l_speed = 1.0, r_speed = 1.0;

    public OuterIntake(HardwareMap hmp) {
        this.left = hmp.get(DcMotor.class, SystemConfig.left_outer_intake);
        this.right = hmp.get(DcMotor.class, SystemConfig.right_outer_intake);

        this.left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake() {
        this.left.setPower(-l_speed);
        this.right.setPower(r_speed);
    }

    public void spit() {
        this.left.setPower(l_speed);
        this.right.setPower(-r_speed);
    }

    public void stop() {
        this.left.setPower(0);
        this.right.setPower(0);
    }
}
