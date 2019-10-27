package org.firstinspires.ftc.teamcode.movement;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Gamepad Manual Control", group = "Control")
public class XBoxControlSystem extends LinearOpMode {
    private static double DRIVE_SPEED = 2;
    private static double TURN_SPEED = 1;
    private static double INTAKE_SPEED = 0.5;
    private static double DELTA_SPEED = 2;
    private RobotControl robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Control System Status: ", "Initializing hardware interfaces...");
        telemetry.update();
        robot = new RobotControl(hardwareMap);

        waitForStart();

        boolean isUp = true;
        robot.left_foundation.setPosition(1968);
        robot.right_foundation.setPosition(220);
        while (opModeIsActive()) {

            // gamepad 1: movement

            // right_bumper = increase movement speed
            if (gamepad1.right_bumper) {
                DRIVE_SPEED = 2;
            }
            // left_bumper = decrease movement speed
            else if (gamepad1.left_bumper) {
                DRIVE_SPEED = .2;
            }
            // left_drive joystick = linear movement
            if (gamepad1.right_stick_x == 0 && gamepad1.right_stick_y == 0) {

                robot.left_drive.setPower((gamepad1.left_stick_y * DRIVE_SPEED - gamepad1.left_stick_x * TURN_SPEED));
                robot.right_drive.setPower(-(gamepad1.left_stick_y * DRIVE_SPEED + gamepad1.left_stick_x * TURN_SPEED));
            } else {
                robot.left_drive.setPower(gamepad1.right_stick_x * TURN_SPEED);
                robot.right_drive.setPower(gamepad1.right_stick_x * TURN_SPEED);
            }

            // gamepad 2

            // A = vertical arm move up

            // bumpers toggles max and min position for foundation


            if (gamepad2.a) {
                telemetry.addData("Status:", "Moving left foundation to down");
                telemetry.update();
                robot.left_foundation.setPosition(1.0);

                //robot.left_foundation.setPosition(1000);
            }
            if (gamepad2.b) {
                telemetry.addData("Status:", "Moving left foundation to up");
                telemetry.update();
                robot.left_foundation.setPosition(.5);
                //robot.left_foundation.setPosition(1968);
            }

            if (gamepad2.x) {
                telemetry.addData("Status:", "Moving right foundation to up");
                telemetry.update();
                robot.right_foundation.setPosition(.7);
                //robot.right_foundation.setPosition(1000);
            }
            if (gamepad2.y) {
                telemetry.addData("Status:", "Moving right foundation to down");
                telemetry.update();
                robot.right_foundation.setPosition(.1);
                //robot.right_foundation.setPosition(220);
            }

            robot.left_intake.setPower(0.3 * (gamepad2.left_stick_y > 0 ? 1 : (gamepad2.left_stick_y == 0 ? 0 : -1)));
            robot.right_intake.setPower(-0.3 * (gamepad2.left_stick_y > 0 ? 1 : (gamepad2.left_stick_y == 0 ? 0 : -1)));

        }
    }
}