package sopadeletras;

import java.util.*;

public class InterfazUsuario {
    private final Scanner scanner = new Scanner(System.in);

    public int pedirTamaño() {
        System.out.print("Ingrese el tamaño de la sopa de letras: ");
        return scanner.nextInt();
    }

    public List<String> pedirPalabras() {
        List<String> palabras = new ArrayList<>();
        System.out.println("Ingrese palabras (escriba 'FIN' para terminar):");
        scanner.nextLine(); // Limpia el buffer

        while (true) {
            System.out.print("Palabra: ");
            String palabra = scanner.nextLine().toUpperCase().trim();
            if (palabra.equals("FIN")) break;
            palabras.add(palabra);
        }
        return palabras;
    }
}
