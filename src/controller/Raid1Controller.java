/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import model.Bloque;
import model.Disco;
import model.SO;

/**
 *
 * @author Vandal
 */
public class Raid1Controller {

    ArrayList<String> bloques;

    public Raid1Controller() {

    }

    public void generarBloques(File file) {
        try {

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
                    bloques.add(string.substring(i, i + 500));
                } else {
                    bloques.add(string.substring(i, string.length() - 1));
                }
                i += 500;
            }

            i = 1;
            for (String bloque : bloques) {
                System.out.println("bloque " + i + bloque + "\n");
                i++;
            }

        } catch (Exception e) {
        }
    }

    private void crearArchivos(File file) throws IOException {
        String cadena;
        String cadena1 = "RAIDS/RAID_1";
        File directorio = new File(cadena1);
        if(!directorio.exists()){
            directorio.mkdir();
        }
        int num_discos = 2;
        int i = 1;
        while(i <= num_discos){
            String cadena2 = "/DISCO_"+i;
            String cadenaAux = cadena1+cadena2;
            File sub_directorio = new File(cadenaAux);
            if(!sub_directorio.exists()){
                sub_directorio.mkdir();
            }
            String ruta = cadena1+"/"+cadena2+"/"+file.getName();

            File archivo = new File(ruta);
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                FileReader f = new FileReader(file);
                BufferedReader b = new BufferedReader(f);
                while ((cadena = b.readLine()) != null) {
                    bw.write(cadena);
                }
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                FileReader f = new FileReader(file);
                BufferedReader b = new BufferedReader(f);
                while ((cadena = b.readLine()) != null) {
                    bw.write(cadena);
                }
            }
            bw.close();
            i++;
        }

    }

    public void cargarArchivo(File file){
        try{
           this.crearArchivos(file); 
        }
        catch(Exception e){}
        
    }
    
    public String armar(String string) {
        String ruta1 = "RAIDS/RAID_1/DISCO_1/" + string;
        String ruta2 = "RAIDS/RAID_1/DISCO_2/" + string;
        ruta1= ruta1.trim();
        ruta2= ruta2.trim();
        File file1 = new File(ruta1);
        File file2 = new File(ruta2);
        String archivoCompleto = "";
        if (file1.exists()) {
            try {
                Scanner scan = new Scanner(file1);
                while (scan.hasNextLine()){
                    archivoCompleto += scan.nextLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (file1.exists()){
            try {
                Scanner scan = new Scanner(file2);
                while (scan.hasNextLine()){
                    archivoCompleto += scan.nextLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return archivoCompleto;
    }

}
