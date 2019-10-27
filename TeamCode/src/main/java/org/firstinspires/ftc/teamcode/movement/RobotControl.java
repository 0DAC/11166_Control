package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;
public class RobotControl {
    SystemConfig sys = new SystemConfig();
    public MotorGroup left_drive, right_drive;
    public MotorGroup left_intake, right_intake;
    public Servo claws;

    public RobotControl(HardwareMap hmp) {
        left_drive = new MotorGroup();
        right_drive = new MotorGroup();
        left_intake = new MotorGroup();
        right_intake = new MotorGroup();

        left_drive.init(hmp, "left_side", sys.left_motors);
        right_drive.init(hmp, "right_side", sys.right_motors);
        left_intake.init(hmp, "left_intake", sys.left_intake);
        right_intake.init(hmp, "right_intake", sys.right_intake);
    }
}
