/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas;

import Utiles.FX.Ventanas.Controladores.ControladorAceptarCancelar;
import Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.emojione.EmojiOne;
import de.jensd.fx.glyphs.emojione.EmojiOneView;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dialogo_Aceptar_CancelarController extends ControladorAceptarCancelar {

    public void show(String mensaje, EmojiOne icono, Realizar r) {
        super.show(mensaje, icono, r);
    }

    public void show(String mensaje, Realizar r) {
        super.show(mensaje, r);
    }

    public void show(Creador<String> c, Realizar r) {
        super.show(c, r);
    }

}
