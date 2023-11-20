package es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driving;

import java.time.LocalDate;


public interface RealizarReservasPort {

    public String reservarHabitacion ( LocalDate hoy, String emailUsuario, LocalDate desde, LocalDate hasta );

}
