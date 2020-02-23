package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Robot;
import org.firstinspires.ftc.teamcode.bot.components.VuforiaStuff;

@Autonomous(name="CircuitBreaker Vision")
public class MainVisionAuto extends LinearOpMode {
    Robot bot;
    VuforiaStuff vu;
    VuforiaStuff.skystonePos pos;
    int stoneDiff;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        vu = new VuforiaStuff(hardwareMap, telemetry);
        waitForStart();
        pos = vu.vuforiascan(false, false);
        // liftSystem.hLift.setPower(-.3);
        switch (pos) {
            case LEFT:
                //driveToPoint(-12, 37, 0, 9);
                bot.strafe_right(0.25, 40);
                stoneDiff = 0;
                break;
            case CENTER:
                //driveToPoint(2, 42, 0, 9);
                bot.drive_backward(0.25, 40);
                stoneDiff = 16;
                break;
            case RIGHT:
                //driveToPoint(10, 9, 0, 9);
                //driveToPoint(0, 33, 0, 9);
                //driveToPoint2(10, 42, 0, 9, 4);
                bot.strafe_left(0.25, 40);
                stoneDiff = 20;
                break;
        }
    }
}
