package org.firstinspires.ftc.teamcode.movement;

import java.util.*;

public class MechVector {
    public double front_left;
    public double front_right;
    public double back_left;
    public double back_right;

    public MechVector(double frontLeft, double frontRight, double backLeft, double backRight) {
        this.front_left = front_left;
        this.front_right = frontRight;
        this.back_left = backLeft;
        this.back_right = backRight;
    }

    public MechVector(double x, double y, double turnPower) {
        this.front_left = x - turnPower - y;
        this.back_left = x - turnPower + y;
        this.front_right = x + turnPower + y;
        this.back_right = x + turnPower - y;
        this.scale();
    }

    public List<Double> to_list() {
        return Arrays.asList(this.front_left, this.front_right, this.back_left, this.back_right);
    }

    // If we're somehow above one, scale back down
    private void scale() {
        List<Double> vals = this.to_list();
        double absMax = Math.max(Collections.max(vals), -Collections.min(vals));
        if (absMax > 1) {
            this.front_left /= absMax;
            this.front_right /= absMax;
            this.back_left /= absMax;
            this.back_right /= absMax;
        }
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MecanumPowers mecanumPowers = (MecanumPowers) o;
        return MathUtil.approxEquals(mecanumPowers.frontLeft, this.front_left) &&
                MathUtil.approxEquals(mecanumPowers.frontRight, this.front_right) &&
                MathUtil.approxEquals(mecanumPowers.backLeft, this.back_left) &&
                MathUtil.approxEquals(mecanumPowers.backRight, this.back_right);
    }*/


    /*
    %.1f----%.1f
    | Front |
    |       |
    |       |
    %.1f----%.1f
     */
    @Override
    public String toString() {
        return String.format(
                "\n" +
                        "(%.1f)---(%.1f)\n" +
                        "|   Front   |\n" +
                        "|           |\n" +
                        "|           |\n" +
                        "(%.1f)---(%.1f)\n"
                , front_left, front_right, back_left, back_right);
    }
}
