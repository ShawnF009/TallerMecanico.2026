package org.iesalandalus.programacion.tallermecanico.Controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import java.time.LocalDate;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void comenzar() {
        vista.getClass(this);
        vista.comenzar();
    }

    public void terminar() {
        vista.terminar();
    }

    public void insertar(Cliente cliente) {
        modelo.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) {
        modelo.insertar(vehiculo);
    }

    public void insertar(Revision revision) {
        modelo.insertar(revision);
    }

    public Cliente buscar(Cliente cliente) {
        return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modelo.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return modelo.buscar(revision);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        return modelo.modificar(cliente, nombre, telefono);
    }

    public Revision anadirHoras(Revision revision, int horas) {
        return modelo.anadirHoras(revision, horas);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) {
        return modelo.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        return modelo.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) {
        modelo.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        modelo.borrar(vehiculo);
    }

    public void borrar(Revision revision) {
        modelo.borrar(revision);
    }

    public Cliente[] getClientes() {
        return modelo.getClientes();
    }

    public Vehiculo[] getVehiculos() {
        return modelo.getVehiculos();
    }

    public Revision[] getRevisiones() {
        return modelo.getRevisiones();
    }

    public Revision[] getRevisiones(Cliente cliente) {
        return modelo.getRevisiones(cliente);
    }

    public Revision[] getRevisiones(Vehiculo vehiculo) {
        return modelo.getRevisiones(vehiculo);
    }
}