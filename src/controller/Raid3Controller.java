/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bloque;
import model.TipoRaid;

/**
 *
 * @author Vandal
 */
public class Raid3Controller {

    ArrayList<String> infoArchivoParidad;
    int numDiscos = 4;

    private class BloqueRaid3 extends Bloque implements Serializable {

        public BloqueRaid3(int numero, int numeroTotal, TipoRaid tipo, String contenido, String nombreArchivo) {
            super(numero, numeroTotal, tipo, contenido, nombreArchivo);
        }

    }

    public Raid3Controller() {
    }

    public void crearDirectorios(int raid, int disco) {
        String cadena1 = "RAIDS/RAID_" + raid + "/";
        File directorio = new File(cadena1);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        cadena1 = "RAIDS/RAID_" + raid + "/" + "DISCO_" + disco + "/";
        directorio = new File(cadena1);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
    }

    public void cargarArchivo(File file) {
        try {
            this.crearArchivos(file);
        } catch (Exception e) {
        }

    }

    private void crearArchivos(File file) {
        this.crearDirectorios(3, 1);
        this.crearDirectorios(3, 2);
        this.crearDirectorios(3, 3);
        this.crearDirectorios(3, 4);

        StringBuilder sb = new StringBuilder();
        Scanner in;
        try {
            in = new Scanner(new FileReader(file));
            while (in.hasNext()) {
                sb.append(in.nextLine() + "\n");
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Raid3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        String string = sb.toString();
        this.construirArchivoDeParidad(string);

        this.crearArchivos(string, file);

    }

    private void construirArchivoDeParidad(String string) {
        this.infoArchivoParidad = new ArrayList<>();
        byte[] byteValueAscii;
        try {
            byteValueAscii = string.getBytes("US-ASCII");
            System.out.println(Arrays.toString(byteValueAscii));
            for (int i = 0; i < byteValueAscii.length; i++) {
                System.out.println(Integer.toBinaryString(byteValueAscii[i]));
                String binario = Integer.toBinaryString(byteValueAscii[i]);
                String pi = calcularParidad(binario);
                String paridadi = pi + binario;

                this.infoArchivoParidad.add(paridadi);
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Raid3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String calcularParidad(String binario) {
        int cont = 0;
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(i) == '1') {
                cont++;
            }
        }

        return Integer.toString(cont % 2);
    }

    private void crearArchivos(String string, File file) {
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            String ruta = "RAIDS/RAID_3/DISCO_" + (i % 3 + 1) + "/" + i + file.getName();
            BloqueRaid3 bloque = new Raid3Controller.BloqueRaid3(i, string.length(), TipoRaid.RAID0, string.substring(i, i + 1), file.getName());
            File archivo;

            archivo = new File(ruta);
            BufferedWriter bw;

            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(string.substring(i, i + 1));
                bw.close();

            } catch (IOException ex) {
                Logger.getLogger(Raid3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (i % 3 == 2 || i == string.length()-1) {
                int ini = i - (i%3);
                String rutaParidad = "RAIDS/RAID_3/DISCO_4" + "/p"+ ini + "_" + i + file.getName();
                File archivoParidad;
                archivoParidad = new File(rutaParidad);
                BufferedWriter bwp;
                try {
                    bwp = new BufferedWriter(new FileWriter(archivoParidad));
                    for (int k = ini ; k <= i; k++) {
                        bwp.write(this.infoArchivoParidad.get(k) + "\n");

                    }
                    bwp.close();
                } catch (IOException ex) {
                    Logger.getLogger(Raid3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
