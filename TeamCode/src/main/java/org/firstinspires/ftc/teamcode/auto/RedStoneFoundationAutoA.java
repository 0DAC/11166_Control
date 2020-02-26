package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Red Stone and Foundation Auto A")

public class RedStoneFoundationAutoA extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            bot.vgroundstonelevel();
            bot.h_auto_extend();
            sleep(400);
            bot.turnerout();

            bot.drive_backward(.75,40);

            bot.vslack(1000);

            bot.grab_stone();
            sleep(400);
            bot.h_grabber_pos();

            bot.drive_forward(1,20);

            bot.turn_90_cw(1);

            bot.set_threshold(3000);
            bot.drive_forward(1,250);
            bot.set_threshold(1500);

            bot.turn_90_ccw(1);
            bot.vgroundstonelevel();
            sleep(300);

            bot.h_extend();

            bot.drive_backward(.5,20);

            bot.lower_foundations();

            bot.vslack(500);
            bot.drop_stone();


            bot.drive_forward(1,20);

            bot.strafe_right(1,30);

            bot.turn_90_ccw(1);

            bot.drive_backward(1,60);

            bot.raise_foundations();
            bot.h_grabber_pos();
            bot.vgroundlevel();

            sleep(60000);

//            bot.strafe_right(.75,750);
//            bot.set_threshold(7000);
//            sleep(1000);
//
//            bot.vgroundstonelevel();
//            bot.h_extend();
//            sleep(400);
//            bot.turnerout();
//            bot.drive_backward(.5,39);
//            sleep(600);
//            bot.vgroundlevel();
//            sleep(1000);
//            bot.grab_stone();
//            sleep(1000);
//            bot.h_grabber_pos();
//            sleep(1000);
//            sleep(600000);

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


