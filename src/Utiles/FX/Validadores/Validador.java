/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Validadores;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.base.ValidatorBase;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author Rene
 */
public class Validador<E> extends ValidatorBase {

    Predicate<Validador> predicate;
    private String mensajeDeError;
    private boolean soloMensaje;

    public Validador() {
        //super();
       //this("Es incorrecto", v->true);
       this(null,null);
    }

    public Validador(String mensaje, Predicate<Validador> p) {
        this(mensaje, false, p);
//        super();
//        this.predicate = p;
//        this.mensajeDeError = mensaje;
    }
 public Validador(String mensaje,boolean soloMensaje, Predicate<Validador> p) {
        super();
        this.predicate = p;
        this.mensajeDeError = mensaje;
        this.soloMensaje=soloMensaje;
    }
    @Override
    protected void eval() {
//        setMessage("No puede estar vacio");
        if (srcControl.get() instanceof DatePicker) {
            //  evalDate();
            // System.out.println("este");
            DatePicker dp = (DatePicker) srcControl.get();
            evalObject(v -> {
                // System.out.println("es="+(dp.getValue()==null||dp.getEditor().getText().isEmpty()));
                return dp.getValue() == null || dp.getEditor().getText().isEmpty();
            });
            return;
        }
        if (srcControl.get() instanceof JFXPasswordField) {
            setMessage(mensajeDeError);
//            System.out.println("psa");
            final PasswordField passwordField = (PasswordField) srcControl.get();
            //System.out.println("passwordField.getText()=" + passwordField.getText());
//            System.out.println("passwordField.getText() == null || passwordField.getText().isEmpty()||passwordField.getText().trim().equals(\"\")="+(passwordField.getText() == null || passwordField.getText().isEmpty()||passwordField.getText().trim().equals("")));
            evalObject(v -> passwordField.getText() == null || passwordField.getText().isEmpty() || passwordField.getText().trim().equals(""));
            // evalTextInputField();
            return;
        }
        if (srcControl.get() instanceof TextInputControl) {
            final TextInputControl textField = (TextInputControl) srcControl.get();

            evalObject(v -> textField.getText() == null || textField.getText().isEmpty());
            // evalTextInputField();
        }
        if (srcControl.get() instanceof ComboBox) {
            //  evalDate();
            // System.out.println("este");
            ComboBox dp = (ComboBox) srcControl.get();
            evalObject(v -> {
                if (dp.getValue() == null || dp.getEditor().getText().isEmpty()) {
                    return true;
                }
                for (int i = 0; i < dp.getItems().size(); i++) {
                    if (dp.getEditor().getText().trim().equalsIgnoreCase(dp.getItems().get(i).toString())) {
                        return false;
                    }
                }
                setMessage("No lo contiene");
                return true;
                // System.out.println("es="+(dp.getValue()==null||dp.getEditor().getText().isEmpty()));
                //return dp.getValue() == null || dp.getEditor().getText().isEmpty();
            });
            return;
        }

    }
    boolean a = true;

    private void evalObject(Predicate<E> pre) {
        if (srcControl.get() == null || pre.test((E) srcControl.get())) {
            hasErrors.set(true);
            
            if ((soloMensaje||(srcControl.get() instanceof PasswordField))&&mensajeDeError!=null&&!mensajeDeError.isEmpty()) {
                setMessage(mensajeDeError);
            } else {
                setMessage("No puede estar vacio");
            }
        } else {

            if (predicate != null) {
                //  System.out.println("mensajeDeError=" + mensajeDeError);
//                if (a&&!getMessage().equals(mensajeDeError)) {
//                    hasErrors.set(false);
//
//                    setMessage(mensajeDeError);
//                    a=false;
//                    validate();
//                    a=true;
//                    return;
//                }

                // hasErrors.set(false);
              //  System.out.println("1 " + getMessage());
                setMessage(null);
                setMessage(mensajeDeError);
                hasErrors.set(!predicate.test(this));
                
               // System.out.println(getMessage());

               
             
            } else {
                hasErrors.set(false);
                setMessage(null);
            }

        }

    }

    @Override
    public void setMessage(String msg) {
        super.setMessage(msg); //To change body of generated methods, choose Tools | Templates.
    }


    

//    private void evalDate() {
//        DatePicker dp = (DatePicker) srcControl.get();
//
//        if (dp.getValue() == null) {
//            setMessage("No puede estar vacio");
//            hasErrors.set(true);
//        } else {
//            setMessage(mensajeDeError);
//            hasErrors.set(!predicate.test(srcControl.get()));
//        }
//    }
//
//    private void evalTextInputField() {
//        TextInputControl textField = (TextInputControl) srcControl.get();
//
//        if (textField.getText() == null || textField.getText().isEmpty()) {
//            setMessage("No puede estar vacio");
//            hasErrors.set(true);
//        } else {
//            setMessage(mensajeDeError);
//            hasErrors.set(!predicate.test(srcControl.get()));
//        }
//    }
    public Predicate<Validador> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<Validador> predicate) {
        this.predicate = predicate;
    }

//    public String getMensajeDeError() {
//        return mensajeDeError;
//    }
//
//    public void setMensajeDeError(String mensajeDeError) {
//        this.mensajeDeError = mensajeDeError;
//    }
}
