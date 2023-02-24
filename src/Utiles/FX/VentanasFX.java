/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX;

import Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.RealizarConException;
import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;
import Utiles.FX.Administradores.VentanaAdministradorController;
import Utiles.FX.Ventanas.Controladores.Borde;
import Utiles.FX.Ventanas.Controladores.Controlador;
import static Utiles.FX.Ventanas.Controladores.Controlador.responerException;
import static Utiles.FX.Ventanas.Controladores.Controlador.showController;
import Utiles.FX.Ventanas.Dialogo_Aceptar_CancelarController;
import Utiles.FX.Ventanas.Dialogo_Get_TextoController;
import Utiles.FX.Ventanas.Dialogo_solo_AceptarController;
import static Utiles.FX.VisualFX.arc;
import Utiles.MetodosUtiles.Archivo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import static Utiles.MetodosUtiles.MetodosUtiles.esNaN;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import java.io.File;
import javafx.scene.input.MouseEvent;
import Utiles.FX.Ventanas.Dialogo_Progress_SpinnerController;
import java.net.URL;

/**
 *
 * @author Rene
 */
public abstract class VentanasFX {

    public static Dialogo_Progress_SpinnerController dialogoProgressSpinner(String mensaje, RealizarConException accionCancelar) {
        try {
            return loadJar(new Dialogo_Progress_SpinnerController(mensaje, accionCancelar), "Utiles/FX/Ventanas/Dialogo_Progress_Spinner.fxml",true);
        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
        return null;
    }

    public static Dialogo_Get_TextoController dialogoGet_Texto() {
        try {
            return loadJar(new Dialogo_Get_TextoController(), "Utiles/FX/Ventanas/Dialogo_Get_Texto.fxml",true);
        } catch (IOException ex) {
            VisualFX.responerException(ex);
        }
        return null;
    }

    public static Dialogo_solo_AceptarController dialogoSoloAceptar() {
        try {
            return loadJar(new Dialogo_solo_AceptarController(), "Utiles/FX/Ventanas/Dialogo_solo_Aceptar.fxml",true);
        } catch (IOException ex) {
            VisualFX.responerException(ex);
        }
        return null;
    }

    public static <E> VentanaAdministradorController<E> dialogoAdministrador(Class tipo, String nombreTabla, UtilizarConException<E> accionAceptar) {
    return dialogoAdministrador(tipo,nombreTabla, accionAceptar, ()->{});
    }
    public static <E> VentanaAdministradorController<E> dialogoAdministrador(Class tipo, String nombreTabla, UtilizarConException<E> accionAceptar, Realizar accionCancelar) {
        try {
            return loadJar(new VentanaAdministradorController<E>( tipo,nombreTabla, accionAceptar, accionCancelar), "Utiles/FX/Administradores/VentanaAdministrador.fxml");
        } catch (IOException ex) {
            VisualFX.responerException(ex);
        }
        return null;
    }
//public static <E> VentanaAdministradorController<E> dialogoAdministrador(Class tipo, String nombreTabla, UtilizarConException<E> accionAceptar, Realizar accionCancelar) {
//        try {
//            return loadJar(new VentanaAdministradorController<E>(tipo, nombreTabla, accionAceptar, accionCancelar), "Utiles/FX/Administradores/VentanaAdministrador.fxml");
//        } catch (IOException ex) {
//            VisualFX.responerException(ex);
//        }
//        return null;
//    }
    public static Borde setBordeExpancivo(Pane p, Stage s) {
        return Borde.setBordeExpancible(p, s);
    }

    public static void setExpandir(Borde b) {
        EventHandler<MouseEvent> e = v -> {
            Realizar r = () -> {
                Stage st = b.getSt();
                double X = v.getScreenX() - st.getX(), Y = v.getScreenY() - st.getY(), mW = b.getP().getMinWidth(), mH = b.getP().getMinHeight(), W = b.getP().getWidth(), H = b.getP().getHeight(),
                        Xd = W - Math.abs(X), Yd = H - Math.abs(Y);
//                System.out.println("expandio X=" + X + " W=" + W);
                Object S = v.getSource();
                Realizar expancionDerecha = () -> {
                    if (X > mW && X != W) {
                        st.setWidth(X);
                    }
                }, expancionAbajo = () -> {
                    if (Y > mH && Y != W) {
                        st.setHeight(Y);
                    }
                }, expancionIsquierda = () -> {
                    if (X < 0) {

                        double Xp = X * -1;
                        st.setWidth(Math.abs(v.getX()) + W);
                        st.setX(st.getX() - Xp);
                    } else {
                        double Wnew = W - v.getX();
                        if (Wnew > mW) {
                            st.setWidth(Wnew);
                            st.setX(st.getX() + X);
                        }
                    }
                }, expancionArriba = () -> {
                    if (Y < 0) {
                        double Yp = Y * -1;
                        st.setHeight(Math.abs(v.getY()) + H);
                        st.setY(st.getY() - Yp);
                    } else {
                        double Hnew = H - v.getY();
                        if (Hnew > mW) {
                            st.setHeight(Hnew);
                            st.setY(st.getY() + Y);
                        }
                    }
                };
                if (or(S, b.getLDerecha())) {
                    expancionDerecha.realizar();
                    return;
                }
                if (or(S, b.getLArriba())) {
                    expancionArriba.realizar();
                    return;

                }
                if (or(S, b.getLIsquerda())) {
                    expancionIsquierda.realizar();
                    return;

                }
                if (or(S, b.getLAbajo())) {
                    expancionAbajo.realizar();
                    return;
                }
                if (or(S, b.getArAbajoDerecha())) {
                    expancionAbajo.realizar();
                    expancionDerecha.realizar();
                    return;
                }
                if (or(S, b.getArAbajoIsquerda())) {
                    expancionAbajo.realizar();
                    expancionIsquierda.realizar();
                    return;
                }
                if (or(S, b.getArArribaIsquierda())) {
                    expancionArriba.realizar();
                    expancionIsquierda.realizar();
                    return;
                }
                if (or(S, b.getArArribaDerecha())) {
                    expancionArriba.realizar();
                    expancionDerecha.realizar();
                    return;
                }
            };
            r.realizar();
            b.actualizar();
            v.consume();
        };

//        b.getLDerecha().setOnMouseDragged(e);
//        b.getLArriba().setOnMouseDragged(e);
//        b.getLIsquerda().setOnMouseDragged(e);
//        b.getLAbajo().setOnMouseDragged(e);
        EventosFX.addOnMouseDragged(e, b.getLDerecha(), b.getLArriba(), b.getLIsquerda(), b.getLAbajo(),
                b.getArAbajoDerecha(), b.getArAbajoIsquerda(), b.getArArribaDerecha(), b.getArArribaIsquierda());
//        VisualFX.addOnMouseDRAGGED(b.getLDerecha(), v -> {
//            double X = v.getSceneX(), Y = v.getSceneY(), mW = b.getP().getMinWidth(), mH = b.getP().getMinHeight(), W = b.getP().getWidth(), H = b.getP().getHeight();
//            if (X > mW && X > W) {
//                b.getP().setPrefWidth(X);
//                System.out.println("expandio X="+X+" W="+W);
//            }
//            //b.getP().
//        });
    }

    public static Dialogo_Aceptar_CancelarController dialogoAceptarCancelar() {
        try {
            return loadJar(new Dialogo_Aceptar_CancelarController(), "Utiles/FX/Ventanas/Dialogo_Aceptar_Cancelar.fxml",true);
        } catch (IOException ex) {
            VisualFX.responerException(ex);
        }
        return null;
    }

    public static <E extends Controlador> E loadClass(Class c, URL u) throws IOException {
        FXMLLoader fx = new FXMLLoader(u);
        Parent root = fx.load();
        Controlador ve = fx.getController();
        ve.iniStage(root);
        return (E) ve;
    }

    public static <E extends Controlador> E loadClass(Class c, String direccion) throws IOException {
//        FXMLLoader fx = new FXMLLoader(c.getResource(direccion));
//        Parent root = fx.load();
//        Controlador ve = fx.getController();
//        ve.iniStage(root);
//        return (E) ve;
        return loadClass(c, c.getResource(direccion));
    }
public static <E extends Controlador> void showControllerEnSubProceso(Class c,Utilizar<E> alShowing)  {
    try { final  E e = loadClass(c, c.getResource(c.getSimpleName().replace("Controller", "") + ".fxml"));
    alShowing.utilizar(e);
    VisualFX.enProcesoVisual(() -> {
           
               e.show();

            
        });
        } catch (Exception ex) {
                responerException(ex);
            }
}
    public static <E extends Controlador> E showController(Class c) throws IOException {
        E e = loadClass(c, c.getResource(c.getSimpleName().replace("Controller", "") + ".fxml"));
        e.show();
        return e;

    }

    public static <E extends Controlador> E load(Object o, String direccion) throws IOException {
        return loadClass(o.getClass(), direccion);
//        FXMLLoader fx = new FXMLLoader(o.getClass().getResource(direccion));
//        Parent root = fx.load();
//        Controlador ve = fx.getController();
//        ve.iniStage(root);
//        return (E) ve;
    }
public static <E extends Controlador> E loadJar(Controlador c, String direccion) throws IOException {
return loadJar(c, direccion,false);
}
    public static <E extends Controlador> E loadJar(Controlador c, String direccion,boolean modal) throws IOException {
        FXMLLoader fx = new FXMLLoader(Archivo.direccionURLJar(direccion));
        fx.setController(c);
//        System.out.println("direccion="+direccion);
        File f = new File(Archivo.direccionURLJar(direccion).toExternalForm());
//        System.out.println("f="+f);
//        System.out.println("exis="+f.exists());
        Parent root = fx.load();
        c.iniStage(root,modal);
        return (E) c;
    }

    public static void dialogoCerrarVentana(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.YES, ButtonType.NO);
                ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
                if (ButtonType.NO.equals(result)) {
                    evt.consume();
                }

            }
        });
    }

    /**
     * le da movilidad a la ventana por arrastre del raton sobre root
     *
     * @param root
     * @param primaryStage
     * @return
     */
    public static Object move(Parent root, Stage primaryStage) {
        class auxiliar {

            double x, y;

            public auxiliar() {

                EventosFX.addOnMousePressed(root, e -> {
                    x = e.getSceneX();
                    y = e.getSceneY();
                });
                EventosFX.addOnMouseDRAGGED(root, e -> {
                    primaryStage.setX(e.getScreenX() - x);
                    primaryStage.setY(e.getScreenY() - y);
                });
//                root.setOnMousePressed(e -> {
//                    x = e.getSceneX();
//                    y = e.getSceneY();
//                });
//                root.setOnMouseDragged(e -> {
//                    primaryStage.setX(e.getScreenX() - x);
//                    primaryStage.setY(e.getScreenY() - y);
//                });
            }

        }
        return new auxiliar();
    }

    /**
     * le da movilidad a la ventana por arrastre del raton sobre los parent
     *
     * @param root
     * @param primaryStage
     * @return
     */
    public static Object[] move(Stage primaryStage, Parent... P) {
        Object[] O = new Object[P.length];
        for (int i = 0; i < P.length; i++) {
            O[i] = move(P[i], primaryStage);
//            P[i].setCursor(Cursor.MOVE);
        }

        return O;
    }

    public static void trans(Scene s, Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        s.setFill(Color.rgb(255, 255, 255, 0.01));
        move(s.getRoot(), stage);
    }

    public static void sinBordes(Scene s, Stage stage) {
        sinBordes(s, stage, false);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        s.setFill(Color.TRANSPARENT);
//        // bordes(s, new CornerRadii(30));
//        move(s.getRoot(), stage);
    }

    public static void sinBordes(Scene s, Stage stage, boolean fija) {
        stage.initStyle(StageStyle.TRANSPARENT);
        s.setFill(Color.TRANSPARENT);
        // bordes(s, new CornerRadii(30));
        if (!fija) {
            move(s.getRoot(), stage);
        }

    }
}
