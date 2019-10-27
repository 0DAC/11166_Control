package org.firstinspires.ftc.teamcode;

/**
 * Contains system configuration variables for the robot
 */
public class SystemConfig {

    public String[] left_motors = new String[] {"left_1", "left_2"};
    public String[] right_motors = new String[] {"right_1", "right_2"};
    public String[] left_intake = new String[] {"intake_1"}; // range is 120-1000
    public String[] right_intake = new String[] {"intake_2"}; // range is 119-1264

    public String left_foundation_servo = "left_foundation";
    public String right_foundation_servo = "right_foundation";
    public String gripper_servo = "stone_gripper";
    public String turner_servo = "gripper_turner";

}
