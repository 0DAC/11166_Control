package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Ken and Richard Auto")
public class StarterAuto extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        waitForStart();
        bot.drive_forward(0.5, 40);
        bot.turn_90_cw( 0.5);
        bot.drive_forward(0.5, 40);
        bot.turn_90_cw( 0.5);
        bot.drive_forward(0.5, 40);
        bot.turn_90_cw( 0.5);
        bot.drive_forward(0.5, 40);
        bot.turn_90_cw( 0.5);
    }
}
