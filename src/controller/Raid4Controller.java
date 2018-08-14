/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import model.Bloque;
import model.TipoBloque;
import model.TipoRaid;

/**
 *
 * @author Vandal
 */
public class Raid4Controller implements Serializable{

    ArrayList<String> segmentos = new ArrayList<String>();

    private class BloqueRaid4 extends Bloque implements Serializable {

        TipoBloque tipoB;

        public BloqueRaid4(int numero, int numeroTotal, TipoRaid tipo, String contenido, String nombreArchivo, TipoBloque tipoB) {
            super(numero, numeroTotal, tipo, contenido, nombreArchivo);
            this.tipoB = tipoB;
        }

    }

    private void repartirArchivo(File file) throws IOException {
        this.crearDirectorios(4, 1);
        this.crearDirectorios(4, 2);
        this.crearDirectorios(4, 3);
        this.generarSegmentos(file);
        for (int i = 0; i < segmentos.size(); i++) {
            this.crearArchivosSegmento(i, file.getName());
        }
    }

    public void crearArchivosSegmento(int iteracion, String nombre) {
        String segmento = this.segmentos.get(iteracion);
        if (segmento.length() % 2 == 0) {
            segmento = segmento += " ";
        }
        String seg1 = this.convertirABinario(segmento.substring(0, segmento.length() / 2));
        String seg2 = this.convertirABinario(segmento.substring(segmento.length() / 2, segmento.length()));
        String paridad = xor(seg1, seg2);
        String ruta1 = "RAIDS/RAID_4/DISCO_1/" + iteracion + nombre;
        String ruta2 = "RAIDS/RAID_4/DISCO_2/" + iteracion + nombre;
        String ruta3 = "RAIDS/RAID_4/DISCO_3/p" + iteracion + nombre;
        BloqueRaid4 bloque1 = new Raid4Controller.BloqueRaid4(iteracion, segmentos.size(), TipoRaid.RAID4, seg1, nombre, TipoBloque.NORMAL);
        BloqueRaid4 bloque2 = new Raid4Controller.BloqueRaid4(iteracion, segmentos.size(), TipoRaid.RAID4, seg2, nombre, TipoBloque.NORMAL);
        BloqueRaid4 bloque3 = new Raid4Controller.BloqueRaid4(iteracion, segmentos.size(), TipoRaid.RAID4, paridad, nombre, TipoBloque.PARIDAD);
        File archivo1 = new File(ruta1);
        File archivo2 = new File(ruta2);
        File archivo3 = new File(ruta3);
        guardarArchivo(bloque1, archivo1);
        guardarArchivo(bloque2, archivo2);
        guardarArchivo(bloque3, archivo3);
    }

    private void guardarArchivo(BloqueRaid4 bloque, File archivo) {
        FileOutputStream fileOut;
        ObjectOutputStream out;
        if (archivo.exists() == false) {
            try {
                fileOut = new FileOutputStream(archivo);
                out = new ObjectOutputStream(fileOut);
                out.writeObject(bloque);
                out.close();
                fileOut.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    String xor(String s1, String s2) {
        String res = "";
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                res += "0";
            } else {
                res += "1";
            }
        }
        return res;
    }

    public String convertirABinario(String string) { //can not ºº×Ö
        char[] strChar = string.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {

            String temp = Integer.toBinaryString(strChar[i]);
            int k = 0;
            while (8 - k >= temp.length()) {
                k++;
                temp = "0" + temp;
            }
            result += temp + "";
        }
        return result;
    }

    public void cargarArchivo(File file) {
        this.segmentos.clear();
        try {
            this.repartirArchivo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void generarSegmentos(File file) {
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
}
