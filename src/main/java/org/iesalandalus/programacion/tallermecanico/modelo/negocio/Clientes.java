package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Clientes {

    private List<Cliente> coleccionClientes;

    public Clientes(){
        this.coleccionClientes = new ArrayList<>();
    }

    public List<Cliente> get(){
        return coleccionClientes;
    }
    public void insertar(Cliente cliente){
            Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");

    }
    public Cliente modificar(Cliente cliente, String nombre, String telefono){
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        return cliente;
    }
    public Cliente buscar(Cliente cliente){
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        return cliente;
    }
    public void borrar(Cliente cliente){
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
    }
}
