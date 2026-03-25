package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {}

    public static void mostrarCabecera(String mensaje) {
        System.out.println("\n" + mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Taller Mecánico");
        for (Opcion o : Opcion.values()) {
            System.out.println(o);
        }
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    public static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        do {
            try {
                String cadena = leerCadena(mensaje);
                fecha = LocalDate.parse(cadena, DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA));
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Formato de fecha incorrecto. Usa " + CADENA_FORMATO_FECHA + ".");
            }
        } while (fecha == null);
        return fecha;
    }

    public static Opcion elegirOpcion() {
        int numero;
        do {
            numero = leerEntero("Elige una opción: ");
            if (!Opcion.esValida(numero)) {
                System.out.println("ERROR: Opción no válida. Inténtalo de nuevo.");
            }
        } while (!Opcion.esValida(numero));
        return Opcion.get(numero);
    }

    public static Cliente leerCliente() {
        String nombre = leerCadena("Nombre del cliente: ");
        String dni = leerCadena("DNI del cliente: ");
        String tlf = leerCadena("Teléfono del cliente: ");
        return new Cliente(nombre, dni, tlf);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("DNI del cliente: ");
        return Cliente.get(dni);
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Marca: ");
        String modelo = leerCadena("Modelo: ");
        String matricula = leerCadena("Matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Matrícula del vehículo: ");
        return Vehiculo.get(matricula);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Nuevo nombre (vacío para no cambiar): ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Nuevo teléfono (vacío para no cambiar): ");
    }

    public static int leerHoras() {
        return leerEntero("Horas a añadir: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Precio del material: ");
    }

    public static LocalDate leerFechaInicio() {
        return leerFecha("Fecha de inicio (" + CADENA_FORMATO_FECHA + "): ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Fecha de cierre (" + CADENA_FORMATO_FECHA + "): ");
    }
}