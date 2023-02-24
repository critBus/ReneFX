/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas.Controladores;

import Utiles.FX.EventosFX;
import Utiles.FX.VentanasFX;
import Utiles.FX.VisualFX;
import static Utiles.FX.VisualFX.arc;
import static Utiles.FX.VisualFX.setStartEnd;
import static Utiles.FX.VisualFX.setStrokeWidth;
import Utiles.MetodosUtiles.Arreglos;
import static Utiles.MetodosUtiles.MetodosUtiles.esNaN;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author Rene
 */
public class Borde {

    private Pane p;
    private Line LArriba, LAbajo, LIsquerda, LDerecha;
    private Stage st;
    private Arc ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha;

//    public Borde(Pane p, Stage s) {
//        ini(p, L[0], L[1], L[2], L[3], s);
//    }
    private Borde(Pane p, Line LArriba, Line LAbajo, Line LIsquerda, Line LDerecha, Stage st, Arc ArArribaIsquierda, Arc ArArribaDerecha, Arc ArAbajoIsquerda, Arc ArAbajoDerecha) {
        this.p = p;
        this.LArriba = LArriba;
        this.LAbajo = LAbajo;
        this.LIsquerda = LIsquerda;
        this.LDerecha = LDerecha;
        this.st = st;
        this.ArArribaIsquierda = ArArribaIsquierda;
        this.ArArribaDerecha = ArArribaDerecha;
        this.ArAbajoIsquerda = ArAbajoIsquerda;
        this.ArAbajoDerecha = ArAbajoDerecha;
        actualizar();
      
        //ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha

    }

//    private void ini(Pane p, Line LArriba, Line LAbajo, Line LIsquerda, Line LDerecha, Stage st) {
//        this.p = p;
//        this.LArriba = LArriba;
//        this.LAbajo = LAbajo;
//        this.LIsquerda = LIsquerda;
//        this.LDerecha = LDerecha;
//        this.st = st;
//
//    }
    public Pane getP() {
        return p;
    }

    public void setP(Pane p) {
        this.p = p;
    }

    public Line getLArriba() {
        return LArriba;
    }

    public void setLArriba(Line LArriba) {
        this.LArriba = LArriba;
    }

    public Line getLAbajo() {
        return LAbajo;
    }

    public void setLAbajo(Line LAbajo) {
        this.LAbajo = LAbajo;
    }

    public Line getLIsquerda() {
        return LIsquerda;
    }

    public void setLIsquerda(Line LIsquerda) {
        this.LIsquerda = LIsquerda;
    }

    public Line getLDerecha() {
        return LDerecha;
    }

    public void setLDerecha(Line LDerecha) {
        this.LDerecha = LDerecha;
    }

    public Stage getSt() {
        return st;
    }

    public void setSt(Stage st) {
        this.st = st;
    }

    public Arc getArArribaIsquierda() {
        return ArArribaIsquierda;
    }

    public void setArArribaIsquierda(Arc ArArribaIsquierda) {
        this.ArArribaIsquierda = ArArribaIsquierda;
    }

    public Arc getArArribaDerecha() {
        return ArArribaDerecha;
    }

    public void setArArribaDerecha(Arc ArArribaDerecha) {
        this.ArArribaDerecha = ArArribaDerecha;
    }

    public Arc getArAbajoIsquerda() {
        return ArAbajoIsquerda;
    }

    public void setArAbajoIsquerda(Arc ArAbajoIsquerda) {
        this.ArAbajoIsquerda = ArAbajoIsquerda;
    }

    public Arc getArAbajoDerecha() {
        return ArAbajoDerecha;
    }

    public void setArAbajoDerecha(Arc ArAbajoDerecha) {
        this.ArAbajoDerecha = ArAbajoDerecha;
    }

