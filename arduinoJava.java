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
public class arduinoJava extends JArduino {

    public arduinoJava(String port) {
        super(port);
    }

    protected void setup() {
        // initialize the digital pin as an output.
        // Pin 13 has an LED connected on most Arduino boards:
        pinMode(DigitalPin.PIN_10, PinMode.OUTPUT);
        digitalWrite(DigitalPin.PIN_10, DigitalState.HIGH);
    }

    protected void loop() {
    }

    public static void main(String[] args) {
        String serialPort;
        if (args.length == 1) {
            serialPort = args[0];
        } else {
            serialPort = Serial4JArduino.selectSerialPort();//Serial chooser interface
        }
        JArduino arduino = new arduinoJava(serialPort);
        arduino.runArduinoProcess();
    }
}
