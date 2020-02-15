package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Blue Stone and Foundation Auto A")

public class BlueStoneFoundationAutoA extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {

        }
    }
}


