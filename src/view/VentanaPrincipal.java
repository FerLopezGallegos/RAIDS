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
import javafx.scene.control.TextArea;
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
    TextArea tf;
    TextField nombreArchivo;
    TextField caracteres;
    
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
        TextField tf1 = new TextField("César Bravo");
        tf1.setEditable(false);
        TextField tf2 = new TextField("Fernanda López");
        tf2.setEditable(false);
        vb.getChildren().addAll(btnSubir, btnMostrar, tf1, tf2);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(20,20,20,20));
        return vb;
    }
    
    private Node panelCentral(){
        BorderPane vb = new BorderPane();
        nombreArchivo = new TextField();
        nombreArchivo.setEditable(false);
        nombreArchivo.setPrefWidth(500);
        this.caracteres = new TextField();
        this.caracteres.setEditable(false);
        this.caracteres.setMinWidth(200);
        
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(0,0,10,0));
        hb.getChildren().addAll(nombreArchivo, caracteres);
        hb.setAlignment(Pos.CENTER_LEFT);
        
        tf = new TextArea();
        tf.setEditable(false);
        tf.autosize();
        tf.setWrapText(true);
        
        vb.setTop(hb);
        vb.setCenter(tf);
        vb.setPadding(new Insets(10));
        
        return vb;
    }
    
    
}