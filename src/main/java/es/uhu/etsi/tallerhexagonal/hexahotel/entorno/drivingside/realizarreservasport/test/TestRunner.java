package es.uhu.etsi.tallerhexagonal.hexahotel.entorno.drivingside.realizarreservasport.test;

import java.io.File;
import java.time.Instant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.testng.TestNG;


@Component
@ConditionalOnProperty(name = "RealizarReservasPort", havingValue = "test")
public class TestRunner implements CommandLineRunner {

	private static final String OUTPUT_DIRECTORY =	System.getProperty("user.home") + File.separator
													+ ".hexahotel" + File.separator
													+ "test-output" + File.separator
													+ Instant.now().toEpochMilli();
	public TestRunner() {
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==========================================================");
		System.out.println("Ejecución de los tests para el puerto 'Realizar Reservas'");
		System.out.println("Utiliza el framework TestNG");
		System.out.println("Se generarán informes HTML en el siguiente directorio:");
		System.out.println(OUTPUT_DIRECTORY);
		System.out.println("==========================================================");
		TestNG testNG = new TestNG();
		testNG.setOutputDirectory ( OUTPUT_DIRECTORY );
		testNG.setTestClasses ( testClasses() );
		testNG.run();
	}

	private Class[] testClasses() {
		return new Class[] { RealizarReservasTests.class };
	}

}
