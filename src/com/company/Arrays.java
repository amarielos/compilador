package com.company;

import java.util.ArrayList;

public class Arrays {
    public ArrayList<String> getTableTipoDato(){
        ArrayList<String> tdArray = new ArrayList();
        tdArray.add("int");
        tdArray.add("double");
        tdArray.add("String");
        tdArray.add("char");
        tdArray.add("float");
        return tdArray;
    }
    public ArrayList<String> getTableRW(){
        ArrayList<String> rwArray = new ArrayList();
        rwArray.add("if");

        return rwArray;
    }
    public ArrayList<String> getTableSimb(){
        ArrayList<String> simbArray =new ArrayList();
        simbArray.add("+ suma");
        simbArray.add("- dif");
        simbArray.add("* multi");
        simbArray.add("/ div");
        simbArray.add("= asig");
        simbArray.add("; fin");
        simbArray.add(", sep");
        simbArray.add("( parAbierto");
        simbArray.add(") parCerrado");
        simbArray.add("| or");
        return simbArray;
    }
    public ArrayList<String> getTableRules(){
        ArrayList<String> rulesArray = new ArrayList();
        rulesArray.add("");
        rulesArray.add(" ");
        rulesArray.add("\n");
    //int x;
        rulesArray.add("TipoDato ID fin ");
    //int x=9;
        rulesArray.add("TipoDato ID asig num fin ");
    //x=8;
        rulesArray.add("ID asig num fin ");
    //x=x+1;
        rulesArray.add("ID asig ID suma num fin ");
        rulesArray.add("ID asig ID dif num fin ");
        rulesArray.add("ID asig ID multi num fin ");
        rulesArray.add("ID asig ID div num fin ");
    //int x=x+1;
        rulesArray.add("TipoDato ID asig ID div num fin ");
        rulesArray.add("TipoDato ID asig ID suma num fin ");
        rulesArray.add("TipoDato ID asig ID dif num fin ");
        rulesArray.add("TipoDato ID asig ID multi num fin ");
    //int x = 3+1;
        rulesArray.add("TipoDato ID asig num div num fin ");
        rulesArray.add("TipoDato ID asig num suma num fin ");
        rulesArray.add("TipoDato ID asig num dif num fin ");
        rulesArray.add("TipoDato ID asig num multi num fin ");
    //x=y;
        rulesArray.add("ID asig ID fin ");
    //x=(8);
        rulesArray.add("ID asig parAbierto num parCerrado fin ");
    //int x=(x+1);
        rulesArray.add("TipoDato ID asig parAbierto ID div num parCerrado fin ");
        rulesArray.add("TipoDato ID asig parAbierto ID suma num parCerrado fin ");
        rulesArray.add("TipoDato ID asig parAbierto ID dif num parCerrado fin ");
        rulesArray.add("TipoDato ID asig parAbierto ID multi num parCerrado fin ");
    //int x=(x+y);
        rulesArray.add("TipoDato ID asig parAbierto ID div ID parCerrado fin ");
        rulesArray.add("TipoDato ID asig parAbierto ID suma ID parCerrado fin ");
        rulesArray.add("TipoDato ID asig parAbierto ID dif ID parCerrado fin ");
        rulesArray.add("TipoDato ID asig parAbierto ID multi ID parCerrado fin ");
    //String cadena = "Hola Mundo";
        rulesArray.add("TipoDato ID asig cad fin ");
    //char caracter = 'H';
        rulesArray.add("TipoDato ID asig carac fin ");

//TERCER PARCIAL----------------------------------------------------------------------------------------------
    // int x,y;
        rulesArray.add("TipoDato ID sep ID fin ");
    // int x=5,y;
        rulesArray.add("TipoDato ID asig num sep ID fin ");
    // int x,y=5;
        rulesArray.add("TipoDato ID sep ID asig num fin ");
    //if(id==id)
        rulesArray.add("RW parAbierto ID asig asig ID parCerrado ");
    //if(id==id) | if(id==num)
        rulesArray.add("RW parAbierto ID asig asig ID parCerrado or RW parAbierto ID asig asig ID parCerrado ");

        return rulesArray;
    }
}