package org.firstinspires.ftc.teamcode;

/**
 * Contains system configuration variables for the robot
 */
public class SystemConfig {

    public static String[] motors = new String[] {"left_front", "left_rear", "right_front", "right_rear"};
    public static String left_intake = "intake_1"; // range is 120-1000
    public static String right_intake = "intake_2"; // range is 119-126

    public static String left_foundation_servo = "left_foundation";
    public static String right_foundation_servo = "right_foundation";

    public static double  LEFT_FOUNDATION_UP    = .5,
                          LEFT_FOUNDATION_DOWN  = .8,
                          RIGHT_FOUNDATION_UP   = .42,
                          RIGHT_FOUNDATION_DOWN = 0;
}
