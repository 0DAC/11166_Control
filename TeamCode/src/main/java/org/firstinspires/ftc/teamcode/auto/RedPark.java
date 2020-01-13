package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Red Side Strafe Parking")
public class RedPark extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        waitForStart();
        bot.power_drive(0, 0.25, 0);
        bot.pause(250);
        bot.power_drive(Math.PI/2, 0.5, 0);
        bot.pause(1000);
    }
}
