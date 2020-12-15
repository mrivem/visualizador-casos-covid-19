package com.teamsolemne.pruebaapi.controller;

import com.teamsolemne.pruebaapi.model.API;
import com.teamsolemne.pruebaapi.model.StatsCountry;
import com.teamsolemne.pruebaapi.model.StatsGlobal;
import com.teamsolemne.pruebaapi.view.P00_StatsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

public class Controlador_C00 implements ActionListener {
    
    private final P00_StatsView frame;
    private StatsGlobal statsGlobal;
    private StatsCountry[] statsCountries;
    
    
    public Controlador_C00 (P00_StatsView frame){
        this.frame = frame;
        setListeners();
    }
    
    public void iniciar(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Visualización casos COVID-19 - Matías Rivera Maldonado");
        
        frame.jPanel_informacion.setVisible(false);
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    private void setListeners(){
        frame.jButton_actualizar.addActionListener(this);
        frame.jComboBox1.addActionListener(this);
    }
    
    private String getGlobalContents(){
        String s = "";
        
        String[] date = statsGlobal.getDate().split("T");
        s += "Ultima fecha de actualización: %s a las %s hrs.\n".formatted(date[0], date[1].replace("Z", ""));
        s += "\n";
        s += "Casos nuevos confirmados: %s\n".formatted(formatNumber(statsGlobal.getNewConfirmed()));
        s += "Muertes nuevas confirmadas: %s\n".formatted(formatNumber(statsGlobal.getNewDeaths()));
        s += "Recuperados nuevos confirmados: %s\n".formatted(formatNumber(statsGlobal.getNewRecovered()));
        s += "\n";
        s += "Casos totales confirmados: %s\n".formatted(formatNumber(statsGlobal.getTotalConfirmed()));
        s += "Muertes totales confirmadas: %s\n".formatted(formatNumber(statsGlobal.getTotalDeaths()));
        s += "Recuperados totales confirmados: %s\n".formatted(formatNumber(statsGlobal.getTotalRecovered()));
        return s;
    }
    
    private String getCountryContents(int id){
        String s = "" ;
        
        StatsCountry sc = statsCountries[id];
        
        String[] date = sc.getDate().split("T");
        s += "Ultima fecha de actualización: %s a las %s hrs.\n".formatted(date[0], date[1].replace("Z", ""));
        s += "\n";
        s += "Casos nuevos confirmados: %s\n".formatted(formatNumber(sc.getNewConfirmed()));
        s += "Muertes nuevas confirmadas: %s\n".formatted(formatNumber(sc.getNewDeaths()));
        s += "Recuperados nuevos confirmados: %s\n".formatted(formatNumber(sc.getNewRecovered()));
        s += "\n";
        s += "Casos totales confirmados: %s\n".formatted(formatNumber(sc.getTotalConfirmed()));
        s += "Muertes totales confirmadas: %s\n".formatted(formatNumber(sc.getTotalDeaths()));
        s += "Recuperados totales confirmados: %s\n".formatted(formatNumber(sc.getTotalRecovered()));
        
        return s;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.jButton_actualizar){
            // Obtengo datos desde la api
            statsGlobal = API.getGlobalData();
            statsCountries = API.getCountriesData();
            // Lleno el campo de el combo box
            String[] comboContents = new String[statsCountries.length];
            for(int i = 0; i < statsCountries.length; i++){
                comboContents[i] = statsCountries[i].getCountryName();
            }
            frame.jComboBox1.setModel(new DefaultComboBoxModel(comboContents));
            // Lleno los campos de texto de informacion
            frame.jTextArea1.setText(getGlobalContents());
            frame.jComboBox1.setSelectedIndex(0);
            frame.jTextArea2.setText(getCountryContents(0));
            // Hago visible el panel de informacion
            frame.jPanel_informacion.setVisible(true);
        }
        
        if(e.getSource() == frame.jComboBox1){            
            int id = frame.jComboBox1.getSelectedIndex();
            frame.jTextArea2.setText(getCountryContents(id));
        }
    }
    
    private String formatNumber(long number){
        return NumberFormat.getNumberInstance().format(number);
    }
    
}
