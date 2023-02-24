/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX.Celdas;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

    private final JFXComboBox<T> comboBox;
    private final ObservableList<T> data;
    private boolean moveCaretToPos = false;
    private int caretPos;
    private T defaultItem;

    public AutoCompleteComboBoxListener(final JFXComboBox<T> comboBox) {
        this.comboBox = comboBox;
        data = comboBox.getItems();
        
        this.comboBox.setEditable(true);
        this.comboBox.setOnKeyPressed(t -> comboBox.hide());
        this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        this.comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                if(!data.contains(this.comboBox.getValue())){
                    
                }
            }
        });
    }
    
    public AutoCompleteComboBoxListener(final JFXComboBox<T> comboBox, T defaultItem) {
        this.comboBox = comboBox;
        data = comboBox.getItems();
        this.defaultItem = defaultItem;

        this.comboBox.setEditable(true);
        this.comboBox.setOnKeyPressed(t -> comboBox.hide());
        this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        this.comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                if(!data.contains(this.comboBox.getValue())){
                    this.comboBox.getSelectionModel().select(defaultItem);
                }
            }
        });
        this.comboBox.getSelectionModel().select(defaultItem);
    }

    @Override
    public void handle(KeyEvent event) {

        if (null != event.getCode()) {
            switch (event.getCode()) {
                case UP:
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                case DOWN:
                    if (!comboBox.isShowing()) {
                        comboBox.show();
                    }
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                case BACK_SPACE:
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                    break;
                case DELETE:
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                    break;
                default:
                    break;
            }
        }

        if (event.getCharacter().matches("[a-z]")) {
            comboBox.setValue((T) String.valueOf(comboBox.getValue()).toUpperCase());
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }

        ObservableList<T> list = FXCollections.observableArrayList();
        data.stream().filter((aData) -> (String.valueOf(aData).toLowerCase().startsWith(
                AutoCompleteComboBoxListener.this.comboBox
                        .getEditor().getText().toLowerCase()))).forEachOrdered((aData) -> {
            list.add(aData);
        });
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        if (!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if (!list.isEmpty()) {
            comboBox.show();
        }
    }

    private void moveCaret(int textLength) {
        if (caretPos == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(caretPos);
        }
        moveCaretToPos = false;
    }
}
