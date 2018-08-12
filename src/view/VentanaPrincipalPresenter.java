/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.SO;
import model.TipoRaid;

/**
 *
 * @author fernandalopezgallegos
 */
public class VentanaPrincipalPresenter {

    SO so;
    VentanaPrincipal vp;
    Stage stage;

    private class ArchivoRaid{
        String archivo;
        TipoRaid tipo;

        public ArchivoRaid(String archivo, TipoRaid tipo) {
            this.archivo = archivo;
            this.tipo = tipo;
        }
        
        
    }
    
    public ArrayList<ArchivoRaid> archivos = new ArrayList<ArchivoRaid>();
    private SelectorRaid selector = new SelectorRaid();
    private SelectorArchivo selectorA = new SelectorArchivo();

    public VentanaPrincipalPresenter(SO so, VentanaPrincipal vp, Stage stage) {
        this.so = so;
        this.stage = stage;
        this.vp = vp;

        attachEvents();
    }

    private void attachEvents() {
        vp.btnSubir.setOnAction(this::abrirArchivo);
        vp.btnMostrar.setOnAction(this::seleccionarArchivo);
    }

    private void seleccionarArchivo(ActionEvent e){
        if (archivos.size()>0){
            SelectorArchivoPresenter sap = new SelectorArchivoPresenter(so, selectorA, stage, this);
            this.selectorA.showAndWait();
        }
    }
    
    private void abrirArchivo(ActionEvent e) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(stage);
        if (file!=null){
            try {
                System.out.println("FILE LENGHT " + file.length());
                SelectorRaidPresenter srp = new SelectorRaidPresenter(so, selector, stage, file, this);
                this.selector.showAndWait();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            
    }

    public ArrayList<String> generarBloques(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(new FileReader(file));
        while (in.hasNext()) {
            sb.append(in.nextLine() + "\n");
        }
        in.close();
        String string = sb.toString();
        ArrayList<String> bloques = new ArrayList<>();
        int i = 0;
        while (i < string.length()) {
            if (i + 500 < string.length()) {
                bloques.add(string.substring(i, i + 500));
            } else {
                bloques.add(string.substring(i, string.length() - 1));
            }
            i += 500;
        }
        /* 
        i = 1;
        for (String bloque : bloques) {
            System.out.println("bloque " + i + bloque + "\n");
            i++;
        } 
        */
        return bloques;
    }
    
    public void agregarArchivoRaid(File file, TipoRaid tipo){
        this.archivos.add(new ArchivoRaid(file.getName(), tipo));
    }
    
    ArrayList<String> obtenerNombresArchivos(){
        ArrayList<String> nombres = new ArrayList<>();
        for (ArchivoRaid archivos : this.archivos) {
            nombres.add(archivos.archivo);
        }
        
        return nombres;
    }
    
    TipoRaid tipoRaidArchivo(String nombre){
        for (ArchivoRaid archivo : archivos) {
            if (nombre.equals(archivo.archivo)){
                return archivo.tipo;
            }
        }
        return null;
    }

}
