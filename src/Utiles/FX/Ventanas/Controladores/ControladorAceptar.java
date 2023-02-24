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
public abstract class ControladorAceptar extends Controlador{
      Realizar R;

    @FXML
    private Text Te;

    
    @FXML
    private JFXButton BAceptar;

    @FXML
    private EmojiOneView Icono;

    @FXML
    void apretoAceptar(MouseEvent event) {
//        System.out.println("R="+R);
        R.realizar();
        close();
    }

    protected void show(String mensaje) {
        show(mensaje, ()->{});
    }

  

    protected void show(String mensaje, EmojiOne icono, Realizar r) {
        Icono.setIcon(icono);
        show(mensaje, r);
    }

    protected void show(String mensaje, Realizar r) {

        R = r;
        Te.setText(mensaje);

        show();
        ///   getDialog().show(sp);
    }

    protected void show(Creador<String> c, Realizar r) {

        R = r;
        Te.setText(c.crear());

        show();
        ///   getDialog().show(sp);
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

    public Text getTe() {
        return Te;
    }

    public void setTe(Text Te) {
        this.Te = Te;
    }

   

    public JFXButton getBAceptar() {
        return BAceptar;
    }

    public void setBAceptar(JFXButton BAceptar) {
        this.BAceptar = BAceptar;
    }

}
