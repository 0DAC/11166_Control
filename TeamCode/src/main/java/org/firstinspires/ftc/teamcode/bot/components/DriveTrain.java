package org.firstinspires.ftc.teamcode.bot.components;

import org.firstinspires.ftc.teamcode.HOMAR.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.SystemConfig;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    private HardwareMap hmp;

    private MecanumDrivetrain drive;
    private DcMotor[] motors;

    public DriveTrain(HardwareMap hardwareMap) {
        this.hmp = hardwareMap;

        motors = new DcMotor[SystemConfig.motors.length];
        for (int i = 0; i < motors.length; i++) motors[i] = hmp.get(DcMotor.class, SystemConfig.motors[i]);
        drive = new MecanumDrivetrain(motors);
    }

    public void power_drive(double angle, double power) {
        drive.setCourse(angle);
        drive.setVelocity(power);
    }
}
