package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class RobotControl {
    SystemConfig sys = new SystemConfig();
    public MotorGroup top_left, top_right, bot_left, bot_right;
    public MotorGroup left_intake, right_intake;
    public Servo claws;

    private double MAX_SPEED = 5;

    public RobotControl(HardwareMap hmp) {
        top_left = new MotorGroup(hmp, sys.top_left);
        top_right = new MotorGroup(hmp, sys.top_right);
        bot_left = new MotorGroup(hmp, sys.bot_left);
        bot_right = new MotorGroup(hmp, sys.bot_right);
    }

    /**
     * Move robot by distance
     * @param angle
     * @param percent_rotation
     * @param distance
     */
    public void strafe_distance(double angle, double percent_rotation, double distance) {

    }

    /**
     * Mechanum strafing
     * For linear motion i.e. NSEW movement set angle to 0
     * Note: motion is defined by the polar vector form (r, theta)
     * @param angle: radian angle rotation (theta component)
     * @param percent_rotation: percent max speed angular rotation
     * @param percent_power: percent max speed vector magnitude (r component)
     */
    public void strafe(double angle, double percent_rotation, double percent_power) {
        double r = percent_power*MAX_SPEED;
        double robotAngle = angle+Math.PI/4;
        double rightX = percent_rotation*MAX_SPEED; // TODO: make sure this isn't too much

        drive(new MechVector(
                limit_scale(Math.cos(robotAngle), r, -1, 1)-rightX,
                limit_scale(Math.sin(robotAngle), r, -1, 1)+rightX,
                limit_scale(Math.sin(robotAngle), r, -1, 1)-rightX,
                limit_scale(Math.cos(robotAngle), r, -1, 1)+rightX
        ));
    }

    public void drive(MechVector powers) {
        top_left.setPower(powers.front_left);
        top_right.setPower(powers.front_right);
        bot_left.setPower(powers.back_left);
        bot_right.setPower(powers.back_right);
    }

    /**
     * Failsafe function for N-S drive (in case the mechanum fails)
     * @param direction: 1 for forward, -1 for backwards
     * @param percent_power: percent full speed
     */
    public void linear_drive(int direction, double percent_power) {
        top_left .setPower(direction*percent_power*MAX_SPEED);
        top_right.setPower(-direction*percent_power*MAX_SPEED);
        bot_left .setPower(direction*percent_power*MAX_SPEED);
        bot_right.setPower(-direction*percent_power*MAX_SPEED);
    }

    /**
     * Full stop function
     */
    public void stop_drive() {
        top_left .setPower(0);
        top_right.setPower(0);
        bot_left .setPower(0);
        bot_right.setPower(0);
    }

    // Convinience math functions

    /**
     * OutOfBounds Check
     * @param val: ditto
     * @param lower: ditto
     * @param upper: ditto
     * @return
     */
    public double limit_val(double val, double lower, double upper) { return Range.clip(val, lower, upper); }

     /**
     * Scale a value within a certain bound
     * @param val: ditto
     * @param factor: ditto
     * @param upper: ditto
     * @param lower: ditto
     * @return
     */
    public double limit_scale(double val, double factor, double upper, double lower) {
        return limit_val(val*factor, upper, lower);
    }
}
