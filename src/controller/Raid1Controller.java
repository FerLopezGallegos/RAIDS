/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vandal
 */
public class Raid1Controller {
    
    File file;
    ArrayList<String> bloques;
    public Raid1Controller(File file) {
        this.file = file;
        bloques = new ArrayList<>();
        
        generarBloques();
    }
    
    public void generarBloques() {
        try{
            
        
            StringBuilder sb = new StringBuilder();
            Scanner in = new Scanner(new FileReader(file));
            while (in.hasNext()) {
                sb.append(in.nextLine() + "\n");
            }
            in.close();
            String string = sb.toString();

            int i = 0;
            while (i < string.length()) {
                if (i + 500 < string.length()) {
                    bloques.add(string.substring(i, i + 500));
                } else {
                    bloques.add(string.substring(i, string.length() - 1));
                }
                i += 500;
            }
            
            i = 1;
            for (String bloque : bloques) {
                System.out.println("bloque " + i + bloque + "\n");
                i++;
            } 
            
        }catch(Exception e){}
    }
    
    
    
}
