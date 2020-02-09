package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

    public class MainParkingAuto {
        Robot bot;
    Side side;
    boolean park_on_wall;

    public MainParkingAuto(Robot bot, Side side, boolean wall_park) {
        this.side = side;
        this.bot = bot;
        this.park_on_wall = wall_park;
    }

    public void run() {
        bot.raise_foundations();
        if (!park_on_wall) bot.drive_forward(0.5, 20);
        bot.pause(2000);
        bot.strafe_right(0.5, side.coeff*15);
    }
}
