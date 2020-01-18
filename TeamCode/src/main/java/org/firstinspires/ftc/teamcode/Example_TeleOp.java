package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Example Movement for Robot")
public class Example_TeleOp extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);
        waitForStart();
        bot.encoder_drive(telemetry, 0.3, new int[]{30, 30, 30, 30});
        for (int i = 0; i < 10; i ++) {
            bot.encoder_drive(telemetry, 0.3, new int[]{30, 30, 30, 30});
            sleep(1000);
            bot.encoder_drive(telemetry, 0.3, new int[]{-30, -30, -30, -30});
            sleep(1000);
        }
        bot.stop();
        //bot.drive.drive_distance(5, Math.PI, 0.5);
        //bot.drive.drive_distance(5, 0, 0.5);
    }
}
