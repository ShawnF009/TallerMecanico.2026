package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public record Vehiculo(String marca, String modelo, String matricula) {

    private static final String ER_MARCA = "^[A-Z][a-zA-Z0-9\\s-]{1,29}$";
    private static final String ER_MATRICULA = "^\\d{4}[B-DF-HJ-NPR-TV-Z]{3}$";

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    public static void validarMarca(String marca) {
        if (marca == null) throw new NullPointerException("La marca no puede ser nula.");
        String m = marca.trim();
        if (m.isEmpty() || !m.matches(ER_MARCA)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    public static void validarModelo(String modelo) {
        if (modelo == null) throw new NullPointerException("El modelo no puede ser nulo.");
        String m = modelo.trim();
        if (m.isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    public static void validarMatricula(String matricula) {
        if (matricula == null) throw new NullPointerException("La matrícula no puede ser nula.");
        String m = matricula.trim().toUpperCase();
        if (m.isEmpty() || !m.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public static Vehiculo get(String matricula) {
        validarMatricula(matricula);
        return new Vehiculo("Sin marca", "Sin modelo", matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) obj;
        return matricula.equals(vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    @Override
    public String toString() {
        return "%s %s - %s".formatted(marca, modelo, matricula);
    }
}


