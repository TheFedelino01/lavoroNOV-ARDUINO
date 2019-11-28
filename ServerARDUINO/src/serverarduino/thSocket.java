/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverarduino;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author saccani_federico
 * GESTISCE INVIO E RICEZIONE
 */
public class thSocket extends Thread {

    private socketUDP socket;
    private salvataggio salvataggio;
    private int portaArduino;
    private String ipArduino;
    
    public thSocket(int portaAscolto, int portaArduino, String ipArduino) {
        socket= new socketUDP(portaAscolto);
        this.portaArduino=portaArduino;
        this.ipArduino=ipArduino;
        salvataggio = new salvataggio();
        
        //socket.setTimeOut(2000);
    }

    //Riceve:
        //DATO;PERIFIRICA_ARDUINO;DATO_RILEVATO  ---> SI SALVA IN LOCALE IL VALORE
        //DIMMELO;PERIFERICA_ARDUINO  ---> PRENDE IL DATO LOCALE 
        //ACCENDI;PERIFERICA_ARDUINO  ---> Accendo periferica sull'arduino
    @Override
    public void run() {
        while (true) {
            cmdRicevuto comandoComplesso = socket.receive();
            
            
            if (comandoComplesso != null) {
                String comandoTxt = comandoComplesso.getComando();
                
                String[] splitted = comandoTxt.split(";");
                String cmd = splitted[0];
                
                if (cmd.equals("DATO")) {
                    socket.inviaACK(comandoComplesso.getPorta(), comandoComplesso.getIP());//invio ACK
                    
                    String nomePeriferica = splitted[1];
                    String dato = splitted[2];
                    
                    salvataggio.addRilevazione(nomePeriferica, dato);
                    
                    //System.out.println("Ricevuta rilevazione: "+nomePeriferica+" dato: "+dato);
                    
                } else if (cmd.equals("DIMMELO")) {
                    String nomePeriferica = splitted[1];
                    String valore = salvataggio.getLastRilevazione(nomePeriferica);
                    
                    //Rispondo al client che mi ha fatto la richiesta inviandogli il valore richiesto
                    socket.send(valore, comandoComplesso.getPorta(), comandoComplesso.getIP());

                }else if(cmd.equals("ACCENDI")){
                    String ipClient=comandoComplesso.getIP();
                    int portaClient = comandoComplesso.getPorta();
                    
                    //Dico a Arduino di accendere il led
                    cmdRicevuto cmdComplesso = socket.send(comandoTxt, portaArduino, ipArduino);
                    
                    if(cmdComplesso!=null)
                        socket.send("STATUS-ON", portaClient, ipClient);//Dico che l'ho acceso
                }
            }
        }
    }
}
