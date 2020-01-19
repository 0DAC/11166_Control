package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Strafe Box Shape Quality Control")
public class Strafe_Box extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);
        waitForStart();
        for (int i = 0; i < 5; i ++) {
            bot.drive_forward(0.5, 40);
            bot.strafe_left(0.5, 40);
            bot.drive_backward(0.5, 40);
            bot.strafe_right(0.5, 40);
        }
    }
}
