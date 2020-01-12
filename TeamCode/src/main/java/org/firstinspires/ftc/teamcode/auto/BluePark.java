package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Blue Side Parking")
public class BluePark extends OpMode {
    Robot bot;

    @Override
    public void init() {
        bot = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        bot.power_drive(Math.PI, 0.5, 0);
        bot.pause(1000);
        bot.stop();
    }
}
