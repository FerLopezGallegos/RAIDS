/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.SO;

/**
 *
 * @author fernandalopezgallegos
 */
public class SelectorRaidPresenter {

    SO so;
    SelectorRaid sr;
    Stage stage;
    File file;

    public SelectorRaidPresenter(SO so, SelectorRaid sr, Stage stage, File file) {
        this.so = so;
        this.stage = stage;
        this.sr = sr;
        this.file = file;
        attachEvents();

    }

    private void attachEvents() {
        sr.raid0.setOnAction(this::raid0);
        sr.raid1.setOnAction(this::raid1);
        sr.raid2.setOnAction(this::raid2);
        sr.raid3.setOnAction(this::raid3);
        sr.raid4.setOnAction(this::raid4);
        sr.raid5.setOnAction(this::raid5);
        sr.raid6.setOnAction(this::raid6);
    }

    void raid0(ActionEvent e) {
        System.out.println("AUCH 0");
        this.so.getControladorRaid0().cargarArchivo(file);
    }

    void raid1(ActionEvent e) {
        System.out.println("AUCH 1");
        this.so.getControladorRaid1().cargarArchivo(file);
        this.stage.close();
    }

    void raid2(ActionEvent e) {
        System.out.println("AUCH 2");
        this.stage.close();
    }

    void raid3(ActionEvent e) {
        System.out.println("AUCH 3");
        this.stage.close();
    }

    void raid4(ActionEvent e) {
        System.out.println("AUCH 4");
        this.stage.close();
    }

    void raid5(ActionEvent e) {
        System.out.println("AUCH 5");
        this.stage.close();
    }

    void raid6(ActionEvent e) {
        System.out.println("AUCH 6");
        this.stage.close();
    }
}
