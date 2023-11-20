package es.uhu.etsi.tallerhexagonal.hexahotel.entorno.drivenside.persistirreservasport.testdouble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.Reserva;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven.PersistirReservasPort;


public class ReservaFakeRepositorio implements PersistirReservasPort {

	private Map<String,Reserva> reservasPorCodigo;
	private AtomicLong id;

	
	public ReservaFakeRepositorio() {
		this.reservasPorCodigo = new HashMap<String, Reserva>();
		this.id = new AtomicLong(1);
	}

	@Override
	public String obtenerSiguienteCodigo() {
		return Long.toString(this.id.getAndIncrement());
	}

	@Override
	public void guardar(Reserva reserva) {
		this.reservasPorCodigo.put(reserva.getCodigo(), reserva);
	}

	@Override
	public List<Reserva> buscarPorHabitacion(String numeroHabitacion) {
		List<Reserva> resultado = new ArrayList<Reserva>();
		for ( Reserva reserva : this.reservasPorCodigo.values() ) {
			if ( reserva.getNumeroHabitacion().equals(numeroHabitacion) ) {
				resultado.add(reserva);
			}
		}
		return resultado;
	}

	public void setSiguienteCodigo(String codigo) {
		this.id = new AtomicLong(Long.valueOf(codigo));
	}

	public Reserva obtenerReserva(String codigoReserva) {
		return this.reservasPorCodigo.get(codigoReserva);		
	}

}
