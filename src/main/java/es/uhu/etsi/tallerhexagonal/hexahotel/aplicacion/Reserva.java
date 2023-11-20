package es.uhu.etsi.tallerhexagonal.hexahotel.aplicacion;

import java.math.BigDecimal;
import java.util.Objects;


public class Reserva {

    private final String codigo;
    private final String emailUsuario;
    private final String numeroHabitacion;
    private final Estancia estancia;
    private BigDecimal precio;

    public Reserva(String codigo, String emailUsuario, String numeroHabitacion, Estancia estancia) {
        this.codigo = codigo;
        this.emailUsuario = emailUsuario;
        this.numeroHabitacion = numeroHabitacion;
        this.estancia = estancia;
    }

    public void calcularPrecio ( BigDecimal eurosNoche ) {
        this.precio = eurosNoche.multiply(BigDecimal.valueOf(this.estancia.numeroDeNoches()));
    }

    public boolean estanciaSeSolapaCon ( Estancia otraEstancia ) {
        return this.estancia.seSolapaCon(otraEstancia);
    }    
    

	public String getCodigo() {
		return codigo;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public String getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public Estancia getEstancia() {
		return estancia;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	@Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Reserva that = (Reserva) other;
        return Objects.equals(this.codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return this.codigo.hashCode();
    }

}
