package org.firstinspires.ftc.teamcode.motors;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;

@TeleOp(name = "Gamepad Manual Control", group = "Control")
public class ControlSystem extends LinearOpMode {
    SystemConfig sys = new SystemConfig();
    MotorGroup left, right;
    Servo arm;
    private static double MAX_DIST = 2;
    private static double DELTA_SPEED = 0.5;
    private Gamepad controller;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Control System Status: ", "Initializing hardware interfaces...");
        telemetry.update();
        left = new MotorGroup();
        right = new MotorGroup();
        // arm = hardwareMap.get(Servo.class, "arm");
        MotorGroup left = new MotorGroup();
        MotorGroup right = new MotorGroup();

        left.init(hardwareMap, telemetry, "left_side", sys.left_motors);
        right.init(hardwareMap, telemetry, "right_side", sys.right_motors);

        waitForStart();

        while (opModeIsActive()) {
            // A = vertical arm move up
            if (gamepad1.a) {
                telemetry.addData("KeyPress:", "A");
                //    arm.setPosition(arm.getPosition()+MAX_DIST);
            }
            // Y = vertical arm move down
            else if (gamepad1.y) {
                telemetry.addData("KeyPress:", "A");
                //    arm.setPosition(arm.getPosition()-MAX_DIST);
            }
            // right_bumper = increase movement speed
            if (gamepad1.right_bumper) {
                MAX_DIST -= DELTA_SPEED;
            }
            // left_bumper = decrease movement speed
            else if (gamepad1.left_bumper) {
                MAX_DIST -= DELTA_SPEED;
            }
            // left joystick = linear movement
            if (gamepad1.right_stick_x == 0 && gamepad1.right_stick_y == 0) {
                telemetry.addData("Left Joystick Values", String.format("(" + gamepad1.left_stick_x + ", " + gamepad1.left_stick_y + ")"));

                left.setPower(-(gamepad1.left_stick_y * MAX_DIST - gamepad1.left_stick_x * MAX_DIST));
                right.setPower((gamepad1.left_stick_y * MAX_DIST + gamepad1.left_stick_x * MAX_DIST));
            }
            else {
                left.setPower(gamepad1.right_stick_x* MAX_DIST);
                right.setPower(gamepad1.right_stick_x * MAX_DIST);
            }

            //left.update();
            //right.update();
            telemetry.update();
        }
    }
}
