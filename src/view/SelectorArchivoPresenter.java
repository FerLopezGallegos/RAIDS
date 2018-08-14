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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        this.vpp.vp.tf.setText("");
        this.inicializarCombo();
        this.attachEvents();
    }

    private void inicializarCombo() {
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

    private void aceptar(ActionEvent e) {
        String nombreArchivo = this.sr.combo.getSelectionModel().getSelectedItem().toString();
        TipoRaid tipo = vpp.tipoRaidArchivo(nombreArchivo);
        switch (tipo) {
            case RAID0:
                System.out.println("Desfragmentar raid 0");
                String res0 = this.so.getControladorRaid0().armar(nombreArchivo);
                if (res0 != null) {
                    this.vpp.vp.tf.setText(res0);
                }
                else{
                    this.mostrarAlerta();
                }
                break;
            case RAID1:
                System.out.println("Desfragmentar raid 1");
                String res1 = this.so.getControladorRaid1().armar(nombreArchivo);
                if(res1 != null){
                    this.vpp.vp.tf.setText(res1);
                }
                else{
                    this.mostrarAlerta();
                }
                
                break;
            case RAID2:
                System.out.println("Desfragmentar raid 2");
                break;
            case RAID3:
                System.out.println("Desfragmentar raid 3");
                String res = this.so.getControladorRaid3().armar(nombreArchivo);
                if (res != null) {
                    this.vpp.vp.tf.setText(res);
                } else {
                    this.mostrarAlerta();
                }

                break;
            case RAID4:
                System.out.println("Desfragmentar raid 4");
                break;
            case RAID5:
                System.out.println("Desfragmentar raid 5");
                break;
            case RAID6:
                System.out.println("Desfragmentar raid 6");
                break;
        }
        this.sr.close();
    }

    private void mostrarAlerta() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error al intentar cargar el archivo");
        alert.setContentText("Se intentó cargar un archivo que no exite en el disco o que está corrupto");

        alert.showAndWait();
    }
}
