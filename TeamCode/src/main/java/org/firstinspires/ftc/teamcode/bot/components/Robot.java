package org.firstinspires.ftc.teamcode.bot.components;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;
import org.firstinspires.ftc.teamcode.bot.MotorGroup;

import java.util.ArrayList;

public class Robot {
    public SystemConfig sys;
    public DogeCommander operator;

    public Intake intake;
    public ServoLatch latch;
    public DriveTrain drive;

    public Robot(DogeOpMode opmode, HardwareMap hmp) {
        sys = new SystemConfig();
        operator = new DogeCommander(opmode);

        //intake = new Intake(hmp);
        //latch = new ServoLatch(hmp);
        drive = new DriveTrain(hmp);

        //operator.registerSubsystem(intake);
        //operator.registerSubsystem(latch);
        operator.registerSubsystem(drive);
    }

    /**
     * Robot drive-by-power
     * @param angle: angle in radians. 0 is straight ahead, PI/2 is straight left
     * @param power: velocity
     */
    public void drive(double angle, double power) {
        drive.power_drive(angle, power);
    }

    public void runCommands(ArrayList<Command> commands) {
        Command[] cmds = new Command[commands.size()];
        for (int i = 0; i < cmds.length; i ++) cmds[i] = commands.get(i);
        operator.runCommandsParallel(cmds);
    }

    public void stop() {
        operator.stop();
    }
}
