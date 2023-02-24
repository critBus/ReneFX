/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import Utiles.FX.VisualFX;
import java.io.File;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

/**
 *
 * @author Rene
 */
public class CellListaFile extends ListCell<File>{

    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if(!empty&&!item.exists()){
            VisualFX.setBackground(this, Color.RED);
            setText(item.toString());
        }
    }
    
}
