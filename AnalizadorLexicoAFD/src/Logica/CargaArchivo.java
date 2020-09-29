
package Logica;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CargaArchivo {
// atributos privados de la clase
    private File archivo;
    private String lineaCodigo;
    // carga el archivo
    public CargaArchivo(File archivo) {
        this.archivo=archivo;
    }// constructor de la clase
        public void append(String s,JEditorPane area){
            // ya que el editor pane no tiene append se lo cree
           String aux = area.getText();
           aux += s;// solo agrega el texto al final del archivo
           area.setText(aux);
    }
    // lee el archivo 
    public void leerArchivo(JTextArea area){
        area.setText("");// reinicia el text area
        try{// abre la via para leer el archivo
            FileReader archivo1 = new FileReader(archivo);
            BufferedReader archivo= new BufferedReader(archivo1);
            String auxiliar = archivo.readLine();// lee la linea
            this.lineaCodigo= auxiliar;// agarra la linea de codigo
            while(auxiliar !=null){// añade linea por linea
                area.append(auxiliar);
                area.append("\n");// agrega salto de linea
                auxiliar=archivo.readLine();
            }
        } catch(Exception e){// hubo problema
            JOptionPane.showMessageDialog(null,"Hubo un problema al analizar el archivo");
        }
    }
    // numera el text area
    public void numerar(JTextArea area, JTextArea numeracion){
        numeracion.setText("");
        area.setCaretPosition(0);// agarra la posicion del carrito al inico
        int columnas=area.getLineCount();// lee cuantas lineas tiene el text area
        System.out.println(columnas);
        for(int i=0;i<columnas;i++){// y va colocando confore aparezcan
            numeracion.append(Integer.toString(i+1));
            numeracion.append("\n");// agrega salto de linea
        }
        numeracion.setCaretPosition(0);
        numeracion.setAutoscrolls(false);
        
    }
}
   
