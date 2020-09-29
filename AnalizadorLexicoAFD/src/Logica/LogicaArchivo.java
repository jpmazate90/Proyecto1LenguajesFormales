package Logica;

import GUI.MenuPrincipal;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class LogicaArchivo {
// metodo de guardar archivo
    public File guardarArchivo(File archivo, JTextArea area) {
        try {// si el archivo existe 
            if (archivo.exists()) {
                // guarda datos de la lista
                try (FileWriter out = new FileWriter(archivo, false); BufferedWriter archivoSalida = new BufferedWriter(out);) {
                    archivoSalida.write(area.getText());
                    archivoSalida.newLine();// agarra la linea y la mete al archivo que se selecciono
                    archivoSalida.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Se guardo correctamente");
                return archivo;
            } else {// si no existe el archivo dice que no se puede
                JOptionPane.showMessageDialog(null, "No funciona");
                return null;
            }
        } catch (Exception e) {
            return guardarComoArchivo(area);
        }
    }

    // archivo guardar como 
    public File guardarComoArchivo(JTextArea area) {
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Guardar");
        guardarComo.showSaveDialog(null);// agarra los valores del text area
        File archivo = new File(guardarComo.getSelectedFile() + ".txt");// lo guarda como punto txt
        try (FileWriter out = new FileWriter(archivo, false); BufferedWriter archivoSalida = new BufferedWriter(out);) {
            archivoSalida.write(area.getText());// agarra el texto y lo escribe dentro del archivo nuevo
            archivoSalida.newLine();
            archivoSalida.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Se guardo correctamente");
        return archivo;

    }
// crea un nuevo archivo
    public void nuevoArchivo(File archivoViejo, JTextArea area, MenuPrincipal menu) {
        boolean noSeModifico = verificarArchivo(area, archivoViejo);
        boolean bandera = true;
        if (noSeModifico == false) {// pregunta si desea guardar los cambios 
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");

            switch (opcion) {
                case 0:// si dice que si se guarda correctamente
                    guardarArchivo(archivoViejo, area);
                    break;
                case 1:// si no dice que no 
                    break;
                case 2:// si lo cancela no hace nada
                    bandera = false;
                    break;
            }
        }// si la bandera es verdadera abre un nuevo menu principal
        if (bandera == true) {
            menu.setVisible(false);
            MenuPrincipal nuevoMenu = new MenuPrincipal();
            nuevoMenu.setVisible(true);
        } else {// se cancela la operacion
            JOptionPane.showMessageDialog(null, "Se cancelo la creacion de un nuevo documento");
        }

    }
// verifica si hay cambios en el archivo
    public boolean verificarArchivo(JTextArea area, File archivo) {
        String[] lineaTexto = area.getText().split("\n");
        try {// separa el texto por lineas
            FileReader archivo1 = new FileReader(archivo);
            BufferedReader archivo2 = new BufferedReader(archivo1);
            String auxiliar = archivo2.readLine();// lee una linea del archivo de texto
            boolean bandera = true;
            for (int i = 0; i < lineaTexto.length; i++) {
                String[] auxiliarEspacio=auxiliar.split(" ");// separa por espacios el vector
                String[] palabraTexto = lineaTexto[i].split(" ");
                // recorre una palabra
                for(int j=0;j<auxiliarEspacio.length;j++){// crea un vector de palabras
                        char[] vectorAuxiliar = auxiliarEspacio[j].toCharArray();
                        char[] vectorPalabra = palabraTexto[j].toCharArray();
                        for(int k=0;k<vectorAuxiliar.length;k++){// compara caracter a caracter
                            if (vectorAuxiliar[k]!=vectorPalabra[k] || palabraTexto[j].length()!=auxiliarEspacio[j].length()) {
                                bandera = false;
                                break;// cambia la bandera y corta
                            }// si la bandera ya es falsa corta
                            if(bandera==false){
                                break;
                            }
                        }
                }// lee una linea
                auxiliar = archivo2.readLine();
            }// regresa la bandera
            return bandera;
        } catch (Exception e) {
            return false;
        }
    }
    
// busca un patron en especifico
    public int buscarPatron(JTextArea area, String patron) {
        int cantidadCoincidencias=0;
        Highlighter luz = area.getHighlighter();
        luz.removeAllHighlights();
        int contador=0;
        Posicion posicion;
        char[] patronEnChars = patron.toCharArray();
        if (patronEnChars.length == 0) {// si no hay texto que evaluar dice que no se puede porque no hay nada
            JOptionPane.showMessageDialog(null, "No hay texto que evaluar por favor introduce texto");
        } else {// de lo contrario entra
            boolean bandera;// agarra el parrafo y lo deja vacio por si ya existia informacion
            String datos = area.getText();// agarra los datos que se analizaran
            String[] vectorLineas = datos.split("\n");// divide el texto en vectores por medio de saltos de linea
            for (int j = 0; j < vectorLineas.length; j++) {
                String[] vectorCadenas = vectorLineas[j].split(" ");// divide los vectores en vectores por medio de espacios
                for (int i = 0; i < vectorCadenas.length; i++) {
                    contador+=vectorCadenas[i].length();
                    cantidadCoincidencias+= validarString(vectorCadenas[i], patron,contador,area);// valida el string si es el buscado o no
                    contador++;
                }
                
            }
        }
        return cantidadCoincidencias;
    }
// valida el string que le mandan, si es igual al que se esta buscando manda la bandera que ese es

    public int validarString(String cadenaAnalizar, String cadenaSolicitada, int contador,JTextArea area) {
        int contadorInicial=0;
        int contadorFinal=0;
        int cantidadCoincidencias = 0;
        boolean bandera = true;
        boolean banderaTieneInicial = false;
// verifica si cumple aunque sea el caracter inicial
        char caracterInicial = cadenaSolicitada.charAt(0);
        boolean existePatron = true;//bandera para analizarla luego
        try{
        for (int i = 0; i < cadenaAnalizar.length(); i++) {
            bandera = true;// verifica el caracter inicial
            if (caracterInicial == cadenaAnalizar.charAt(i)) {
                contadorInicial=i;
                banderaTieneInicial = true;// corta la cadena 
                String cadenaCortada = hacerSubstring(cadenaAnalizar, i);
                // verifica la cadena cortada, es decir analiza solo la parte cortada
                for (int a = 0; a < cadenaSolicitada.length(); a++) {
                    if (cadenaSolicitada.charAt(a) != cadenaCortada.charAt(a)) {
                        bandera = false;
                        existePatron = false;
                        contadorInicial=0;
                        break;
                    } else {// si el patron va correspondiendo sigue
                        existePatron = true;
                    }// verifica que no sea null
                    if (cadenaCortada.charAt(a) == -1) {
                        bandera = false;
                        Posicion posicion = new Posicion(false, 0,0);
                        return cantidadCoincidencias;
                    }
                }// si existe el patron antes de terminar la cadena de una vez manda que si se puede
                if (existePatron == true) {
                    bandera = true;
                    contadorFinal=contadorInicial+cadenaSolicitada.length();
                        Posicion posicion = new Posicion(bandera, contadorInicial, contadorFinal);
                        cantidadCoincidencias++;
                        int auxiliar=contador-cadenaAnalizar.length()+posicion.getPosicionInicial();
                        colocarPatron(area,auxiliar,auxiliar+posicion.getPosicionFinal()-posicion.getPosicionInicial());
                }
            }
        }// retorna la bandera
        }catch(StringIndexOutOfBoundsException e){
            return cantidadCoincidencias;
        }
        // si la bandera es falsa manda que no se puede
        if (banderaTieneInicial == false) {
            Posicion posicion = new Posicion(false, 0,0);
            return cantidadCoincidencias;
        }// verifica si cumple las condiciones para mandar true
        if (bandera == true && banderaTieneInicial == true && existePatron == true) {
            return cantidadCoincidencias;
        }// regresa que no hubieron coincidencias
        Posicion posicion = new Posicion(false, 0,0);
            return cantidadCoincidencias;
    }
// coloca el highlight al patron que se esta buscando
    public void colocarPatron(JTextArea area,int posicionInicial,int posicionFinal){
        DefaultHighlighter.DefaultHighlightPainter color = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
        Highlighter h = area.getHighlighter();
        try {// agrega el patron conforme las posiciones que le mandaron
            h.addHighlight(posicionInicial,
                    posicionFinal,
                    color);// agrega el color
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    // implementa la funcionalidad de un substring
    public String hacerSubstring(String cadena,  int posicion){
        String cadenaAMandar="";// agarra el string
        char caracteres[]=cadena.toCharArray();// hace vector de caracteres el string
        for(int i=posicion;i<cadena.length();i++){
            cadenaAMandar+=caracteres[i];// añade caracter a caracter
        }//retorna el caracter
        return cadenaAMandar;
    }
}
