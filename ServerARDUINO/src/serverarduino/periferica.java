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
public class periferica {
    private String nomePeriferica;
    private List<String> valori;
    
    public periferica(){
        nomePeriferica="";
        valori=new ArrayList<String>();
    }
    
    public periferica(String nomePeriferica){
        this.nomePeriferica=nomePeriferica;
        valori=new ArrayList<String>();
    }

    public void addRilevazione(String dato){
        valori.add(dato);
    }
    
    public String getNome() {
        return nomePeriferica;
    }

    public String getLastValue() {
        return valori.get(valori.size()-1);
    }

}
