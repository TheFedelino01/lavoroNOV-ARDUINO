/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverarduino;
/**
 *
 * @author saccani_federico
 */
public class ServerARDUINO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int portaAscolto=3333;
        int portaArduino=5555;
        String ipArduino="172.16.102.62";
        thSocket thRicevi = new thSocket(portaAscolto,portaArduino,ipArduino);
        thRicevi.run();
    }
    
}
