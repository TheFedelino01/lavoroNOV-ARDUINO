package peerarduino;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class DaArduone {

    private static int port=0;
    private static SerialPort serialPort;
    

    public static String getFromArduone(){

        try {       
            open(port);
            byte size = serialPort.readBytes(1)[0];
            return serialPort.readString(size);
        }catch (NullPointerException e) {
            return "Devi prima fare setCom";
        } catch (SerialPortException ex) {
            System.exit(0);
            return ex.getMessage();
        }
    }
    
    public static void accendiLed(){

        try {
            open(port);
            serialPort.writeString("ACCENDI");
            
        } catch (SerialPortException ex) {
            System.exit(0);
            Logger.getLogger(DaArduone.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void open(int com) {
        try {
            if(!serialPort.isOpened())
                serialPort.openPort();      
            serialPort.setParams(9600, 8, 1, 0);
        } catch (Exception e) {         
            System.out.println(e.getMessage());
        }
    }
    
    public static void setPort(int com){
        serialPort = new SerialPort("COM" + com);
        port=com;
    }
}
