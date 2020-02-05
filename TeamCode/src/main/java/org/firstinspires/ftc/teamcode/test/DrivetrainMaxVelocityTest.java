package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.SystemConfig;

@TeleOp

public class DrivetrainMaxVelocityTest extends LinearOpMode {
    DcMotorEx motor;
    double currentLeftFrontVelocity;
    double currentLeftRearVelocity;
    double currentRightFrontVelocity;
    double currentRightRearVelocity;
    double maxLeftFrontVelocity = 0.0;
    double maxLeftRearVelocity = 0.0;
    double maxRightFrontVelocity = 0.0;
    double maxRightRearVelocity = 0.0;

    private DcMotorEx leftFront, leftRear, rightFront, rightRear; // drivetrain motors
    @Override

    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, SystemConfig.top_l);
        leftRear = hardwareMap.get(DcMotorEx.class, SystemConfig.bot_l);
        rightFront = hardwareMap.get(DcMotorEx.class, SystemConfig.top_r);
        rightRear = hardwareMap.get(DcMotorEx.class, SystemConfig.bot_r);
        waitForStart();

        while (opModeIsActive()) {
            leftFront.setPower(1);
            leftRear.setPower(1);
            rightFront.setPower(1);
            rightRear.setPower(1);
            currentLeftFrontVelocity = leftFront.getVelocity();
            currentLeftFrontVelocity = leftRear.getVelocity();
            currentRightFrontVelocity = rightFront.getVelocity();
            currentRightFrontVelocity = rightRear.getVelocity();

            if (currentLeftFrontVelocity > maxLeftFrontVelocity) {
                maxLeftFrontVelocity = currentLeftFrontVelocity;
            }
            if (currentLeftRearVelocity > maxLeftRearVelocity) {
                maxLeftRearVelocity = currentLeftRearVelocity;
            }
            if (currentRightFrontVelocity > maxRightFrontVelocity) {
                maxRightFrontVelocity = currentRightFrontVelocity;
            }
            if (currentRightRearVelocity > maxRightRearVelocity) {
                maxRightRearVelocity = currentRightRearVelocity;
            }
            telemetry.addData("Current left front velocity", currentLeftFrontVelocity);
            telemetry.addData("Maximum left rear velocity", currentLeftRearVelocity);
            telemetry.addData("Current right front velocity", currentRightFrontVelocity);
            telemetry.addData("Maximum right rear velocity", currentRightRearVelocity);
            telemetry.update();
        }
    }
}