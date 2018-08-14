/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class Raid6Controller implements Serializable {

    ArrayList<String> segmentos = new ArrayList<String>();

    public String armar(String string) {
        String ruta = "RAIDS/RAID_6/DISCO_1/0" + string;
        ruta = ruta.trim();
        File file = new File(ruta);
        Raid6Controller.BloqueRaid6 bloque = null;
        String archivoCompleto = "";
        if (file.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(new File(ruta));
                ObjectInputStream in = new ObjectInputStream(fileIn);
                bloque = (Raid6Controller.BloqueRaid6) in.readObject();
                in.close();
                fileIn.close();
                if (bloque != null) {
                    ArrayList<Raid6Controller.BloqueRaid6> completo = this.buscarArchivos(bloque);
                    for (int i = 0; i < completo.size(); i++) {
                        archivoCompleto += this.convertirAString(completo.get(i).getContenido());
                        System.out.println(archivoCompleto.length());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return archivoCompleto;
    }

    public ArrayList<Raid6Controller.BloqueRaid6> buscarArchivos(Raid6Controller.BloqueRaid6 inicio) {
        ArrayList<Raid6Controller.BloqueRaid6> completo = new ArrayList<>();
        int cantidad = inicio.getNumeroTotal();
        String ruta1="";
        String ruta2="";
        String ruta3="";
        String ruta4="";
        File file1;
        File file2;
        File file3;
        File file4;
        Raid6Controller.BloqueRaid6 nuevo;
        FileInputStream fileIn;
        ObjectInputStream in;
        for (int i = 0; i < cantidad; i++) {
            int discoParidad = i % 3;
            switch (discoParidad) {
                case 0:
                    discoParidad = 3;
                    break;
                case 1:
                    discoParidad = 2;
                    break;
                case 2:
                    discoParidad = 1;
                    break;
            }
            switch (discoParidad) {
                case 1:
                    ruta1 = "RAIDS/RAID_6/DISCO_1/p" + i + inicio.getNombreArchivo();
                    ruta2 = "RAIDS/RAID_6/DISCO_2/p" + i + inicio.getNombreArchivo();
                    ruta3 = "RAIDS/RAID_6/DISCO_3/" + i + inicio.getNombreArchivo();
                    ruta4 = "RAIDS/RAID_6/DISCO_4/" + i + inicio.getNombreArchivo();
                    break;
                case 2:
                    ruta1 = "RAIDS/RAID_6/DISCO_1/" + i + inicio.getNombreArchivo();
                    ruta2 = "RAIDS/RAID_6/DISCO_2/p" + i + inicio.getNombreArchivo();
                    ruta3 = "RAIDS/RAID_6/DISCO_3/p" + i + inicio.getNombreArchivo();
                    ruta4 = "RAIDS/RAID_6/DISCO_4/" + i + inicio.getNombreArchivo();
                    break;
                case 3:
                    ruta1 = "RAIDS/RAID_6/DISCO_1/" + i + inicio.getNombreArchivo();
                    ruta2 = "RAIDS/RAID_6/DISCO_2/" + i + inicio.getNombreArchivo();
                    ruta3 = "RAIDS/RAID_6/DISCO_3/p" + i + inicio.getNombreArchivo();
                    ruta4 = "RAIDS/RAID_6/DISCO_4/p" + i + inicio.getNombreArchivo();
                    break;
            }
            file1 = new File(ruta1);
            file2 = new File(ruta2);
            file3 = new File(ruta3);
            file4 = new File(ruta4);
            if (file1.exists() && file2.exists() && file3.exists() && file4.exists()) {
                try {
                    fileIn = new FileInputStream(file1);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid6Controller.BloqueRaid6) in.readObject();
                    if (nuevo.tipoB==TipoBloque.NORMAL) completo.add(nuevo);
                    fileIn = new FileInputStream(file2);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid6Controller.BloqueRaid6) in.readObject();
                    if (nuevo.tipoB==TipoBloque.NORMAL) completo.add(nuevo);
                    fileIn = new FileInputStream(file3);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid6Controller.BloqueRaid6) in.readObject();
                    if (nuevo.tipoB==TipoBloque.NORMAL) completo.add(nuevo);
                    fileIn = new FileInputStream(file4);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid6Controller.BloqueRaid6) in.readObject();
                    if (nuevo.tipoB==TipoBloque.NORMAL) completo.add(nuevo);
                    in.close();
                    fileIn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return completo;
    }

    private class BloqueRaid6 extends Bloque implements Serializable {

        TipoBloque tipoB;

        public BloqueRaid6(int numero, int numeroTotal, TipoRaid tipo, String contenido, String nombreArchivo, TipoBloque tipoB) {
            super(numero, numeroTotal, tipo, contenido, nombreArchivo);
            this.tipoB = tipoB;
        }

    }

    private void repartirArchivo(File file) throws IOException {
        this.crearDirectorios(6, 1);
        this.crearDirectorios(6, 2);
        this.crearDirectorios(6, 3);
        this.crearDirectorios(6, 4);
        this.generarSegmentos(file);
        for (int i = 0; i < segmentos.size(); i++) {
            this.crearArchivosSegmento(i, file.getName());
        }
    }

    public void crearArchivosSegmento(int iteracion, String nombre) {
        String segmento = this.segmentos.get(iteracion);
        int discoParidad = iteracion % 3;
        switch (discoParidad) {
            case 0:
                discoParidad = 3;
                break;
            case 1:
                discoParidad = 2;
                break;
            case 2:
                discoParidad = 1;
                break;
        }
        String seg1 = this.convertirABinario(segmento.substring(0, segmento.length() / 2));
        String seg2 = this.convertirABinario(segmento.substring(segmento.length() / 2, segmento.length()));
        String paridad = xor(seg1, seg2);
        String paridad1 = paridad.substring(iteracion, paridad.length()/2);
        String paridad2 = paridad.substring(paridad.length()/2, paridad.length());
        String ruta1 = "";
        String ruta2 = "";
        String ruta3 = "";
        String ruta4 = "";
        switch (discoParidad) {
            case 1:
                ruta1 = "RAIDS/RAID_6/DISCO_1/p" + iteracion + nombre;
                ruta2 = "RAIDS/RAID_6/DISCO_2/p" + iteracion + nombre;
                ruta3 = "RAIDS/RAID_6/DISCO_3/" + iteracion + nombre;
                ruta4 = "RAIDS/RAID_6/DISCO_4/" + iteracion + nombre;
                break;
            case 2:
                ruta1 = "RAIDS/RAID_6/DISCO_1/" + iteracion + nombre;
                ruta2 = "RAIDS/RAID_6/DISCO_2/p" + iteracion + nombre;
                ruta3 = "RAIDS/RAID_6/DISCO_3/P" + iteracion + nombre;
                ruta4 = "RAIDS/RAID_6/DISCO_4/" + iteracion + nombre;
                break;
            case 3:
                ruta1 = "RAIDS/RAID_6/DISCO_1/" + iteracion + nombre;
                ruta2 = "RAIDS/RAID_6/DISCO_2/" + iteracion + nombre;
                ruta3 = "RAIDS/RAID_6/DISCO_3/p" + iteracion + nombre;
                ruta4 = "RAIDS/RAID_6/DISCO_4/p" + iteracion + nombre;
                break;
        }
        BloqueRaid6 bloque1 = new Raid6Controller.BloqueRaid6(iteracion, segmentos.size(), TipoRaid.RAID6, seg1, nombre, TipoBloque.NORMAL);
        BloqueRaid6 bloque2 = new Raid6Controller.BloqueRaid6(iteracion, segmentos.size(), TipoRaid.RAID6, seg2, nombre, TipoBloque.NORMAL);
        BloqueRaid6 bloque3 = new Raid6Controller.BloqueRaid6(iteracion, segmentos.size(), TipoRaid.RAID6, paridad1, nombre, TipoBloque.PARIDAD);
        BloqueRaid6 bloque4 = new Raid6Controller.BloqueRaid6(iteracion, segmentos.size(), TipoRaid.RAID6, paridad2, nombre, TipoBloque.PARIDAD);
        File archivo1 = new File(ruta1);
        File archivo2 = new File(ruta2);
        File archivo3 = new File(ruta3);
        File archivo4 = new File(ruta4);
        switch (discoParidad) {
            case 1:
                guardarArchivo(bloque3, archivo1);
                guardarArchivo(bloque4, archivo2);
                guardarArchivo(bloque1, archivo3);
                guardarArchivo(bloque2, archivo4);
                break;
            case 2:
                guardarArchivo(bloque1, archivo1);
                guardarArchivo(bloque3, archivo2);
                guardarArchivo(bloque4, archivo3);
                guardarArchivo(bloque2, archivo4);
                break;
            case 3:
                guardarArchivo(bloque1, archivo1);
                guardarArchivo(bloque2, archivo2);
                guardarArchivo(bloque3, archivo3);
                guardarArchivo(bloque4, archivo4);
                break;
        }
    }

    private void guardarArchivo(BloqueRaid6 bloque, File archivo) {
        FileOutputStream fileOut;
        ObjectOutputStream out;
        //System.out.println(archivo.getAbsolutePath());
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

    private String xor(String s1, String s2) {
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

    public String convertirABinario(String string) {
        char[] strChar = string.toCharArray();
        String res = "";
        for (int i = 0; i < strChar.length; i++) {

            String temp = Integer.toBinaryString(strChar[i]);
            int k = 0;
            while (8 - k >= temp.length()) {
                k++;
                temp = "0" + temp;
            }
            res += temp + "";
        }
        return res;
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
                if (i + 1000 < string.length()) {
                    segmentos.add(string.substring(i, i + 1000));
                } else {
                    segmentos.add(string.substring(i, string.length() - 1));
                }
                i += 1000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertirAString(String input) {
        String res = "";
        String[] string = null;
        string = new String[input.length() / 8];
        int k = 0;
        for (int i = 0; i < input.length() / 8; i++) {
            string[i] = "";
        }
        for (int i = 0; i < input.length(); i += 8) {
            string[k] = input.substring(i, i + 8) + "";
            k++;
        }
        for (int i = 0; i < string.length; i++) {
            int temp = Integer.parseInt(string[i], 2);
            res = res + (char) temp;
        }
        return res;
    }

}
