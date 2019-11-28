/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peerarduino;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author broch_mattia
 */
public class PeerArduino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean attivo=true;
        socketUDP s= new socketUDP(3333);
        DaArduone.setPort(3);
        
        while(attivo){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(PeerArduino.class.getName()).log(Level.SEVERE, null, ex);
            }
            s.send(DaArduone.getFromArduone(), 5555, "localhost");//manda al server 
            if(s.receive().getComando()=="ACCENDI"){
                DaArduone.accendiLed();
                //manda ack
                s.inviaACK(5555, "localhost");
            }
        }
        
    }
    
}