    private void actualizarEsquinasArc() {
//        public static void iniEsquinasArc(Borde b) {
        // NaN
//        Pane p = b.getP();
//        Stage s = b.getSt();
        double W = getWidth(), H = getHeight();
//        System.out.println("W="+W+" H="+H );
//        Arc ArArribaIsquierda = arc(90, 90), ArArribaDerecha = arc(0, 90), ArAbajoIsquerda = arc(180, 90), ArAbajoDerecha = arc(270, 90);
//        Arc ArArribaIsquierda = b.getArArribaIsquierda(), ArArribaDerecha = b.getArArribaDerecha(), ArAbajoIsquerda = b.getArAbajoIsquerda(), ArAbajoDerecha = b.getArAbajoDerecha();
        ArArribaIsquierda.setCenterX(ArArribaIsquierda.getRadiusX());
        ArArribaIsquierda.setCenterY(ArArribaIsquierda.getRadiusY());
        ArArribaIsquierda.setCursor(Cursor.NW_RESIZE);
//        ArArribaIsquierda.setFill(Color.RED);

        ArArribaDerecha.setCenterX(W - ArArribaDerecha.getRadiusX());
        ArArribaDerecha.setCenterY(ArArribaDerecha.getRadiusY());
//        ArArribaDerecha.setCenterX(W - 100);
//        ArArribaDerecha.setCenterY(90);
        ArArribaDerecha.setCursor(Cursor.NE_RESIZE);
//        ArArribaDerecha.setFill(Color.BLACK);
//        System.out.println("ArArribaDerecha.getRadiusX()="+ArArribaDerecha.getRadiusX());

        ArAbajoIsquerda.setCenterX(ArAbajoIsquerda.getRadiusX());
        ArAbajoIsquerda.setCenterY(H - ArAbajoIsquerda.getRadiusY());
        ArAbajoIsquerda.setCursor(Cursor.SW_RESIZE);
//        ArAbajoIsquerda.setFill(Color.BLUE);

        ArAbajoDerecha.setCenterX(W - ArAbajoDerecha.getRadiusY());
        ArAbajoDerecha.setCenterY(H - ArAbajoDerecha.getRadiusY());
        ArAbajoDerecha.setCursor(Cursor.SE_RESIZE);
//        ArAbajoDerecha.setFill(Color.YELLOW);

//        p.getChildren().addAll(ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha);
//        return new Arc[]{ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha};
    }

    public double getWidth() {
        return esNaN(st.getWidth()) ? p.getPrefWidth() : st.getWidth();
    }

    public double getHeight() {
        return esNaN(st.getHeight()) ? p.getPrefHeight() : st.getHeight();
    }

    private void actualizarLineas() {
        double W = getWidth(), H = getHeight();
//        double X = p.getPrefWidth(), Y = p.getPrefHeight(), d = 3;
        setStartEnd(ArArribaIsquierda.getRadiusX(), 0, W - ArArribaDerecha.getRadiusX(), 0, LArriba);
        setStartEnd(ArArribaIsquierda.getRadiusX(), H, W - ArArribaDerecha.getRadiusX(), H, LAbajo);
        setStartEnd(0, ArArribaIsquierda.getRadiusY(), 0, H - ArAbajoIsquerda.getRadiusY(), LIsquerda);
        setStartEnd(W, ArArribaIsquierda.getRadiusY(), W, H - ArAbajoIsquerda.getRadiusY(), LDerecha);
        LArriba.setCursor(Cursor.N_RESIZE);
        LAbajo.setCursor(Cursor.S_RESIZE);
        LDerecha.setCursor(Cursor.W_RESIZE);
        LIsquerda.setCursor(Cursor.E_RESIZE);

        setStrokeWidth(5, LAbajo, LArriba, LDerecha, LIsquerda);
    }

    public void actualizar() {
        actualizarEsquinasArc();
        actualizarLineas();
    }

    public static Borde setBordeExpancible(Pane p, Stage s) {
        Line L[] = Arreglos.fill(() -> new Line(), 4);
        Arc ArArribaIsquierda = arc(90, 90), ArArribaDerecha = arc(0, 90), ArAbajoIsquerda = arc(180, 90), ArAbajoDerecha = arc(270, 90);
        VisualFX.soloBordes( ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha);
        EventosFX.addOnHoverBorederColor(Color.BLACK, Color.ORANGE, L[0], L[1], L[2], L[3], ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha);
        Borde B = new Borde(p, L[0], L[1], L[2], L[3], s, ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha);
        p.getChildren().addAll(L[0], L[1], L[2], L[3], ArArribaIsquierda, ArArribaDerecha, ArAbajoIsquerda, ArAbajoDerecha);
        VentanasFX.setExpandir(B);
        
      
        return B;
    }

}
