package com.teamsolemne.pruebaapi;

import com.teamsolemne.pruebaapi.controller.Controlador_C00;
import com.teamsolemne.pruebaapi.view.P00_StatsView;

public class Main {
    public static void main(String[] args){
        Controlador_C00 c = new Controlador_C00(new P00_StatsView());
        c.iniciar();
    }
}
