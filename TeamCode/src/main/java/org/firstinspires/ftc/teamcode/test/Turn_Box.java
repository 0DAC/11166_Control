package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Turn Box Shape Quality Control")
public class Turn_Box extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);
        waitForStart();
        for (int i = 0; i < 5; i ++) {
            for (int j = 0; j < 4; j ++) {
                bot.drive_forward(0.5, 40);
                bot.turn_90_ccw(0.5);
            }
        }
    }
}
