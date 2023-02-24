/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.RealizarConException;
import Utiles.FX.Ventanas.Controladores.ControladorCancelarDialogo;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.emojione.EmojiOneView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Rene
 */
public class Dialogo_Progress_SpinnerController  extends ControladorCancelarDialogo{
    

    @FXML
    private EmojiOneView Icono;

   
    @FXML
    private EmojiOneView Icono1;

    public Dialogo_Progress_SpinnerController( String TMensaje,RealizarConException accionCancelar) {
        setMensaje(TMensaje);
        setR(accionCancelar);
        
    }


   

}


