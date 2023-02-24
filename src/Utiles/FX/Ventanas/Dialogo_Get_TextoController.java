/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas;

import Utiles.FX.Ventanas.Controladores.ControladorAceptarCancelar;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.FX.Validadores.Validacion;
import Utiles.FX.VentanasFX;
import Utiles.FX.VisualFX;
import com.jfoenix.controls.JFXTextField;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Rene
 */
public class Dialogo_Get_TextoController extends ControladorAceptarCancelar {

    //Consumer<String> accionAceptar;
    Dialogo_solo_AceptarController dialogo_solo_AceptarController;
    @FXML
    private JFXTextField TF;

    public void show(String mensaje, Consumer<String> accionAceptar) {
        super.show(mensaje, () -> {
            if (TF.getText().isEmpty()) {
                if (dialogo_solo_AceptarController == null) {
                    dialogo_solo_AceptarController = VentanasFX.dialogoSoloAceptar();
                }
                dialogo_solo_AceptarController.show("El texto no puede estar vacio");
                return;
            }
            accionAceptar.accept(TF.getText());
        });
    }

    @Override
    public void iniSeguridad() {
        super.iniSeguridad(); //To change body of generated methods, choose Tools | Templates.
        Validacion.setValidator(getBAceptar(), TF);
        TF.validate();
    }

}
