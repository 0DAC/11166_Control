package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@TeleOp(name="Manual Value Tuning")
public class Value_Test_Teleop extends OpMode {
    Robot bot;
    int value = 40; // one  tile

    @Override
    public void init() {
        bot = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            value -= 1;
            telemetry.addData("Value: ", "Updating");
            telemetry.update();
            bot.pause(1000);
        }
        else if (gamepad1.right_bumper) {
            value += 1;
            telemetry.addData("Value: ", "Updating");
            telemetry.update();
            bot.pause(1000);
        }

        if (gamepad1.left_trigger != 0) {
            value -= 10;
            telemetry.addData("Value: ", "Updating");
            telemetry.update();
            bot.pause(1000);
        }
        else if (gamepad1.right_trigger!= 0) {
            value += 10;
            telemetry.addData("Value: ", "Updating");
            telemetry.update();
            bot.pause(1000);
        }

        if (gamepad1.a) bot.encoder_drive(0.5, new int[] {value, value, value, value});
        else if (gamepad1.b) bot.encoder_drive(0.5, new int[] {-value, -value, -value, -value});
        else if (gamepad1.x) bot.encoder_drive(0.5, new int[] {value, -value, -value, value});
        else if (gamepad1.y) bot.encoder_drive(0.5, new int[] {-value, value, value, -value});

        telemetry.addData("Value: ", value);
        telemetry.update();
    }
}
