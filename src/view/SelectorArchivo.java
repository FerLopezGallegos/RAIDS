/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Vandal
 */
public class SelectorArchivo extends Stage{
    
    ComboBox<String> combo = new ComboBox();
    Button btnAceptar;
    
    public SelectorArchivo(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,300,200);
        this.setScene(scene);
        this.setTitle("Seleccione archivo a cargar");
        root.setCenter(this.inicializar());
    }
    
    private Node inicializar(){
        VBox vb = new VBox();
        this.btnAceptar = new Button("Aceptar");
        vb.getChildren().addAll(combo,btnAceptar);
        vb.setPadding(new Insets (20));
        vb.setSpacing(30);
        vb.setAlignment(Pos.CENTER);
        return vb;
    }
    
}
