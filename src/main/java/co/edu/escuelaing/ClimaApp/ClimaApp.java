package co.edu.escuelaing.ClimaApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class ClimaApp
{
	Cache cache;
	
	public ClimaApp() {
		super();
		cache = new Cache();
	}
	
	/**
	 * Main method of the application
	 * @param args
	 */
	public static void main(String[] args) {
		ClimaApp cserver = new ClimaApp();
		try {
			cserver.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startServer() throws IOException  {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}

		boolean running = true;
		while (running) {
			Socket clientSocket = null;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("GET")) {
					String resource = inputLine.split(" ")[1];
					URL myurl = new URL("http://"+resource);
					if(myurl.getQuery()!=null) {
						String vlugar = myurl.getQuery().split("=")[0];
						if(vlugar.equals("lugar")) {
							String lugar = myurl.getQuery().split("=")[1];
							if(cache.estaContenido(lugar)) {
								out.println(imprimir(cache.traerDato(lugar)));
							}else {
								try {
									out.println(imprimir(climaQuery(lugar)));
								}catch(IOException e) {
									out.println(imprimir("error ciudad no encontrada"));
								}
								
							}
						}
					}
					
				}
				if (!in.ready()) {
					break;
				}
			}
			
			out.close();
			in.close();
			clientSocket.close();
		}
		serverSocket.close();
		
	}
	
	private String imprimir(String query) {
		return "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n" 
				+ "<!DOCTYPE html>\n"
				+ "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
				+ "<title>Title of the document</title>\n" + "</head>\n" + "<body>\n"
				+ query + "</body>\n" + "</html>\n";
	}
	
	public String climaQuery(String lugar) throws IOException {
		  String resultado = "";
		  URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+lugar+"&appid=5a8839a20989072f63e7849b48f659a0");

		  // Abrir la conexión e indicar que será de tipo GET
		  HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		  conexion.setRequestMethod("GET");
		  // Búferes para leer
		  BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		  String linea;
		  // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		  while ((linea = rd.readLine()) != null) {
		    resultado+=linea;
		  }
		  // Cerrar el BufferedReader
		  rd.close();
		  cache.ponerDato(lugar, resultado);
		  return resultado;
	}

	/**
	 * 
	 * This method is used to get to port of system
	 * @return the port of system or 35000 if is empty
	 */
	private int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 35000; // returns default port if heroku-port isn't set(i.e. on localhost)
	}
}
