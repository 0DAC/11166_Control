package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;
import org.firstinspires.ftc.teamcode.bot.components.VuforiaStuff;

@Autonomous(name="Base Auto")
public class MainVisionAuto extends LinearOpMode {
    Robot bot;
    VuforiaStuff.skystonePos pos;
    int stoneDiff;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        pos = VuforiaStuff.vuforiascan(false, false);
        // liftSystem.hLift.setPower(-.3);
        switch (pos) {
            case LEFT:
                //driveToPoint(-12, 37, 0, 9);
                stoneDiff = 0;
                break;
            case CENTER:
                //driveToPoint(2, 42, 0, 9);
                stoneDiff = 16;
                break;
            case RIGHT:
                //driveToPoint(10, 9, 0, 9);
                //driveToPoint(0, 33, 0, 9);
                //driveToPoint2(10, 42, 0, 9, 4);
                stoneDiff = 20;
                break;
        }
        waitForStart();
    }
}
