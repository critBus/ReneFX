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
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Rene
 */
public abstract class ControladorAceptarCancelarDialogo  extends Controlador{
     Realizar R;

    

    
    @FXML
    private JFXButton BAceptar;

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

    @FXML
    void apretoAceptar(MouseEvent event) {
//        System.out.println("R="+R);
        R.realizar();
        close();
    }

    

  

    protected void show(Realizar r) {
    R = r;
    show();
    }

    @Override
    public void iniStage(Parent p) {
        super.iniStage(p,true); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        BAceptar.setDefaultButton(true);
//        System.out.println("ini cerar");
    }

    public Realizar getR() {
        return R;
    }

    public void setR(Realizar R) {
        this.R = R;
    }

   

   

    public JFXButton getBAceptar() {
        return BAceptar;
    }

    public void setBAceptar(JFXButton BAceptar) {
        this.BAceptar = BAceptar;
    }

}
