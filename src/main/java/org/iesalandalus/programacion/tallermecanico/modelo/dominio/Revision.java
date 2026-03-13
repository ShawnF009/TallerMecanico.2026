package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Revision {

    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final double PRECIO_HORA = 15.0;

    private final Cliente cliente;
    private final Vehiculo vehiculo;
    private final LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private double precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        if (cliente == null) throw new NullPointerException("El cliente no puede ser nulo.");
        if (vehiculo == null) throw new NullPointerException("El vehículo no puede ser nulo.");
        if (fechaInicio == null) throw new NullPointerException("La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
    }

    public Revision(Revision revision) {
        if (revision == null) throw new NullPointerException("La revisión no puede ser nula.");
        this.cliente = revision.cliente;
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
    }

    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public int getHoras() { return horas; }
    public double getPrecioMaterial() { return precioMaterial; }

    public void anadirHoras(int horas) {
        if (estaCerrada()) throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        if (horas <= 0) throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        this.horas += horas;
    }

    public void anadirPrecioMaterial(double precioMaterial) {
        if (estaCerrada()) throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        if (precioMaterial <= 0) throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        this.precioMaterial += precioMaterial;
    }

    public void cerrar(LocalDate fechaFin) {
        if (estaCerrada()) throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        if (fechaFin == null) throw new NullPointerException("La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        if (fechaFin.isBefore(fechaInicio)) throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        this.fechaFin = fechaFin;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public double getPrecio() {
        if (!estaCerrada()) return 0;
        long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();
        return (dias * horas * PRECIO_HORA) + precioMaterial;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Revision revision = (Revision) obj;
        return cliente.equals(revision.cliente) &&
                vehiculo.equals(revision.vehiculo) &&
                fechaInicio.equals(revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cliente, vehiculo, fechaInicio);
    }

    @Override
    public String toString() {
        String clienteStr = cliente.toString();
        String vehiculoStr = vehiculo.toString();
        String fechaInicioStr = fechaInicio.format(FORMATO_FECHA);
        if (estaCerrada()) {
            String fechaFinStr = fechaFin.format(FORMATO_FECHA);
            return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",
                    clienteStr, vehiculoStr, fechaInicioStr, fechaFinStr, horas, precioMaterial, getPrecio());
        } else {
            return String.format("%s - %s: (%s - ), %d horas, %.2f € en material",
                    clienteStr, vehiculoStr, fechaInicioStr, horas, precioMaterial);
        }
    }
}
