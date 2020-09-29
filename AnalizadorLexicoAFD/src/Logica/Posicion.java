
package Logica;

public class Posicion {
    // atributos de la clase privados
    private boolean cumple;
    private int posicionInicial;
    private int posicionFinal;
// constructor de la clase 
    public Posicion(boolean cumple, int posicionInicial, int posicionFinal) {
        this.cumple = cumple;
        this.posicionInicial = posicionInicial;
        this.posicionFinal = posicionFinal;
    }
// atributo 
    public boolean isCumple() {
        return cumple;
    }
// atributo 
    public void setCumple(boolean cumple) {
        this.cumple = cumple;
    }// atributo 

    public int getPosicionInicial() {
        return posicionInicial;
    }// atributo 

    public void setPosicionInicial(int posicionInicial) {
        this.posicionInicial = posicionInicial;
    }
// atributo 
    public int getPosicionFinal() {
        return posicionFinal;
    }
// atributo 
    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }
    
    
}
