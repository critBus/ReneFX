/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Validadores;

import Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.FX.EventosFX;
import Utiles.FX.VisualFX;
import static Utiles.FX.VisualFX.activarComponentes;
import static Utiles.FX.VisualFX.esValido;
import static Utiles.FX.VisualFX.getObservableValue;
import static Utiles.FX.VisualFX.setRealizarAObservableValues;
import static Utiles.FX.VisualFX.validar;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

/**
 *
 * @author Rene
 */
public class Validacion {

    private Creador<ValidatorBase> creadorDeValidador;
    private JFXDialog dialogQueValida;
    //private Button botonADesactivar;
    private LinkedList<Node> aDesactivar = new LinkedList();
    private LinkedList paraSacarPropiedadesQueValidan = new LinkedList();
    private LinkedList<Button> botonesQueValidan = new LinkedList();
    private LinkedList<Node> nodosAValidar = new LinkedList();
    private Stage ventanaQueValida;

    boolean iniciado = false;

    public Validacion(Creador<ValidatorBase> creadorDeValidador) {
        this.creadorDeValidador = creadorDeValidador;
    }

    public Validacion(String mensaje, Predicate<Validador> p, Node... NodosAValidar) {
        this(() -> new Validador(mensaje, p));
        addNodosAValidar(NodosAValidar);

    }

//    public Validacion(String mensaje, Predicate<Validador> p) {
//        this(() -> new Validacion(mensaje, p));
//    }
    public Validacion setDialogQueValida(JFXDialog dialogQueValida) {
        this.dialogQueValida = dialogQueValida;
        return this;
    }

    public Validacion addADesactivar(Node... desactivar) {
        aDesactivar.addAll(Arrays.asList(desactivar));
        return this;
    }
//    public Validacion setBotonADesactivar(Button botonADesactivar) {
//        this.botonADesactivar = botonADesactivar;
//        return this;
//    }

    public Validacion setVentanaQueValida(Stage ventanaQueValida) {
        this.ventanaQueValida = ventanaQueValida;
        return this;
    }

    public Validacion addObjectParaSacarPropiedadesQueValidan(Object... O) {
        paraSacarPropiedadesQueValidan.addAll(Arrays.asList(O));
        return this;
    }

    public Validacion addBotonesQueValidan(Button... B) {
        botonesQueValidan.addAll(Arrays.asList(B));
        return this;
    }

    public Validacion addNodosAValidar(Node... N) {
        nodosAValidar.addAll(Arrays.asList(N));
        return this;
    }

