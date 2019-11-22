package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.movement.RobotControl;

@Autonomous(name="Test Strafing Capability", group="Tests")
public class Strafing_Test extends LinearOpMode {
    RobotControl robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotControl(this.hardwareMap);
        robot.strafe(0, 0, 0.5);
        sleep(1000);
        robot.stop_drive();
    }
}