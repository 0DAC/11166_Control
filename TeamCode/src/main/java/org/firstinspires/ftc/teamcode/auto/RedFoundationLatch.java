package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Red Side Foundation Latch ")
public class RedFoundationLatch extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        waitForStart();
        bot.power_drive(0, -0.25, 0);
        bot.pause(500);
        bot.power_drive(Math.PI/2, 0.5, 0);
        bot.pause(500);
        bot.power_drive(0, -0.1, 0);
        bot.pause(9000);
        sleep(500);
        bot.lower_foundations();
        sleep(500);
        bot.pause(500);
        bot.power_drive(0, 0.1, 0);
        bot.pause(12000);
        bot.raise_foundations();

        // update this part with the Park
        bot.power_drive(0, -0.25, 0);
        bot.pause(500);
        bot.power_drive(Math.PI/2, -0.5, 0);
        bot.pause(1900);
    }

}


