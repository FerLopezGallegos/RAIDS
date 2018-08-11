/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Vandal
 */
public class SelectorRaid extends Stage {
    
    Button raid0;
    Button raid1;
    Button raid2;
    Button raid3;
    Button raid4;
    Button raid5;
    Button raid6;
    
    public SelectorRaid(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,300,400);
        this.setScene(scene);
        this.setTitle("Seleccione raid");
        
        root.setCenter(this.inicializarBotones());
    }
    
    private VBox inicializarBotones(){
        VBox vb = new VBox();
        raid0 = new Button("Raid 0");
        raid0.setPrefSize(100, 40);
        
        raid1 = new Button("Raid 1");
        raid1.setPrefSize(100, 40);
        
        raid2 = new Button("Raid 2");
        raid2.setPrefSize(100, 40);
        
        raid3 = new Button("Raid 3");
        raid3.setPrefSize(100, 40);
        
        raid4 = new Button("Raid 4");
        raid4.setPrefSize(100, 40);
        
        raid5 = new Button("Raid 5");
        raid5.setPrefSize(100, 40);
        
        raid6 = new Button("Raid 6");
        raid6.setPrefSize(100, 40);
        
        vb.getChildren().addAll(raid0,raid1,raid2,raid3,raid4,raid5,raid6);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(20,20,20,20));
        return vb;
    }
    

    
}
