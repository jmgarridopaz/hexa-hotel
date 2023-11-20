package es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven;

import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.Reserva;

import java.util.List;


public interface PersistirReservasPort {

    public String obtenerSiguienteCodigo();
    public void guardar(Reserva reserva);
    public List<Reserva> buscarPorHabitacion(String numeroHabitacion);

}
