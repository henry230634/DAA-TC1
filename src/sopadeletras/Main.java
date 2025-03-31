package sopadeletras;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterfazUsuario ui = new InterfazUsuario();
        int tamaño = ui.pedirTamaño();
        SopaDeLetras sopa = new SopaDeLetras(tamaño);

        List<String> palabras = ui.pedirPalabras();
        for (String palabra : palabras) {
            if (!sopa.colocarPalabra(palabra)) {
                System.out.println("⚠️ No se pudo colocar la palabra: " + palabra);
            }
        }

        sopa.rellenarConLetrasAleatorias();
        sopa.mostrarSopa();
        sopa.mostrarUbicaciones();
    }
}
