/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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

    public SelectorRaidPresenter(SO so, SelectorRaid sr, Stage stage) {
        this.so = so;
        this.stage = stage;
        this.sr = sr;

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
    }

    void raid1(ActionEvent e) {
        System.out.println("AUCH 1");
    }

    void raid2(ActionEvent e) {
        System.out.println("AUCH 2");
    }

    void raid3(ActionEvent e) {
        System.out.println("AUCH 3");
    }

    void raid4(ActionEvent e) {
        System.out.println("AUCH 4");
    }

    void raid5(ActionEvent e) {
        System.out.println("AUCH 5");
    }

    void raid6(ActionEvent e) {
        System.out.println("AUCH 6");
    }
}
