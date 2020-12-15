package com.teamsolemne.pruebaapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestAPI {
    
    public static JSONObject GET_RANDOM_MEAL(){
        try {
            // Preparas la URL de consulta para la API
            //URL url = new URL("https://api.covid19api.com/summary");
            URL url = new URL("https://www.themealdb.com/api/json/v1/1/random.php");
            
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

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args){
        System.out.println(GET_RANDOM_MEAL());
        
        /*try {
            // Preparas la URL de consulta para la API
            //URL url = new URL("https://api.covid19api.com/summary");
            URL url = new URL("https://www.themealdb.com/api/json/v1/1/random.php");
            
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
                
                System.out.println(data_obj);
                /*
                // Desde data_obj obtengo el array de paises
                JSONArray arr_paises = (JSONArray) data_obj.get("Countries");
                
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
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
