/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.scene.control.ListView;

/**
 *
 * @author Rene
 */
public class TreeTableCellTExtArea<E> extends JFXTreeTableCell<E, JFXTextArea> {

    double HeightPorCelda;

    public TreeTableCellTExtArea() {
        this(25);
    }

    public TreeTableCellTExtArea(double HeightPorCelda) {
        this.HeightPorCelda = HeightPorCelda;
        setEditable(false);
          setGraphic(null);
//        if (getItem() != null) {
//            setGraphic(getItem());
//            getItem().setEditable(false);
//        }

    }

    @Override
    protected void updateItem(JFXTextArea item, boolean empty) {
        super.updateItem(item, empty);
          setGraphic(null);
        if (!empty) {
            setGraphic(item);
            item.setEditable(false);
        }

    }

    public double getHeightPorCelda() {
        return HeightPorCelda;
    }

    public void setHeightPorCelda(double HeightPorCelda) {
        this.HeightPorCelda = HeightPorCelda;
    }

}
