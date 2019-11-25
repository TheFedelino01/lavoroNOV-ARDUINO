/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverarduino;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saccani_federico
 */
public class salvataggio {
    periferiche periferiche;
    
    public salvataggio(){
        periferiche = new periferiche();
        periferiche.addRilevazione("POT1","automatico");
    }
    
    public void addRilevazione(String periferica, String dato){
        periferiche.addRilevazione(periferica, dato);
    }

    public String getLastRilevazione(String periferica) {
        return periferiche.getLastRilevazione(periferica);
    }
        
}
