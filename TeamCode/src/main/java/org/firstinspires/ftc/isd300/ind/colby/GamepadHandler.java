package org.firstinspires.ftc.isd300.ind.colby;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/7/2017.
 */

public abstract class GamepadHandler {

    protected Gamepad gamepad;
    protected String id;

    private boolean last_dpad_down;
    private boolean last_dpad_left;
    private boolean last_dpad_up;
    private boolean last_dpad_right;

    private boolean last_guide;

    private boolean last_x;
    private boolean last_y;
    private boolean last_b;
    private boolean last_a;

    private boolean last_start;
    private boolean last_back;

    private boolean last_left_bumper;
    private boolean last_left_stick_button;
    private float last_left_stick_x;
    private float last_left_stick_y;
    private float last_left_trigger;
    private boolean last_right_bumper;
    private boolean last_right_stick_button;
    private float last_right_stick_x;
    private float last_right_stick_y;
    private float last_right_trigger;

    public GamepadHandler(Gamepad pad, String i) {
        this.gamepad = pad;
        this.id = i;
    }

    public void gamepadCheck() {
        if (this.gamepad.right_trigger != this.last_right_trigger) {
            this.gamepad_right_trigger(this.gamepad.right_trigger);
            this.last_right_trigger = this.gamepad.right_trigger;
        }

        if (this.gamepad.right_stick_x != this.last_right_stick_x) {
            this.gamepad_right_stick(this.gamepad.right_stick_x, this.gamepad.right_stick_y);
            this.last_right_stick_x = this.gamepad.right_stick_x;
            this.last_right_stick_y = this.gamepad.right_stick_y;
        } else if (this.gamepad.right_stick_y != this.last_right_stick_y) {
            this.gamepad_right_stick(this.gamepad.right_stick_x, this.gamepad.right_stick_y);
            this.last_right_stick_x = this.gamepad.right_stick_x;
            this.last_right_stick_y = this.gamepad.right_stick_y;
        }

        if (this.gamepad.right_bumper != this.last_right_bumper) {
            this.gamepad_right_bumper(this.gamepad.right_bumper);
            this.last_right_bumper = this.gamepad.right_bumper;
        }

        if (this.gamepad.right_stick_button != this.last_right_stick_button) {
            this.gamepad_right_stick_button(this.gamepad.right_stick_button);
            this.last_right_stick_button = this.gamepad.right_stick_button;
        }

        if (this.gamepad.left_trigger != this.last_left_trigger) {
            this.gamepad_left_trigger(this.gamepad.left_trigger);
            this.last_left_trigger = this.gamepad.left_trigger;
        }

        if (this.gamepad.left_stick_x != this.last_left_stick_x) {
            this.gamepad_left_stick(this.gamepad.left_stick_x, this.gamepad.left_stick_y);
            this.last_left_stick_x = this.gamepad.left_stick_x;
            this.last_left_stick_y = this.gamepad.left_stick_y;
        } else if (this.gamepad.left_stick_y != this.last_left_stick_y) {
            this.gamepad_left_stick(this.gamepad.left_stick_x, this.gamepad.left_stick_y);
            this.last_left_stick_x = this.gamepad.left_stick_x;
            this.last_left_stick_y = this.gamepad.left_stick_y;
        }

        if (this.gamepad.left_bumper != this.last_left_bumper) {
            this.gamepad_left_bumper(this.gamepad.left_bumper);
            this.last_left_bumper = this.gamepad.left_bumper;
        }

        if (this.gamepad.left_stick_button != this.last_left_stick_button) {
            this.gamepad_left_stick_button(this.gamepad.left_stick_button);
            this.last_left_stick_button = this.gamepad.left_stick_button;
        }

        if (this.gamepad.dpad_down != this.last_dpad_down) {
            this.gamepad_dpad_down(this.gamepad.dpad_down);
            this.last_dpad_down = this.gamepad.dpad_down;
        }

        if (this.gamepad.dpad_left != this.last_dpad_left) {
            this.gamepad_dpad_left(this.gamepad.dpad_left);
            this.last_dpad_left = this.gamepad.dpad_left;
        }

        if (this.gamepad.dpad_up != this.last_dpad_up) {
            this.gamepad_dpad_up(this.gamepad.dpad_up);
            this.last_dpad_up = this.gamepad.dpad_up;
        }

        if (this.gamepad.dpad_right != this.last_dpad_right) {
            this.gamepad_dpad_right(this.gamepad.dpad_right);
            this.last_dpad_right = this.gamepad.dpad_right;
        }

        if (this.gamepad.guide != this.last_guide) {
            this.gamepad_guide(this.gamepad.guide);
            this.last_guide = this.gamepad.guide;
        }

        if (this.gamepad.x != this.last_x) {
            this.gamepad_x(this.gamepad.x);
            this.last_x = this.gamepad.x;
        }

        if (this.gamepad.y != this.last_y) {
            this.gamepad_y(this.gamepad.y);
            this.last_y = this.gamepad.y;
        }

        if (this.gamepad.a != this.last_a) {
            this.gamepad_a(this.gamepad.a);
            this.last_a = this.gamepad.a;
        }

        if (this.gamepad.b != this.last_b) {
            this.gamepad_b(this.gamepad.b);
            this.last_b = this.gamepad.b;
        }

        if (this.gamepad.start != this.last_start) {
            this.gamepad_start(this.gamepad.start);
            this.last_start = this.gamepad.start;
        }

        if (this.gamepad.back != this.last_back) {
            this.gamepad_back(this.gamepad.back);
            this.last_back = this.gamepad.back;
        }
    }




