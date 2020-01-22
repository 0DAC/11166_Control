package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.components.Camera;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

import static org.firstinspires.ftc.teamcode.bot.components.Camera.skystonePos.RIGHT;

@Autonomous(name="Test Vision Move")
public class BlueVision extends LinearOpMode {
    Robot bot;
    Camera camera;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap);
        camera = new Camera(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            Camera.skystonePos pos = camera.vuforiascan(false, true);
            camera.scanForStone();
            switch (pos) {
                case RIGHT:
                    //bot.strafe_left(0.5, 40);
                    telemetry.addData("Stone Position:", "RIGHT");
                    break;
                case CENTER:
                    //bot.drive_backward(0.5, 40);
                    telemetry.addData("Stone Position:", "CENTER");
                    break;
                case LEFT:
                    telemetry.addData("Stone Position:", "LEFT");
                    //bot.strafe_right(0.5, 40);
                    break;
            }
            telemetry.update();
        }

    }
}
