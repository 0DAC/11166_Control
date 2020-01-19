package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name = "Strafe PID Tuning", group = "auto")
public class StrafePIDTuner extends LinearOpMode {
    private final double speed = 0.7;
    private final long time_per = 1000;

    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);

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
        telemetry.update();


    }
}
