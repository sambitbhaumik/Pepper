import gnu.io.*;
import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.JArduino;
import org.sintef.jarduino.PinMode;
import org.sintef.jarduino.comm.Serial4JArduino;
/*
Blink
Turns on an LED on for one second, then off for one second, repeatedly.
This example code is in the public domain.
*/

public class Blink extends JArduino {

    public Blink(String port) {
        super(port);
    }

    @Override
    protected void setup() {
        // initialize the digital pin as an output.
        // Pin 13 has an LED connected on most Arduino boards:
        pinMode(DigitalPin.PIN_12, PinMode.OUTPUT);
    }

    @Override
    protected void loop() {
        // set the LED on
        digitalWrite(DigitalPin.PIN_12, DigitalState.HIGH);
        delay(100); // wait for a second
        // set the LED off
        digitalWrite(DigitalPin.PIN_12, DigitalState.LOW);
        delay(100); // wait for a second
    }

    public static void main(String[] args) {
        String serialPort;
        if (args.length == 1) {
            serialPort = args[0];
        } else {
            serialPort = Serial4JArduino.selectSerialPort();//Serial chooser interface
        }
        JArduino arduino = new Blink(serialPort);
        arduino.runArduinoProcess();
    }
}