package sopadeletras;

import java.util.*;

public class SopaDeLetras {
    private char[][] sopa;
    private int tamaño;
    private final Random random = new Random();
    private final List<String> palabrasColocadas = new ArrayList<>();
    private final Map<String, int[]> coordenadasPalabras = new HashMap<>();

    private static final int[][] DIRECCIONES = {
        {0, 1}, {1, 0}, {1, 1}, {-1, 1}, {0, -1}, {-1, 0}, {-1, -1}, {1, -1}
    };

    public SopaDeLetras(int tamaño) {
        this.tamaño = tamaño;
        sopa = new char[tamaño][tamaño];
        for (char[] fila : sopa) Arrays.fill(fila, ' ');
    }

    public boolean colocarPalabra(String palabra) {
        for (int intentos = 0; intentos < 100; intentos++) {
            int fila = random.nextInt(tamaño);
            int col = random.nextInt(tamaño);
            int[] direccion = DIRECCIONES[random.nextInt(DIRECCIONES.length)];
            int dx = direccion[0], dy = direccion[1];

            if (puedeColocar(palabra, fila, col, dx, dy)) {
                int xFinal = fila + (palabra.length() - 1) * dx;
                int yFinal = col + (palabra.length() - 1) * dy;

                for (int i = 0; i < palabra.length(); i++) {
                    sopa[fila + i * dx][col + i * dy] = palabra.charAt(i);
                }

                coordenadasPalabras.put(palabra, new int[]{fila + 1, col + 1, xFinal + 1, yFinal + 1});
                palabrasColocadas.add(palabra);
                return true;
            }
        }
        return false;
    }

    private boolean puedeColocar(String palabra, int fila, int col, int dx, int dy) {
        int xFinal = fila + (palabra.length() - 1) * dx;
        int yFinal = col + (palabra.length() - 1) * dy;
        if (xFinal < 0 || xFinal >= tamaño || yFinal < 0 || yFinal >= tamaño) return false;
        for (int i = 0; i < palabra.length(); i++) {
            int x = fila + i * dx, y = col + i * dy;
            if (sopa[x][y] != ' ' && sopa[x][y] != palabra.charAt(i)) return false;
        }
        return true;
    }

    public void rellenarConLetrasAleatorias() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (sopa[i][j] == ' ') {
                    sopa[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    public void mostrarSopa() {
        System.out.println("\n🧩 Sopa de Letras Generada:");

        System.out.print("   ");
        for (int j = 0; j < tamaño; j++) {
            System.out.printf("%2d ", j + 1);
        }
        System.out.println();

        for (int i = 0; i < tamaño; i++) {
            System.out.printf("%2d| ", i + 1);
            for (int j = 0; j < tamaño; j++) {
                System.out.print(sopa[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void mostrarUbicaciones() {
        System.out.println("\n📍 Ubicación de las palabras:");
        for (String palabra : palabrasColocadas) {
            int[] coord = coordenadasPalabras.get(palabra);
            System.out.printf("La palabra '%s' comienza en (%d, %d) y termina en (%d, %d)\n",
                    palabra, coord[0], coord[1], coord[2], coord[3]);
        }
    }
}
