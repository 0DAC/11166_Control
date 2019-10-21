package org.firstinspires.ftc.teamcode.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Groups DC Motors together to synchronize motion
 */
public class MotorGroup {

    String name;        // for telemetry update purposes
    int n;              // number of motors in this group
    int target_pos;     // accumulate change in motors to allow multi-controller control
    DcMotor[] motors;   // pointers to motors

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_CM       = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_CM           = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_CM * 3.1415);
    static double           SPEED                   = 0.6;

    public void init(HardwareMap hmp, String[] motor_names) {
        motors = new DcMotor[motor_names.length];
        for (int i = 0; i < n; i ++) {
            motors[i] = hmp.get(DcMotor.class, motor_names[i]);
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void changeSpeed(double change) {
        SPEED += change;
        SPEED = Math.min(SPEED, 1.0);
        SPEED = Math.max(SPEED, -1.0);
    }

    public void move(double distance_cm) {
        target_pos += (int)(distance_cm*COUNTS_PER_CM);
    }

    public void update() {
        for (int i = 0; i < n; i ++) {
            motors[i].setTargetPosition(target_pos);
            motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors[i].setPower(Math.abs(SPEED));
        }
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        target_pos = motors[0].getCurrentPosition();
    }
}