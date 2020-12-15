package com.teamsolemne.pruebaapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WA {
    public static void main(String[] args){
        try {
            // Preparas la URL de consulta para la API
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
                
                // Desde data_obj obtengo el array de paises
                JSONArray arr_meals = (JSONArray) data_obj.get("meals");
                
                // Me paseo por el array de paises
                for (int i = 0; i < arr_meals.size(); i++) {
                    // Obtengo el item de cada iteracion para acortar un poco el codigo
                    JSONObject obj_meal = (JSONObject) arr_meals.get(i);
                    
                    System.out.format("Categoria: %s\n", obj_meal.get("strCategory"));
                    System.out.format("Area: %s\n", obj_meal.get("strArea"));
                    System.out.format("Instrucciones: %s\n", obj_meal.get("strInstructions"));

                    System.out.format("Ingrediente1: %s\n", obj_meal.get("strIngredient1"));
                    System.out.format("Ingrediente2: %s\n", obj_meal.get("strIngredient2"));
                    System.out.format("Ingrediente3: %s\n", obj_meal.get("strIngredient3"));
                    System.out.format("Ingrediente4: %s\n", obj_meal.get("strIngredient4"));
                    System.out.format("Ingrediente5: %s\n", obj_meal.get("strIngredient5"));
                    System.out.format("Ingrediente6: %s\n", obj_meal.get("strIngredient6"));
                    System.out.format("Ingrediente7: %s\n", obj_meal.get("strIngredient7"));
                    System.out.format("Ingrediente8: %s\n", obj_meal.get("strIngredient8"));
                    System.out.format("Ingrediente9: %s\n", obj_meal.get("strIngredient9"));
                    System.out.format("Ingrediente10: %s\n", obj_meal.get("strIngredient10"));
                    System.out.format("Ingrediente11: %s\n", obj_meal.get("strIngredient11"));
                    System.out.format("Ingrediente12: %s\n", obj_meal.get("strIngredient12"));
                    System.out.format("Ingrediente13: %s\n", obj_meal.get("strIngredient13"));
                    System.out.format("Ingrediente14: %s\n", obj_meal.get("strIngredient14"));
                    System.out.format("Ingrediente15: %s\n", obj_meal.get("strIngredient15"));
                    System.out.format("Ingrediente16: %s\n", obj_meal.get("strIngredient16"));
                    System.out.format("Ingrediente17: %s\n", obj_meal.get("strIngredient17"));
                    System.out.format("Ingrediente18: %s\n", obj_meal.get("strIngredient18"));
                    System.out.format("Ingrediente19: %s\n", obj_meal.get("strIngredient19"));
                    System.out.format("Ingrediente20: %s\n", obj_meal.get("strIngredient20"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
