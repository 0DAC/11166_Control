package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name = "StrafeDriftTest", group = "auto")
public class StrafeDriftTest extends LinearOpMode {
    private final double speed = 0.7;
    private final long time_per = 3000;

    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        waitForStart();
        bot.pause(time_per);
        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(Math.PI, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(3 * Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(0, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);

        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(Math.PI, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(3 * Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(0, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);


        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(Math.PI, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(3 * Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(0, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);


        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(Math.PI, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(3 * Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.pause(time_per);
        bot.power_drive(0, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();
        bot.power_drive(Math.PI / 2, speed, 0);
        bot.print_encoder_vals(telemetry);
        telemetry.update();


    }
}

