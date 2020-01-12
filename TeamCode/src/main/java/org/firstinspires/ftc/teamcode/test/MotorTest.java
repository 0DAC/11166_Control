package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HOMAR.internal.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.SystemConfig;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name = "Motor Test", group = "test")
public class MotorTest extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public MecanumDrivetrain drivetrain;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor top_l = hardwareMap.get(DcMotor.class, SystemConfig.top_l);
        DcMotor top_r = hardwareMap.get(DcMotor.class, SystemConfig.top_r);
        DcMotor bot_l = hardwareMap.get(DcMotor.class, SystemConfig.bot_l);
        DcMotor bot_r = hardwareMap.get(DcMotor.class, SystemConfig.bot_r);

        top_l.setPower(5);
        sleep(500);
        top_l.setPower(0);
        top_r.setPower(5);
        sleep(500);
        top_r.setPower(0);
        bot_l.setPower(5);
        sleep(500);
        bot_l.setPower(0);
        bot_r.setPower(5);
        sleep(500);
        bot_r.setPower(0);
    }
}
