package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Funciones {
    Checks check = new Checks();

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
                if (check.esTD(s) != null) {
                    lineaSin+=check.esTD(s);
                    //this.getAnalisisLex(this.buscarTD(s));
                }else if (check.esRW(s) != null) {
                    lineaSin+=check.esRW(s);
                    //this.getAnalisisLex(this.buscarTD(s));
                }else if (s.equals("cad")){
                    lineaSin+="cad ";
                }else if (s.equals("carac")){
                    lineaSin+="carac ";
                } else if (check.esID(s) != null) {
                    lineaSin+=check.esID(s);
                    //this.getAnalisisLex(this.esID(s));
                } else if (check.esNum(s) != null) {
                    lineaSin+=check.esNum(s);
                    //this.getAnalisisLex(this.esNum(s));
                } else if (check.esSimb(s) != null) {
                    lineaSin+=check.esSimb(s);
                    //this.getAnalisisLex(this.esSimb(s));
                }
            }
            System.out.println(lineaSin);
            this.getAnalisisSin(lineaSin, i+1);
        }
        System.out.println("Analisis terminado");
    }

    public void getAnalisisSin(String line, int nLine){
        Diccionarios table= new Diccionarios();
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



    /*
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
    }*/

}
