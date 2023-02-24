/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;

/**
 *
 * @author Rene
 */
public class TreeTableCellLista<E> extends JFXTreeTableCell<E, JFXListView<String>> {

    double HeightPorCelda;
    JFXListView<String> lista;

    public TreeTableCellLista() {
        this(26);
    }

    public TreeTableCellLista(double HeightPorCelda) {
        // super();
        this.HeightPorCelda = HeightPorCelda;
        lista = new JFXListView<String>();
        setGraphic(null);
//        gra();
//        synchronized
//        setGraphic(lista);
//        if (getItem()!=null) {
//            double h = 0;
//            for (int i = 0; i < getItem().getItems().size(); i++) {
//                if (getItem().getItems().get(i) == null || getItem().getItems().get(i).isEmpty()) {
//                    break;
//                }
//                h += HeightPorCelda;
//            }
//           // item.setPrefSize(getWidth(), h);
////            System.out.println("h="+h);
////            System.out.println("item.getWidth()="+item.getWidth());
//            setMinSize(getItem().getWidth(), h);
//            
//            setGraphic(getItem());
//          //  System.out.println("set");
//        }
    }
//    boolean poner = true;
//
//    private synchronized void gra() {
//        if (poner) {
//            poner = false;
//           // if (poner) {
//                setGraphic(lista);
//           // }
//
//        }
//
//    }

    @Override
    protected void updateItem(final JFXListView<String> item, boolean empty) {
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
            // item.setPrefSize(getWidth(), h);
//            System.out.println("h="+h);
//            System.out.println("item.getWidth()="+item.getWidth());
            setMinSize(item.getWidth(), h);
//            setMaxHeight(h);
//            setPrefHeight(h);
//            setMinHeight(h);

//            item.setCellFactory(v -> {
//                ListCell l = new ListCell<>();
//                l.setMinHeight(26);
//                l.setMaxHeight(26);
//                l.setPrefHeight(26);
//                return l;
//            });
//           lista.setItems(item.getItems());

            setGraphic(item);
            //  System.out.println("set");
        }
    }

    public double getHeightPorCelda() {
        return HeightPorCelda;
    }

    public void setHeightPorCelda(double HeightPorCelda) {
        this.HeightPorCelda = HeightPorCelda;
    }

}
