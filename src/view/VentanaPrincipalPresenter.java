/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.ArrayList;
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
    
    private void abrirArchivo(ActionEvent e){
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(stage);
        if (file!=null){
            System.out.println("FILE LENGHT " + file.length());
            this.selector.showAndWait();
            this.archivos.add(file);

        }
    }
    
}
