package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class Robot {
    public SystemConfig sys;

    public Intake intake;
    public ServoLatch latch;
    public DriveTrain drive;

    public Robot(DogeOpMode opmode, HardwareMap hmp) {
        sys = new SystemConfig();

        //intake = new Intake(hmp);
        //latch = new ServoLatch(hmp);
        drive = new DriveTrain(hmp);
    }

    /**
     * Robot drive-by-power
     * @param angle: angle in radians. 0 is straight ahead, PI/2 is straight left
     * @param power: velocity
     */
    public void power_drive(double angle, double power) {
        drive.power_drive(angle, power);
    }

    // TODO: implement intake and latch functionality

}
