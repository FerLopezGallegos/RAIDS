/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import model.Bloque;
import model.TipoRaid;

/**
 *
 * @author Vandal
 */
public class Raid0Controller {

    ArrayList<String> segmentos = new ArrayList<>();

    private class BloqueRaid0 extends Bloque implements Serializable{

        public BloqueRaid0(int numero, int numeroTotal, TipoRaid tipo, String contenido, String nombreArchivo) {
            super(numero, numeroTotal, tipo, contenido, nombreArchivo);
        }

    }

    public Raid0Controller() {

    }

    private void repartirArchivo(File file) throws IOException {
        this.crearDirectorios(0, 1);
        this.crearDirectorios(0, 2);
        String ruta;
        BloqueRaid0 bloque;
        this.generarBloque(file);
        FileOutputStream fileOut;
        ObjectOutputStream out;
        File archivo;
        for (int i = 0; i < segmentos.size(); i++) {
            ruta = "RAIDS/RAID_0/DISCO_" + (i % 2+1) + "/" + i + file.getName();
            bloque = new BloqueRaid0(i, segmentos.size(), TipoRaid.RAID0, this.segmentos.get(i), file.getName());
            archivo = new File(ruta);
            if (archivo.exists()==false){
                try{
                    fileOut = new FileOutputStream(new File(ruta));
                    out = new ObjectOutputStream(fileOut);
                    out.writeObject(bloque);
                    out.close();
                    fileOut.close();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
                
        }
    }
    
    public void crearDirectorios(int raid, int disco){
        String cadena1 = "RAIDS/RAID_"+raid+"/";
        File directorio = new File(cadena1);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        cadena1 = "RAIDS/RAID_"+raid+"/"+"DISCO_"+disco+"/";
        directorio = new File(cadena1);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
    }

    public void generarBloque(File file) {
        try {
            this.segmentos = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            Scanner in = new Scanner(new FileReader(file));
            while (in.hasNext()) {
                sb.append(in.nextLine() + "\n");
            }
            in.close();
            String string = sb.toString();

            int i = 0;
            while (i < string.length()) {
                if (i + 500 < string.length()) {
                    segmentos.add(string.substring(i, i + 500));
                } else {
                    segmentos.add(string.substring(i, string.length() - 1));
                }
                i += 500;
            }

        } catch (Exception e) {
        }
    }

    public void cargarArchivo(File file) {
        try {
            this.repartirArchivo(file);
        } catch (Exception e) {
        }

    }

    public void armar(String string) {

    }

}
