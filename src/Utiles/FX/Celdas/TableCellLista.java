/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;

/**
 *
 * @author Rene
 */
public class TableCellLista<E> extends TableCell<E, ListView<String>> {
     
    double HeightPorCelda;

    public TableCellLista() {
        this(25);
    }

    
    public TableCellLista(double HeightPorCelda) {
        this.HeightPorCelda = HeightPorCelda;
         setGraphic(null);
    }
    
    
    @Override
    protected void updateItem(final ListView<String> item, boolean empty) {
        super.updateItem(item, empty);
         setGraphic(null);
        if (!empty) {
            double h = 0;
            for (int i = 0; i < item.getItems().size(); i++) {
                if (item.getItems().get(i) == null || item.getItems().get(i).isEmpty()) {
                    break;
                }
                h += HeightPorCelda;
            }
            item.setPrefSize(getWidth(), h);
            setGraphic(item);
        }
    }

    public double getHeightPorCelda() {
        return HeightPorCelda;
    }

    public void setHeightPorCelda(double HeightPorCelda) {
        this.HeightPorCelda = HeightPorCelda;
    }

}
