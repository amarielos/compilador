package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Funciones {
    // METODO, RECIBE COMO PARÁMETRO UNA PALABRA Y VERIFICA SI ES RW
    public String buscarRW(String word){
        Arrays table = new Arrays();
        ArrayList<String> tableArray = table.getTableRW();
        for (String s : tableArray) {
            if (Objects.equals(word, s)) {
                return "RW ";
            }
        }return null;
    }

    // METODO, RECIBE COMO PARÁMETRO UNA PALABRA Y VERIFICA SI ES TD
    public String buscarTD(String word){
        Arrays table = new Arrays();
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
        Arrays table = new Arrays();
        ArrayList<String> simbArray = table.getTableSimb();
        for (String s : simbArray) {
            String[] SimbDesc = s.split(" ");
            if (word.equals(SimbDesc[0])){
                return  SimbDesc[1]+" ";
            }
        }
        return null;
    }

    //ayuda al split a mantener los delimitadores
    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public String ExtractComments(String code){
        String[] CodewoComment = code.split("(//)[A-Za-z0-9\\s]+(//)");
        String complete = "";
        for (int i = 0; i < CodewoComment.length; i++) {
            String part = CodewoComment[i];
            complete+=part;
        }
        return complete;
    }

    //extrae cadenas
    public String CatchString(String line){
        String[] lineWOcadena = line.split("(\")[,.¿?¡!;:A-Za-z0-9\\s]*(\")");
        if(lineWOcadena.length>1){
            line=lineWOcadena[0]+"cad"+lineWOcadena[1];
            return line;
        }
        return null;
    }

    //extrae los caracteres
    public String CatchChar(String line){
        String[] lineWOcadena = line.split("(')[,.¿?¡!;:A-Za-z0-9\\s]?(')");
        if(lineWOcadena.length>1){
            line=lineWOcadena[0]+"carac"+lineWOcadena[1];
            return line;
        }
        return null;
    }


    //metodo con validaciones para obtener los tokens, añade lexema y token a un arraylist
    public void getLexemas(String srcCode) throws IOException {
        String codeWOcomment = this.ExtractComments(srcCode);

        String[] Code_lines = codeWOcomment.split("\n");

        for (int i = 0; i < Code_lines.length; i++) {
            //System.out.println("\nLínea "+(i+1));
            String lineaSin = "";
            String line = Code_lines[i];

            if (this.CatchString(line)!=null){
                line = this.CatchString(line);
            }else if(this.CatchChar(line)!=null){
                line = this.CatchChar(line);
            }

            String[] lineArray = line.split(String.format(WITH_DELIMITER, "[| (),\s;=+*/-]"));
            for (String s : lineArray) {
                if (this.buscarTD(s) != null) {
                    lineaSin+=this.buscarTD(s);
                    //this.getAnalisisLex(this.buscarTD(s));
                }else if (this.buscarRW(s) != null) {
                    lineaSin+=this.buscarRW(s);
                    //this.getAnalisisLex(this.buscarTD(s));
                }else if (s.equals("cad")){
                    lineaSin+="cad ";
                }else if (s.equals("carac")){
                    lineaSin+="carac ";
                } else if (this.esID(s) != null) {
                    lineaSin+=this.esID(s);
                    //this.getAnalisisLex(this.esID(s));
                } else if (this.esNum(s) != null) {
                    lineaSin+=this.esNum(s);
                    //this.getAnalisisLex(this.esNum(s));
                } else if (this.esSimb(s) != null) {
                    lineaSin+=this.esSimb(s);
                    //this.getAnalisisLex(this.esSimb(s));
                }
            }
            //System.out.println(lineaSin);
            this.getAnalisisSin(lineaSin, i+1);
        }
    }

    public void getAnalisisSin(String line, int nLine){
        Arrays table= new Arrays();
        ArrayList<String> rules = table.getTableRules();
        boolean valido = false;
        for (String r:rules) {
            if (Objects.equals(line, r)) {
                valido=true;
                break;
            }
        }
        if(!valido){
            System.out.println("error sintáctico, Línea "+ nLine);
        }
    }

    //metodo con el arraylist del analisis lexico, cambiar atributo para retornar el arraylist
    public void getAnalisisLex(String lexTok){
        ArrayList<String> analisis = new ArrayList();
        analisis.add(lexTok);
        //this.printAnalisis(analisis);
    }

    public void printAnalisis(ArrayList<String> analisis){
        for(String s : analisis){
            System.out.print(s+" ");
        }
    }

}
