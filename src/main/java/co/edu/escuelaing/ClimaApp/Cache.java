package co.edu.escuelaing.ClimaApp;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private static Map<String, String> cache ;
	
	public Cache() {
		cache = new HashMap<String, String>();
	}
	
	public void ponerDato(String key, String clave) {
		cache.put(key, clave);
	}
	
	public String traerDato( String key) {
		return cache.get(key);
	}
	
	public Boolean estaContenido(String key) {
		return cache.containsKey(key);
	}
	
}
