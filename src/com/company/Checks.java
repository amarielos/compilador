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

    public boolean esInt(String word){
        return word.matches("^-?[0-9]+?$");
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

    public void scope(int scope){
        if(scope==0){
            System.out.println("");
        }else if(scope>0){
            System.out.println("ERROR: '}' expected");
        }else if(scope<0){
            System.out.println("ERROR: '{' expected");
        }
    }

    public int compatibilidad(String valor1, String valor2){
        if(valor1.equals("int") && valor2.equals("int")){
            return 1;
        }else if(valor1.equals("double") && valor2.equals("int")){
            return 1;
        }else if(valor1.equals("double") && valor2.equals("double")){
            return 1;
        }else if(valor1.equals("int") && valor2.equals("double")){
            return 2;
        }
            return 0;
    }

    public void inicializacion(ArrayList<Id> arrayId){
        Funciones obj = new Funciones();
        int cont=0;
        for (Id i:arrayId) {
            if(i.tipo_valor==null){
                System.out.println("Warning: variable '"+i.id+"' no ha sido inicializada");
                cont++;
            }
        }
        if(cont==0) obj.printIDArray(arrayId);
    }


}

