/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import static Utiles.FX.VisualFX.getI;
import com.jfoenix.controls.JFXTreeTableView;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TreeTableView;

/**
 *
 * @author Rene
 */
public class objetoTreeTableView extends RecursiveTreeObject<objetoTreeTableView> {

    Object valor;
     SimpleBooleanProperty selected = new SimpleBooleanProperty(false,"selected");
     private int indice;

    public objetoTreeTableView(Object valor, int indice) {
        this.valor = valor;
        this.indice = indice;
    }

    public objetoTreeTableView(Object valor) {
        this.valor = valor;
    }
 public SimpleBooleanProperty selectedProperty() {
 return selected;
 }

    public SimpleBooleanProperty getSelected() {
        return selected;
    }

    public void setSelected(SimpleBooleanProperty selected) {
        this.selected = selected;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
 
 
    public <E> E getValor() {
        return (E) valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public static <E> E getValorSeleccionado(TreeTableView<objetoTreeTableView> TV) {

        return TV.getSelectionModel().getSelectedItem() == null ? null : TV.getSelectionModel().getSelectedItem().getValue().<E>getValor();
        //  int indice = getI(TV);
        // System.out.println("TV.getSelectionModel().getSelectedItem()="+TV.getSelectionModel().getSelectedItem());
        // System.out.println("indice="+indice);
        //return indice == -1 ? null : valores.get(indice).<E>getValor();
    }

    public static <E> LinkedList<E> getSeleccionadosTablaSelec(JFXTreeTableView<objetoTreeTableView> TV, ObservableList<E> ob) {
        LinkedList<E> l = new LinkedList<>();
        TV.getSelectionModel().setCellSelectionEnabled(true);
        for (int i = 0; i < ob.size(); i++) {
//            System.out.println("TV.getColumns().get(0).getCellData(i)="+TV.getColumns().get(0).getCellData(i).getClass());

//            boolean b=TV.getSelectionModel().isSelected(i, TV.getColumns().get(0));
//           
//            System.out.println("b="+b);
            SimpleBooleanProperty s = (SimpleBooleanProperty) TV.getColumns().get(0).getCellObservableValue(i);
//            System.out.println("i="+i+" s="+s.getValue());
//            boolean a=TV.getColumns().get(0).
            if (s.getValue()) {
                l.add(ob.get(i));
            }
            

        }
       return l;
    }

    public static <E> E getValorDe(ObservableList<objetoTreeTableView> valores, ObservableList<objetoTreeTableView> dondeBuscar, Object aBuscar) {
        int indice = getIndex(dondeBuscar, aBuscar);
        return indice == -1 ? null : valores.get(indice).<E>getValor();
    }

    public static int getIndex(ObservableList<objetoTreeTableView> ob, Object o) {
        for (int i = 0; i < ob.size(); i++) {
            if (ob.get(i).getValor().equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public static ObservableList<objetoTreeTableView> getOb(Object... V) {
        ObservableList<objetoTreeTableView> O = FXCollections.observableArrayList();
        for (int i = 0; i < V.length; i++) {
            O.add(new objetoTreeTableView(V[i],i));
        }
        return O;
    }

    public static ObservableList<objetoTreeTableView> getOb(List l) {
        ObservableList<objetoTreeTableView> O = FXCollections.observableArrayList();
        for (int i = 0; i < l.size(); i++) {
            O.add(new objetoTreeTableView(l.get(i)));
        }
        return O;
    }

    public static ObservableList<TreeItem<objetoTreeTableView>> getObTreeItem(Object... V) {
        ObservableList<TreeItem<objetoTreeTableView>> O = FXCollections.observableArrayList();
        for (int i = 0; i < V.length; i++) {
            O.add(new TreeItem<objetoTreeTableView>(new objetoTreeTableView(V[i])));
        }
        return O;
    }

    public static void remove(ObservableList<objetoTreeTableView> ob, List l) {
        if (!l.isEmpty()) {
            int indiceEliminado = 0;

            for (int i = 0; i < ob.size(); i++) {

                for (int j = indiceEliminado; j < l.size(); j++) {
                    if (ob.get(i).getValor().equals(l.get(j))) {
                        ob.remove(i--);
                        if (j == indiceEliminado) {
                            if (++indiceEliminado == l.size()) {
                                return;
                            }
                        }
                        break;
                    }
                }

            }
        }

    }

    public static void add(ObservableList<objetoTreeTableView> ob, List l) {
        for (int i = 0; i < l.size(); i++) {
            ob.add(new objetoTreeTableView(l.get(i)));
        }

    }

    public static ObservableList<objetoTreeTableView> bind(ObservableList l) {
        ObservableList<objetoTreeTableView> ob = objetoTreeTableView.getOb(l);

        l.addListener((ListChangeListener) v -> {
            while (v.next()) {
                if (v.wasAdded()) {
//                    System.out.println("v.getAddedSize()=" + v.getAddedSize());
//                    ob.add(v.getAddedSize(), new objetoTreeTableView(l.get(v.getAddedSize())));
                    add(ob, v.getAddedSubList());
                    continue;
                }
                if (v.wasPermutated()) {
                    ob.add(v.getPermutation(v.getFrom()), ob.remove(v.getFrom()));
                    continue;
                }
                if (v.wasRemoved()) {
                    remove(ob, v.getRemoved());
                }

            }
        });
        return ob;
    }
}
