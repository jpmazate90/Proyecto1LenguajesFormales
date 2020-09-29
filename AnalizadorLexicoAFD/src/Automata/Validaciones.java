package Automata;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class Validaciones {

    // constantes atributos finales
    private final String TOKEN_NUMERO_ENTERO = "NUMERO ENTERO";
    private final String TOKEN_NUMERO_DECIMAL = "NUMERO DECIMAL";
    private final String TOKEN_IDENTIFICADOR = "IDENTIFICADOR";
    private final String TOKEN_IF = "IF";
    private final String TOKEN_CLASS = "CLASS";
    private final String TOKEN_FOR = "FOR";
    private final String TOKEN_THEN = "THEN";
    private final String TOKEN_ELSE = "ELSE";
    private final String TOKEN_PUBLIC = "PUBLIC";
    private final String TOKEN_PRIVATE = "PRIVATE";
    private final String TOKEN_PACKAGE = "PACKAGE";
    private final String TOKEN_IMPORT = "IMPORT";
    private final String TOKEN_PROTECTED = "PROTECTED";
    private final String TOKEN_STATIC = "STATIC";
    private final String TOKEN_VOID = "VOID";
    private final String TOKEN_INT = "INT";
    private final String TOKEN_TRUE = "TRUE";
    private final String TOKEN_FALSE = "FALSE";
    private final String TOKEN_EXTENDS = "EXTENDS";
    private final String TOKEN_SHORT = "SHORT";
    private final String TOKEN_BOOLEAN = "BOOLEAN";
    private final String TOKEN_FLOAT = "FLOAT";
    private final String TOKEN_INTERFACE = "INTERFACE";
    private final String TOKEN_FINAL = "FINAL";
    private final String TOKEN_RETURN = "RETURN";
    private final String TOKEN_WHILE = "WHILE";
    private final String TOKEN_CASE = "CASE";
    private final String TOKEN_IMPLEMENTS = "IMPLEMENTS";
    private final String TOKEN_LITERAL = "LITERAL";
    private final String TOKEN_SIGNO_PUNTUACION = "SIGNO DE PUNTUACION";
    private final String TOKEN_OPERADOR_ARITMETICO = "OPERADOR ARITMETICO";
    private final String TOKEN_SIGNO_AGRUPACION = "SIGNO AGRUPACION";
    private final String TOKEN_COMENTARIO_LINEA = "COMENTARIO LINEA";
    private final String TOKEN_COMENTARIO_BLOQUE = "COMENTARIO BLOQUE";
    // mas atributos finales constantes
    private final char A = 'a';
    private final char B = 'b';
    private final char C = 'c';
    private final char D = 'd';
    private final char E = 'e';
    private final char F = 'f';
    private final char G = 'g';
    private final char H = 'h';
    private final char I = 'i';
    private final char J = 'j';
    private final char K = 'k';
    private final char L = 'l';
    private final char M = 'm';
    private final char N = 'n';
    private final char O = 'o';
    private final char P = 'p';
    private final char Q = 'q';
    private final char R = 'r';
    private final char S = 's';
    private final char T = 't';
    private final char U = 'u';
    private final char V = 'v';
    private final char W = 'w';
    private final char X = 'x';
    private final char Y = 'y';
    private final char Z = 'z';
    // mas atributos finales constantes
    private final char PUNTO = '.';
    private final char COMA = ',';
    private final char PUNTOCOMA = ';';
    private final char DOSPUNTOS = ':';
    // mas atributos finales constantes
    private final char MULTIPLICACION = '*';
    private final char MODULO = '%';
    private final char DIAGONAL = '/';
    // mas atributos finales constantes
    private final char COMILLA = '\"';
    private final char SALTO_LINEA = '\n';
    // mas atributos finales constantes
    private final char PARENTESIS_ABIERTO = '(';
    private final char PARENTESIS_CERRADO = ')';
    private final char CORCHETE_ABIERTO = '[';
    private final char CORCHETE_CERRADO = ']';
    private final char LLAVE_ABIERTA = '{';
    private final char LLAVE_CERRADA = '}';
    // mas atributos finales constantes
    private final char[] LETRAS_MINUSCULAS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final char[] LETRAS_MAYUSCULAS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final char[] NUMEROS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    // verifica si es un numero
    public boolean verificarSiEsNumero(char[] numeros, char[] caracteres, int contador) {
        try {// verifica si algo es un numero
            for (int i = 1; i < numeros.length; i++) {
                if (caracteres[contador] == numeros[i]) {
                    return true;
// si cumple manda que si 
                }
            }// si no manda que no
            return false;
        } catch (Exception e) {
            return false;
        }

    }
// verifica si el caracter que se mando es un numero desde 0 a 9

    public boolean verificarSiEsNumeroConCero(char[] numeros, char[] caracteres, int contador) {
        try {
            for (int i = 0; i < numeros.length; i++) {
                if (caracteres[contador] == numeros[i]) {
                    return true;
// si cumple manda que si
                }
            }
            return false;// si no manda que no
        } catch (Exception e) {
            return false;// si no manda que no
        }

    }

// verifica si se puede ir directamente al estado e3
    public boolean verificarE3(char caracter) {
        // verifica si es de estos caracteres
        if (caracter == 'a' || caracter == 'd' || caracter == 'g' || caracter == 'h' || caracter == 'q' || caracter == 'u' || caracter == 'x' || caracter == 'y' || caracter == 'z') {
            return true;
            // si es algun caracter de estos
        } else {
            if (caracter == 'j' || caracter == 'k' || caracter == 'l' || caracter == 'm' || caracter == 'n' || caracter == 'o' || caracter == '_') {
                return true;
            } else {// y por ultimo verifica si es alguna letra en mayusculas
                for (int i = 0; i < LETRAS_MAYUSCULAS.length; i++) {
                    if (caracter == LETRAS_MAYUSCULAS[i]) {
                        return true;
                    }
                }
            }
        }// si llega aqui significa que no y lo retorna

        return false;
    }

    // verifica un caracter si esta etre los que acepta un identificador
    public boolean verificarCaracter(char caracter) {
        for (int i = 0; i < LETRAS_MINUSCULAS.length; i++) {
            if (caracter == LETRAS_MINUSCULAS[i]) {
                return true;// si es una letra minusculas
            } else if (caracter == LETRAS_MAYUSCULAS[i]) {
                return true;// si es una letra mayuscula
            } else if (caracter == '-' || caracter == '_') {
                return true;// si es guion bajo o medio
            } else if (verificarNumeros(caracter)) {
                return true;// y por ultio si es del 0 al nueve
            } else {
                for (int j = 0; j < LETRAS_MINUSCULAS.length; j++) {
                    char c = LETRAS_MINUSCULAS[j];

                }
            }
        }
        return false;
    }

    // verifica si es unn numero valido
    public boolean verificarNumeros(char caracter) {
        for (int i = 0; i < NUMEROS.length; i++) {
            if (caracter == NUMEROS[i]) {
                return true;
// REGRESA que si 
            }
        }// regresa que no
        return false;
    }

    // hace el recuento de lexemas
    public void recuentoLexemas(ArrayList<ModeloToken> lexemas, DefaultTableModel tablaLexemas) {
        ArrayList<ModeloToken> modelo = new ArrayList<ModeloToken>();
        for (int i = 0; i < lexemas.size(); i++) {// agarra los lexemas que ya existen
            contarLexemas(lexemas.get(i).getLexema(), lexemas.get(i).getNombreToken(), modelo);
        }// los añade a la tabla que se le mando 
        for (int i = 0; i < modelo.size(); i++) {
            String hola = modelo.get(i).getNombreToken();
            String datos[] = new String[3];// los añade a la tabla que se le mando 
            datos[0] = modelo.get(i).getLexema();
            datos[1] = modelo.get(i).getNombreToken();// los añade a la tabla que se le mando 
            datos[2] = Integer.toString(modelo.get(i).getVeces());
            tablaLexemas.addRow(datos);// los añade a la tabla que se le mando 
        }

    }
// cuenta los lexemas
    public void contarLexemas(String cadena, String tokenNombre, ArrayList<ModeloToken> lexema) {
        boolean banderaEsPalabraNueva = true;
        boolean banderaSiCumpleLexema = true;
        String nombreToken = tokenNombre;
        ModeloToken token;// crea un objeto modelo token
        for (int i = 0; i < lexema.size(); i++) {// agarra el lexema en la posicion 1
            if (cadena.length() == lexema.get(i).getLexema().length()) {
                for (int j = 0; j < lexema.get(i).getLexema().length(); j++) {
                    banderaSiCumpleLexema = true;//verifica si el lexema se repite o no
                    if (cadena.charAt(j) != lexema.get(i).getLexema().charAt(j)) {
                        banderaSiCumpleLexema = false;
                        break;
                    }
                }// si el lexema se repite solo agrega una unidad a las veces repetida
                if (banderaSiCumpleLexema == true) {
                    lexema.get(i).setVeces(lexema.get(i).getVeces() + 1);
                    banderaEsPalabraNueva = false;
                }
            }
        }// si no existe se crea un elemento del vector para luego ya empezar a sumarle.
        if (banderaEsPalabraNueva == true) {
            token = new ModeloToken(cadena, nombreToken);
            lexema.add(token);
        }

    }
// el recento de tokens no es mas que agarrar cada posicion del arreglo de modelos tokens
    public void recuentoToken(ArrayList<ModeloToken> modelo, DefaultTableModel tablaTokens) {
        for (int i = 0; i < modelo.size(); i++) {
            String datos[] = new String[4];// CON CADA posicion los va agregando a una tabla
            datos[0] = modelo.get(i).getNombreToken();
            datos[1] = modelo.get(i).getLexema();// CON CADA posicion los va agregando a una tabla
            datos[2] = Integer.toString(modelo.get(i).getFila());
            datos[3] = Integer.toString(modelo.get(i).getColumna());// CON CADA posicion los va agregando a una tabla
            tablaTokens.addRow(datos);
        }// CON CADA posicion los va agregando a una tabla
    }
// AGREGA el erro que exista a la lista en una linea 
    public void agregarError(DefaultListModel modelo, ModeloToken token) {
        String error = " Error en la fila: " + token.getFila() + " ,Columna: " + token.getColumna() + " ,Lexema: " + token.getLexema();
        modelo.addElement(error);// indica el error en la posicion que es y lo agrega a la lista
    }
// getter del atributo
    public String getTOKEN_NUMERO_ENTERO() {
        return TOKEN_NUMERO_ENTERO;
    }
// getter del atributo
    public String getTOKEN_NUMERO_DECIMAL() {
        return TOKEN_NUMERO_DECIMAL;
    }
// getter del atributo
    public String getTOKEN_IDENTIFICADOR() {
        return TOKEN_IDENTIFICADOR;
    }
// getter del atributo
    public String getTOKEN_IF() {
        return TOKEN_IF;
    }
// getter del atributo
    public String getTOKEN_CLASS() {
        return TOKEN_CLASS;
    }
// getter del atributo
    public String getTOKEN_FOR() {
        return TOKEN_FOR;
    }
// getter del atributo
    public String getTOKEN_THEN() {
        return TOKEN_THEN;
    }
// getter del atributo
    public String getTOKEN_ELSE() {
        return TOKEN_ELSE;
    }
// getter del atributo
    public String getTOKEN_PUBLIC() {
        return TOKEN_PUBLIC;
    }
// getter del atributo
    public String getTOKEN_PRIVATE() {
        return TOKEN_PRIVATE;
    }
// getter del atributo
    public String getTOKEN_PACKAGE() {
        return TOKEN_PACKAGE;
    }
// getter del atributo
    public String getTOKEN_PROTECTED() {
        return TOKEN_PROTECTED;
    }
// getter del atributo
    public String getTOKEN_IMPORT() {
        return TOKEN_IMPORT;
    }
// getter del atributo
    public String getTOKEN_STATIC() {
        return TOKEN_STATIC;
    }
// getter del atributo
    public String getTOKEN_VOID() {
        return TOKEN_VOID;
    }
// getter del atributo
    public String getTOKEN_INT() {
        return TOKEN_INT;
    }
// getter del atributo
    public String getTOKEN_TRUE() {
        return TOKEN_TRUE;
    }
// getter del atributo
    public String getTOKEN_FALSE() {
        return TOKEN_FALSE;
    }
// getter del atributo
    public String getTOKEN_EXTENDS() {
        return TOKEN_EXTENDS;
    }
// getter del atributo
    public String getTOKEN_SHORT() {
        return TOKEN_SHORT;
    }
// getter del atributo
    public String getTOKEN_BOOLEAN() {
        return TOKEN_BOOLEAN;
    }
// getter del atributo
    public String getTOKEN_FLOAT() {
        return TOKEN_FLOAT;
    }
// getter del atributo
    public String getTOKEN_INTERFACE() {
        return TOKEN_INTERFACE;
    }
// getter del atributo
    public String getTOKEN_FINAL() {
        return TOKEN_FINAL;
    }
// getter del atributo
    public String getTOKEN_RETURN() {
        return TOKEN_RETURN;
    }
// getter del atributo
    public String getTOKEN_WHILE() {
        return TOKEN_WHILE;
    }
// getter del atributo
    public String getTOKEN_CASE() {
        return TOKEN_CASE;
    }
// getter del atributo
    public String getTOKEN_IMPLEMENTS() {
        return TOKEN_IMPLEMENTS;
    }
// getter del atributo
    public String getTOKEN_LITERAL() {
        return TOKEN_LITERAL;
    }
// getter del atributo
    public String getTOKEN_SIGNO_PUNTUACION() {
        return TOKEN_SIGNO_PUNTUACION;
    }
// getter del atributo
    public String getTOKEN_OPERADOR_ARITMETICO() {
        return TOKEN_OPERADOR_ARITMETICO;
    }
// getter del atributo
    public String getTOKEN_SIGNO_AGRUPACION() {
        return TOKEN_SIGNO_AGRUPACION;
    }
// getter del atributo
    public String getTOKEN_COMENTARIO_LINEA() {
        return TOKEN_COMENTARIO_LINEA;
    }
// getter del atributo
    public String getTOKEN_COMENTARIO_BLOQUE() {
        return TOKEN_COMENTARIO_BLOQUE;
    }
// getter del atributo
    public char getA() {
        return A;
    }
// getter del atributo
    public char getB() {
        return B;
    }
// getter del atributo
    public char getC() {
        return C;
    }
// getter del atributo
    public char getD() {
        return D;
    }
// getter del atributo
    public char getE() {
        return E;
    }
// getter del atributo
    public char getF() {
        return F;
    }
// getter del atributo
    public char getG() {
        return G;
    }
// getter del atributo
    public char getH() {
        return H;
    }
// getter del atributo
    public char getI() {
        return I;
    }
// getter del atributo
    public char getJ() {
        return J;
    }
// getter del atributo
    public char getK() {
        return K;
    }
// getter del atributo
    public char getL() {
        return L;
    }
// getter del atributo
    public char getM() {
        return M;
    }
// getter del atributo
    public char getN() {
        return N;
    }
// getter del atributo
    public char getO() {
        return O;
    }
// getter del atributo
    public char getP() {
        return P;
    }
// getter del atributo
    public char getQ() {
        return Q;
    }
// getter del atributo
    public char getR() {
        return R;
    }
// getter del atributo
    public char getS() {
        return S;
    }
// getter del atributo
    public char getT() {
        return T;
    }
// getter del atributo
    public char getU() {
        return U;
    }
// getter del atributo
    public char getV() {
        return V;
    }
// getter del atributo
    public char getW() {
        return W;
    }
// getter del atributo
    public char getX() {
        return X;
    }
// getter del atributo
    public char getY() {
        return Y;
    }
// getter del atributo
    public char getZ() {
        return Z;
    }
// getter del atributo
    public char getPUNTO() {
        return PUNTO;
    }
// getter del atributo
    public char getCOMA() {
        return COMA;
    }
// getter del atributo
    public char getPUNTOCOMA() {
        return PUNTOCOMA;
    }
// getter del atributo
    public char getDOSPUNTOS() {
        return DOSPUNTOS;
    }
// getter del atributo
    public char getMULTIPLICACION() {
        return MULTIPLICACION;
    }
// getter del atributo
    public char getMODULO() {
        return MODULO;
    }
// getter del atributo
    public char getDIAGONAL() {
        return DIAGONAL;
    }
// getter del atributo
    public char getCOMILLA() {
        return COMILLA;
    }
// getter del atributo
    public char getSALTO_LINEA() {
        return SALTO_LINEA;
    }
// getter del atributo
    public char getPARENTESIS_ABIERTO() {
        return PARENTESIS_ABIERTO;
    }
// getter del atributo
    public char getPARENTESIS_CERRADO() {
        return PARENTESIS_CERRADO;
    }
// getter del atributo
    public char getCORCHETE_ABIERTO() {
        return CORCHETE_ABIERTO;
    }
// getter del atributo
    public char getCORCHETE_CERRADO() {
        return CORCHETE_CERRADO;
    }
// getter del atributo
    public char getLLAVE_ABIERTA() {
        return LLAVE_ABIERTA;
    }
// getter del atributo
    public char getLLAVE_CERRADA() {
        return LLAVE_CERRADA;
    }

}
