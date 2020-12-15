package com.teamsolemne.pruebaapi.model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API {
    
    public static JSONObject getCovidData(){
        try {
            // Preparas la URL de consulta para la API
            URL url = new URL("https://api.covid19api.com/summary");
            
            // Generas una conexion HTTP del tipo GET
            // Para otros tipos de conexion ver
            // https://www.restapitutorial.com/lessons/httpmethods.html
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect(); // Acá se ejecuta la consulta

            // Obtengo el codigo de respuesta (200 es todo OK)
            // Para otros codigos de respuesta ver
            // https://www.restapitutorial.com/httpstatuscodes.html
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                // Si la consulta retorna algo que no sea OK, lanza error (sale del bloque 'try' y entra al 'catch' mas abajo
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                // Variables para leer la respuesta
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                
                // El scanner escribe la respuesta JSON al String linea por linea
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                // Cierro el scanner
                scanner.close();

                // Uso JSON simple (la libreria importada) para convertir el String a un JSONObject
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);
                
                return data_obj;
                
                // Desde data_obj obtengo el array de paises
                // JSONArray arr_paises = (JSONArray) data_obj.get("Countries");
                /*
                // Me paseo por el array de paises
                for (int i = 0; i < arr_paises.size(); i++) {
                    // Obtengo el item de cada iteracion para acortar un poco el codigo
                    JSONObject obj_pais = (JSONObject) arr_paises.get(i);
                    // Si el nombre del pais es 'Chile'
                    if (obj_pais.get("Country").equals("Chile")) {
                        System.out.format("Estadisticas para Chile (ultima actualización: %s)\n", obj_pais.get("Date"));
                        
                        System.out.format("Nuevos confirmados: %s\n", obj_pais.get("NewConfirmed"));
                        System.out.format("Nuevos muertos: %s\n", obj_pais.get("NewDeaths"));
                        System.out.format("Nuevos recuperados: %s\n", obj_pais.get("NewRecovered"));
                        
                        System.out.format("Total confirmados: %s\n", obj_pais.get("TotalConfirmed"));
                        System.out.format("Total muertos: %s\n", obj_pais.get("TotalDeaths"));
                        System.out.format("Total recuperados: %s\n", obj_pais.get("TotalRecovered"));
                        
                        // Solo nos interesa la info de chile, si ya la imprimimos se sale del loop
                        // somos el mejor pais de chile
                        break;
                    }
                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static StatsGlobal getGlobalData(){
        return getGlobalData(getCovidData());
    }
    
    public static StatsGlobal getGlobalData(JSONObject jo){
        JSONObject jsonGlobal = (JSONObject) jo.get("Global");
        final long newConfirmed = (long) jsonGlobal.get("NewConfirmed");
        final long newDeaths = (long) jsonGlobal.get("NewDeaths");
        final long newRecovered = (long) jsonGlobal.get("NewRecovered");
        final long totalConfirmed = (long) jsonGlobal.get("TotalConfirmed");
        final long totalDeaths = (long) jsonGlobal.get("TotalDeaths");
        final long totalRecovered = (long) jsonGlobal.get("TotalRecovered");
        final String date = (String) jo.get("Date");
        return new StatsGlobal(date, newConfirmed, newDeaths, newRecovered, totalConfirmed, totalDeaths, totalRecovered);
    }
    
    public static StatsCountry[] getCountriesData(){
        return getCountriesData(getCovidData());
    }
    
    public static StatsCountry[] getCountriesData(JSONObject jo){
        JSONArray arr_paises = (JSONArray) jo.get("Countries");
        final int totalPaises = arr_paises.size();
        
        StatsCountry[] statsCountries = new StatsCountry[totalPaises];
        
        for (int i = 0; i < totalPaises; i++) {
            // Obtengo el item de cada iteracion para acortar un poco el codigo
            JSONObject obj_country = (JSONObject) arr_paises.get(i);
            
            final String countryName = (String) obj_country.get("Country");
            final String countryCode = (String) obj_country.get("CountryCode");
            final String countrySlug = (String) obj_country.get("CountrySlug");
            final String date = (String) obj_country.get("Date");
            final long newConfirmed = (long) obj_country.get("NewConfirmed");
            final long newDeaths = (long) obj_country.get("NewDeaths");
            final long newRecovered = (long) obj_country.get("NewRecovered");
            final long totalConfirmed = (long) obj_country.get("TotalConfirmed");
            final long totalDeaths = (long) obj_country.get("TotalDeaths");
            final long totalRecovered = (long) obj_country.get("TotalRecovered");
            
            statsCountries[i] = new StatsCountry(countryName, countryCode, countrySlug, date, newConfirmed, newDeaths, newRecovered, totalConfirmed, totalDeaths, totalRecovered);
        }
        return statsCountries;
    }
}
