/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX;

import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.util.function.Consumer;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Version 0.3
 *
 * @author Rene
 */
public abstract class EventosFX {
 public static void addOnHoverBorederColor( Color fuera, Color hover,Shape ... A) {
     for (int i = 0; i < A.length; i++) {
         addOnHoverBorederColor(A[i], fuera, hover);
     }
 }
    public static void addOnHoverBorederColor(Shape a, Color fuera, Color hover) {
        addOnMouseENTERED(a, v -> a.setStroke(Color.ORANGE));
        addOnMouseEXITED(a, v -> a.setStroke(Color.BLACK));
    }

    public static void addOnAction(EventHandler t, Node... N) {
        for (int i = 0; i < N.length; i++) {
            addOnAction(N[i], t);
        }
    }

    public static void addOnAction(Node e, EventHandler t) {
        e.addEventHandler(ActionEvent.ACTION, t);
        //e.addEventHandler(ActionEvent.ACTION, v->{System.out.println("onAc");});
        // e.ad
    }
    //

    public static void addOnSelected(ToggleGroup t, Consumer c) {
        t.selectedToggleProperty().addListener((Observable v) -> c.accept(v));
    }

    public static void addOnSelected(TreeTableView t, Consumer c) {
        t.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) (ListChangeListener.Change v) -> c.accept(v));
    }

    public static <E> void addOnSelected(TableView<E> t, Consumer<E> c) {
        t.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) (ListChangeListener.Change v) -> c.accept(VisualFX.getSeleccionado(t)));
    }
    public static <E> void addOnSelected(ListView<E> t, Consumer<E> c) {
    t.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) (ListChangeListener.Change v) -> c.accept(VisualFX.getSeleccionado(t)));
    }
    //
    //
    //    public static void copiarVisualDeAaB(RadioButton a, RadioButton b) {
    //        b.setText(a.getText());
    //        b.setGraphic(a.getGraphic());
    //    }

    public static void addOnMouseDragged(EventHandler<MouseEvent> t, Node... N) {
        for (int i = 0; i < N.length; i++) {
            addOnMouseDRAGGED(N[i], t);
            //            N[i].setOnMouseDragged(t);
        }
    }

    public static void addOnDialogOpened(JFXDialog e, EventHandler t) {
        e.addEventHandler(JFXDialogEvent.OPENED, t);
    }

    public static void addOnDialogClosed(EventHandler t, JFXDialog... D) {
        for (int i = 0; i < D.length; i++) {
            addOnDialogClosed(D[i], t);
        }
    }

    public static void addOnDialogClosed(JFXDialog e, EventHandler t) {
        e.addEventHandler(JFXDialogEvent.CLOSED, t);
    }

    //    public static void addOnCLOSE_REQUEST(Stage s, Realizar r) {
    //        s.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, v -> r.realizar());
    //    }
    public static void addOnHIDING(Stage s, Realizar r) {
        s.addEventHandler(WindowEvent.WINDOW_HIDING, (WindowEvent v) -> r.realizar());
    }

    //    public static void addOnCLOSE_REQUEST(Realizar r, Stage... D) {
    //        for (int i = 0; i < D.length; i++) {
    //            addOnCLOSE_REQUEST(D[i], r);
    //        }
    //
    //    }
    public static void addOnHIDING(Realizar r, Stage... D) {
        for (int i = 0; i < D.length; i++) {
            addOnHIDING(D[i], r);
        }
    }

    public static void addOnShowing(Stage s, Realizar r) {
        s.addEventHandler(WindowEvent.WINDOW_SHOWING, (WindowEvent v) -> r.realizar());
    }

    public static void addOnMousePressed(ObservableList<Node> N, Utilizar<Node> u) {
        for (int i = 0; i < N.size(); i++) {
            final Node n = N.get(i);
            addOnMousePressed(n, (MouseEvent v) -> u.utilizar(n));
        }
    }

    public static void addOnMousePressed(EventHandler<MouseEvent> t, Node... N) {
        for (int i = 0; i < N.length; i++) {
            addOnMousePressed(N[i], t);
        }
    }

    public static void addOnMousePressed(Node e, EventHandler<MouseEvent> t) {
        e.addEventHandler(MouseEvent.MOUSE_PRESSED, t);
    }

    public static void addOnMouseENTERED(Node e, EventHandler<MouseEvent> t) {
        e.addEventHandler(MouseEvent.MOUSE_ENTERED, t);
    }

    public static void addOnMouseEXITED(Node e, EventHandler<MouseEvent> t) {
        e.addEventHandler(MouseEvent.MOUSE_EXITED, t);
    }

    public static void addOnMouseDRAGGED(Node e, EventHandler<MouseEvent> t) {
        e.addEventHandler(MouseEvent.MOUSE_DRAGGED, t);
    }

    public static void addOnMouseReleased(Realizar R, Node... N) {
        addOnMouseReleased((MouseEvent v) -> R.realizar(), N);
    }

    public static void addOnMouseReleased(EventHandler<MouseEvent> t, Node... N) {
        for (int i = 0; i < N.length; i++) {
            addOnMouseReleased(N[i], t);
        }
    }

    public static void addOnMouseReleased(Node e, EventHandler<MouseEvent> t) {
        e.addEventHandler(MouseEvent.MOUSE_RELEASED, t);
        // e.addEventHandler(MouseEvent.MOUSE_RELEASED, ss->{System.out.println("levanto");});
    }

    public static void alSeleccionar(ListView L, Realizar r) {
        L.getSelectionModel().selectedIndexProperty().addListener((Observable v) -> {
            r.realizar();
        });
    }

}
