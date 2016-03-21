package com.italcred.devel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ProcesaWS {
	
	private static final String[] dictionary = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	
	public static void Procesar(){

		try{
			BufferedReader br;
			HttpURLConnection connection;
			String entrada;
			String str_url;
			URL url;
			JSONParser parser;
			String newResponse;
			String response;
			ArrayList<Locacion> locaciones = new ArrayList<>();			
			for (int index = 0; index < dictionary.length; index++){
				entrada = dictionary[index];
				str_url = "http://geo.correo.com.uy/servicios/SugerenciaCalleCompleta?entrada="+entrada+"&tipoRespuesta=json";
				url = new URL(str_url);
				connection = (HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Content-Type", "text/html");
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				response = null;
				newResponse = "";
				while((response = br.readLine()) != null){
					newResponse += response;
				}
				br.close();
				connection.disconnect();

				parser = new JSONParser();
				JSONArray jarray = (JSONArray)parser.parse(newResponse);
				Iterator<Object> it = jarray.iterator();
				while (it.hasNext()){
					JSONObject jobject = (JSONObject)it.next();
					locaciones.add(new Locacion((String)jobject.get("departamento"), 
							(String)jobject.get("localidad"), (String)jobject.get("calle")));
				}
			}
			DireccionesToExcel.generarPlanilla(locaciones);			
		}catch(IOException | ParseException ex){
			ex.printStackTrace(System.err);
		}

	}	
}
