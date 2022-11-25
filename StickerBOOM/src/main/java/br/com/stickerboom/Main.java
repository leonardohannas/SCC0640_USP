package br.com.stickerboom;

import br.com.stickerboom.database.StickerBoomDB;
import br.com.stickerboom.io.view.Terminal;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Terminal.build();
        StickerBoomDB.build();
        Terminal.printStickerBoomLogo();

        System.out.println(StickerBoomDB.getAdministrator("123.456.789-10"));
    }
}
