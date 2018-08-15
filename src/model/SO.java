/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Raid0Controller;
import controller.Raid1Controller;
import controller.Raid3Controller;
import controller.Raid4Controller;
import controller.Raid5Controller;
import controller.Raid6Controller;

/**
 *
 * @author fernandalopezgallegos
 */
public class SO {
    
    private Raid0Controller controladorRaid0;
    private Raid1Controller controladorRaid1;
    private Raid3Controller controladorRaid3;
    private Raid4Controller controladorRaid4;
    private Raid5Controller controladorRaid5;
    private Raid6Controller controladorRaid6;

    public SO() {
        this.controladorRaid0 = new Raid0Controller();
        this.controladorRaid1 = new Raid1Controller();
        this.controladorRaid3 = new Raid3Controller();
        this.controladorRaid4 = new Raid4Controller();
        this.controladorRaid5 = new Raid5Controller();
        this.controladorRaid6 = new Raid6Controller();
        
        
    }

    public Raid0Controller getControladorRaid0() {
        return controladorRaid0;
    }

    public Raid1Controller getControladorRaid1() {
        return controladorRaid1;
    }

    public Raid3Controller getControladorRaid3() {
        return controladorRaid3;
    }

    public Raid4Controller getControladorRaid4() {
        return controladorRaid4;
    }

    public Raid5Controller getControladorRaid5() {
        return controladorRaid5;
    }

    public Raid6Controller getControladorRaid6() {
        return controladorRaid6;
    }
    
    
    
    
    
    
}
