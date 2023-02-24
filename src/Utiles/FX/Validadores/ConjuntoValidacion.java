/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Validadores;

import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import static Utiles.FX.VisualFX.activarComponentes;
import static Utiles.FX.VisualFX.setRealizarAObservableValues;
import com.jfoenix.validation.base.ValidatorBase;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.print.Collation;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

/**
 *
 * @author Rene
 */
public class ConjuntoValidacion {

    private LinkedList<Validacion> validaciones;
    private LinkedList<Predicate> aplicadorDeValidaciones;
    private ObservableList<ObservableValue> observableValuesQueValidan;
    private LinkedList<Object> paraSacarPropiedadesQueValidan;
    private Predicate validacionGeneral;
    private LinkedList<Node> nodosADesactivar;
    
    boolean iniciado ;

    public ConjuntoValidacion(Node... nodosADesactivar) {
        this.nodosADesactivar = new LinkedList<>(Arrays.asList(nodosADesactivar));
        validaciones = new LinkedList<>();
        aplicadorDeValidaciones = new LinkedList<>();
        Collections.fill(aplicadorDeValidaciones, v -> true);
        observableValuesQueValidan = FXCollections.observableArrayList();
        paraSacarPropiedadesQueValidan=new LinkedList<>();
        iniciado=false;
    }

    
    public ConjuntoValidacion addValidacion(Validacion validacion) {
        validaciones.add(validacion);
        return this;
    }
    public Validacion addValidacion(String mensaje, Predicate<Validador> p, Node... NodosAValidar) {
        Validacion v = new Validacion(mensaje, p, NodosAValidar);
        validaciones.add(v);
        return v;
    }

    public Validacion addValidacion(String mensaje, Predicate<Validador> p, Node NodosAValidar[], Predicate aplicadorDeValidacion) {
        Validacion v = addValidacion(mensaje, p, NodosAValidar);
        setAplicadorDeValidacion(validaciones.size() - 1, validacionGeneral);
        return v;
    }

    public ConjuntoValidacion setAplicadorDeValidacion(int indice, Predicate p) {
        aplicadorDeValidaciones.set(indice, p);
        return this;
    }
    
    public ConjuntoValidacion addObservableValuesQueValidan(ObservableValue ...ObservableValuesQueValidan){
    observableValuesQueValidan.addAll(Arrays.asList(ObservableValuesQueValidan));
    return this;
    }
    
    public ConjuntoValidacion addObjectParaSacarPropiedadesQueValidan(Object... O) {
        paraSacarPropiedadesQueValidan.addAll(Arrays.asList(O));
        return this;
    }

    public ConjuntoValidacion setValidacionGeneral(Predicate validacionGeneral) {
        this.validacionGeneral = validacionGeneral;
        return this;
    }
    public ConjuntoValidacion start(){
    if (iniciado) {
            return null;
        }
        iniciado = true;
        ValidatorBase VV[][]=new ValidatorBase[validaciones.size()][];
        for (int i = 0; i < VV.length; i++) {
            VV[i]=validaciones.get(i).start();
        }
        
        relacionarValidador(VV, aplicadorDeValidaciones.toArray(new Predicate[0]), observableValuesQueValidan, paraSacarPropiedadesQueValidan.toArray(), validacionGeneral, nodosADesactivar.toArray(new Node[0]));
       //  (ValidatorBase VV[][], Predicate seleccionarV[],                     ObservableList<E> observableList,                           Object nodosAEscuchar[], Predicate booleanoExtra, Node... N) { 
    return this;
    }
    
    
//    public static void relacionarValidador(ValidatorBase VV[][], Predicate seleccionarV[], Node... N) {
//        relacionarValidador(VV, seleccionarV, null, null, N);
//    }
    public static void relacionarValidador(ValidatorBase VV[][], Object nodosAEscuchar[], Node... N) {
        relacionarValidador(VV, nodosAEscuchar, null, N);
    }

    public static void relacionarValidador(ValidatorBase VV[][], Object nodosAEscuchar[], Predicate booleanoExtra, Node... N) {
        relacionarValidador(VV, null, null, nodosAEscuchar, booleanoExtra, N);
    }

//    public static<E extends ObservableValue> void aa(ObservableList<E> observableList) {
//    }
    public static <E extends ObservableValue> void relacionarValidador(ValidatorBase VV[][], ObservableList<E> observableList, Predicate booleanoExtra, Node... N) {
        relacionarValidador(VV, null, observableList, null, booleanoExtra, N);
    }

    public static <E extends ObservableValue> void relacionarValidador(ValidatorBase VV[][], Predicate seleccionarV[], Object nodosAEscuchar[], Predicate booleanoExtra, Node... N) {
        relacionarValidador(VV, seleccionarV, null, nodosAEscuchar, booleanoExtra, N);
    }

    public static <E extends ObservableValue> void relacionarValidador(ValidatorBase VV[][], Predicate seleccionarV[], ObservableList<E> observableList, Object nodosAEscuchar[], Predicate booleanoExtra, Node... N) {
        Realizar R = () -> {
            if (seleccionarV == null) {

                activarComponentes(VV, booleanoExtra, N);
            } else {
                LinkedList<ValidatorBase[]> l = new LinkedList<>();
                for (int i = 0; i < VV.length; i++) {
                    if (seleccionarV[i].test(i)) {
                        l.add(VV[i]);
                    }
                }
                ValidatorBase VV2[][] = new ValidatorBase[l.size()][];

                for (int i = 0; i < l.size(); i++) {
                    VV2[i] = l.get(i);
                }//l.toArray(new ValidatorBase[0][])
                activarComponentes(VV2, booleanoExtra, N);
            }

        };

        for (int i = 0; i < VV.length; i++) {
            for (int j = 0; j < VV[i].length; j++) {
                VV[i][j].hasErrorsProperty().addListener(v -> {
                    R.realizar();
                });
            }
        }
        if (nodosAEscuchar != null) {
            setRealizarAObservableValues(R, nodosAEscuchar);
        }
        if (observableList != null) {
            observableList.addListener((ListChangeListener) v -> {
                R.realizar();
                ChangeListener chl = (x, x2, x3) -> {
                    R.realizar();
                };
                observableList.forEach(v2 -> {
                    v2.removeListener(chl);
                    v2.addListener(chl);
                });

            });

        }

    }

    

}
