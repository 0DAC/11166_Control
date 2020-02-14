package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Red Stone and Foundation Auto")

public class RedStoneFoundationAuto extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            //bot.pause(13000);
            bot.vgroundstonelevel();
            bot.h_extend();
            sleep(400);
            bot.turnerout();
            bot.drive_backward(.5,39);
            sleep(600);
            bot.vgroundlevel();
            sleep(1000);
            bot.grab_stone();
            sleep(1000);
            bot.h_grabber_pos();
            sleep(1000);
            bot.strafe_left(.75,500);
            sleep(4000);
            bot.vraise_lift();
            bot.h_extend();
            sleep(600);
            bot.toggle_grabber();
            bot.h_grabber_pos();
            sleep(600);
            bot.vlower_lift();
            bot.strafe_left(.75,750);

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


