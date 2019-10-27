package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.movement.RobotControl;

@Autonomous(name="Servo Test", group="Test")
public class ServoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RobotControl robot = new RobotControl(hardwareMap);

        telemetry.setAutoClear(false);

        telemetry.addData("Motor Being Tested: ", "left_foundation");
        telemetry.update();
        robot.left_foundation.setPosition(1000);
        sleep(1000);
        robot.left_foundation.setPosition(1968);
        sleep(1000);

        telemetry.addData("Motor Being Tested: ", "right_foundation");
        telemetry.update();
        robot.right_foundation.setPosition(1000);
        sleep(1000);
        robot.right_foundation.setPosition(220);
        sleep(1000);

        /*telemetry.addData("Motor Being Tested: ", "turner");
        telemetry.update();
        robot.turner.setPosition(1000);
        sleep(1000);
        robot.turner.setPosition(0);
        sleep(1000);

        telemetry.addData("Motor Being Tested: ", "gripper");
        telemetry.update();
        robot.gripper.setPosition(1000);
        sleep(1000);
        robot.gripper.setPosition(0);*/
    }
}
