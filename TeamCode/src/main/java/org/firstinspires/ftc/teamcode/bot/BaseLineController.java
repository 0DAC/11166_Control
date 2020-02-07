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

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            // driver gamepad
            if (gamepad1.dpad_left) bot.power_drive(Math.PI*3/2, 0.2, 0);
            else if (gamepad1.dpad_right) bot.power_drive(Math.PI/2, 0.2, 0);
            else if (gamepad1.dpad_up) bot.power_drive(0, 0.2, 0);
            else if (gamepad1.dpad_down) bot.power_drive(Math.PI, 0.2, 0);
            else bot.xbox_drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.y) {
                bot.hextend_toggle();
                sleep(600);
            }
            if (gamepad1.a) {
                bot.toggle_turner();
                sleep(600);
            }

            if (gamepad1.x) {
                bot.toggle_foundation();
                sleep(600);
            }

            if (gamepad1.b) {
                bot.toggle_grabber();
                sleep(600);
            }

            if (gamepad1.start) {
                bot.h_grabber_pos();
                sleep(600);
            }

            if (gamepad1.back) {
                bot.lift_by_pos(50);
                sleep(600);
            }

            if (gamepad1.left_bumper) {
                bot.vfloatup(1500);
            }

            if(gamepad1.right_bumper) {
                bot.vslack(1500);
            }

            // comment out while using PID tuner
            // raise lift
            if (gamepad1.left_trigger != 0) {
                bot.vlower_lift();
            }
            else if (gamepad1.right_trigger != 0) {
                bot.vraise_lift();
            }
            else bot.vhold();

           // secondary gamepad
           if (gamepad2.left_trigger != 0) bot.intake();
           else if (gamepad2.right_trigger != 0) bot.spit();
           else bot.stop_intake();

           //deploy Capstone
           if (gamepad2.x){
               bot.h_extend();
           }
           if (gamepad2.y) {
               bot.t_capstone_pos();
           }
           if (gamepad2.a) {
               bot.h_capstone_pos();
           }
           if (gamepad2.b) {
               bot.place_capstone();
           }
           if (gamepad2.right_bumper) bot.grab_n_retract();
           if (gamepad2.left_bumper) bot.extend_n_grab();
           bot.print_servo_vals(telemetry);
           telemetry.update();

        }
    }
}

