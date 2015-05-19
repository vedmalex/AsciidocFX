package com.kodcu.component;

/**
 * Created by usta on 16.03.2015.
 */
public final class RenameDialog extends TextDialog {

    public RenameDialog(String content, String title) {
        super(content, title);
    }

    public static RenameDialog create() {
        RenameDialog dialog = new RenameDialog("Введите новое имя файла ", "Переименовать файл ");
        dialog.setKeyReleaseEvent("^[^\\\\/:?*\"<>|]+$");
        return dialog;
    }
}
