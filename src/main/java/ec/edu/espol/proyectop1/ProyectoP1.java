/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.proyectop1;

import ec.edu.espol.proyectop1.clases.Comprador;
import ec.edu.espol.proyectop1.clases.Usuario;
import ec.edu.espol.proyectop1.clases.Vendedor;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicolassierra
 */
public class ProyectoP1 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) { // Mientras la opción no sea "Salir"
            System.out.println("----- MENÚ PRINCIPAL -----");
            System.out.println("1. Vendedor");
            System.out.println("2. Comprador");
            System.out.println("3. Salir");
            System.out.println("--------------------------");
            System.out.println("Ingrese su opción:");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del salto de línea

            switch (opcion) {
                case 1:
                    Vendedor vendedorMenu = new Vendedor( 0, "nombres",  "apellidos",  "organizacion",  "correo", " clave");
                    vendedorMenu.vMenu();
                    break;
                case 2:
                    Comprador compradorMenu = new Comprador(0, "nombres", "apellidos","organizacion", "correo", "clave");
                    compradorMenu.cMenu();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
}