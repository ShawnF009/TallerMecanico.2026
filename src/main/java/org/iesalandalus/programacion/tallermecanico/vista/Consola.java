package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println("\n" + "=".repeat(mensaje.length()));
        System.out.println(mensaje);
        System.out.println("=".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        System.out.println("\n--- MENÚ ---");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
        System.out.print("Elige una opción: ");
    }

    public static Opcion elegirOpcion() {
        int numero = -1;
        do {
            mostrarMenu();
            try {
                numero = Entrada.entero();
                if (!Opcion.esValida(numero)) {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número.");
            }
        } while (!Opcion.esValida(numero));
        return Opcion.get(numero);
    }

    private static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            try {
                valor = Entrada.entero();
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número entero.");
            }
        } while (!valido);
        return valor;
    }

    private static float leerReal(String mensaje) {
        float valor = 0;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            try {
                valor = Entrada.entero();
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número decimal.");
            }
        } while (!valido);
        return valor;
    }

    private static String leerCadena(String mensaje) {
        String cadena = "";
        do {
            System.out.print(mensaje);
            cadena = Entrada.cadena();
            if (cadena.isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            }
        } while (cadena.isEmpty());
        return cadena;
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        do {
            System.out.print(mensaje + " (" + CADENA_FORMATO_FECHA + "): ");
            try {
                fecha = LocalDate.ofEpochDay(Entrada.entero());
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Usa: " + CADENA_FORMATO_FECHA);
            }
        } while (fecha == null);
        return fecha;
    }

    public static Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre del cliente: ");
        String dni = leerCadena("Introduce el DNI del cliente: ");
        String telefono = leerCadena("Introduce el teléfono del cliente: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("Introduce el DNI del cliente: ");
        return new Cliente(null, dni, null);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono: ");
    }

    public static Vehiculo leerVehiculo() {
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        String marca = leerCadena("Introduce la marca del vehículo: ");
        String modelo = leerCadena("Introduce el modelo del vehículo: ");
        return new Vehiculo(matricula, marca, modelo);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return new Vehiculo(matricula, null, null);
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la revisión");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce el número de horas: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre");
    }
}