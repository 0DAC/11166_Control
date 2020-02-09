package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Blue Stone and Foundation Auto")

public class BlueStoneFoundationAuto extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            //bot.pause(13000);
            bot.vgroundstonelevel();
            sleep(600);
            bot.h_extend();
            sleep(600);
            bot.toggle_turner();
            sleep(800);
            bot.drive_backward(.5,20);
            bot.toggle_turner();
            sleep(800);
            bot.vslack(500);
            bot.grab_stone();
            sleep(600);
            bot.h_grabber_pos();
            sleep(600000);

//        if (side == Side.RIGHT) bot.strafe_right(0.75, 10);
//        else bot.strafe_left(0.75, 10);


//        bot.drive_backward(0.5, 50);
//        if (side == Side.RIGHT) bot.strafe_right(0.75, 20);
//        else bot.strafe_left(0.75, 20);
//        bot.drive_backward(1.0, 10);
//        bot.lower_foundations();
//
//        if (side == Side.RIGHT) bot.strafe_left(0.75, 40);
//        else bot.strafe_right(0.75, 40);
//
//        bot.drive_forward(1.0, 20);
//
//        if (side == Side.RIGHT) bot.turn(.6, 95); //old value for speed turn = 1.0
//        else bot.turn(.6, -95);
//
//        // park near the wall
//        if (side == Side.RIGHT) bot.strafe_right(0.75, 25); //old value for distance was 40
//        else bot.strafe_left(0.75, 25);
//        bot.drive_backward(1.0, 40);
//
//        bot.raise_foundations();
//
//        if (side == Side.RIGHT) bot.strafe_left(0.75, 19); //old value for distance was 40
//        else bot.strafe_right(0.75, 19);
//
//        bot.drive_forward(1.0, 80);
        }
    }
}


