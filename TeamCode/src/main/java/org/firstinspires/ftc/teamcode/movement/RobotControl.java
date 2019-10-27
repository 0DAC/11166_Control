package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;
public class RobotControl {
    SystemConfig sys = new SystemConfig();
    public MotorGroup left_drive, right_drive;
    public MotorGroup left_intake, right_intake;
    public Servo left_foundation, right_foundation, gripper, turner;

    // SERVO CONSTANTS
    private double  LEFT_FOUNDATION_UP   =.5,
                    LEFT_FOUNDATION_DOWN =.8,
                    RIGHT_FOUNDATION_UP  =.42,
                    RIGHT_FOUNDATION_DOWN= 0;
    private boolean LEFT_UP = true,
                    RIGHT_UP = true;

    public RobotControl(HardwareMap hmp) {
        left_drive = new MotorGroup();
        right_drive = new MotorGroup();
        left_intake = new MotorGroup();
        right_intake = new MotorGroup();

        left_drive.init(hmp, "left_side", sys.left_motors);
        right_drive.init(hmp, "right_side", sys.right_motors);
        left_intake.init(hmp, "left_intake", sys.left_intake);
        right_intake.init(hmp, "right_intake", sys.right_intake);
        left_foundation = hmp.get(Servo.class, sys.left_foundation_servo);
        right_foundation = hmp.get(Servo.class, sys.right_foundation_servo);
        //gripper = hmp.get(Servo.class, sys.gripper_servo);
        //turner = hmp.get(Servo.class, sys.turner_servo);
    }

    public void l_foundation_toggle() {
        if (LEFT_UP) l_foundation_down();
        else l_foundation_up();
    }

    public void l_foundation_up() {
        left_foundation.setPosition(LEFT_FOUNDATION_UP);
    }

    public void l_foundation_down() {
        left_foundation.setPosition(LEFT_FOUNDATION_UP);
    }

    public void r_foundation_toggle() {
        if (RIGHT_UP) r_foundation_down();
        else r_foundation_up();
    }

    public void r_foundation_up() {
        right_foundation.setPosition(RIGHT_FOUNDATION_UP);
    }

    public void r_foundation_down() {
        right_foundation.setPosition(RIGHT_FOUNDATION_DOWN);
    }
}
