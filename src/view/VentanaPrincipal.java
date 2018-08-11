/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.SO;

/**
 *
 * @author Vandal
 */
public class VentanaPrincipal extends BorderPane {
    
   
    private SO so;
    
    Button btnSubir;
    
    public VentanaPrincipal(SO so){
        
        this.so = so;
        btnSubir = new Button("Subir Archivo");
        
        this.setCenter(btnSubir);
        
        
    }
    
    
}