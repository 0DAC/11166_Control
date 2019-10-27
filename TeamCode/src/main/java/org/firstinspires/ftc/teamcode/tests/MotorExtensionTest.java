package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.movement.RobotControl;

@Autonomous(name="Motor Calibration", group="Test")
public class MotorExtensionTest extends LinearOpMode {

    RobotControl robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setAutoClear(false);
        telemetry.addData("[Status] ", "Initiating Motor Test...");
        telemetry.update();

        robot = new RobotControl(hardwareMap);


    }
}
