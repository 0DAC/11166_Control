package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.android.dx.command.Main;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Blue Side Foundation Latch ")
public class BlueFoundationLatch extends LinearOpMode {
    Robot bot;
    MainFoundationAuto prog;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        prog = new MainFoundationAuto(bot, Side.RIGHT);
        waitForStart();
        prog.run();
    }
}
