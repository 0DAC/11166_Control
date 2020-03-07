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

    boolean IS_IN_BOT = false;
    boolean IS_OUT_BOT = false;
    boolean IS_PLACED = true;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);

        bot.h_extend_full();
        bot.pause(2000);
        bot.turnerin();
        bot.pause(1000);
        bot.h_retract();

        waitForStart();
        while (opModeIsActive()) {
            // driver gamepad
            if (gamepad1.dpad_right) bot.power_drive(Math.PI*3/2, 0.4, 0);
            else if (gamepad1.dpad_left) bot.power_drive(Math.PI/2, 0.4, 0);
            else if (gamepad1.dpad_up) bot.power_drive(0, 0.4, 0);
            else if (gamepad1.dpad_down) bot.power_drive(Math.PI, 0.4, 0);
            else bot.xbox_drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.y && IS_IN_BOT && !IS_OUT_BOT && !IS_PLACED) {
                IS_IN_BOT = false;
                IS_OUT_BOT = true;
                IS_PLACED = false;
                bot.toggle_speed(.3);
                bot.h_extend_full();
                sleep(1200);
                bot.turnerout();
                sleep(500);
                bot.vlifttolevel();
                bot.h_extend();
            }
            if (gamepad1.a && !gamepad1.start && !IS_IN_BOT && IS_OUT_BOT && !IS_PLACED) {
                IS_IN_BOT = false;
                IS_OUT_BOT = false;
                IS_PLACED = true;
                bot.toggle_speed(1);
                bot.drop_stone();
                bot.vdisengagebylevel();
                bot.raise_foundations();
                sleep(400);
                bot.intake();
                bot.servointake();
                bot.drive_forward(.75,35);
                bot.stop_intake();
                sleep(600);
                bot.vretractlift();
                bot.h_extend_full();
                sleep(200);
                bot.turnerin();
                sleep(800);
                bot.h_engage();
            }
            if (gamepad1.b && !gamepad1.start && !IS_IN_BOT && !IS_OUT_BOT && IS_PLACED) {
                bot.toggle_speed(.7);
                IS_IN_BOT = true;
                IS_OUT_BOT = false;
                IS_PLACED = false;
                bot.updateheight(1);
                bot.h_engage();
                bot.vraise_lift_by_ticks(140);
                bot.servointake();
                sleep(1500);
                bot.stop_intake();
                bot.vraise_lift_by_ticks(110);
                sleep(200);
                bot.grab_stone();
            }
            else {
                bot.vhold();
            }

            if (gamepad1.x) {
                bot.toggle_foundation();
                sleep(600);
            }

            if (gamepad1.start) {
                bot.h_extend();
                sleep(400);
                bot.turnerout();
                sleep(400);
                bot.h_grabber_pos();
            }

            if (gamepad1.back) {
                bot.toggle_speed(1);
            }

            if (gamepad1.left_bumper) {
                bot.updateheight(1);
                sleep(400);
            }
            else if (gamepad1.right_bumper) {
                bot.updateheight(-1);
                sleep(400);
            }

            if (gamepad1.left_trigger != 0) {
                bot.vdisengagebylevel();
                sleep(1000);
            } else if (gamepad1.right_trigger != 0) {
                bot.vslack();
            }

            // secondary gamepad
            if (gamepad2.left_trigger != 0) bot.intake();
            else if (gamepad2.right_trigger != 0) bot.spit();
            else bot.stop_intake();

            //deploy Capstone
//           if (gamepad2.x){
//               bot.h_extend();
//               sleep(600);
//           }
//           if (gamepad2.y) {
//               bot.t_capstone_pos();
//               sleep(600);
//           }
//           if (gamepad2.a && !gamepad2.start) {
//               bot.place_capstone();
//               sleep(600);
//           }
//           if (gamepad2.b && !gamepad2.start) {
//               bot.h_capstone_pos();
//               sleep(600);
//           }
//           if (gamepad2.right_bumper) bot.grab_n_retract();
//           if (gamepad2.left_bumper) bot.extend_n_grab();
            bot.print_servo_vals(telemetry);
            telemetry.update();
        }
    }
}

