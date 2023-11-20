package es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion.ports.driven;

import java.math.BigDecimal;
import java.util.List;


public interface ConsultarHabitacionesPort {

    public BigDecimal obtenerPrecioNoche();
    public List<String> obtenerTodas();

}
