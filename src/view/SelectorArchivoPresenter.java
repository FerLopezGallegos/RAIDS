/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Desktop;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.SO;
import model.TipoRaid;

/**
 *
 * @author Vandal
 */
public class SelectorArchivoPresenter {

    SO so;
    SelectorArchivo sr;
    Stage stage;
    VentanaPrincipalPresenter vpp;

    SelectorArchivoPresenter(SO so, SelectorArchivo selectorA, Stage stage, VentanaPrincipalPresenter vpp) {
        this.so = so;
        this.sr = selectorA;
        this.stage = stage;
        this.vpp = vpp;
        this.inicializarCombo();
        this.attachEvents();
    }
    
    private void inicializarCombo(){
        ObservableList<String> options = FXCollections.observableArrayList();
        ArrayList<String> nombres = this.vpp.obtenerNombresArchivos();
        for (String nombre : nombres) {
            options.add(nombre);
        }
        this.sr.combo.setItems(options);
        this.sr.combo.getSelectionModel().selectFirst();
    }

    private void attachEvents() {
        this.sr.btnAceptar.setOnAction(this::aceptar);
    }
    
    private void aceptar(ActionEvent e){
        String nombreArchivo = this.sr.combo.getSelectionModel().getSelectedItem().toString();
        TipoRaid tipo = vpp.tipoRaidArchivo(nombreArchivo);
        String texto = "";
        switch (tipo){
            case RAID0:
                System.out.println("Desfragmentar raid 0");
                texto = this.so.getControladorRaid0().armar(nombreArchivo);
                this.vpp.vp.tf.setText(texto);
                break;
            case RAID1:
                System.out.println("Desfragmentar raid 1");
                texto = this.so.getControladorRaid1().armar(nombreArchivo);
                this.vpp.vp.tf.setText(texto);
                break;
            case RAID2:
                System.out.println("Desfragmentar raid 2");
                break;
            case RAID3:
                System.out.println("Desfragmentar raid 3");
                break;
            case RAID4:
                texto = this.so.getControladorRaid4().armar(nombreArchivo);
                this.vpp.vp.tf.setText(texto);
                System.out.println("Desfragmentar raid 4");
                break;
            case RAID5:
                texto = this.so.getControladorRaid5().armar(nombreArchivo);
                this.vpp.vp.tf.setText(texto);
                System.out.println("Desfragmentar raid 5");
                break;
            case RAID6:
                System.out.println("Desfragmentar raid 6");
                break;
        }
        this.vpp.vp.caracteres.setText(""+texto.length());
        this.vpp.vp.nombreArchivo.setText(nombreArchivo);
        this.sr.close();
    }
    
}
