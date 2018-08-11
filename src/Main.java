/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.SO;
import view.VentanaPrincipal;
import view.VentanaPrincipalPresenter;

/**
 *
 * @author Vandal
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        SO so = new SO(); //Sistema operativo
        
        VentanaPrincipal vp = new VentanaPrincipal(so);
        
        Scene scene = new Scene(vp,800,600);
        
        VentanaPrincipalPresenter presenter = new VentanaPrincipalPresenter(so, vp, primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RAIDS");
        primaryStage.show();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
