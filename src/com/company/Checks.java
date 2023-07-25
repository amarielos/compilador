package com.company;
import java.util.ArrayList;
import java.util.Objects;

public class Checks {
    //Aqui se hacen todas las comprobaciones entre el codigo fuente y los diccionarios

    // METODO, RECIBE COMO PARÁMETRO UNA PALABRA Y VERIFICA SI ES RW
    public String esRW(String word){
        Diccionarios table = new Diccionarios();
        ArrayList<String> tableArray = table.getTableRW();
        for (String s : tableArray) {
            if (Objects.equals(word, s)) {
                return "RW ";
            }
        }return null;
    }

    // METODO, RECIBE COMO PARÁMETRO UNA PALABRA Y VERIFICA SI ES TD
    public String esTD(String word){
        Diccionarios table = new Diccionarios();
        ArrayList<String> tableArray = table.getTableTipoDato();
        for (String s : tableArray) {
            if (Objects.equals(word, s)) {
                return "TipoDato ";
            }
        }return null;
    }

    // METODO, RECIBE COMO PARÁMETRO UNA PALABRA Y VERIFICA SI ES UN ID VÁLIDO
    public String esID(String word){
        if (word.matches("^([a-zA-Z_$][a-zA-Z\\d_$]*)$")){
            return "ID ";
        }
        return null;
    }

    // METODO, RECIBE COMO PARÁMETRO UNA PALABRA Y VERIFICA SI ES UN NUMERO (int o decimal)
    public String esNum(String word){
        if (word.matches("^-?[0-9]+([.][0-9]+)?$")){
            return "num ";
        }
        return null;
    }

    //METODO, RECIBE PALABRA Y REGRESA SI ES UN SIMBOLO (1 CARACTER)
    public String esSimb(String word){
        Diccionarios table = new Diccionarios();
        ArrayList<String> simbArray = table.getTableSimb();
        String[] SimbDesc;

        for (String s : simbArray) {
            SimbDesc = s.split(" ");
            if (word.equals(SimbDesc[0])){
                return  SimbDesc[1]+" ";
            }
        }
        return null;
    }

    public String scope(int scope){
        if(scope==0){
            return "";
        }else if(scope>0){
            return "ERROR: '}' expected";
        }else if(scope<0){
            return "ERROR: '{' expected";
        }
        return "";
    }
}
