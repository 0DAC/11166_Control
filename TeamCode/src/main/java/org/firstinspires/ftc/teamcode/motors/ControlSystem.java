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

    public ControlSystem() {
        left = new MotorGroup();
        right = new MotorGroup();
        // arm = hardwareMap.get(Servo.class, "arm");
        controller = new Gamepad();

        left.init(this.hardwareMap, new String[] {"left_top", "left_bottom"});
        right.init(this.hardwareMap, new String[] {"right_top", "right_bottom"});
    }

    @Override
    public void runOpMode() throws InterruptedException {
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
            left.changeSpeed(DELTA_SPEED);
            right.changeSpeed(DELTA_SPEED);
        }
        // left_bumper = decrease movement speed
        else if (controller.left_bumper) {
            left.changeSpeed(-DELTA_SPEED);
            right.changeSpeed(-DELTA_SPEED);
        }
        // left joystick = linear movement
        left.move(-MAX_DIST*controller.left_stick_y);
        right.move(MAX_DIST*controller.left_stick_y);
        if (controller.left_stick_x > 0)
            right.move(MAX_DIST*controller.left_stick_x);
        else if (controller.left_stick_x < 0)
            left.move(-MAX_DIST*controller.left_stick_x);
        // right joystick = in-place rotation
        left.move(MAX_DIST*controller.right_stick_x);
        right.move(MAX_DIST*controller.right_stick_x);

        left.update();
        right.update();
    }
}
