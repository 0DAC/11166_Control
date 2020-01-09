package org.firstinspires.ftc.teamcode.bot.components;

import org.firstinspires.ftc.teamcode.HOMAR.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.SystemConfig;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    private HardwareMap hmp;

    public MecanumDrivetrain drive;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DriveTrain(HardwareMap hardwareMap) {
        this.hmp = hardwareMap;

        frontLeft = hardwareMap.get(DcMotor.class, "driveFrontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "driveFrontRight");
        backLeft = hardwareMap.get(DcMotor.class, "driveBackLeft");
        backRight = hardwareMap.get(DcMotor.class, "driveBackRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontRight.setDirection(DcMotor.Direction.REVERSE);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backRight.setDirection(DcMotor.Direction.REVERSE);

        drive = new MecanumDrivetrain(new DcMotor[]{frontLeft, frontRight, backLeft, backRight});
    }

    public void xbox_drive(double x, double y, double turn) {
        double course = Math.atan2(-y, x) - Math.PI/2;
        double velocity = Math.hypot(x, y);
        double rotation = -turn;
        power_drive(course, x, velocity, rotation);
    }

    public void power_drive(double course, double strafe, double velocity, double rotation) {
        drive.setCourse(course);
        drive.setVelocity(velocity);
        drive.setRotation(rotation);
        drive.setStrafing(strafe);
    }
}
