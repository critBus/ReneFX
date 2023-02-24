/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Ventanas.Controladores;

import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.RealizarConException;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.emojione.EmojiOneView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Rene
 */
public abstract class ControladorCancelarDialogo  extends Controlador {
    private String mensaje;
      private RealizarConException R;
     @FXML
    private AnchorPane PAnchor;

    @FXML
    private Text TMensaje;

    @FXML
    private JFXButton BCancelar;

   
    @FXML
    private Text TTitulo;

    
    
    @Override
    public void iniStage(Parent p) {
        super.iniStage(p); //To change body of generated methods, choose Tools | Templates.
        this.TMensaje.setText(mensaje);
    }

    

   

    @FXML
    void apretoCancelar(MouseEvent event) {
        try{
        R.realizar();
        }catch(Exception ex){
            responerException(ex);
        }
        close();
    }

    public RealizarConException getR() {
        return R;
    }

    public void setR(RealizarConException R) {
        this.R = R;
    }

   

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        if (this.TMensaje!=null){
        this.TMensaje.setText(mensaje);
        } 
    }

    

    
    
}
