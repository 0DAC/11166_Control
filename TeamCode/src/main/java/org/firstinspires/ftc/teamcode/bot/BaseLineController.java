package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

/**
 * Created by Michaela on 1/3/2018.
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
        if (gamepad1.x) bot.out_intake();
        else if (gamepad1.y) bot.out_spit();
        else bot.out_stop_intake();

        if (gamepad1.a) bot.in_intake();
        else if (gamepad1.b) bot.in_spit();
        else bot.in_stop_intake();

        if (gamepad1.left_bumper) bot.lower_foundations();
        else if (gamepad1.right_bumper) bot.raise_foundations();

        if (gamepad1.left_trigger != 0) bot.lower_foundations();
        else if (gamepad1.right_trigger != 0) bot.raise_foundations();
        bot.xbox_drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}

