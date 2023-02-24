/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3;
import javafx.scene.control.ListCell;

/**
 *
 * @author Rene
 */
public class CellLista<E> extends ListCell<E>{

   Utilizar3<ListCell,E,Boolean> u;

    public CellLista(Utilizar3<ListCell,E,Boolean> u) {
        this.u = u;
         setGraphic(null);
    }
    
    @Override
    protected void updateItem(E item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
         setGraphic(null);
        u.utilizar(this, item, empty);
    }

    public Utilizar3<ListCell,E,Boolean> getU() {
        return u;
    }

    public void setU(Utilizar3<ListCell,E,Boolean> u) {
        this.u = u;
    }
    
}
