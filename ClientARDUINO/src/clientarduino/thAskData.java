/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientarduino;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author saccani_federico GESTISCE INVIO E RICEZIONE
 */
public class thAskData extends Thread {

    private socketUDP socket;

    private int portaInvio;
    private String ipServer;
    private JTextArea outputRis;
    private JLabel statusOnline;
    
    public thAskData(socketUDP socket,int portaInvio, String ipServer, JTextArea outputRis,JLabel statusOnline) {
        this.socket=socket;
        this.ipServer = ipServer;
        this.outputRis=outputRis;
        this.statusOnline=statusOnline;
        //socket.setTimeOut(2000);
        
        statusOnline.setText("PENDING...");
    }

    //INVIA LA RICHIESTA PER OTTENERE IL DATO DEL POTENZIOMETRO
    @Override
    public void run() {
        while (true) {
            cmdRicevuto cmdComplesso = socket.send("DIMMELO;POT1", 3333, ipServer);
            socket.inviaACK(portaInvio, ipServer);

            if (cmdComplesso != null) {
                outputRis.append(cmdComplesso.getComando());
                statusOnline.setText("ONLINE");
            } else {
                statusOnline.setText("OFFLINE");
            }
        }
    }
}
