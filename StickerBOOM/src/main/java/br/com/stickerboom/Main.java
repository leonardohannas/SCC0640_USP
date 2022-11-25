package br.com.stickerboom;

import br.com.stickerboom.database.StickerBoomDB;
import br.com.stickerboom.entity.Collector;
import br.com.stickerboom.io.view.Terminal;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Terminal.build();
        StickerBoomDB.build();
        Terminal.printStickerBoomLogo();

        System.out.println(StickerBoomDB.getAdministrator("123.456.789-10"));
        System.out.println(StickerBoomDB.getCollector("678.901.234-56"));

        Collector collector = new Collector("111.111.111-11", "Lucas", "Sanca, 1");
        StickerBoomDB.insertCollector(collector);
        System.out.println(StickerBoomDB.getCollector("111.111.111-11"));

    }
}
