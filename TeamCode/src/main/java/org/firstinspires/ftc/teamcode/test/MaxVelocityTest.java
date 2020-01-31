package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.SystemConfig;

@TeleOp

public class MaxVelocityTest extends LinearOpMode {
    DcMotorEx motor;
    double currentLeftVelocity;
    double currentRightVelocity;
    double maxLeftVelocity = 0.0;
    double maxRightVelocity = 0.0;

    private DcMotorEx left, right; // vertical extension
    @Override

    public void runOpMode() {
        left = hardwareMap.get(DcMotorEx.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotorEx.class, SystemConfig.right_lift);
        waitForStart();

        while (opModeIsActive()) {
            left.setPower(1);
            right.setPower(1);
            currentLeftVelocity = left.getVelocity();
            currentRightVelocity = right.getVelocity();

            if (currentLeftVelocity > maxLeftVelocity) {
                maxLeftVelocity = currentLeftVelocity;
            }
            if (currentRightVelocity > maxRightVelocity) {
                maxRightVelocity = currentRightVelocity;
            }
            telemetry.addData("Current left velocity", currentLeftVelocity);
            telemetry.addData("Maximum left velocity", maxLeftVelocity);
            telemetry.addData("Current right velocity", currentRightVelocity);
            telemetry.addData("Maximum right velocity", currentRightVelocity);
            telemetry.update();
        }
    }
}