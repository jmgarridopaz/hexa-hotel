package es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion;

import java.time.LocalDate;
import java.util.Objects;
import static java.time.temporal.ChronoUnit.DAYS;


public class Estancia {

    private final LocalDate entrada;
    private final LocalDate salida;

    public Estancia(LocalDate entrada, LocalDate salida) {
        if ( entrada==null || salida==null ) {
            throw new RuntimeException("La entrada y la salida son obligatorias");
        }
        if ( ! entrada.isBefore(salida) ) {
            throw new RuntimeException("La entrada debe ser anterior a la salida");
        }
        this.entrada = entrada;
        this.salida = salida;
    }

    public LocalDate getEntrada() {
        return this.entrada;
    }

    public LocalDate getSalida() {
        return this.salida;
    }

    public long numeroDeNoches() {
        return DAYS.between(this.entrada,this.salida);
    }

    public boolean seSolapaCon(Estancia otraEstancia) {
        LocalDate otraEntrada = otraEstancia.getEntrada();
        LocalDate estaSalida = this.getSalida();
        if ( ! otraEntrada.isBefore(estaSalida) ) {
            return false;
        }
        LocalDate otraSalida = otraEstancia.getSalida();
        LocalDate estaEntrada = this.getEntrada();
        if ( ! otraSalida.isAfter(estaEntrada) ) {
            return false;
        }
        return true;
    }

    public boolean comienzaAntesDe ( LocalDate fecha ) {
        return this.entrada.isBefore(fecha);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Estancia that = (Estancia) other;
        return this.entrada.equals(that.entrada) && this.salida.equals(that.salida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.entrada,this.salida);
    }

    @Override
    public String toString() {
        return "Estancia { entrada = '" + this.entrada + "' , salida = '" + this.salida + "' }";
    }

}
