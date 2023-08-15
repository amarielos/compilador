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

    //imprime la tabla de id's
    public void printIDArray(ArrayList<Id> arrayId) {
        for (Id i : arrayId) {
            System.out.println("id: "+i.id);
            System.out.println("tipo id: "+i.tipo_id);
            System.out.println("scope: "+i.scope);
            System.out.println("tipo valor: "+i.tipo_valor);
            if(i.tipo_valor.equals("int")){
                System.out.println("valor: "+i.valorInt);
            }else if(i.tipo_valor.equals("double")){
                System.out.println("valor: "+i.valorDouble);
            }
            System.out.println("\n");
        }
    }

    //inicio entrega 1 ---------------------------------------------------------------------------------------------------
    //busqueda de id, retorna el id encontrado
    public Id buscarId(ArrayList<Id> arrayID, String _id){
        for (Id i:arrayID) {
            if(i.id.equals(_id)){
                return i;
            }
        }
        return null;
    }

    //al cerra un contexto, elimina las variables dentro del contexto cerrado
    public ArrayList<Id> cerrarScope(ArrayList<Id> arrayID, int _scope){
        for (int i = 0; i < arrayID.size(); i++) {
            if(arrayID.get(i).scope==_scope){
                arrayID.remove(i);
                i--;
            }
        }
        return arrayID;
    }

    //verifica y retorna el estado del id: crear, modificar, no existe, ya existe
    public int crearObjeto(ArrayList<Id> arrayId, String id, String tipo_id, int scope){
            if(tipo_id!=null){
                if(buscarId(arrayId,id)!=null){
                    if (buscarId(arrayId,id).scope!=scope){
                        return 0;
                    }else{
                    return 1;}
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
    //fin entrega 1 ---------------------------------------------------------------------------------------------------

    //setea el valor al id, llama a compatibilidad y conversion implicita
    public void setValor(String id, ArrayList<Id> arrayId,String tipo_valor, int valorInt, double valorDouble){
        Id obj = buscarId(arrayId, id);
        int op=check.compatibilidad(obj.tipo_id,tipo_valor);
            if(op==1) {
                if (tipo_valor.equals("int")) {
                    obj.setValorInt(valorInt, tipo_valor);
                } else if (tipo_valor.equals("double")) {
                    obj.setValorDouble(valorDouble, tipo_valor);
                }
            }else if(op==2){
                valorInt= (int) valorDouble;
                tipo_valor="int";
                obj.setValorInt(valorInt, tipo_valor);
            }else if(op==3){
                valorDouble= valorInt;
                tipo_valor="double";
                obj.setValorDouble(valorDouble, tipo_valor);
            }
            else{
                System.out.println("Error: "+obj.tipo_id+" no es compatible con "+tipo_valor);
            }
    }

    //metodo con validaciones para obtener los tokens, añade lexema y token a un arraylist
    public void secretaria(String srcCode) throws IOException {
    //VARIABLES
        //variables para el objeto id
        int scope = 0, valorInt;
        String id, tipo_id, tipo_valor, id2;
        Id ID2;
        double valorDouble;
        //Arraylist de objetos id
        ArrayList<Id> arrayId = new ArrayList<>();
        String codeWOcomment, lineaSin, line;
        String[] Code_lines, lineArray;

        codeWOcomment = this.ExtractComments(srcCode);

        Code_lines = codeWOcomment.split("\n");

        //recorre el array del codigo fuente, cada elemento es una linea
        for (int i = 0; i < Code_lines.length; i++) {
            //reincio de valores con cada linea
            id = null;
            id2=null;
            tipo_id = null;
            tipo_valor=null;
            valorDouble=0.0;
            valorInt=0;

            //System.out.println("\nLínea "+(i+1));
            lineaSin = "";
            line = Code_lines[i];

            if (this.CatchString(line) != null) {
                line = this.CatchString(line);
            } else if (this.CatchChar(line) != null) {
                line = this.CatchChar(line);
            }

            lineArray = line.split(String.format(WITH_DELIMITER, "[|{} (),\t\s;=+*/-]"));

            //recorre cada elemento del array linea
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
                    if(id!=null){
                        id2=s;
                    }else{
                        id = s;
                    }
                    lineaSin += check.esID(s);
                } else if (check.esNum(s) != null) {
                    lineaSin += check.esNum(s);
                    if(check.esInt(s)){
                        valorInt= Integer.parseInt(s);
                        tipo_valor="int";
                    }else{
                        valorDouble = Double.parseDouble(s);
                        tipo_valor="double";
                    }
                } else if (check.esSimb(s) != null) {
                    if (check.esSimb(s).equals("llaveAbierta ")) {
                        scope++;
                    } else if (check.esSimb(s).equals("llaveCerrada ")) {
                       // printIDArray(arrayId);
                        arrayId=cerrarScope(arrayId,scope);
                        scope--;
                    } else {
                        lineaSin += check.esSimb(s);
                    }
                }
            }

            //validar creacion o modificacion de id
            if (id != null) {
                int op = crearObjeto(arrayId, id,tipo_id, scope);
                if(op==0){
                    Id objId = new Id(id, tipo_id, scope);
                    arrayId.add(objId);
                    if(id2!=null){
                        if(buscarId(arrayId,id2)!=null) {
                            ID2 = buscarId(arrayId, id2);
                            tipo_valor = ID2.tipo_valor;
                            valorDouble = ID2.valorDouble;
                            valorInt = ID2.valorInt;
                        }else{
                            System.out.println("Error: Variable '"+id2+"' no existe");
                        }
                    }
                    if(tipo_valor!=null){
                        this.setValor(id, arrayId, tipo_valor, valorInt, valorDouble);
                    }
                }else if(op==1){
                    System.out.println("Error "+ "en linea "+(i+1)+": variable ya ha sido definida");
                }else if(op==2){
                    if(id2!=null){
                        if(buscarId(arrayId,id2)!=null) {
                            ID2 = buscarId(arrayId, id2);
                            tipo_valor = ID2.tipo_valor;
                            valorDouble = ID2.valorDouble;
                            valorInt = ID2.valorInt;
                        }else{
                            System.out.println("Error: Variable '"+id2+"' no existe");
                        }
                    }
                    if(tipo_valor!=null){
                        this.setValor(id, arrayId, tipo_valor, valorInt, valorDouble);
                    }
                }else if(op==3){
                    System.out.println("Error "+"en linea "+(i+1)+": variable no ha sido definida");
                }
            }

            //System.out.println(lineaSin);
            this.getAnalisisSin(lineaSin, i + 1);
        }

        check.scope(scope);
        check.inicializacion(arrayId);
        System.out.println("Analisis terminado");
    }

    //compara cada linea con las reglas del analizador sintactico
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
