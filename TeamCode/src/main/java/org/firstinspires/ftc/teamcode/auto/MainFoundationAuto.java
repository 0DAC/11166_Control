package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

public class MainFoundationAuto {
    Robot bot;
    Side side;

    public MainFoundationAuto(Robot bot, Side side) {
        this.side = side;
        this.bot = bot;
        bot.raise_foundations();
    }

    public void run() {
        //bot.pause(13000);
        bot.drive_backward(0.5, 50);
        if (side == Side.RIGHT) bot.strafe_right(0.75, 20);
        else bot.strafe_left(0.75, 20);
        bot.drive_backward(1.0, 10);
        bot.lower_foundations();
        bot.pause(500);

        if (side == Side.RIGHT) bot.strafe_left(0.75, 40);
        else bot.strafe_right(0.75, 40);

        bot.drive_forward(1.0, 20);

        if (side == Side.RIGHT) bot.turn(.6, 95); //old value for speed turn = 1.0
        else bot.turn(.6, -95);

        // park near the wall
        if (side == Side.RIGHT) bot.strafe_right(0.75, 25); //old value for distance was 40
        else bot.strafe_left(0.75, 25);
        bot.drive_backward(1.0, 40);

        bot.raise_foundations();

        if (side == Side.RIGHT) bot.strafe_left(0.75, 19); //old value for distance was 40
        else bot.strafe_right(0.75, 19);

        bot.drive_forward(1.0, 80);
    }
}
