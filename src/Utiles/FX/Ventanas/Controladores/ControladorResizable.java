/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas.Controladores;

import Utiles.FX.VentanasFX;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * BMazimizar y apretoMazimizar
 * @author Rene
 */
public abstract class ControladorResizable extends ControladorConCierre {

    private Borde borde;
    @FXML
    private JFXButton BMazimizar;

    @FXML
    void apretoMazimizar(ActionEvent event) {
        getVentana().setMaximized(!getVentana().isMaximized());
        BMazimizar.setGraphic(new FontAwesomeIconView(!getVentana().isMaximized() ? FontAwesomeIcon.WINDOW_MAXIMIZE : FontAwesomeIcon.WINDOW_RESTORE, "30"));
        borde.actualizar();
    }

    @Override
    public void iniStage(Parent p) {
        super.iniStage(p); //To change body of generated methods, choose Tools | Templates.
        borde = VentanasFX.setBordeExpancivo((Pane) getPAnchor(), getVentana());
    }

    public void maximizar(){
    getVentana().setMaximized(true);
    }
}
