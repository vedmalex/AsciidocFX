package com.kodcu.component;

import javafx.scene.control.ButtonType;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;

/**
 * Created by usta on 06.03.2015.
 */
public final class AlertHelper {

    public static Optional<ButtonType> deleteAlert() {
        AlertDialog deleteAlert = new AlertDialog(AlertType.WARNING, null, ButtonType.YES, ButtonType.CANCEL);
        deleteAlert.setHeaderText("Вы действительно хотите удалить файл(ы)?");
        return deleteAlert.showAndWait();
    }

    public static Optional<ButtonType> nullDirectoryAlert() {
        AlertDialog deleteAlert = new AlertDialog(AlertType.WARNING, null, ButtonType.OK);
        deleteAlert.setHeaderText("Выберите словарь(и)");
        return deleteAlert.showAndWait();
    }

    public static Optional<ButtonType> notImplementedDialog() {
        AlertDialog alert = new AlertDialog(AlertType.WARNING, null, ButtonType.OK);
        alert.setHeaderText("Эта фича недоступна для Markdown.");
        return alert.showAndWait();
    }

    public static Optional<ButtonType> saveAlert() {
        AlertDialog saveAlert = new AlertDialog();
        saveAlert.setHeaderText("Этот файл не сохранен. Вы хотите его закрыть?");
        return saveAlert.showAndWait();
    }
}
