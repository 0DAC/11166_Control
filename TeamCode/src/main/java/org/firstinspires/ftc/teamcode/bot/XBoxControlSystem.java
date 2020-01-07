package org.firstinspires.ftc.teamcode.bot;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.actions.SetDrive;
import org.firstinspires.ftc.teamcode.bot.actions.SetLatch;
import org.firstinspires.ftc.teamcode.bot.actions.ToggleLatch;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

import java.util.ArrayList;

@TeleOp(name = "Gamepad Manual Control", group = "Control")
public class XBoxControlSystem extends LinearOpMode implements DogeOpMode {
    private static double DRIVE_SPEED = 2;
    private static double TURN_SPEED = 1;
    private static double INTAKE_SPEED = 0.5;
    private static double DELTA_SPEED = 2;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Control System Status: ", "Initializing hardware interfaces...");
        telemetry.update();
        robot = new Robot(this, hardwareMap);

        waitForStart();

        ArrayList<Command> commands = new ArrayList<>();
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

            // gamepad 2

            // A = vertical arm move up

            // bumpers toggles max and min position for foundation


            if (gamepad2.a) {
                telemetry.addData("Status:", "Moving left foundation to down");
                telemetry.update();
            }
            if (gamepad2.b) {
                telemetry.addData("Status:", "Moving left foundation to up");
                telemetry.update();
            }

            if (gamepad2.x) {
                telemetry.addData("Status:", "Moving right foundation to up");
                telemetry.update();
                commands.add(new SetLatch(robot.latch, true));
            }
            if (gamepad2.y) {
                telemetry.addData("Status:", "Moving right foundation to down");
                telemetry.update();
                commands.add(new SetLatch(robot.latch, false));
            }

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            commands.add(new SetDrive(robot.drive, Math.atan2(y, x), Math.sqrt(x*x+y*y)));

            robot.runCommands(commands);
        }
        robot.stop();
    }
}