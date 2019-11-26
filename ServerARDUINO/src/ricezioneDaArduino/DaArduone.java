package ricezioneDaArduino;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

public class DaArduone {

    private int port=0;
    private SerialPort serialPort;

    public String getFromArduone() throws InterruptedException {

        try {       
            open(port);
            return serialPort.readString();
        } catch (NullPointerException e) {
            return "Devi prima fare setCom";
        } catch (SerialPortException ex) {
            return ex.getMessage();
        }
    }
    
    public void accendiLed(){

        try {
            open(port);
            serialPort.writeString("Accendi");
            serialPort.closePort();
            
        } catch (SerialPortException ex) {
            Logger.getLogger(DaArduone.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void open(int com) {
        try {
            if(!serialPort.isOpened())
                serialPort.openPort();      
            serialPort.setParams(9600, 8, 1, 0);
        } catch (Exception e) {         
            System.out.println(e.getMessage());
        }
        

    }
    public void setPort(int com){
        serialPort = new SerialPort("COM" + com);
        port=com;
    }
}
