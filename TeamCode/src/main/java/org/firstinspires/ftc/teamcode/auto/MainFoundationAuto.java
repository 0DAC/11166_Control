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

        if (side == Side.RIGHT) bot.strafe_left(0.75, 40);
        else bot.strafe_right(0.75, 40);

        bot.drive_forward(1.0, 20);

        if (side == Side.RIGHT) bot.turn(1.0, 110);
        else bot.turn(1.0, -110);

        // park near the wall
        if (side == Side.RIGHT) bot.strafe_right(1.0, 40);
        else bot.strafe_left(1.0, 40);
        bot.drive_backward(1.0, 40);

        bot.raise_foundations();

        bot.drive_forward(1.0, 100);
    }
}
