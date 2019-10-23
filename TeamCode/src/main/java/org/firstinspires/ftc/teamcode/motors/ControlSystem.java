package org.firstinspires.ftc.teamcode.motors;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Gamepad Manual Control", group = "Control")
public class ControlSystem extends LinearOpMode {
    MotorGroup left, right;
    Servo arm;
    private static double MAX_DIST = 5;
    private static double DELTA_SPEED = 0.1;
    private Gamepad controller;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Control System Status: ", "Initializing hardware interfaces...");
        telemetry.update();
        left = new MotorGroup();
        right = new MotorGroup();
        // arm = hardwareMap.get(Servo.class, "arm");
        controller = new Gamepad();
        MotorGroup left = new MotorGroup();
        MotorGroup right = new MotorGroup();

        left.init(hardwareMap, telemetry, "left_side", new String[] {"left_1", "left_2"});
        right.init(hardwareMap, telemetry, "right_side", new String[] {"right_1", "right_2"});

        while (opModeIsActive()) {
            // A = vertical arm move up
            if (controller.a) {
                //    arm.setPosition(arm.getPosition()+MAX_DIST);
            }
            // Y = vertical arm move down
            else if (controller.y) {
                //    arm.setPosition(arm.getPosition()-MAX_DIST);
            }
            // right_bumper = increase movement speed
            if (controller.right_bumper) {
                //    left.changeSpeed(DELTA_SPEED);
                //    right.changeSpeed(DELTA_SPEED);
            }
            // left_bumper = decrease movement speed
            else if (controller.left_bumper) {
                //    left.changeSpeed(-DELTA_SPEED);
                //    right.changeSpeed(-DELTA_SPEED);
            }
            // left joystick = linear movement
            left.setPower(controller.left_stick_y * MAX_DIST + Math.max(0, controller.left_stick_x) * MAX_DIST);
            right.setPower(-(controller.left_stick_y * MAX_DIST + Math.min(0, controller.left_stick_x) * MAX_DIST));
            // right joystick = in-place rotation

            //left.update();
            //right.update();
        }
    }
}