    public ValidatorBase[] start() {
        if (iniciado) {
            return null;
        }
        iniciado = true;
        if (ventanaQueValida != null) {
            paraSacarPropiedadesQueValidan.add(ventanaQueValida);
        }

        return setValidator2(creadorDeValidador, dialogQueValida, aDesactivar.toArray(new Node[0]), paraSacarPropiedadesQueValidan.toArray(), botonesQueValidan.toArray(new Button[0]), nodosAValidar.toArray(new Node[0]));
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, JFXDialog dialog, Button botonesQueValidan[], Node... T) {
        return setValidator(creador, dialog, null, null, botonesQueValidan, T);
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, Stage ventana, Button botonesQueValidan[], Node... T) {
        return setValidator(creador, null, null, new Object[]{ventana}, botonesQueValidan, T);
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, JFXDialog dialog, Button botonADesactivar, Button botonesQueValidan[], Node... T) {
        return setValidator(creador, dialog, botonADesactivar, null, botonesQueValidan, T);
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, Stage ventana, Button botonADesactivar, Button botonesQueValidan[], Node... T) {
        return setValidator(creador, null, botonADesactivar, new Object[]{ventana}, botonesQueValidan, T);
    }
//ObservableValue proidadesQueValidan[]

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, JFXDialog dialog, Object sacarPropiedadesQueValidan[], Button botonesQueValidan[], Node... T) {
        return setValidator(creador, dialog, null, sacarPropiedadesQueValidan, botonesQueValidan, T);
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, Stage ventana, Node... T) {
        return setValidator(creador, null, null, new Object[]{ventana}, null, T);
    }

    public static ValidatorBase[] setValidator(JFXDialog dialog, Node... T) {
        return setValidator(null, dialog, null, null, null, T);
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, final JFXDialog dialog, Node... T) {
        return setValidator(creador, dialog, null, null, null, T);
    }

    public static ValidatorBase[] setValidator(Stage s, Node... T) {
        return setValidator(null, null, null, new Object[]{s}, null, T);
    }

    public static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, final JFXDialog dialog, Object sacarPropiedadesQueValidan[], Node... T) {
        return setValidator(creador, dialog, null, sacarPropiedadesQueValidan, null, T);
    }

    public static ValidatorBase[] setValidator(Button botonAdesactivar, TextInputControl... T) {
        return setValidator(() -> new RequiredFieldValidator("No puede estra vacio"), null, botonAdesactivar, null, botonAdesactivar != null ? new Button[]{botonAdesactivar} : null, T);
    }

    private static ValidatorBase[] setValidator(Creador<ValidatorBase> creador, final JFXDialog dialog, final Button botonADesactivar, Object sacarPropiedadesQueValidan[], Button botonesQueValidan[], Node... T) {
        ValidatorBase V[] = new ValidatorBase[T.length];

        final IFXValidatableControl L[] = new IFXValidatableControl[T.length];
        for (int i = 0; i < T.length; i++) {

            V[i] = creador != null ? creador.crear() : new Validador();
            //    IFXLabelFloatControl              IFXValidatableControl
            if (T[i] instanceof IFXValidatableControl) {
                L[i] = (IFXValidatableControl) T[i];
                L[i].setValidators(V[i]);
                // System.out.println("dio");
            }

        }
        Realizar R = () -> {
//            if(T[0] instanceof TextField){
//            System.out.println("apreto "+((TextField) T[0]).getText());
//            }

            validar(L);
            if (botonADesactivar != null) {
                activarComponentes(esValido(V), botonADesactivar);
            }
        };
        if (botonADesactivar != null) {
            EventosFX.addOnMouseReleased(e -> R.realizar(), botonADesactivar);
        }
        for (int i = 0; i < T.length; i++) {
            //System.out.println("add");
            //   if (T[i] instanceof IFXLabelFloatControl) {
            ObservableValue o = getObservableValue(T[i]);
            if (o != null) {
                o.addListener(e -> R.realizar());
            }
//                if (T[i] instanceof TextField) {
//                    ((TextField) T[i]).textProperty().addListener(e -> R.realizar());
//                }
//                if (T[i] instanceof ComboBoxBase) {
//                    ((ComboBoxBase) T[i]).valueProperty().addListener(e -> R.realizar());
//                }
            T[i].focusedProperty().addListener(e -> R.realizar());
            // }
        }
        if (botonesQueValidan != null && botonesQueValidan.length != 0) {
            // System.out.println("aa");
            //addOnMouseReleased(e -> R.realizar(), botonesQueValidan);
            EventosFX.addOnMouseReleased(e -> R.realizar(), botonesQueValidan);
        }

        if (dialog != null) {
//            dialog.setOnDialogOpened(e->{System.out.println("abrio 22");});
//            dialog.hoverProperty().addListener(e -> {
//                  System.out.println("abrio");
//                if (dialog.isHover()) {
//                    R.realizar();
//                  
//                }
//            });
            //dialog.hoverProperty().addListener(e -> );
            EventosFX.addOnDialogOpened(dialog, e -> R.realizar());
        }
        if (sacarPropiedadesQueValidan != null) {
            setRealizarAObservableValues(R, sacarPropiedadesQueValidan);
//            ObservableValue proidadesQueValidan[] = getObservableValues(sacarPropiedadesQueValidan);
//            for (int i = 0; i < proidadesQueValidan.length; i++) {
//                proidadesQueValidan[i].addListener(e -> R.realizar());
//            }
        }

        return V;
    }

    private static ValidatorBase[] setValidator2(Creador<ValidatorBase> creador, final JFXDialog dialog, final Node aDesactivar[], Object sacarPropiedadesQueValidan[], Button botonesQueValidan[], Node... T) {
        ValidatorBase V[] = new ValidatorBase[T.length];

        final IFXValidatableControl L[] = new IFXValidatableControl[T.length];
        for (int i = 0; i < T.length; i++) {

            V[i] = creador != null ? creador.crear() : new Validador();
            //    IFXLabelFloatControl              IFXValidatableControl
            if (T[i] instanceof IFXValidatableControl) {
                L[i] = (IFXValidatableControl) T[i];
                L[i].setValidators(V[i]);
                // System.out.println("dio");
            }

        }
        Realizar R = () -> {

            validar(L);
            if (aDesactivar != null && aDesactivar.length > 0) {
                activarComponentes(esValido(V), aDesactivar);
            }
//            if (botonADesactivar != null) {
//                activarComponentes(esValido(V), botonADesactivar);
//            }
        };
        if (aDesactivar != null && aDesactivar.length > 0) {
            EventosFX.addOnMouseReleased(e -> R.realizar(), aDesactivar);
        }
//        if (botonADesactivar != null) {
//            EventosFX.addOnMouseReleased(e -> R.realizar(), botonADesactivar);
//        }
        for (int i = 0; i < T.length; i++) {
            //System.out.println("add");
            //   if (T[i] instanceof IFXLabelFloatControl) {
            ObservableValue o = getObservableValue(T[i]);
            if (o != null) {
                o.addListener(e -> R.realizar());
            }
//                if (T[i] instanceof TextField) {
//                    ((TextField) T[i]).textProperty().addListener(e -> R.realizar());
//                }
//                if (T[i] instanceof ComboBoxBase) {
//                    ((ComboBoxBase) T[i]).valueProperty().addListener(e -> R.realizar());
//                }
            T[i].focusedProperty().addListener(e -> R.realizar());
            // }
        }
        if (botonesQueValidan != null && botonesQueValidan.length != 0) {
            // System.out.println("aa");
            //addOnMouseReleased(e -> R.realizar(), botonesQueValidan);
            EventosFX.addOnMouseReleased(e -> R.realizar(), botonesQueValidan);
        }

        if (dialog != null) {
//            dialog.setOnDialogOpened(e->{System.out.println("abrio 22");});
//            dialog.hoverProperty().addListener(e -> {
//                  System.out.println("abrio");
//                if (dialog.isHover()) {
//                    R.realizar();
//                  
//                }
//            });
            //dialog.hoverProperty().addListener(e -> );
            EventosFX.addOnDialogOpened(dialog, e -> R.realizar());
        }
        if (sacarPropiedadesQueValidan != null) {
            setRealizarAObservableValues(R, sacarPropiedadesQueValidan);
//            ObservableValue proidadesQueValidan[] = getObservableValues(sacarPropiedadesQueValidan);
//            for (int i = 0; i < proidadesQueValidan.length; i++) {
//                proidadesQueValidan[i].addListener(e -> R.realizar());
//            }
        }

        return V;
    }
}
