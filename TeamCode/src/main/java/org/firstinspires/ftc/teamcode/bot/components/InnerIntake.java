package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class InnerIntake {
    private CRServo left, right;

    public InnerIntake(HardwareMap hmp) {
        left = hmp.get(CRServo.class, SystemConfig.left_inner_intake);
        right = hmp.get(CRServo.class, SystemConfig.right_inner_intake);
    }

    public void intake() {
        left.setPower(1);
        right.setPower(-1);
    }

    public void spit() {
        left.setPower(-1);
        right.setPower(1);
    }

    public void stop() {
        left.setPower(0);
        right.setPower(0);
    }
}
