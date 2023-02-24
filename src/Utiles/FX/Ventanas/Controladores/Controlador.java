/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas.Controladores;

import Utiles.ClasesUtiles.BasesDeDatos.BDConexion;
//import Utiles.ClasesUtiles.BasesDeDatos.BDConexionController;
import Utiles.ClasesUtiles.BasesDeDatos.BDTipoDeConexion;
import Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import Utiles.ClasesUtiles.Interfases.Funcionales.CreadorConException;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.RealizarConException;
import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;
import Utiles.ClasesUtiles.Interfases.Visuales.ControladorVisual;
//import Utiles.FX.ActualizadorVisual;
import Utiles.FX.CSS.MetodosCSS;
import Utiles.FX.EventosFX;
import Utiles.FX.TablasFX;
import Utiles.FX.Ventanas.Dialogo_Get_TextoController;
import Utiles.FX.Ventanas.Dialogo_Progress_SpinnerController;
import Utiles.FX.VisualFX;
import Utiles.FX.VentanasFX;
import Utiles.FX.objetoTreeTableView;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * version 0.2
 * @author Rene
 */
public abstract class Controlador implements ControladorVisual, Initializable {

    public File carpetaEA;
    public String nombreEA;
    private Stage ventana;
    private Realizar actulizaciones;
//    private ActualizadorVisual actualizadorVisual;
    @FXML
    private AnchorPane PAnchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("inicio");

    }

    public File getDireccionEA() {
        return new File(carpetaEA + "/" + nombreEA);
    }

    public void iniStage(Parent p) {
        iniStage(p, false);
    }

    public void iniStage(Parent p, boolean modal) {
        //this.scene = s;
        iniDirecciones();
        Scene s = new Scene(p);
        if (s.getStylesheets().isEmpty()) {
            s.getStylesheets().add(getClass().getClassLoader().getResource("Utiles/FX/CSS/css.css").toExternalForm());
        }

        ventana = new Stage();
        ventana.setScene(s);
        if (modal) {
            ventana.initModality(Modality.APPLICATION_MODAL);
        } else {
            //System.out.println("puso modalidad none");
            ventana.initModality(Modality.NONE);
        }

        VentanasFX.sinBordes(s, ventana);
        //iniDirecciones();
        iniObservableList();
        try {
            cargarEstadoActual();
        } catch (Exception ex) {
            try {
                VisualFX.responerException(ex);
                getCarpetaEA().mkdirs();
                File direccionEA = getDireccionEA();
                if (!direccionEA.exists()) {
                    Archivo.crearParent(direccionEA);
                    Archivo.copiarDesdeJar("Utiles\\ClasesUtiles\\BasesDeDatos\\EstadoActual.s3db", direccionEA);
                }

                crearEstadoActual();
                cargarEstadoActual();
            } catch (Exception ex1) {
                VisualFX.responerException(ex);
            }
        }
        crearTablas();
        iniSeguridad();

        addOnClosed(() -> {
            try {
                crearEstadoActual();
                guardarEstadoActual();
            } catch (Exception ex1) {
                VisualFX.responerException(ex1);
            }
        });
        Parent[] P = darMovimiento();
        if (P != null && P.length > 0) {
            VentanasFX.move(getVentana(), P);
        }
//        if (actulizaciones != null) {
//            actualizadorVisual = new ActualizadorVisual(actulizaciones, this);
//        }
    }

    public void actualizar() throws Exception {

    }

    public void cargarEstadoActual() throws Exception {
    }

    public void crearEstadoActual() throws Exception {
    }

    public void guardarEstadoActual() throws Exception {
    }

    public void iniSeguridad() {
    }

    public void iniObservableList() {
    }

    public void iniDirecciones() {
        setCarpetaEA("Data");
        setNombreEA("EstadoActual.s3db");
    }

    public AnchorPane getPAnchor() {
        return PAnchor;
    }

    public void crearTablas() {
    }

    public Realizar getActulizaciones() {
        return actulizaciones;
    }

    public void setActulizaciones(Realizar actulizaciones) {
        this.actulizaciones = actulizaciones;
    }

    public Stage getVentana() {
        return ventana;
    }

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    public File getCarpetaEA() {
        return carpetaEA;
    }

    public String getNombreEA() {
        return nombreEA;
    }

    public void setCarpetaEA(String carpetaEA) {
        setCarpetaEA(new File(carpetaEA));
    }

    public void setCarpetaEA(File carpetaEA) {
        this.carpetaEA = carpetaEA;
    }

    public void setNombreEA(String nombreEA) {
        this.nombreEA = nombreEA;
    }

    public void close() {
        ventana.close();

    }

    public void show() {
        // ventana.showAndWait();
        ventana.show();
        //dialog.setStyle("-fx-background-radius: 40; -fx-border-radius: 40; -fx-background-color: red; -fx-border-width: 5; -fx-border-color: orange;");
        //  dialog.show(sp);
    }

    public void addOnClosed(Realizar r) {
        EventosFX.addOnHIDING(ventana, r);
    }

    public void addOnShowing(Realizar r) {
        EventosFX.addOnShowing(ventana, r);
    }

    public boolean isShowing() {
        return ventana.isShowing();
    }

    public void minimizar() {
        //System.out.println("Se llama minimisar");
        ventana.setIconified(true);
    }

    public void minimizar(boolean estadoMinimisado) {
        ventana.setIconified(estadoMinimisado);
    }

