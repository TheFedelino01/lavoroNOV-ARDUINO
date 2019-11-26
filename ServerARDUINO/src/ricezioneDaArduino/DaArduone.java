package ricezioneDaArduino;

import jssc.SerialPort;
import jssc.SerialPortException;

public class DaArduone {

    public static SerialPort serialPort;

    public static String getFromArduone() throws InterruptedException {

        try {         
            return serialPort.readString();
        } catch (NullPointerException e) {
            return "Devi prima fare setCom";
        } catch (SerialPortException ex) {
            return ex.getMessage();
        }
    }

    public static void setCom(int com) {
        serialPort = new SerialPort("COM" + com);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
