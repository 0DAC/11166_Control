package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Blue Side Xtreme Strafe Parking")
public class BlueFarPark extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        bot.power_drive(Math.PI/2, 0.25, 0);
        bot.pause(1000);
        bot.power_drive(0, 0.25, 0);
        bot.pause(500);
    }
}
