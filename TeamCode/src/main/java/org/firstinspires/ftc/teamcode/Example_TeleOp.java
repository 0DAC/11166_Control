package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Example Movement for Robot")
public class Example_TeleOp extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            double stone_x = bot.get_skystone_pos();
        }
        bot.stop();
        //bot.drive.drive_distance(5, Math.PI, 0.5);
        //bot.drive.drive_distance(5, 0, 0.5);
    }
}
