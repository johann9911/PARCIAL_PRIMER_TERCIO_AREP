package co.edu.escuelaing.ClimaApp;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void deberiaConsultarQueryBogota()
    {
    	ClimaApp clima = new ClimaApp();
    	
        try {
			assertTrue(clima.climaQuery("bogota").equals("{\"coord\":{\"lon\":-74.0817,\"lat\":4.6097},\"weather\":"
					+ "[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\""
					+ ":\"stations\",\"main\":{\"temp\":293.15,\"feels_like\":288.24,\"temp_min\":293.15,\"temp_max\""
					+ ":293.15,\"pressure\":1023,\"humidity\":49},\"visibility\":10000,\"wind\":{\"speed\":6.69,\"deg\":280},"
					+ "\"clouds\":{\"all\":75},\"dt\":1613592650,\"sys\":{\"type\":1,\"id\":8582,\"country\":\"CO\",\"sunrise"
					+ "\":1613560256,\"sunset\":1613603399},\"timezone\":-18000,\"id\":3688689,\"name\":\"Bogotá\",\"cod\":200}"));
		} catch (IOException e) {
			assertTrue(false);
		}
    }
}
