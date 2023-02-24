/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas.Controladores;

import Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.emojione.EmojiOne;
import de.jensd.fx.glyphs.emojione.EmojiOneView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Rene
 */
public abstract class ControladorAceptarCancelar extends ControladorAceptar {

    @FXML
    private JFXButton BCancelar;

    @FXML
    void apretoCancelar(MouseEvent event) {
        close();
    }

    public JFXButton getBCancelar() {
        return BCancelar;
    }

    public void setBCancelar(JFXButton BCancelar) {
        this.BCancelar = BCancelar;
    }
}
