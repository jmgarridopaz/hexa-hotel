package es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion;

import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven.ConsultarHabitacionesPort;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven.PersistirReservasPort;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driving.RealizarReservasPort;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class HexaHotel implements RealizarReservasPort {

    private final ConsultarHabitacionesPort habitacionRepositorio;
    private final PersistirReservasPort reservaRepositorio;

    public HexaHotel(ConsultarHabitacionesPort habitacionRepositorio, PersistirReservasPort reservaRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
        this.reservaRepositorio = reservaRepositorio;
    }

    @Override
    public String reservarHabitacion ( LocalDate hoy, String emailUsuario, LocalDate desde, LocalDate hasta ) {
        Estancia estancia = new Estancia(desde,hasta);
        if ( estancia.comienzaAntesDe(hoy) ) {
            throw new RuntimeException("La estancia no puede comenzar antes de la fecha actual");
        }
        String numeroHabitacion = buscarHabitacionDisponible(estancia);
        if (numeroHabitacion == null) {
            throw new RuntimeException("No existe habitación disponible para las fechas dadas");
        }
        String codigoReserva = this.reservaRepositorio.obtenerSiguienteCodigo();
        BigDecimal eurosNoche = this.habitacionRepositorio.obtenerPrecioNoche();
        Reserva reserva = new Reserva ( codigoReserva, emailUsuario, numeroHabitacion, estancia );
        reserva.calcularPrecio(eurosNoche);
        this.reservaRepositorio.guardar(reserva);
        return codigoReserva;
    }

    private String buscarHabitacionDisponible(Estancia estancia) {
        List<String> todasHabitaciones = this.habitacionRepositorio.obtenerTodas();
        for ( String numeroHabitacion : todasHabitaciones ) {
            if ( habitacionDisponible(numeroHabitacion,estancia) ) {
                return numeroHabitacion;
            }
        }
        return null;
    }

    private boolean habitacionDisponible ( String numeroHabitacion, Estancia estanciaRequerida ) {
        List<Reserva> reservasDeHabitacion = this.reservaRepositorio.buscarPorHabitacion(numeroHabitacion);
        for ( Reserva reserva : reservasDeHabitacion ) {
            if ( reserva.estanciaSeSolapaCon(estanciaRequerida) ) {
                return false;
            }
        }
        return true;
    }

}
