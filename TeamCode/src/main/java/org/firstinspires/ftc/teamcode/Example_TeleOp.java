package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Example Movement for Robot")
public class Example_TeleOp extends OpMode {
    Robot bot;

    @Override
    public void init() {
        bot = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        // drives forward
        bot.power_drive(0, 5, 0);
        // drives 50 (not sure of units yet) at full power
        bot.drive.drive_distance(50, 0, 1.0);

        // hard coded turn 90
        bot.turn_90_ccw(1.0);
    }
}
