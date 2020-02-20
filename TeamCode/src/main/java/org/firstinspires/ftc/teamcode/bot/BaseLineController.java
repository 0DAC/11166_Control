package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

/**
 * Created by Michael on 1/3/2018.
 */

@TeleOp(name = "Xbox TeleOp", group = "control")

public class BaseLineController extends LinearOpMode {
    Robot bot;

    double liftlevel = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            // driver gamepad
            if (gamepad1.dpad_right) bot.power_drive(Math.PI*3/2, 0.4, 0);
            else if (gamepad1.dpad_left) bot.power_drive(Math.PI/2, 0.4, 0);
            else if (gamepad1.dpad_up) bot.power_drive(0, 0.4, 0);
            else if (gamepad1.dpad_down) bot.power_drive(Math.PI, 0.4, 0);
            else bot.xbox_drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.y) {
                bot.grab_stone();
                sleep(400);
                bot.h_extend_full();
                sleep(1000);
                bot.turnerout();
                sleep(400);
                bot.h_extend();
                sleep(400);
                bot.vlifttolevel(liftlevel, 1);
                sleep(600);
            }
            else if (gamepad1.a && !gamepad1.start) {
                bot.drop_stone();
                bot.vlifttolevel(liftlevel+.5, 1);
                sleep(1500);
                bot.vlifttolevel(.5, .7);
                bot.turnerin();
                sleep(600);
                bot.vlifttolevel(0, .7);
                bot.h_retract();
            }
            else bot.vhold();

            if (gamepad1.x) {
                bot.toggle_foundation();
                sleep(600);
            }

            if (gamepad1.b && !gamepad1.start) {
                bot.vgroundstonelevel();
                sleep(500);
                bot.servointake();
                sleep(1500);
                bot.stop_intake();
                bot.vnudgedown();
                bot.grab_stone();
            }

            if (gamepad1.start) {
                bot.h_extend();
                sleep(400);
                bot.turnerout();
                sleep(400);
                bot.h_grabber_pos();
            }

            if (gamepad1.back) {
                bot.vgroundstonelevel();
                sleep(600);
            }

            if (gamepad1.left_bumper) {
                if (liftlevel < 10) {
                    liftlevel += 1;
                    telemetry.addData("Lift Level", liftlevel);
                }
                sleep(300);
            }
            else if (gamepad1.right_bumper) {
                if (liftlevel > 0) {
                    liftlevel -= 1;
                    telemetry.addData("Lift Level", liftlevel);
                }
                sleep(300);
            }

            // comment out while using PID tuner
            // raise lift
            if (gamepad1.left_trigger != 0) {
                bot.vglideup();
            }
            else if (gamepad1.right_trigger != 0) {
                bot.vglidedown();
            }
            else bot.vhold();

           // secondary gamepad
           if (gamepad2.left_trigger != 0) bot.intake();
           else if (gamepad2.right_trigger != 0) bot.spit();
           else bot.stop_intake();

           //deploy Capstone
           if (gamepad2.x){
               bot.h_extend();
               sleep(600);
           }
           if (gamepad2.y) {
               bot.t_capstone_pos();
               sleep(600);
           }
           if (gamepad2.a && !gamepad2.start) {
               bot.place_capstone();
               sleep(600);
           }
           if (gamepad2.b && !gamepad2.start) {
               bot.h_capstone_pos();
               sleep(600);
           }
//           if (gamepad2.right_bumper) bot.grab_n_retract();
//           if (gamepad2.left_bumper) bot.extend_n_grab();
           bot.print_servo_vals(telemetry);
           telemetry.update();

        }
    }
}

