package Automata;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Automata {
// atributos privados de la clase
    private int contador;
    private char[] caracteres;
    private final char SIGNO_SUMA = '+';
    private final char SIGNO_RESTA = '-';
    private final char[] NUMEROS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final char CERO = '0';
    private final char UNO = '1';
    private final char DOS = '2';
    private final char TRES = '3';
    private final char CUATRO = '4';
    private final char CINCO = '5';
    private final char SEIS = '6';
    private final char SIETE = '7';
    private final char OCHO = '8';
    private final char NUEVE = '9';
    private final char PUNTO = '.';
    private String lexemaActual = "";
    private int fila = 1;
    private int filaAuxiliar=1;
    private int columna = 1;
    private int contadorAuxiliar = 1;
// arraylist que serrvirarn luego
    private Validaciones validador = new Validaciones();
    private ArrayList<ModeloToken> modelo = new ArrayList<ModeloToken>();
    private ArrayList<ModeloToken> modeloErrores = new ArrayList<ModeloToken>();
    private DefaultListModel modeloLista;
    private DefaultTableModel modeloLexemas;
    private DefaultTableModel modeloTokens;
    // constructor de la clase
    public Automata(DefaultListModel modeloLista,DefaultTableModel modeloLexemas, DefaultTableModel modeloTokens){
        this.modeloLista=modeloLista;
        this.modeloLexemas=modeloLexemas;
        this.modeloTokens=modeloTokens;
    }
// regresa unas tablas
    public Tablas inicio(String cadena, int posicionInicioCadena) {
        char[] caracteres = cadena.toCharArray();
        contador = 0;// parte la cadena del area y lo convierte en array de chars
        this.caracteres = caracteres;// se va a e0 que es el estado inicial
        e0(caracteres, posicionInicioCadena);
        Tablas tablas = new Tablas(modeloLexemas, modeloTokens);
        return tablas;

    }
// aumenta variables de contadores
    public void aumentarVariables() {
        contador++;
        contadorAuxiliar++;
    }
// añade un caracter especifico al lexema para ir construyendo la palabra
    public void añadirCaracter(char[] caracteres, int contador) {
        lexemaActual += caracteres[contador];
        System.out.println(lexemaActual);
    }
// reinicia el lexema
    public void reiniciarLexema() {
        lexemaActual = "";
    }
// reinicia el contador auxiliar
    public void reiniciarContadorAuxiliar() {
        contadorAuxiliar = 1;
    }
// auenta la fila
    public void aumentarFila() {
        fila++;
    }
// agrega el lexema al arraylist 
    public void agregarLexema(String nombreToken, String lexema, int fila, int columna) {
        ModeloToken token = new ModeloToken(nombreToken, lexema, fila, columna-lexema.length()-1);
        modelo.add(token);
    }
// agrega un error a la lista de errores
    public void agregarError(String lexemaError, int fila, int columna) {
        ModeloToken token = new ModeloToken(lexemaError, fila, columna-lexemaError.length());
        validador.agregarError(modeloLista, token);
        
    }
// aqui es donde inicia todo el automata de aqui se va a todos los estados
    public void e0(char[] caracteres, int posiconInicioCadena) {
        if (contador < caracteres.length) {
            if (caracteres[contador] == SIGNO_SUMA || caracteres[contador] == SIGNO_RESTA) {
                hacerOperacionAumentarVariable();
                e17();// si es signo de suma o resta se va a su respectivo estado
            } else if (caracteres[contador] == CERO) {
                hacerOperacionAumentarVariable();
                e1();// si es un cero se va al estado 1
            } else if (validador.verificarSiEsNumero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();
                e2();// si es un 1-9 se va a e2
            } else if (validador.verificarE3(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// si es un caracter que no es de palabra reservada y es identificador entra
            }  else if (caracteres[contador]==validador.getB()) {
                hacerOperacionAumentarVariable();// si es una b se va a e4
                e4();
            }  else if (caracteres[contador]==validador.getC()) {
                hacerOperacionAumentarVariable();// si es una c se va a e5
                e5();
            } else if (caracteres[contador]==validador.getE()) {
                hacerOperacionAumentarVariable();// si es una e se va a e6
                e6();
            }   else if (caracteres[contador]==validador.getF()) {
                hacerOperacionAumentarVariable();// si es una f se va a e7
                e7();
            } else if (caracteres[contador]==validador.getI()) {
                hacerOperacionAumentarVariable();// si es una i se va a e8
                e8();
            } else if (caracteres[contador]==validador.getP()) {
                hacerOperacionAumentarVariable();// si es una p se va a e9
                e9();
            } else if (caracteres[contador]==validador.getR()) {
                hacerOperacionAumentarVariable();// si es una r se va a e10
                e10();
            } else if (caracteres[contador]==validador.getS()) {
                hacerOperacionAumentarVariable();// si es una s se va a e11
                e11();
            } else if (caracteres[contador]==validador.getT()) {
                hacerOperacionAumentarVariable();// si es una t se va a e12
                e12();
            } else if (caracteres[contador]==validador.getV()) {
                hacerOperacionAumentarVariable();// si es una v se va a e13
                e13();
            } else if (caracteres[contador]==validador.getW()) {
                hacerOperacionAumentarVariable();// si es una w se va a e14
                e14();
            } else if (caracteres[contador]==validador.getPUNTO() ||caracteres[contador]==validador.getCOMA()||caracteres[contador]==validador.getPUNTOCOMA()||caracteres[contador]==validador.getDOSPUNTOS()) {
                hacerOperacionAumentarVariable();// si es un signo de puntuacion se va a e15
                e15();
            }  else if (caracteres[contador]==validador.getCOMILLA()) {
                hacerOperacionAumentarVariable();// si es una comilla se va a e16
                e16();
            } else if (caracteres[contador]==validador.getMULTIPLICACION() || caracteres[contador]==validador.getMODULO()) {
                hacerOperacionAumentarVariable();// si es un asterisco o un porcentje se va a e18
                e18();
            }  else if (caracteres[contador]==validador.getDIAGONAL()) {
                hacerOperacionAumentarVariable();// si es una diagonal se va a e19
                e19();
            } else if (caracteres[contador]==validador.getPARENTESIS_ABIERTO() || caracteres[contador]==validador.getPARENTESIS_CERRADO()|| caracteres[contador]==validador.getCORCHETE_ABIERTO()|| caracteres[contador]==validador.getCORCHETE_CERRADO()|| caracteres[contador]==validador.getLLAVE_ABIERTA()|| caracteres[contador]==validador.getLLAVE_CERRADA()) {
                hacerOperacionAumentarVariable();//si es signo de agrupacion se va a e20
                e20();
            }  else if (caracteres[contador] == '\n') {
                aumentarVariables();// si es un salto de linea o un espacio solo aumenta variables los ignora
                    reiniciarContadorAuxiliar();
                    aumentarFila();
                e0(caracteres, posiconInicioCadena);
            } else if (caracteres[contador] == ' ') {
                    aumentarVariables();// si es un salto de linea o un espacio solo aumenta variables los ignora
                e0(caracteres, posiconInicioCadena);
            } else {// si no es ningunoreporta el error y sigue
                añadirCaracter(caracteres, contador);
                agregarError(lexemaActual, fila, contadorAuxiliar);
                reiniciarLexema();
                aumentarVariables();
                e0(caracteres, posiconInicioCadena);
            }

        } else {// si el contador es mas grande que el arreglo significa que termino de recorrer el texto
            validador.recuentoLexemas(modelo,modeloLexemas);// hace recuento de tokne
            validador.recuentoToken(modelo, modeloTokens);// hace recueto de lexemas
            JOptionPane.showMessageDialog(null,"Se ha terminado la evaluacion del texto");
        }

    }

    public void e1() {
        if (contador < caracteres.length) {// si es un punto seva a e21
            if (caracteres[contador] == PUNTO) {
                hacerOperacionAumentarVariable();
                e21();
            } else if (caracteres[contador] == ' ') {// si es un espacioreporta el token
                hacerOperacionesEspacio(validador.getTOKEN_NUMERO_ENTERO());
            } else if (caracteres[contador] == '\n') {// si es un salto de linea reporta el token
                hacerOperacionesSaltoLinea(validador.getTOKEN_NUMERO_ENTERO());
            } else {// si no es ninguno signfica que entro en error pero es un estado final
                hacerOperaciones(validador.getTOKEN_NUMERO_ENTERO());
            }
        } else {// si ya no hay mas que leer reporta que termino y el token
            hacerOperaciones(validador.getTOKEN_NUMERO_ENTERO());
        }
    }

    public void e2() {// si el contador es mayor ya no entra
        if (contador < caracteres.length) {
            if (caracteres[contador] == CERO) {// si es un cero se va a e22
                hacerOperacionAumentarVariable();
                e22();
            } else if (caracteres[contador] == PUNTO) {// si es un punto se va a e21
                hacerOperacionAumentarVariable();
                e21();
            } else if (caracteres[contador] == ' ') {// si es un espacio reporta que es un token entero
                hacerOperacionesEspacio(validador.getTOKEN_NUMERO_ENTERO());
            } else if (caracteres[contador] == '\n') {// si es un espacio reporta que es un token entero
                hacerOperacionesSaltoLinea(validador.getTOKEN_NUMERO_ENTERO());
            } else if (validador.verificarSiEsNumero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();
                e2();// si es un espacio reporta que es un token entero
            } else {// si es un error reporta el token
                hacerOperaciones(validador.getTOKEN_NUMERO_ENTERO());
            }
        } else {// si es un error reporta el token
            hacerOperaciones(validador.getTOKEN_NUMERO_ENTERO());
        }
    }

    public void e17() {// si el contador es mayor no entra
        if (contador < caracteres.length) {
            if (caracteres[contador] == CERO) {
                hacerOperacionAumentarVariable();
                e1();// si es un cero se va a e1
            } else if (caracteres[contador] == ' ') {// si es un espacio reporta que es un operador
                hacerOperacionesEspacio(validador.getTOKEN_OPERADOR_ARITMETICO());
            } else if (caracteres[contador] == '\n') {// igual si es salto linea
                hacerOperacionesSaltoLinea(validador.getTOKEN_OPERADOR_ARITMETICO());
            } else if (validador.verificarSiEsNumero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();
                e2();

            } else {// si hay error tambien reporta
                hacerOperaciones(validador.getTOKEN_OPERADOR_ARITMETICO());
            }
        } else {// si hay error tambien reporta
            hacerOperaciones(validador.getTOKEN_OPERADOR_ARITMETICO());
        }

    }

    public void e22() {// no entra si es mayor
        if (contador < caracteres.length) {
            if (caracteres[contador] == CERO) {// si es 0 se va a e22
                hacerOperacionAumentarVariable();
                e22();
            } else if (caracteres[contador] == PUNTO) {// si es punto se va a e21
                hacerOperacionAumentarVariable();
                e21();
            } else if (validador.verificarSiEsNumero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();// si esalgun numero de 1-9 se va a e2
                e2();
            } else if (caracteres[contador] == ' ') {// si es espacio reporta el token
                hacerOperacionesEspacio(validador.getTOKEN_NUMERO_ENTERO());
            } else if (caracteres[contador] == '\n') {// si es salto linea igual
                hacerOperacionesSaltoLinea(validador.getTOKEN_NUMERO_ENTERO());
            } else {// si es error reporta token
                hacerOperaciones(validador.getTOKEN_NUMERO_ENTERO());
            }
        } else {// si es error reporta token
            hacerOperaciones(validador.getTOKEN_NUMERO_ENTERO());
        }

    }

    public void e21() {// si es mayor no entra
        if (contador < caracteres.length) {
            if (caracteres[contador] == CERO) {// si es cero entra
                hacerOperacionAumentarVariable();
                e50();
            } else if (validador.verificarSiEsNumero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();// si es un numero de 1-9 se va a e51
                e51();
            } else if (caracteres[contador] == ' ') {// si es error dice que es error
                hacerOperacionErrorEspacio();
            } else if (caracteres[contador] == '\n') {// si es error dice que es error
                hacerOperacionErrorSaltoLinea();
            } else {// si es error dice que es error
                hacerOperacionError();
            }
        } else {// si es error dice que es error
            hacerOperacionError();
        }
    }

    public void e50() {
        if (contador < caracteres.length) {// si es mayor no etra
            if (caracteres[contador] == CERO) {// si es cero entra a e50
                hacerOperacionAumentarVariable();
                e50();
            } else if (validador.verificarSiEsNumero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();// si es 1-9 va a e51
                e51();
            } else if (caracteres[contador] == ' ') {// si es espacio reporta token
                hacerOperacionesEspacio(validador.getTOKEN_NUMERO_DECIMAL());
            } else if (caracteres[contador] == '\n') {// si es salto linea igual
                hacerOperacionesSaltoLinea(validador.getTOKEN_NUMERO_DECIMAL());
            } else {// si es error reporta token
                hacerOperaciones(validador.getTOKEN_NUMERO_DECIMAL());
            }
        } else {// reporta token
            hacerOperaciones(validador.getTOKEN_NUMERO_DECIMAL());
        }
    }

    public void e51() {
        if (contador < caracteres.length) {// valida si es numero de 0-9
            if (validador.verificarSiEsNumeroConCero(NUMEROS, caracteres, contador)) {
                hacerOperacionAumentarVariable();
                e51();
            } else if (caracteres[contador] == ' ') {// si es espacio reporta el deccimal
                hacerOperacionesEspacio(validador.getTOKEN_NUMERO_DECIMAL());
            } else if (caracteres[contador] == '\n') {// si es salto linea igual
                hacerOperacionesSaltoLinea(validador.getTOKEN_NUMERO_DECIMAL());
            } else {// reporta error
                hacerOperaciones(validador.getTOKEN_NUMERO_DECIMAL());
            }
        } else {// reporta error
            hacerOperaciones(validador.getTOKEN_NUMERO_DECIMAL());
        }
    }

    public void e3() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// si es algun caracter de identificador sigue en e3
                e3();
            } else if (caracteres[contador] == ' ') {// si es un espacio reporta el identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if (caracteres[contador] == '\n') {// igual con salto linea
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            } else {// si es error reporte
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta error
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }

    public void e4() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getO()) {// si es una o se va a e23
                hacerOperacionAumentarVariable();
                e23();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e23() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getO()) {// si es una 0 va a 52
                hacerOperacionAumentarVariable();
                e52();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e52() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getL()) {// se va con l a e77
                hacerOperacionAumentarVariable();
                e77();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e77() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e se va a e99
                hacerOperacionAumentarVariable();
                e99();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e99() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con una a se va a e116
                hacerOperacionAumentarVariable();
                e116();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e116() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getN()) {// con n se va a e127
                hacerOperacionAumentarVariable();
                e127();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e127() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {// si la cadena sigue y es acceptado en identificado
                hacerOperacionAumentarVariable();// se va a e3
                e3();
            } else if(caracteres[contador] == ' '){// reporta token boolean
                hacerOperacionesEspacio(validador.getTOKEN_BOOLEAN());
            } else if(caracteres[contador] == '\n'){// reporta token boolean
                hacerOperacionesSaltoLinea(validador.getTOKEN_BOOLEAN());
            }else{    // reporta token boolean
                hacerOperaciones(validador.getTOKEN_BOOLEAN());
            }

        } else {// reporta token boolean
            hacerOperaciones(validador.getTOKEN_BOOLEAN());
        }
    }
    
    public void e5(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con en a e24
                hacerOperacionAumentarVariable();
                e24();
            }else if (caracteres[contador]==validador.getL()) {// con l se va a e25
                hacerOperacionAumentarVariable();
                e25();
            }  else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e24() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con una s se va a e53
                hacerOperacionAumentarVariable();
                e53();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e25() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// se va con a a e54
                hacerOperacionAumentarVariable();
                e54();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e53() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e se va a e78
                hacerOperacionAumentarVariable();
                e78();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e54() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con una s se va a e79
                hacerOperacionAumentarVariable();
                e79();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e78() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {//si la cadena sigue significa
                hacerOperacionAumentarVariable();// que esta es un identificador y se va a e3
                e3();// sicumple algun caracter de identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_CASE());// reporta token case
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_CASE());// reporta token case
            }else{    
                hacerOperaciones(validador.getTOKEN_CASE());// reporta token case
            }

        } else {// reporta token case
            hacerOperaciones(validador.getTOKEN_CASE());
        }
    
    }
    public void e79() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con una s se va a e100
                hacerOperacionAumentarVariable();
                e100();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e100() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {//si la cadena sigue significa
                hacerOperacionAumentarVariable();// que esta es un identificador y se va a e3
                e3();// sicumple algun caracter de identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_CLASS());// reporta token class
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_CLASS());// reporta token class
            }else{    // reporta token class
                hacerOperaciones(validador.getTOKEN_CLASS());
            }
        } else {// reporta token class
            hacerOperaciones(validador.getTOKEN_CLASS());
        }
    }
    public void e6(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getL()) {// con una l se va a e26
                hacerOperacionAumentarVariable();
                e26();
            }else if (caracteres[contador]==validador.getX()) {
                hacerOperacionAumentarVariable();
                e27();// con una x se va a e27
            }  else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e26() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con una se se va a e55
                hacerOperacionAumentarVariable();
                e55();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e27() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getT()) {// con una t se va a ee56
                hacerOperacionAumentarVariable();
                e56();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e55() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con una e se va a e80
                hacerOperacionAumentarVariable();
                e80();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e56() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con una e se va a e81
                hacerOperacionAumentarVariable();
                e81();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e80() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// e 80 es un estado final
                e3();// si viene algun caracter de identificador se va a e3
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_ELSE());// reporta token else
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_ELSE());// reporta token else
            }else{    // reporta token else
                hacerOperaciones(validador.getTOKEN_ELSE());
            }
        } else {// reporta token else
            hacerOperaciones(validador.getTOKEN_ELSE());
        }
    }
    public void e81() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getN()) {// con una n se va a e101
                hacerOperacionAumentarVariable();
                e101();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e101() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getD()) {// con una d se va a e117
                hacerOperacionAumentarVariable();
                e117();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e117() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con una s se va a e128
                hacerOperacionAumentarVariable();
                e128();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final
    public void e128() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// e128 es un estado final si viene algo es un 
                e3();// idetificador
            } else if(caracteres[contador] == ' '){// reporta token extends
                hacerOperacionesEspacio(validador.getTOKEN_EXTENDS());
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_EXTENDS());// reporta token extends
            }else{    
                hacerOperaciones(validador.getTOKEN_EXTENDS());// reporta token extends
            }
        } else {// reporta token extends
            hacerOperaciones(validador.getTOKEN_EXTENDS());// reporta token extends
        }
    }
    public void e7(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con una a se va a e28
                hacerOperacionAumentarVariable();
                e28();
            }else if (caracteres[contador]==validador.getI()) {// con una i se va a e29
                hacerOperacionAumentarVariable();
                e29();
            }  else if (caracteres[contador]==validador.getL()) {// con una lse va a e30
                hacerOperacionAumentarVariable();
                e30();
            } else if (caracteres[contador]==validador.getO()) {// con una o se va a e31
                hacerOperacionAumentarVariable();
                e31();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e28() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getL()) {// con una l se va a e57
                hacerOperacionAumentarVariable();
                e57();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e29() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getN()) {// con una n se va a e58
                hacerOperacionAumentarVariable();
                e58();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e30() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getO()) {// se va a e59
                hacerOperacionAumentarVariable();
                e59();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e31() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getR()) {// con r se va a e60
                hacerOperacionAumentarVariable();
                e60();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e57() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con una s se va a e82
                hacerOperacionAumentarVariable();
                e82();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e58() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con una a se va a e83
                hacerOperacionAumentarVariable();
                e83();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e59() {
        if (contador < caracteres.length) {// con una a se va a e84
            if (caracteres[contador] == validador.getA()) {
                hacerOperacionAumentarVariable();
                e84();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    // estado final for
    public void e60() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_FOR());// reporta token for
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_FOR());// reporta token for
            }else{    // reporta token for
                hacerOperaciones(validador.getTOKEN_FOR());
            }
        } else {// reporta token for
            hacerOperaciones(validador.getTOKEN_FOR());
        }
    }
    public void e82() {
        if (contador < caracteres.length) {// con una e se va a e102
            if (caracteres[contador] == validador.getE()) {
                hacerOperacionAumentarVariable();
                e102();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e83() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getL()) {// con una l se va a e103
                hacerOperacionAumentarVariable();
                e103();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e84() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getT()) {// con una t a e104
                hacerOperacionAumentarVariable();
                e104();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final false
    public void e102() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_FALSE());// reporta token false
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_FALSE());// reporta token false
            }else{    // reporta token false
                hacerOperaciones(validador.getTOKEN_FALSE());
            }
        } else {// reporta token false
            hacerOperaciones(validador.getTOKEN_FALSE());
        }
    }// estado final final
    public void e103() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_FINAL());// reporte token final
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_FINAL());// reporte token final
            }else{    // reporte token final
                hacerOperaciones(validador.getTOKEN_FINAL());
            }
        } else {// reporte token final
            hacerOperaciones(validador.getTOKEN_FINAL());
        }
    }// reporta token float
    public void e104() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_FLOAT());// reporta token float
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_FLOAT());// reporta token float
            }else{    // reporta token float
                hacerOperaciones(validador.getTOKEN_FLOAT());
            }
        } else {
            hacerOperaciones(validador.getTOKEN_FLOAT());
        }
    }
    
    public void e8(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getF()) {// con una f a e32
                hacerOperacionAumentarVariable();
                e32();
            }else if (caracteres[contador]==validador.getM()) {// con m a e33
                hacerOperacionAumentarVariable();
                e33();
            }  else if (caracteres[contador]==validador.getN()) {// con n a e34
                hacerOperacionAumentarVariable();
                e34();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final if
    public void e32() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_IF());// reporta token if
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_IF());// reporta token if
            }else{    // reporta token if
                hacerOperaciones(validador.getTOKEN_IF());
            }

        } else {// reporta token if
            hacerOperaciones(validador.getTOKEN_IF());
        }
    }
    public void e33() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getP()) {// on p a e61
                hacerOperacionAumentarVariable();
                e61();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());// reporta  el token identificador
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e34() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getT()) {// con t a e62
                hacerOperacionAumentarVariable();
                e62();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e61() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getL()) {// con l a e85
                hacerOperacionAumentarVariable();
                e85();
            } else if (caracteres[contador] == validador.getO()) {// con o a e86
                hacerOperacionAumentarVariable();
                e86();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final int
    public void e62() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e87
                hacerOperacionAumentarVariable();
                e87();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta token int
                hacerOperacionesEspacio(validador.getTOKEN_INT());
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_INT());// reporta token int
            }else{    // reporta token int
                hacerOperaciones(validador.getTOKEN_INT());
            }

        } else {// reporta token int
            hacerOperaciones(validador.getTOKEN_INT());
        }
    }
    public void e85() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e105
                hacerOperacionAumentarVariable();
                e105();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e86() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getR()) {// con r a e106
                hacerOperacionAumentarVariable();
                e106();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e87() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getR()) {// con r a e107
                hacerOperacionAumentarVariable();
                e107();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e105() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getM()) {// con m a e118
                hacerOperacionAumentarVariable();
                e118();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e106() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getT()) {// con t a e119
                hacerOperacionAumentarVariable();
                e119();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e107() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getF()) {// conf a e120
                hacerOperacionAumentarVariable();
                e120();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e118() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e141
                hacerOperacionAumentarVariable();
                e141();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e141() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getN()) {// con n a e142
                hacerOperacionAumentarVariable();
                e142();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e142() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getT()) {// con t a e129
                hacerOperacionAumentarVariable();
                e129();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final import
    public void e119() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_IMPORT());// reporta token import
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_IMPORT());// reporta token import
            }else{    // reporta token import
                hacerOperaciones(validador.getTOKEN_IMPORT());
            }

        } else {// reporta token import
            hacerOperaciones(validador.getTOKEN_IMPORT());
        }
    }
    public void e120() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con a a e130
                hacerOperacionAumentarVariable();
                e130();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e129() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getS()) {// con s a e134
                hacerOperacionAumentarVariable();
                e134();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e130() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getC()) {// con c a e135
                hacerOperacionAumentarVariable();
                e135();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final implements
    public void e134() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_IMPLEMENTS());// reporte token implements
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_IMPLEMENTS());// reporte token implements
            }else{    // reporte token implements
                hacerOperaciones(validador.getTOKEN_IMPLEMENTS());
            }

        } else {// reporte token implements
            hacerOperaciones(validador.getTOKEN_IMPLEMENTS());
        }
    }
    public void e135() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e137
                hacerOperacionAumentarVariable();
                e137();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }

        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }// estado final interface
    public void e137() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_INTERFACE());// reporta token interface
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_INTERFACE());// reporta token interface
            }else{    // reporta token interface
                hacerOperaciones(validador.getTOKEN_INTERFACE());
            }

        } else {// reporta token interface
            hacerOperaciones(validador.getTOKEN_INTERFACE());
        }
    }
    public void e9(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con a a e35
                hacerOperacionAumentarVariable();
                e35();
            }else if (caracteres[contador]==validador.getR()) {// con r a e36
                hacerOperacionAumentarVariable();
                e36();
            }  else if (caracteres[contador]==validador.getU()) {// con u a e37
                hacerOperacionAumentarVariable();
                e37();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e35() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getC()) {// con c a e63
                hacerOperacionAumentarVariable();
                e63();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e36() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getI()) {// con i a e64
                hacerOperacionAumentarVariable();
                e64();
            } else if (caracteres[contador] == validador.getO()) {// con o a e65
                hacerOperacionAumentarVariable();
                e65();// reporta  el token identificador
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e37() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getB()) {// con b a e66
                hacerOperacionAumentarVariable();
                e66();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e63() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getK()) {// con k a e88
                hacerOperacionAumentarVariable();
                e88();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e64() {
        if (contador < caracteres.length) {// con v a e89
            if (caracteres[contador] == validador.getV()) {
                hacerOperacionAumentarVariable();
                e89();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e65() {
        if (contador < caracteres.length) {// con t a e90
            if (caracteres[contador] == validador.getT()) {
                hacerOperacionAumentarVariable();
                e90();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e66() {
        if (contador < caracteres.length) {// con l a e91
            if (caracteres[contador] == validador.getL()) {
                hacerOperacionAumentarVariable();
                e91();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e88() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con a a e108
                hacerOperacionAumentarVariable();
                e108();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e89() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con a a e109
                hacerOperacionAumentarVariable();
                e109();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e90() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e110
                hacerOperacionAumentarVariable();
                e110();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e91() {
        if (contador < caracteres.length) {// con i a e111
            if (caracteres[contador] == validador.getI()) {
                hacerOperacionAumentarVariable();
                e111();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    
    public void e108() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getG()) {// con g a e121
                hacerOperacionAumentarVariable();
                e121();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e109() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getT()) {// con una t a e122
                hacerOperacionAumentarVariable();
                e122();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e110() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getC()) {// con  a e123
                hacerOperacionAumentarVariable();
                e123();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e111() {
        if (contador < caracteres.length) {// con c a e124
            if (caracteres[contador] == validador.getC()) {
                hacerOperacionAumentarVariable();
                e124();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e121() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e131
                hacerOperacionAumentarVariable();
                e131();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e122() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e132
                hacerOperacionAumentarVariable();
                e132();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }
    public void e123() {
        if (contador < caracteres.length) {// con t a e133
            if (caracteres[contador] == validador.getT()) {
                hacerOperacionAumentarVariable();
                e133();// reporta  el token identificador
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final token public
    }public void e124() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_PUBLIC());// reporta token public
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_PUBLIC());// reporta token public
            }else{    // reporta token public
                 hacerOperaciones(validador.getTOKEN_PUBLIC());
            }
        } else {// reporta token public
            hacerOperaciones(validador.getTOKEN_PUBLIC());
        }
    }// estado final package
    public void e131() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_PACKAGE());// reporta token package
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_PACKAGE());// reporta token package
            }else{    // reporta token package
                 hacerOperaciones(validador.getTOKEN_PACKAGE());
            }
        } else {// reporta token package
            hacerOperaciones(validador.getTOKEN_PACKAGE());
        }// estado final private
    }public void e132() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_PRIVATE());// reporta token private
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_PRIVATE());// reporta token private
            }else{    
                hacerOperaciones(validador.getTOKEN_PRIVATE());// reporta token private
            }
        } else {
            hacerOperaciones(validador.getTOKEN_PRIVATE());// reporta token private
        }
    }
    public void e133() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e136
                hacerOperacionAumentarVariable();
                e136();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e136() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getD()) {// con d a e138
                hacerOperacionAumentarVariable();
                e138();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final protected
    }public void e138() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_PROTECTED());// reporte token protected
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_PROTECTED());// reporte token protected
            }else{    
                hacerOperaciones(validador.getTOKEN_PROTECTED());// reporte token protected
            }

        } else {// reporte token protected
            hacerOperaciones(validador.getTOKEN_PROTECTED());
        }
    }public void e10(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e38
                hacerOperacionAumentarVariable();
                e38();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e38() {
        if (contador < caracteres.length) {// con t se va a e67
            if (caracteres[contador] == validador.getT()) {
                hacerOperacionAumentarVariable();
                e67();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e67() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getU()) {// con una u a e92
                hacerOperacionAumentarVariable();
                e92();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e92() {
        if (contador < caracteres.length) {// con r a e112
            if (caracteres[contador] == validador.getR()) {
                hacerOperacionAumentarVariable();
                e112();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e112() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getN()) {// con n a e125
                hacerOperacionAumentarVariable();
                e125();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final return
    }public void e125() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_RETURN());// reporta token return
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_RETURN());// reporta token return
            }else{    
                hacerOperaciones(validador.getTOKEN_RETURN());// reporta token return
            }
        } else {
            hacerOperaciones(validador.getTOKEN_RETURN());// reporta token return
        }
    }public void e11(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getH()) {// con h a e39
                hacerOperacionAumentarVariable();
                e39();
            }else if (caracteres[contador] == validador.getT()) {
                hacerOperacionAumentarVariable();// con t a e40
                e40();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e39() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getO()) {// con o a e68
                hacerOperacionAumentarVariable();
                e68();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e40() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getA()) {// con a e69
                hacerOperacionAumentarVariable();
                e69();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e68() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getR()) {// con r a e93
                hacerOperacionAumentarVariable();
                e93();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e69() {
        if (contador < caracteres.length) {// con t a e94
            if (caracteres[contador] == validador.getT()) {
                hacerOperacionAumentarVariable();
                e94();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e93() {
        if (contador < caracteres.length) {// con t a e113
            if (caracteres[contador] == validador.getT()) {
                hacerOperacionAumentarVariable();
                e113();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e94() {
        if (contador < caracteres.length) {// con i a e114
            if (caracteres[contador] == validador.getI()) {
                hacerOperacionAumentarVariable();
                e114();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final short
    }public void e113() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_SHORT());// reporta token short
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_SHORT());// reporta token short
            }else{    // reporta token short
                 hacerOperaciones(validador.getTOKEN_SHORT());
            }

        } else {// reporta token short
            hacerOperaciones(validador.getTOKEN_SHORT());
        }
    }public void e114() {
        if (contador < caracteres.length) {// con c a e126
            if (caracteres[contador] == validador.getC()) {
                hacerOperacionAumentarVariable();
                e126();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final static
    }public void e126() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_STATIC());// reporta token static
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_STATIC());// reporta token static
            }else{    // reporta token static
                 hacerOperaciones(validador.getTOKEN_STATIC());
            }
        } else {// reporta token static
            hacerOperaciones(validador.getTOKEN_STATIC());
        }
    }public void e12(){
        if (contador < caracteres.length) {// con h a e41
            if (caracteres[contador] == validador.getH()) {
                hacerOperacionAumentarVariable();
                e41();
            }else if (caracteres[contador] == validador.getR()) {// con r a e42
                hacerOperacionAumentarVariable();
                e42();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e41() {
        if (contador < caracteres.length) {// con e a e70
            if (caracteres[contador] == validador.getE()) {
                hacerOperacionAumentarVariable();
                e70();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e42() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getU()) {// con u a e71
                hacerOperacionAumentarVariable();
                e71();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e70() {
        if (contador < caracteres.length) {// con n a e95
            if (caracteres[contador] == validador.getN()) {
                hacerOperacionAumentarVariable();
                e95();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e71() {
        if (contador < caracteres.length) {// con e a e96
            if (caracteres[contador] == validador.getE()) {
                hacerOperacionAumentarVariable();
                e96();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final then
    }public void e95() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_THEN());// reporte token then
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_THEN());// reporte token then
            }else{    // reporte token then
                 hacerOperaciones(validador.getTOKEN_THEN());
            }

        } else {// reporte token then
            hacerOperaciones(validador.getTOKEN_THEN());
        }// estado final true
    }public void e96() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_TRUE());// reporte token true
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_TRUE());// reporte token true
            }else{    // reporte token true
                 hacerOperaciones(validador.getTOKEN_TRUE());
            }
        } else {// reporte token true
            hacerOperaciones(validador.getTOKEN_TRUE());
        }
    }public void e13(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getO()) {// con o a e43
                hacerOperacionAumentarVariable();
                e43();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e43() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getI()) {// con i a e72
                hacerOperacionAumentarVariable();
                e72();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e72() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getD()) {// con d a e97
                hacerOperacionAumentarVariable();
                e97();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final void
    }public void e97() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_VOID());// reporte token void
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_VOID());// reporte token void
            }else{    // reporte token void
                 hacerOperaciones(validador.getTOKEN_VOID());
            }
        } else {// reporte token void
            hacerOperaciones(validador.getTOKEN_VOID());
        }
    }public void e14(){
        if (contador < caracteres.length) {// con h a e44
            if (caracteres[contador] == validador.getH()) {
                hacerOperacionAumentarVariable();
                e44();
            }else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e44() {
        if (contador < caracteres.length) {// con i a e73
            if (caracteres[contador] == validador.getI()) {
                hacerOperacionAumentarVariable();
                e73();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e73() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getL()) {// reporte con l a e98
                hacerOperacionAumentarVariable();
                e98();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }
    }public void e98() {
        if (contador < caracteres.length) {
            if (caracteres[contador] == validador.getE()) {// con e a e115
                hacerOperacionAumentarVariable();
                e115();
            } else if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();// reporta  el token identificador
                e3();
            } else if(caracteres[contador] == ' '){// reporta  el token identificador
                hacerOperacionesEspacio(validador.getTOKEN_IDENTIFICADOR());
            } else if(caracteres[contador] == '\n'){// reporta  el token identificador
                hacerOperacionesSaltoLinea(validador.getTOKEN_IDENTIFICADOR());
            }else{    // reporta  el token identificador
                 hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
            }
        } else {// reporta  el token identificador
            hacerOperaciones(validador.getTOKEN_IDENTIFICADOR());
        }// estado final while
    }public void e115() {
        if (contador < caracteres.length) {
            if (validador.verificarCaracter(caracteres[contador])) {
                hacerOperacionAumentarVariable();
                e3();// reporta  el token identificador
            } else if(caracteres[contador] == ' '){
                hacerOperacionesEspacio(validador.getTOKEN_WHILE());// reporte token while
            } else if(caracteres[contador] == '\n'){
                hacerOperacionesSaltoLinea(validador.getTOKEN_WHILE());// reporte token while
            }else{    // reporte token while
                 hacerOperaciones(validador.getTOKEN_WHILE());
            }
        } else {// reporte token while
            hacerOperaciones(validador.getTOKEN_WHILE());
        }// estado final token signo puntuacion
    }public void e15(){
        if (contador < caracteres.length) {
            if (caracteres[contador] == ' ') {// si viene un espacio reporta signo puntuacion
                hacerOperacionesEspacio(validador.getTOKEN_SIGNO_PUNTUACION());
            } else if (caracteres[contador] == '\n') {// si viene salto linea reporta signo puntuacion
                hacerOperacionesSaltoLinea(validador.getTOKEN_SIGNO_PUNTUACION());
            }else {// reporta signo puntuacion
                hacerOperaciones(validador.getTOKEN_SIGNO_PUNTUACION());;
            }
        } else {//reporta signo puntuacion
            hacerOperaciones(validador.getTOKEN_SIGNO_PUNTUACION());
        }
    }
    public void e16(){
        if (contador < caracteres.length) {// si es una comilla a e45
            if(caracteres[contador]==validador.getCOMILLA()){
                hacerOperacionAumentarVariable();
                e45();
            }else if(caracteres[contador]==' '){// si es espacio reporta el error
                hacerOperacionAumentarVariable();
                e16();
            }else if(caracteres[contador]=='\n'){// si es salto linea reporta el error
                hacerOperacionErrorSaltoLinea();
            }else{//cualquier caracter hara que siga aqui
                hacerOperacionAumentarVariable();
                e16();
            }
        } else {//reporta el error
            hacerOperacionError();
        }
    }
    public void e45(){// estado final del literal solo reporta el token
        hacerOperaciones(validador.getTOKEN_LITERAL());
    }
    public void e18(){// estado final del operador artimetico solo reporta el token
        hacerOperaciones(validador.getTOKEN_OPERADOR_ARITMETICO());
    }
    int contadorComentario=0;
    public void e19(){
        if (contador < caracteres.length) {// si es un asterisco se va a e48
            if(caracteres[contador]==validador.getMULTIPLICACION()){
                filaAuxiliar=fila;
                contadorComentario=contadorAuxiliar;
                hacerOperacionAumentarVariable();
                e48();// si es una diagonal a e49
            }else if(caracteres[contador]==validador.getDIAGONAL()){
                e49();
            }else if(caracteres[contador]==' '){// si es un espacio reporta operador aritmetico
                hacerOperacionesEspacio(validador.getTOKEN_OPERADOR_ARITMETICO());
            }else if(caracteres[contador]=='\n'){// si es salto linea reporta operador aritmetico
                hacerOperacionesSaltoLinea(validador.getTOKEN_OPERADOR_ARITMETICO());
            }else{//reporta operador aritmetico
                hacerOperaciones(validador.getTOKEN_OPERADOR_ARITMETICO());
            }
        } else {//reporta operador aritmetico
            hacerOperaciones(validador.getTOKEN_OPERADOR_ARITMETICO());
        }
    }public void e48() {
        
        if (contador < caracteres.length) {// si es asterisco a a e139
            if (caracteres[contador] == validador.getMULTIPLICACION()) {
                hacerOperacionAumentarVariable();
                e139();
            }else{// si no a e48
                if(caracteres[contador]== '\n'){
                    aumentarFila();
                    reiniciarContadorAuxiliar();
                }
                hacerOperacionAumentarVariable();
                e48();
            }
        } else {// si no es nada a operacion error
            System.out.println("Entro en e48 error");
            hacerOperacionError();
            
        }
    }public void e139() {
        if (contador < caracteres.length) {// si es diagonal a e 140
            if (caracteres[contador] == validador.getDIAGONAL()) {
                hacerOperacionAumentarVariable();
                e140();
            }else{// si es multiplicacion a e48
                if(caracteres[contador]==validador.getMULTIPLICACION()){
                    e48();
                }else if(caracteres[contador]=='\n'){
                    
                    aumentarFila();
                    reiniciarContadorAuxiliar();
                    hacerOperacionAumentarVariable();
                    e48();
                }else{// si no a e48
                    hacerOperacionAumentarVariable();
                    e48();
                }
            }
        } else {// si no reporta el error
            hacerOperacionError();
        }
    }public void e140() {// reporta el comentario en bloque
        agregarLexemaComentarioBloque(validador.getTOKEN_COMENTARIO_BLOQUE(), lexemaActual, filaAuxiliar, contadorComentario);// agrega al lexema
        reiniciarLexema();// reinicia el lexema
        e0(caracteres, contador);
    }
    public void e49() {
        if (contador < caracteres.length) {// si es salto de linea a e76
            if(caracteres[contador]==validador.getSALTO_LINEA()){
                e76();
            }else{// si no a e49
                hacerOperacionAumentarVariable();
                e49();
            }
        } else {// reporta el token comentari linea
            hacerOperaciones(validador.getTOKEN_COMENTARIO_LINEA());
        }
    }
    public void e76() {// reporta el token comentario linea
        hacerOperacionesSaltoLinea(validador.getTOKEN_COMENTARIO_LINEA());
    }
    public void e20(){// reporta el token signo agrupacion
        hacerOperaciones(validador.getTOKEN_SIGNO_AGRUPACION());
    }// realiza operaciones cuando encuentra un token
    public void hacerOperaciones(String token) {
        agregarLexema(token, lexemaActual, fila, contadorAuxiliar);// agrega al lexema
        reiniciarLexema();// reinicia el lexema
        e0(caracteres, contador);
    }

    public void hacerOperacionesEspacio(String token) {
        agregarLexema(token, lexemaActual, fila, contadorAuxiliar);// agrega al lexema
        reiniciarLexema();// reinicia el lexema
        aumentarVariables();// aumenta las variables
        e0(caracteres, contador);
    }

    public void hacerOperacionesSaltoLinea(String token) {
        agregarLexema(token, lexemaActual, fila, contadorAuxiliar);// agrega el elxema
        reiniciarLexema();// reinicia al lexemea
        aumentarVariables();// aumenta variables
        aumentarFila();// aumenta las filas
        reiniciarContadorAuxiliar();// reinicia el contador auxiliar que es el de columna
        e0(caracteres, contador);// se va a e0
    }
// aumenta las variables 
    public void hacerOperacionAumentarVariable() {
        añadirCaracter(caracteres, contador);// añade el caracter y aumenta las variables
        aumentarVariables();
    }

    public void hacerOperacionErrorEspacio() {// si viene un espacio a esto se llama
        agregarError(lexemaActual, fila, contadorAuxiliar);// agrega el error
        reiniciarLexema();// reinicia el elxema
        aumentarVariables();//aumenta variables
        e0(caracteres, contador);// e0
    }

    public void hacerOperacionErrorSaltoLinea() {
        agregarError(lexemaActual, fila, contadorAuxiliar);// agrega el error
        reiniciarLexema();// reinicia el lexema
        aumentarVariables();// aumenta las variables
        reiniciarContadorAuxiliar();// reinicia el contador auxilair
        aumentarFila();// aumenta las filas
        e0(caracteres, contador);//eo
    }

    public void hacerOperacionError() {
        agregarError(lexemaActual, fila, contadorAuxiliar);// agrega el error
        reiniciarLexema();// reinicia el lexema
        e0(caracteres, contador);// e0
    }
    public void agregarLexemaComentarioBloque(String nombreToken, String lexema, int fila, int columna) {
        ModeloToken token = new ModeloToken(nombreToken, lexema, fila, columna-2);
        modelo.add(token);
    }
}
