package Automata;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
// clase tablas

public class Tablas {

    // atrigutos de la clase
    private DefaultTableModel modeloLexema;
    private DefaultTableModel modeloTokens;
// constructor de la clase

    public Tablas(DefaultTableModel modeloLexema, DefaultTableModel modeloTokens) {
        this.modeloLexema = modeloLexema;
        this.modeloTokens = modeloTokens;
    }
// getter del atributo

    public DefaultTableModel getModeloLexema() {
        return modeloLexema;
    }
// setter del atributo

    public void setModeloLexema(DefaultTableModel modeloLexema) {
        this.modeloLexema = modeloLexema;
    }

// getter del atributo
    public DefaultTableModel getModeloTokens() {
        return modeloTokens;
    }
// setter del atributo

    public void setModeloTokens(DefaultTableModel modeloTokens) {
        this.modeloTokens = modeloTokens;
    }

}
