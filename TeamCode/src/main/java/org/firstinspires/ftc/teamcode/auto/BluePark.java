package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Blue Side Strafe Parking")
public class BluePark extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        // if tune this, also update the FoundationLatch
        waitForStart();
        bot.power_drive(0, 0.25, 0);
        bot.pause(250);
        bot.power_drive(Math.PI/2, -0.5, 0);
        bot.pause(1000);
    }
}