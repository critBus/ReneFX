/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 *
 * @author Rene
 */
public class listaTreeTable<E> {

    private ObservableList<objetoTreeTableView> ob;

    public listaTreeTable(ObservableList<E> l) {
        ob = objetoTreeTableView.getOb(l);

        l.addListener((ListChangeListener) v -> {
            while (v.next()) {
                if (v.wasAdded()) {
                    ob.add(v.getAddedSize(), new objetoTreeTableView(l.get(v.getAddedSize())));
                    continue;
                }
                if (v.wasPermutated()) {
                    ob.add(v.getPermutation(v.getFrom()), ob.remove(v.getFrom()));
                    continue;
                }
                if (v.wasRemoved()) {
                    objetoTreeTableView.remove(ob, v.getRemoved());
                }
                
            }
        });

    }

    public ObservableList<objetoTreeTableView> getOb() {
        return ob;
    }

}