//    public BDConexionController crearBDConexion() {
//        return BDConexionController.getMY_SQLConexion(getDireccionEA());
//    }
    public BDConexion getBDConexion() {
        return BDConexion.getConexionSQL_LITE(getDireccionEA());
    }

    public Parent[] darMovimiento() {
        return new Parent[0];
    }

    public static void addTextLn(TextInputControl t, String texto) {
        VisualFX.addTextLn(t, texto);
    }

    public static boolean responerException(Exception ex) {
        return VisualFX.responerException(ex);
    }

    public void showProgressDialogSpinnerCancelar(String mensaje, RealizarConException accionSubProceso) {
        final Thread t[] = {null};
        Dialogo_Progress_SpinnerController dlg = VentanasFX.dialogoProgressSpinner(mensaje, () -> t[0].interrupt());
        final boolean termino[] = {false};
        t[0] = MetodosUtiles.subProceso(() -> {
            try {
                accionSubProceso.realizar();
            } catch (Exception ex) {
                VisualFX.enProcesoVisual(() -> responerException(ex));
            } finally {
                VisualFX.enProcesoVisual(() -> dlg.close());
                termino[0] = true;
            }
        });
        if (!termino[0]) {
            dlg.show();
            if (termino[0]) {
                dlg.close();
            }
        }

    }

    public <E> void showProgressDialogSpinnerCancelar(String mensaje, CreadorConException<E> accionSubProceso, UtilizarConException<E> alTerminarEnVisual) {
        showProgressDialogSpinnerCancelar(mensaje, () -> alTerminarEnVisual.utilizar(accionSubProceso.crear()));

    }

    public static <E extends Controlador> E showController(Class c) throws IOException {
        return VentanasFX.showController(c);
    }

    public static void enProcesoVisual(Runnable r) {
        VisualFX.enProcesoVisual(r);
    }

    public static void activarComponentes(boolean b, Node... N) {
        VisualFX.activarComponentes(b, N);
    }

    public static boolean isVisibleOR(Controlador... C) {
        return VisualFX.isVisibleOR(C);
    }

    public static void showDialogoGetTexto(String mensaje, Consumer<String> accionAceptar) {
        VentanasFX.dialogoGet_Texto().show(mensaje, accionAceptar);
    }

    public Object[] setMove(Parent... P) {
        return Utiles.FX.VentanasFX.move(getVentana(), P);
    }
    
    public static void crearTabla(JFXTreeTableView<objetoTreeTableView> TV, ObservableList<objetoTreeTableView> ob, JFXTreeTableColumn<objetoTreeTableView, ?>... T) {
        TablasFX.crearTabla(TV, ob, T);
    }

    public static <E> ObservableList<objetoTreeTableView> getObTreeTableView(E... O) {
        ObservableList<objetoTreeTableView> res = FXCollections.observableArrayList();
        VisualFX.actualizarObs(res, O);
        return res;
    }
    
    public static <E> JFXTreeTableColumn<objetoTreeTableView, String> getTreeTableColumn(String nombre, double w, Callback<E, String> c) {
    return TablasFX.getTreeTableColumn(nombre, w, c);
    }
    public static void relacionarComponentes(final JFXTreeTableView TV, ObservableList ob, Node soloNoVacia, Node... nesecitaTenerSelecionado) {
    Utiles.FX.VisualFX.relacionarComponentes(TV, ob, soloNoVacia, nesecitaTenerSelecionado);
    }
    public static <E> E getSeleccionado(TreeTableView<objetoTreeTableView> TV) {
    return objetoTreeTableView.getValorSeleccionado(TV);
    }
    
    public static void setDisable(boolean disable,Node ... N){
     VisualFX.setDisable(disable, N);
    }
    public static int getI(ToggleGroup t) {
    return VisualFX.getI(t);
    }
    public static FileChooser getFileChooser(String descripcion, String... extenciones) {
    return VisualFX.getFileChooser(descripcion, extenciones);
    }
}
