
package Automata;

public class ModeloToken {
    // atributos privados de la clase
    private String nombreToken;
    private String lexema;
    private int fila;
    private int columna;
    private int veces;
// este es el construcotr
    public ModeloToken(String nombreToken, String lexema, int fila, int columna) {
        this.nombreToken = nombreToken;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
    }
// sobrecarga del constructor
    public ModeloToken(String lexema, int fila, int columna) {
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
    }
    
// sobrecarga del constructor
    public ModeloToken(String lexema, String token){
        this.lexema=lexema;
        this.nombreToken=token;
        this.veces=1;
    }
    
    
//getter del atributo
    public String getNombreToken() {
        return nombreToken;
    }
//setter del atributo
    public void setNombreToken(String nombreToken) {
        this.nombreToken = nombreToken;
    }
//getter del atributo
    public String getLexema() {
        return lexema;
    }
//setter del atributo
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
//getter del atributo
    public int getFila() {
        return fila;
    }
//setter del atributo
    public void setFila(int fila) {
        this.fila = fila;
    }
//getter del atributo
    public int getColumna() {
        return columna;
    }
//setter del atributo
    public void setColumna(int columna) {
        this.columna = columna;
    }
//getter del atributo
    public int getVeces() {
        return veces;
    }
//setter del atributo
    public void setVeces(int veces) {
        this.veces = veces;
    }
    
    
}
