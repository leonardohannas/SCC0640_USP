package br.com.stickerboom.io.view;

import java.util.ResourceBundle;

public final class Terminal {

    private static Terminal instance;
    private static ResourceBundle messages;

    private Terminal() {
        messages = ResourceBundle.getBundle("br.com.stickerboom.io.view.messages");
    }

    public static Terminal getInstance() {
        if (instance == null)
            instance = new Terminal();
        return instance;
    }

    public static void printStickerBoomLogo() {
        System.out.println(messages.getString("stickerboom_logo"));
    }
}
