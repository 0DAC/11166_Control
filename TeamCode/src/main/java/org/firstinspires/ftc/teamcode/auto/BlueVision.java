package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Camera;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Test Vision Move")
public class BlueVision extends LinearOpMode {
    Robot bot;
    Camera camera;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);
        camera = new Camera(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {

        }
    }
}