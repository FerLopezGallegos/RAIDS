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
public class Raid5Controller implements Serializable {

    ArrayList<String> segmentos = new ArrayList<String>();

    public String armar(String string) {
        String ruta = "RAIDS/RAID_5/DISCO_1/0" + string;
        ruta = ruta.trim();
        File file = new File(ruta);
        Raid5Controller.BloqueRaid5 bloque = null;
        String archivoCompleto = "";
        if (file.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(new File(ruta));
                ObjectInputStream in = new ObjectInputStream(fileIn);
                bloque = (Raid5Controller.BloqueRaid5) in.readObject();
                in.close();
                fileIn.close();
                if (bloque != null) {
                    ArrayList<Raid5Controller.BloqueRaid5> completo = this.buscarArchivos(bloque);
                    if(completo == null){
                        return null;
                    }
                    for (int i = 0; i < completo.size(); i++) {
                        archivoCompleto += this.convertirAString(completo.get(i).getContenido());
                        //System.out.println(archivoCompleto.length());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            return null;
        }

        return archivoCompleto;
    }

    public ArrayList<Raid5Controller.BloqueRaid5> buscarArchivos(Raid5Controller.BloqueRaid5 inicio) {
        ArrayList<Raid5Controller.BloqueRaid5> completo = new ArrayList<>();
        int cantidad = inicio.getNumeroTotal();
        String ruta1 = "";
        String ruta2 = "";
        String ruta3 = "";
        File file1;
        File file2;
        File file3;
        Raid5Controller.BloqueRaid5 nuevo;
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
                    ruta1 = "RAIDS/RAID_5/DISCO_1/p" + i + inicio.getNombreArchivo();
                    ruta2 = "RAIDS/RAID_5/DISCO_2/" + i + inicio.getNombreArchivo();
                    ruta3 = "RAIDS/RAID_5/DISCO_3/" + i + inicio.getNombreArchivo();
                    break;
                case 2:
                    ruta1 = "RAIDS/RAID_5/DISCO_1/" + i + inicio.getNombreArchivo();
                    ruta2 = "RAIDS/RAID_5/DISCO_2/p" + i + inicio.getNombreArchivo();
                    ruta3 = "RAIDS/RAID_5/DISCO_3/" + i + inicio.getNombreArchivo();
                    break;
                case 3:
                    ruta1 = "RAIDS/RAID_5/DISCO_1/" + i + inicio.getNombreArchivo();
                    ruta2 = "RAIDS/RAID_5/DISCO_2/" + i + inicio.getNombreArchivo();
                    ruta3 = "RAIDS/RAID_5/DISCO_3/p" + i + inicio.getNombreArchivo();
                    break;
            }
            file1 = new File(ruta1);
            file2 = new File(ruta2);
            file3 = new File(ruta3);
            if (file1.exists() && file2.exists() && file3.exists()) {
                try {
                    fileIn = new FileInputStream(file1);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid5Controller.BloqueRaid5) in.readObject();
                    if (nuevo.tipoB == TipoBloque.NORMAL) {
                        completo.add(nuevo);
                    }
                    fileIn = new FileInputStream(file2);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid5Controller.BloqueRaid5) in.readObject();
                    if (nuevo.tipoB == TipoBloque.NORMAL) {
                        completo.add(nuevo);
                    }
                    fileIn = new FileInputStream(file3);
                    in = new ObjectInputStream(fileIn);
                    nuevo = (Raid5Controller.BloqueRaid5) in.readObject();
                    if (nuevo.tipoB == TipoBloque.NORMAL) {
                        completo.add(nuevo);
                    }
                    in.close();
                    fileIn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else{
                return null;
            }
        }
        return completo;
    }

    private class BloqueRaid5 extends Bloque implements Serializable {

        TipoBloque tipoB;

        public BloqueRaid5(int numero, int numeroTotal, TipoRaid tipo, String contenido, String nombreArchivo, TipoBloque tipoB) {
            super(numero, numeroTotal, tipo, contenido, nombreArchivo);
            this.tipoB = tipoB;
        }

    }

    private void repartirArchivo(File file) throws IOException {
        this.crearDirectorios(5, 1);
        this.crearDirectorios(5, 2);
        this.crearDirectorios(5, 3);
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
        String ruta1 = "";
        String ruta2 = "";
        String ruta3 = "";
        switch (discoParidad) {
            case 1:
                ruta1 = "RAIDS/RAID_5/DISCO_1/p" + iteracion + nombre;
                ruta2 = "RAIDS/RAID_5/DISCO_2/" + iteracion + nombre;
                ruta3 = "RAIDS/RAID_5/DISCO_3/" + iteracion + nombre;
                break;
            case 2:
                ruta1 = "RAIDS/RAID_5/DISCO_1/" + iteracion + nombre;
                ruta2 = "RAIDS/RAID_5/DISCO_2/p" + iteracion + nombre;
                ruta3 = "RAIDS/RAID_5/DISCO_3/" + iteracion + nombre;
                break;
            case 3:
                ruta1 = "RAIDS/RAID_5/DISCO_1/" + iteracion + nombre;
                ruta2 = "RAIDS/RAID_5/DISCO_2/" + iteracion + nombre;
                ruta3 = "RAIDS/RAID_5/DISCO_3/p" + iteracion + nombre;
                break;
        }
        BloqueRaid5 bloque1 = new Raid5Controller.BloqueRaid5(iteracion, segmentos.size(), TipoRaid.RAID5, seg1, nombre, TipoBloque.NORMAL);
        BloqueRaid5 bloque2 = new Raid5Controller.BloqueRaid5(iteracion, segmentos.size(), TipoRaid.RAID5, seg2, nombre, TipoBloque.NORMAL);
        BloqueRaid5 bloque3 = new Raid5Controller.BloqueRaid5(iteracion, segmentos.size(), TipoRaid.RAID5, paridad, nombre, TipoBloque.PARIDAD);
        File archivo1 = new File(ruta1);
        File archivo2 = new File(ruta2);
        File archivo3 = new File(ruta3);
        switch (discoParidad) {
            case 1:
                guardarArchivo(bloque3, archivo1);
                guardarArchivo(bloque1, archivo2);
                guardarArchivo(bloque2, archivo3);
                break;
            case 2:
                guardarArchivo(bloque1, archivo1);
                guardarArchivo(bloque3, archivo2);
                guardarArchivo(bloque2, archivo3);
                break;
            case 3:
                guardarArchivo(bloque1, archivo1);
                guardarArchivo(bloque2, archivo2);
                guardarArchivo(bloque3, archivo3);
                break;
        }

    }

    private void guardarArchivo(BloqueRaid5 bloque, File archivo) {
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
        byte[] bytes = string.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
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
