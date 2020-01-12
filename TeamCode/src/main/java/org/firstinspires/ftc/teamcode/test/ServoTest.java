package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;
import org.firstinspires.ftc.teamcode.bot.components.InnerIntake;

@Autonomous(name="Servo Functionality Test")
public class ServoTest extends LinearOpMode {
    InnerIntake innerIntake;
    Servo l_foundation, r_foundation;

    @Override
    public void runOpMode() {
        innerIntake = new InnerIntake(hardwareMap);
        l_foundation = hardwareMap.get(Servo.class, SystemConfig.left_foundation_servo);
        r_foundation = hardwareMap.get(Servo.class, SystemConfig.right_foundation_servo);

        while (opModeIsActive()) {
            l_foundation.setPosition(0.1);
            r_foundation.setPosition(1);
            sleep(1000);
            l_foundation.setPosition(1605/2400);
            r_foundation.setPosition(490/2400);
        }
    }
}
