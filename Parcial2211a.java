/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcial.pkg22.pkg11a;

/**
 *
 * @author franc
 */
import java.util.Arrays;

public class Parcial2211a {
    
      // Encontrar la nota mas alta
    
    private static double getMaxNota(double[] notas) {
        double max = notas[0];
        for (double nota : notas) {
            if (nota > max) {
                max = nota;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // Datos de ejemplo
        String[] nombres = {"Juan", "Ana", "Luis", "María", "Pedro"};
        String[] apellidos = {"Pérez", "García", "Rodríguez", "López", "Gómez"};
        double[] notas = {88.5, 92.0, 75.5, 85.0, 92.0};

        // Ordenar datos
        ordenarPorNotas(nombres, apellidos, notas);

        // Mostrar datos ordenados
        mostrarLista(nombres, apellidos, notas);

        // Buscar una nota específica
        buscarNota(nombres, apellidos, notas, 92.0);
    }

    // Función para ordenar los datos usando Radix Sort
    public static void ordenarPorNotas(String[] nombres, String[] apellidos, double[] notas) {
        int n = notas.length;
        double maxNota = getMaxNota(notas);

        // Iterar para cada dígito decimal (parte entera y decimal)
        for (int exp = 1; maxNota / exp > 0; exp *= 10) {
            aplicarRadix(nombres, apellidos, notas, exp);
        }
    }

    private static void aplicarRadix(String[] nombres, String[] apellidos, double[] notas, int exp) {
        int n = notas.length;
        double[] outputNotas = new double[n];
        String[] outputNombres = new String[n];
        String[] outputApellidos = new String[n];
        int[] count = new int[10];

        // Contar ocurrencias de dígitos
        for (int i = 0; i < n; i++) {
            int digit = (int) ((notas[i] / exp) % 10);
            count[digit]++;
        }

        // Ajustar las posiciones
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construir los arreglos de salida
        for (int i = n - 1; i >= 0; i--) {
            int digit = (int) ((notas[i] / exp) % 10);
            int pos = count[digit] - 1;
            outputNotas[pos] = notas[i];
            outputNombres[pos] = nombres[i];
            outputApellidos[pos] = apellidos[i];
            count[digit]--;
        }

        // Copiar de vuelta a los arreglos originales
        for (int i = 0; i < n; i++) {
            notas[i] = outputNotas[i];
            nombres[i] = outputNombres[i];
            apellidos[i] = outputApellidos[i];
        }
    }
    
  

    // Función para realizar búsquedas por nota
    
    public static void buscarNota(String[] nombres, String[] apellidos, double[] notas, double notaBuscada) {
        System.out.println("\nBúsqueda de estudiantes con nota: " + notaBuscada);
        boolean encontrado = false;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] == notaBuscada) {
                System.out.println("Nombre: " + nombres[i] + ", Apellido: " + apellidos[i] + ", Nota: " + notas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron estudiantes con la nota: " + notaBuscada);
        }
    }

    // Función para mostrar la lista de notas
    public static void mostrarLista(String[] nombres, String[] apellidos, double[] notas) {
        System.out.println("\nLista de notas (de mayor a menor):");
        for (int i = 0; i < notas.length; i++) {
            System.out.println("Nombre: " + nombres[i] + ", Apellido: " + apellidos[i] + ", Nota: " + notas[i]);
        }
    }
}
