/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peerarduino;

import java.util.Random;
import java.util.Scanner;
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
        socketUDP s= new socketUDP(5555);
        Scanner sc = new Scanner(System.in); 
        System.out.print("Inserisci porta com: COM");
        int com = sc.nextInt();
        DaArduone.setPort(com);
        DaArduone.open(com);
        
        s.setTimeOut(2000);
        
        while(attivo){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(PeerArduino.class.getName()).log(Level.SEVERE, null, ex);
            }
            Random ran = new Random();
            s.send("DATO;POT1;"+ran.nextInt(5000), 3333, "172.16.102.63");//manda al server 
            
            cmdRicevuto cmd =s.receive(); 
            if(cmd !=null && cmd.getComando().equals("ACCENDI;LED")){   
                System.out.println("Accendo");           
                DaArduone.accendiLed();
                s.inviaACK(3333, cmd.getIP());
            }
        }
        
    }
    
}
