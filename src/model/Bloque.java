/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author fernandalopezgallegos
 */
public class Bloque implements Serializable{
    
    protected int numero;
    protected int numeroTotal;
    protected TipoRaid tipo;
    protected String contenido;
    protected String nombreArchivo;

    public Bloque(int numero, int numeroTotal, TipoRaid tipo, String contenido, String nombreArchivo) {
        this.numero = numero;
        this.numeroTotal = numeroTotal;
        this.tipo = tipo;
        this.contenido = contenido;
        this.nombreArchivo = nombreArchivo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumeroTotal() {
        return numeroTotal;
    }

    public void setNumeroTotal(int numeroTotal) {
        this.numeroTotal = numeroTotal;
    }

    public TipoRaid getTipo() {
        return tipo;
    }

    public void setTipo(TipoRaid tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    
    
}
