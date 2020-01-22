package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Blue Side Foundation Latch ")
public class BlueFoundationLatch extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        waitForStart();
        bot.drive_backward(0.5, 80);
        bot.strafe_right(0.5, 20);
        bot.lower_foundations();
        sleep(500);
        bot.strafe_left(0.5, 10);
        bot.turn_90_ccw(0.5);
    }
}
