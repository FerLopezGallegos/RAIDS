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

/**
 *
 * @author fernandalopezgallegos
 */
public class VentanaPrincipalPresenter {

    SO so;
    VentanaPrincipal vp;
    Stage stage;

    private ArrayList<File> archivos = new ArrayList<File>();
    private SelectorRaid selector = new SelectorRaid();

    public VentanaPrincipalPresenter(SO so, VentanaPrincipal vp, Stage stage) {
        this.so = so;
        this.stage = stage;
        this.vp = vp;

        attachEvents();
    }

    private void attachEvents() {
        vp.btnSubir.setOnAction(this::abrirArchivo);
    }

    private void abrirArchivo(ActionEvent e) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(stage);
        try {
            System.out.println("FILE LENGHT " + file.length());
            SelectorRaidPresenter srp = new SelectorRaidPresenter(so, selector, stage, file);
            this.selector.showAndWait();
            this.archivos.add(file);

        } catch (Exception ex) {
            ex.printStackTrace();
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

}
