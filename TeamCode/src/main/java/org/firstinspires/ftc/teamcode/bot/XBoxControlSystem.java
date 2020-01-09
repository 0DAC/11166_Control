package org.firstinspires.ftc.teamcode.bot;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
        robot = new Robot(this, hardwareMap, telemetry);

        waitForStart();

        ArrayList<Command> commands = new ArrayList<>();
        telemetry.setAutoClear(true);
        while (opModeIsActive()) {
            robot.xbox_control(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Course:", robot.drive.drive.getCourse());
            telemetry.addData("Velocity:", robot.drive.drive.getVelocity());
            telemetry.addData("Rotation:", robot.drive.drive.getRotation());
            telemetry.update();
        }
    }
}