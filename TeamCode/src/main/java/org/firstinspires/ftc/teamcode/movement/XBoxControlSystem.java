package org.firstinspires.ftc.teamcode.movement;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Gamepad Manual Control", group = "Control")
public class XBoxControlSystem extends LinearOpMode {
    private static double DRIVE_SPEED = 2;
    private static double TURN_SPEED = 1;
    private static double INTAKE_SPEED = 0.5;
    private static double DELTA_SPEED = 0.1;
    private RobotControl robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Control System Status: ", "Initializing hardware interfaces...");
        telemetry.update();
        robot = new RobotControl(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            // A = vertical arm move up
            if (gamepad1.a) {
                robot.left_intake.setPower(INTAKE_SPEED);
                robot.right_intake.setPower(-INTAKE_SPEED);
                sleep(1000);
                robot.left_intake.setPower(0);
                robot.right_intake.setPower(0);
            }
            // Y = vertical arm move down
            else if (gamepad1.y) {
                robot.left_intake.setPower(-INTAKE_SPEED);
                robot.right_intake.setPower(INTAKE_SPEED);
                sleep(1000);
                robot.left_intake.setPower(0);
                robot.right_intake.setPower(0);
            }
            // right_bumper = increase movement speed
            if (gamepad1.right_bumper) {
                //DRIVE_SPEED -= DELTA_SPEED;
            }
            // left_bumper = decrease movement speed
            else if (gamepad1.left_bumper) {
                //DRIVE_SPEED -= DELTA_SPEED;
            }
            // left_drive joystick = linear movement
            if (gamepad1.right_stick_x == 0 && gamepad1.right_stick_y == 0) {
                telemetry.addData("Left Joystick Values", String.format("(" + gamepad1.left_stick_x + ", " + gamepad1.left_stick_y + ")"));

                robot.left_drive.setPower((gamepad1.left_stick_y * DRIVE_SPEED - gamepad1.left_stick_x * TURN_SPEED));
                robot.right_drive.setPower(-(gamepad1.left_stick_y * DRIVE_SPEED + gamepad1.left_stick_x * TURN_SPEED));
            }
            else {
                robot.left_drive.setPower(gamepad1.right_stick_x * TURN_SPEED);
                robot.right_drive.setPower(gamepad1.right_stick_x * TURN_SPEED);
            }

            telemetry.update();
        }
    }
}
