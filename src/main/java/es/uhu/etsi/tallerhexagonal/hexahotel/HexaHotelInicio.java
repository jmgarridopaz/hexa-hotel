package es.uhu.etsi.tallerhexagonal.hexahotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class HexaHotelInicio {
	
	public static void main(String[] args) {
		SpringApplication.run(HexaHotelInicio.class, args);
	}

}
