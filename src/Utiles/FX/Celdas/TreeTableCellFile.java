/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import Utiles.FX.VisualFX;
import Utiles.FX.objetoTreeTableView;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import java.io.File;
import javafx.scene.paint.Color;

/**
 *
 * @author Rene
 */
public class TreeTableCellFile extends JFXTreeTableCell<objetoTreeTableView, File>{

    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if(!empty){
            if(!item.exists()){
             VisualFX.setBackground(this, Color.RED);
            }
//            System.out.println("item="+item);
            setText(item.toString());
        }
    }
    
}
