
package analizadorlexicoafd;

import Automata.Automata;
import GUI.MenuPrincipal;
import java.awt.Frame;
import java.awt.peer.MenuPeer;

public class AnalizadorLexicoAFD {

    public static void main(String[] args) {
        // inicia el software con el menu principal
        MenuPrincipal menu = new MenuPrincipal();
        menu.setExtendedState(Frame.MAXIMIZED_BOTH);
        menu.setVisible(true);
    }
    
}
