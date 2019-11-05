package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Groups DC Motors together to synchronize motion
 */
public class MotorGroup {

    int n;              // number of motors in this group
    DcMotor[] motors;   // pointers to motors
    HardwareMap hmp;    // save local copy of hardware map

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_CM = 10.16;     // For figuring circumference
    static final double COUNTS_PER_CM = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_CM * 3.1415);

    public MotorGroup(HardwareMap hmp, String motor_names) {
        this.hmp = hmp;
        this.n = 1;
        motors = new DcMotor[1];
        init_motors(new String[] {motor_names});
    }

    public MotorGroup(HardwareMap hmp, String[] motor_names) {
        this.hmp = hmp;
        this.n = motor_names.length;
        motors = new DcMotor[motor_names.length];
        init_motors(motor_names);
    }

    private void init_motors(String[] motor_names) {
        for (int i = 0; i < this.n; i++) {
            motors[i] = hmp.get(DcMotor.class, motor_names[i]);
            assert motors[i] != null : "Could not find " + motor_names[i];
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    // TeleOp Control Functions

    public void setPower(double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    // Auto or Drive-By-Distance Functions
    public double[] getEncoderValues() {
        double[] vals = new double[n];
        for (int i = 0; i < n; i ++) {
            vals[i] = motors[i].getCurrentPosition();
        }
        return vals;
    }

    public void reset_encoders() {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

}
