package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Systems Check")
public class SystemsCheck extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        waitForStart();
        telemetry.addData("Status: ", "Testing Movement");
        /*bot.drive_forward(0.5, 10);
        bot.drive_backward(0.5, 10);
        bot.strafe_left(0.5, 10);
        bot.strafe_right(0.5, 10);*/
        sleep(1000);

        telemetry.addData("Status: ", "Testing Intake");
        bot.intake();
        sleep(500);
        bot.spit();
        sleep(500);
        bot.stop_intake();
        sleep(1000);

        telemetry.addData("Status: ", "Testing Servos");
        bot.raise_foundations();
        bot.lower_foundations();
        bot.hextend_toggle();
        bot.toggle_turner();
        bot.toggle_turner();
        bot.toggle_grabber();
        bot.toggle_grabber();
        sleep(1000);
        bot.hextend_toggle();
        sleep(1000);

        telemetry.addData("Status: ", "Testing Lift");
        bot.vraise_lift_by_ticks(50);
//        bot.vlower_lift();
        sleep(1000);

        telemetry.addData("Status: ", "Testing Complete");
    }
}
