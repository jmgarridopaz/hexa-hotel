package es.uhu.etsi.tallerhexagonal.hexahotel.entorno.drivingside.realizarreservasport.test;

import org.testng.annotations.Test;

import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.Estancia;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.HexaHotel;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.Reserva;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven.ConsultarHabitacionesPort;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven.PersistirReservasPort;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driving.RealizarReservasPort;
import es.uhu.etsi.tallerhexagonal.hexahotel.entorno.drivenside.consultarhabitacionesport.testdouble.HabitacionStubRepositorio;
import es.uhu.etsi.tallerhexagonal.hexahotel.entorno.drivenside.persistirreservasport.testdouble.ReservaFakeRepositorio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;


public class RealizarReservasTests {

	@Test
	public void siempre_true() {
		assertThat("prueba",is("prueba"));
	}
	
	@Test
	public void deberia_reservar_la_unica_libre() {
		// given
		ConsultarHabitacionesPort habitacionRepositorio = new HabitacionStubRepositorio(new BigDecimal("50.00"),"101","102");
		ReservaFakeRepositorio reservaRepositorio = new ReservaFakeRepositorio();
		Reserva reserva101 = new Reserva("1", "prueba@gmail.com", "101", new Estancia(LocalDate.of(2023,Month.DECEMBER, 1),LocalDate.of(2023,Month.DECEMBER, 4)));
		Reserva reserva102 = new Reserva("2", "prueba2@gmail.com", "102", new Estancia(LocalDate.of(2023,Month.DECEMBER, 10),LocalDate.of(2023,Month.DECEMBER, 12)));
		reservaRepositorio.guardar(reserva101);
		reservaRepositorio.guardar(reserva102);
		reservaRepositorio.setSiguienteCodigo("3");
		// when
		RealizarReservasPort app = new HexaHotel(habitacionRepositorio, reservaRepositorio);
		String codigoReserva = app.reservarHabitacion(LocalDate.now(),"prueba3@gmail.com", LocalDate.of(2023,Month.DECEMBER, 3), LocalDate.of(2023,Month.DECEMBER, 7));
		// then
		Reserva reservaGuardada = reservaRepositorio.obtenerReserva(codigoReserva);
		assertThat(reservaGuardada.getCodigo(), is("3"));
		assertThat(reservaGuardada.getEmailUsuario(), is("prueba3@gmail.com"));
		assertThat(reservaGuardada.getNumeroHabitacion(), is("102"));
		assertThat(reservaGuardada.getEstancia(), is(new Estancia(LocalDate.of(2023,Month.DECEMBER, 3), LocalDate.of(2023,Month.DECEMBER, 7))));
		assertThat(reservaGuardada.getPrecio(), is(new BigDecimal("200.00")));		
	}
	
}
