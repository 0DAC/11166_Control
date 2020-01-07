package org.firstinspires.ftc.teamcode.tests;

import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Motor Calibration", group="Test")
public class MotorExtensionTest extends LinearOpMode implements DogeOpMode {

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setAutoClear(false);
        telemetry.addData("[Status] ", "Initiating Motor Test...");
        telemetry.update();

        robot = new Robot(this, hardwareMap);

        while (opModeIsActive()) {
            robot.drive(0, 1);
        }
    }
}
