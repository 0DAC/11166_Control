package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Servo Functionality Test")
public class ServoTest extends LinearOpMode {
    Servo l_foundation, r_foundation;
    Robot r;

    @Override
    public void runOpMode() {
        r = new Robot(hardwareMap);
        waitForStart();
        r.encoder_drive(telemetry, 0.3, new int[]{30, 30, 30, 30});
    }
}
