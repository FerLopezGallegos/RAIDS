/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.SO;

/**
 *
 * @author Vandal
 */
public class VentanaPrincipal extends BorderPane {
    private SO so;
    
    Button btnSubir;
    Button btnMostrar;
    
    public VentanaPrincipal(SO so){
        
        this.so = so;
        btnSubir = new Button("Subir Archivo");
        btnMostrar = new Button("Mostrar Archivo");
        
        this.setLeft(this.panelIzq());
        this.setCenter(this.panelCentral());
        
    }
    
    private Node panelIzq(){
        VBox vb = new VBox();
        this.setLeft(btnSubir);
        btnSubir.setPrefSize(150, 40);
        this.setLeft(btnMostrar);
        btnMostrar.setPrefSize(150, 40);
        vb.getChildren().addAll(btnSubir, btnMostrar);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(20,20,20,20));
        return vb;
    }
    
    private Node panelCentral(){
        BorderPane vb = new BorderPane();
        TextField tf = new TextField();
        tf.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tf.setEditable(false);
        vb.setCenter(tf);
        vb.setPadding(new Insets(10));
        return vb;
    }
    
    
}