     /* ACT ON GAMEPAD CHANGES*/

    protected abstract void gamepad_x(boolean b);
    protected abstract void gamepad_y(boolean b);
    protected abstract void gamepad_a(boolean b);
    protected abstract void gamepad_b(boolean b);
    protected abstract void gamepad_start(boolean b);
    protected abstract void gamepad_back(boolean b);
    protected abstract void gamepad_dpad_down(boolean b);
    protected abstract void gamepad_dpad_left (boolean b);
    protected abstract void gamepad_dpad_up (boolean b);
    protected abstract void gamepad_dpad_right (boolean b);
    protected abstract void gamepad_guide (boolean b);
    protected abstract void gamepad_left_bumper (boolean b);
    protected abstract void gamepad_left_stick_button (boolean b);
    protected abstract void gamepad_left_stick(float x, float y);
    protected abstract void gamepad_left_trigger (float f);
    protected abstract void gamepad_right_bumper (boolean b);
    protected abstract void gamepad_right_stick_button (boolean b);
    protected abstract void gamepad_right_stick(float x, float y);
    protected abstract void gamepad_right_trigger (float f);

    public String getId() {
        return this.id;
    }

    public Gamepad getGamepad() {
        return this.gamepad;
    }

    public boolean getLast_dpad_down() {
        return last_dpad_down;
    }

    public boolean getLast_dpad_left() {
        return last_dpad_left;
    }

    public boolean getLast_dpad_up() {
        return last_dpad_up;
    }

    public boolean getLast_dpad_right() {
        return last_dpad_right;
    }

    public boolean getLast_guide() {
        return last_guide;
    }

    public boolean getLast_x() {
        return last_x;
    }

    public boolean getLast_y() {
        return last_y;
    }

    public boolean getLast_b() {
        return last_b;
    }

    public boolean getLast_a() {
        return last_a;
    }

    public boolean getLast_start() {
        return last_start;
    }

    public boolean getLast_back() {
        return last_back;
    }

    public boolean getLast_left_bumper() {
        return last_left_bumper;
    }

    public boolean getLast_left_stick_button() {
        return last_left_stick_button;
    }

    public float getLast_left_stick_x() {
        return last_left_stick_x;
    }

    public float getLast_left_stick_y() {
        return last_left_stick_y;
    }

    public float getLast_left_trigger() {
        return last_left_trigger;
    }

    public boolean getLast_right_bumper() {
        return last_right_bumper;
    }

    public boolean getLast_right_stick_button() {
        return last_right_stick_button;
    }

    public float getLast_right_stick_x() {
        return last_right_stick_x;
    }

    public float getLast_right_stick_y() {
        return last_right_stick_y;
    }

    public float getLast_right_trigger() {
        return last_right_trigger;
    }





}
