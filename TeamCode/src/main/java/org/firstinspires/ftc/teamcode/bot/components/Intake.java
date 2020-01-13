package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class Intake {
    private DcMotor out_left, out_right;
    private CRServo in_left, in_right;
    private double l_speed = 1.0, r_speed = 1.0;

    public Intake(HardwareMap hmp) {
        this.out_left = hmp.get(DcMotor.class, SystemConfig.left_outer_intake);
        this.out_right = hmp.get(DcMotor.class, SystemConfig.right_outer_intake);

        this.out_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.out_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.out_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.out_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.in_left = hmp.get(CRServo.class, SystemConfig.left_inner_intake);
        this.in_right = hmp.get(CRServo.class, SystemConfig.right_inner_intake);

    }

    public void intake() {
        this.out_left.setPower(-l_speed);
        this.out_right.setPower(r_speed);

        this.in_right.setPower(-1);
        this.in_left.setPower(1);
    }

    public void spit() {
        this.out_left.setPower(l_speed);
        this.out_right.setPower(-r_speed);

        this.in_right.setPower(1);
        this.in_left.setPower(-1);
    }

    public void stop() {
        this.out_left.setPower(0);
        this.out_right.setPower(0);

        this.in_right.setPower(0);
        this.in_left.setPower(0);
    }
}
