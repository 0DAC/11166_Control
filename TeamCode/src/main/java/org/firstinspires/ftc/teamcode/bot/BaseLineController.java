package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

/**
 * Created by Michael on 1/3/2018.
 */

@TeleOp(name = "Xbox TeleOp", group = "control")

public class BaseLineController extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_trigger != 0) {
                bot.intake();
            } else if (gamepad1.right_trigger != 0) {
                bot.spit();
            } else {
                bot.stop_intake();
            }

            // driver gamepad
            if (gamepad2.dpad_down) bot.power_drive(Math.PI*3/2, 0.5, 0);
            else if (gamepad2.dpad_up) bot.power_drive(Math.PI/2, 0.5, 0);
            else if (gamepad2.dpad_left) bot.power_drive(0, 0.5, 0);
            else if (gamepad2.dpad_right) bot.power_drive(Math.PI, 0.5, 0);
            else bot.xbox_drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.x) bot.lower_foundations();
            else if (gamepad1.y) bot.raise_foundations();

            if (gamepad2.a) bot.toggle_grabber();
            if (gamepad2.b) bot.hextend_toggle();

            // driver gamepad
        }
    }
}

