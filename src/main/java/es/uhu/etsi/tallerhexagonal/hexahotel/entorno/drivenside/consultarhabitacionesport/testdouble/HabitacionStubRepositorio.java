package es.uhu.etsi.tallerhexagonal.hexahotel.entorno.drivenside.consultarhabitacionesport.testdouble;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven.ConsultarHabitacionesPort;


public class HabitacionStubRepositorio implements ConsultarHabitacionesPort {

	private final BigDecimal precioNoche;
	private final List<String> habitaciones;
	
	
	public HabitacionStubRepositorio(BigDecimal precioNoche, String... habitaciones) {
		this.precioNoche = precioNoche;
		this.habitaciones = new ArrayList<String>();
		if ( habitaciones!=null && habitaciones.length>0 ) {
			for ( String habitacion : habitaciones ) {
				this.habitaciones.add(habitacion);
			}
		}
	}

	@Override
	public BigDecimal obtenerPrecioNoche() {
		return this.precioNoche;
	}

	@Override
	public List<String> obtenerTodas() {
		return this.habitaciones;
	}

}
