/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Administradores;

import Utiles.ClasesUtiles.Admininistrador.Administrador;
import Utiles.ClasesUtiles.BasesDeDatos.BDAdministrador;
import Utiles.ClasesUtiles.BasesDeDatos.BDAdministrador2;
import Utiles.ClasesUtiles.BasesDeDatos.BDAdministrador;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.RealizarConException;
import Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;
import Utiles.FX.EventosFX;
import Utiles.FX.Ventanas.Controladores.Controlador;
import Utiles.FX.VisualFX;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Rene
 */
public class VentanaAdministradorController<E> extends Controlador {

    Administrador<E> Ad;
    UtilizarConException<E> accionAceptar;
    Realizar accionCancelar;
    FileChooser jf;
    @FXML
    private JFXListView<String> LV;

    @FXML
    private JFXButton BSeleccionar;

    @FXML
    private JFXButton BCambiarNombre;

    @FXML
    private JFXButton BModificarInformacion;

    @FXML
    private JFXButton BCargarInformacion;

    @FXML
    private JFXButton BGuardarInforamcion;

    @FXML
    private JFXButton BCancelar;

    @FXML
    private JFXButton BEliminarInformacion;

    @FXML
    private JFXTextArea T;

    @FXML
    void apretoCambiarNombre(ActionEvent event) {
        System.out.println("no implementado");
    }

    @FXML
    void apretoCancelar(ActionEvent event) {
        accionCancelar.realizar();
        close();
    }

    @FXML
    void apretoCargarInformacion(ActionEvent event) {
        try {
            File f = jf.showOpenDialog(getVentana());
            if (f != null) {
                Ad.addAdministradorDesdeArchivo(f);
                actualizar();
            }
        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
    }

    @FXML
    void apretoEliminarInformacion(ActionEvent event) {
        try {
            String seleccionado = VisualFX.getSeleccionado(LV);
            Ad.eliminarInformacion(seleccionado);
            LV.getItems().remove(seleccionado);
        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
    }

    @FXML
    void apretoGuardarInforamcion(ActionEvent event) {
        jf.showSaveDialog(getVentana());
    }

    @FXML
    void apretoModificarInformacion(ActionEvent event) {
        System.out.println("no implementado");
    }

    @FXML
    void apretoSeleccionar(ActionEvent event) {
        try {
            Object o = Ad.getInformacion(VisualFX.getSeleccionado(LV));
            System.out.println(" o2=" + o + " " + o.getClass());
            E e = (E) o;
            System.out.println("recasteo");
            accionAceptar.utilizar(e);
            close();
        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
    }

//    public VentanaAdministradorController(Class tipo, String nombreTabla, UtilizarConException<E> accionAceptar, Realizar accionCancelar) {
//        try {
//            //Ad = new BDAdministrador<E>(tipo, nombreTabla);
//            Ad = new BDAdministrador2<E>( nombreTabla,()->getBDConexion());
//            jf = VisualFX.getFileChooser("extenciones " + BDAdministrador.EXTENCION_PARCIAL, BDAdministrador.EXTENCION_PARCIAL);
//            this.accionAceptar = accionAceptar;
//            this.accionCancelar = accionCancelar;
//
//        } catch (Exception ex) {
//            VisualFX.responerException(ex);
//        }
//    }
    private String nombreTabla;

    public VentanaAdministradorController(Class tipo, String nombreTabla, UtilizarConException<E> accionAceptar, Realizar accionCancelar) {
        try {
            System.out.println("ini AD");
            this.nombreTabla = nombreTabla;
            iniDirecciones();
            Ad = new BDAdministrador<E>(tipo, nombreTabla, () -> getBDConexion());
            jf = VisualFX.getFileChooser("extenciones " + BDAdministrador.EXTENCION_PARCIAL, BDAdministrador.EXTENCION_PARCIAL);
            this.accionAceptar = accionAceptar;
            this.accionCancelar = accionCancelar;

        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources); //To change body of generated methods, choose Tools | Templates.
        try {
//            System.out.println("paso por ad");
            //To change body of generated methods, choose Tools | Templates.
            EventosFX.addOnSelected(LV, v -> {
                try {
//                    System.out.println("T=" + T);
                    if (v != null) {
                        if (T != null) {
                            Administrador<E> ad = getAd();
//                            System.out.println("getAd()" + ad);
                            if (ad != null) {
                                T.setText(getAd().getInformacion(v) + "");
                            }
                        }
                    }

                } catch (Exception ex) {
                    responerException(ex);
                }
            });
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    @Override
    public void show() {
        //To change body of generated methods, choose Tools | Templates.
        try {
            actualizar();
            super.show();
        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
    }

    @Override
    public void actualizar() throws Exception {
        super.actualizar(); //To change body of generated methods, choose Tools | Templates.
        LV.getItems().setAll(Ad.getNombres());
        // LV.getItems().clear();
        // LV.getItems().addAll(Ad.getNombres());
    }

    public Administrador<E> getAd() {
        return Ad;
    }

}
