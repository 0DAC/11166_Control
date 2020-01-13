package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

/**
 * Created by Michael on 1/3/2018.
 */

@TeleOp(name = "Xbox TeleOp", group = "control")

public class BaseLineController extends OpMode {
    Robot bot;
    @Override
    public void init() {
        bot = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad2.left_trigger != 0) {
            bot.intake();
        }
        else if (gamepad2.right_trigger != 0) {
            bot.spit();
        }
        else {
            bot.stop_intake();
        }

        // claw toggle: back
        if (gamepad2.back) bot.toggle_grabber();

        if (gamepad2.x) bot.lower_foundations();
        else if (gamepad2.y) bot.raise_foundations();

        if (gamepad2.dpad_down) bot.vlower_lift();
        else if (gamepad2.dpad_up) bot.vraise_lift();
        else bot.vstop();

        if (gamepad2.a) bot.hlower_lift();
        else if (gamepad2.b) bot.hraise_lift();
        else bot.hstop();
        bot.xbox_drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}

