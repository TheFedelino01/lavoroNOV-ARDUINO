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
public class periferiche {
    private List<periferica> periferiche;
    
    public periferiche(){
        periferiche = new ArrayList<periferica>();
    }
    
    public void addRilevazione(String periferica, String dato){
        periferica per;
        boolean aggiunto=false;
        int i=0;
        
        //Aggiunge la rilevazione alla periferica
        while(i<periferiche.size() && !aggiunto){
            per=periferiche.get(i);
            
            if(per.getNome().equals(periferica)){
                per.addRilevazione(dato);
                aggiunto=true;
            }
            
            i++;
        }
        if(aggiunto==false){
            periferiche.add(new periferica(periferica));//Creo il gruppo periferica
            periferiche.get(periferiche.size()-1).addRilevazione(dato); //aggiungo il dato al gruppo appena creato
        }
    }

    public String getLastRilevazione(String periferica) {
        periferica per;
        int i=0;
        
        //Prendo l'ultimo dato salvato nella periferica
        while(i<periferiche.size()){
            per=periferiche.get(i);
            
            if(per.getNome().equals(periferica))
                return per.getLastValue();
            
            i++;
        }
        
        return null;
    }
        
}
