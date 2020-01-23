package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Red Side Foundation Latch ")
public class RedFoundationLatch extends LinearOpMode {
    Robot bot;
    MainFoundationAuto prog;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        prog = new MainFoundationAuto(bot, Side.LEFT);
        waitForStart();
        prog.run();
    }

}


