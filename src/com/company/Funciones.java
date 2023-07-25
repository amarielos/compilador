package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Funciones {
    Checks check = new Checks();

    //ayuda al split a mantener los delimitadores
    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    //extrae comentarios
    public String ExtractComments(String code) {
        String[] CodewoComment = code.split("(//)[A-Za-z0-9\\s]+(//)");
        String complete = "", part;
        for (int i = 0; i < CodewoComment.length; i++) {
            part = CodewoComment[i];
            complete += part;
        }
        return complete;
    }

    //extrae cadenas
    public String CatchString(String line) {
        String[] lineWOcadena = line.split("(\")[,.¿?¡!;:A-Za-z0-9\\s]*(\")");
        if (lineWOcadena.length > 1) {
            line = lineWOcadena[0] + "cad" + lineWOcadena[1];
            return line;
        }
        return null;
    }

    //extrae los caracteres
    public String CatchChar(String line) {
        String[] lineWOcadena = line.split("(')[,.¿?¡!;:A-Za-z0-9\\s]?(')");
        if (lineWOcadena.length > 1) {
            line = lineWOcadena[0] + "carac" + lineWOcadena[1];
            return line;
        }
        return null;
    }

    public void printIDArray(ArrayList<Id> arrayId) {
        for (Id i : arrayId) {
            System.out.println("id: "+i.id);
            System.out.println("tipo id: "+i.tipo_id);
            System.out.println("scope: "+i.scope);
            System.out.println("\n");
        }
    }

    public Id buscarId(ArrayList<Id> arrayID, String _id){
        for (Id i:arrayID) {
            if(i.id.equals(_id)){
                return i;
            }
        }
        return null;
    }

    public ArrayList<Id> cerrarScope(ArrayList<Id> arrayID, int _scope){
        for (int i = 0; i < arrayID.size(); i++) {
            if(arrayID.get(i).scope==_scope){
                arrayID.remove(i);
                i--;
            }
        }
        return arrayID;
    }

    public int crearObjeto(ArrayList<Id> arrayId, String id, String tipo_id){
            if(tipo_id!=null){
                if(buscarId(arrayId,id)!=null){
                    return 1;
                }else{
                    return 0;
                }
            }else{
                if(buscarId(arrayId,id)!=null){
                    return 2;
                }else{
                    return 3;
                }
            }
    }

    //metodo con validaciones para obtener los tokens, añade lexema y token a un arraylist
    public void analisis(String srcCode) throws IOException {

        //variables para el objeto id
        int scope = 0;
        String id, tipo_id;
        //Arraylist de objetos id
        ArrayList<Id> arrayId = new ArrayList();


        String codeWOcomment, lineaSin, line;
        String[] Code_lines, lineArray;

        codeWOcomment = this.ExtractComments(srcCode);

        Code_lines = codeWOcomment.split("\n");

        for (int i = 0; i < Code_lines.length; i++) {
            //reincio de valores con cada linea
            id = null;
            tipo_id = null;

            //System.out.println("\nLínea "+(i+1));
            lineaSin = "";
            line = Code_lines[i];

            if (this.CatchString(line) != null) {
                line = this.CatchString(line);
            } else if (this.CatchChar(line) != null) {
                line = this.CatchChar(line);
            }

            lineArray = line.split(String.format(WITH_DELIMITER, "[|{} (),\t\s;=+*/-]"));
            for (String s : lineArray) {
                if (check.esTD(s) != null) {
                    tipo_id = s;
                    lineaSin += check.esTD(s);

                } else if (check.esRW(s) != null) {
                    lineaSin += check.esRW(s);

                } else if (s.equals("cad")) {
                    lineaSin += "cad ";
                } else if (s.equals("carac")) {

                    lineaSin += "carac ";
                } else if (check.esID(s) != null) {
                    id = s;
                    lineaSin += check.esID(s);
                } else if (check.esNum(s) != null) {
                    lineaSin += check.esNum(s);
                } else if (check.esSimb(s) != null) {
                    if (check.esSimb(s).equals("llaveAbierta ")) {
                        scope++;
                    } else if (check.esSimb(s).equals("llaveCerrada ")) {
                        arrayId=cerrarScope(arrayId,scope);
                        scope--;
                    } else {
                        lineaSin += check.esSimb(s);
                    }

                }
            }
            if (id != null) {
                int op = crearObjeto(arrayId, id,tipo_id);
                if(op==0){
                    Id objId = new Id(id, tipo_id, scope);
                    arrayId.add(objId);
                }else if(op==1){
                    System.out.println("Error "+ "en linea "+(i+1)+": variable ya ha sido definida");
                }else if(op==2){
                    System.out.print("");
                }else if(op==3){
                    System.out.println("Error "+"en linea "+(i+1)+": variable no ha sido definida");
                }
            }
            //System.out.println(lineaSin);
            //this.getAnalisisSin(lineaSin, i + 1);
        }

        //printIDArray(arrayId);
        System.out.println(check.scope(scope));
        System.out.println("Analisis terminado");
    }

    public void getAnalisisSin(String line, int nLine) {
        Diccionarios table = new Diccionarios();
        ArrayList<String> rules = table.getTableRules();
        boolean valido = false;
        for (String r : rules) {
            if (Objects.equals(line, r)) {
                valido = true;
                break;
            }
        }
        if (!valido) {
            System.out.println("error sintáctico, Línea " + nLine);
        }
    }


}
