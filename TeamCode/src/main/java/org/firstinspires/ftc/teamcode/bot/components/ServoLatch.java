package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HOMAR.activator.ServoActivator;
import org.firstinspires.ftc.teamcode.SystemConfig;

/**
 * Synchronized latch control with DogeCommander, HOMAR
 */
public class ServoLatch implements Subsystem {
    private SystemConfig sys;
    private HardwareMap hardwareMap;

    private ServoActivator leftLatch;
    private ServoActivator rightLatch;

    private boolean latchUp;

    public ServoLatch(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        this.sys = new SystemConfig();

        latchUp = true;
    }

    public void toggle() {
        setState(!this.latchUp);
    }

    public void setState(boolean latchUp) {
        if (latchUp) release();
        else hook();
    }

    public void hook() {
        latchUp = false;
    }

    public void release() {
        latchUp = false;
    }

    @Override
    public void initHardware() {
        leftLatch = new ServoActivator(hardwareMap.get(Servo.class, sys.left_foundation_servo), sys.LEFT_FOUNDATION_UP, sys.LEFT_FOUNDATION_DOWN);
        rightLatch = new ServoActivator(hardwareMap.get(Servo.class, sys.right_foundation_servo), sys.RIGHT_FOUNDATION_UP, sys.RIGHT_FOUNDATION_DOWN);
    }

    @Override
    public void periodic() {
        leftLatch.setActivated(latchUp);
        rightLatch.setActivated(latchUp);
    }
}
