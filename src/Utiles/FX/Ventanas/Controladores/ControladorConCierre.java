/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas.Controladores;

import Utiles.FX.Ventanas.Dialogo_Aceptar_CancelarController;
import Utiles.FX.VentanasFX;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * BCerrar y apretoCerrar  <br/>
 * BMinimizar y  apretoMinimizar
 * 
 * @author Rene
 */
public abstract class ControladorConCierre extends Controlador {

    private Dialogo_Aceptar_CancelarController dialogo_Aceptar_CancelarController;

    @FXML
    private JFXButton BCerrar;

    @FXML
    private JFXButton BMinimizar;

    @FXML
    void apretoMinimizar(ActionEvent event) {
        minimizar();
    }

    @FXML
    void apretoCerrar(ActionEvent event) {
        if (dialogo_Aceptar_CancelarController == null) {
            dialogo_Aceptar_CancelarController = VentanasFX.dialogoAceptarCancelar();
        }
        dialogo_Aceptar_CancelarController.show("Desea cerrar la Aplicacion", () -> close());
    }
    
    public ControladorConCierre move( Parent... P) {
    VentanasFX.move(getVentana(), P);
    return this;
    }
}
