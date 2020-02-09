package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.android.dx.command.Main;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

@Autonomous(name="Strafe Right Park")
public class StrafeRightPark extends LinearOpMode {
    Robot bot;
    MainParkingAuto prog;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);
        prog = new MainParkingAuto(bot, Side.RIGHT, true);
        waitForStart();
        prog.run();
    }
}
