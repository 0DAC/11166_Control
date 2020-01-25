package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Grabber Functionality Test")
public class GrabberTest extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);
        waitForStart();
        bot.grabber_turn_right();
        bot.grabber_turn_left();
        bot.grab_stone();
        bot.drop_stone();
    }
}
