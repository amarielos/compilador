package com.company;
//import com.jtattoo.plaf.texture.TextureLookAndFeel;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
        /*
        String word = JOptionPane.showInputDialog("Ingresa una palabra:");
        Funciones metodo1 = new Funciones();

        boolean res = metodo1.esID(word);
        System.out.println(res);

        Funciones metodo1 = new Funciones();
        metodo1.getLexemas();
*/

       // UIManager.setLookAndFeel(new TextureLookAndFeel());
        Ventana ventana = new Ventana();
        ventana.Ventanita();


    }
}
