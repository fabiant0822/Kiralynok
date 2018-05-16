package kiralynok;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Fábián Tamás 2018.05.16.
 */
public class Tabla {

    private final char[][] T;
    private final char UresCella;

    public Tabla(char P) {
        T = new char[8][8];
        UresCella = P;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                T[i][j] = UresCella;
            }
        }
    }

    public void elhelyez(int n) {
        Random r = new Random();
        for (int i = 0; i < n;) {
            int sor = r.nextInt(8);
            int oszlop = r.nextInt(8);
            if (T[sor][oszlop] == UresCella) {
                T[sor][oszlop] = 'K';
                i++;
            }
        }
    }

    public void fajlbair(FileWriter writer) {
        try {
            for (int sor = 0; sor < 8; sor++) {
                for (int oszlop = 0; oszlop < 8; oszlop++) {
                    writer.write(T[sor][oszlop]);
                }
                writer.write("\r\n");
            }
            writer.write("\r\n");
        } catch (IOException e) {
            System.out.println("Hiba a fájl írásakor!");
        }
    }

    public void megjelenit() {
        for (int sor = 0; sor < 8; sor++) {
            for (int oszlop = 0; oszlop < 8; oszlop++) {
                System.out.print(T[sor][oszlop]);
            }
            System.out.println();
        }
    }

    public boolean uresSor(int sorAzon) {
        for (int oszlop = 0; oszlop < 8; oszlop++) {
            if (T[sorAzon][oszlop] != UresCella) {
                return false;
            }
        }
        return true;
    }

    public boolean uresOszlop(int oszlopAzon) {
        for (int sor = 0; sor < 8; sor++) {
            if (T[sor][oszlopAzon] != UresCella) {
                return false;
            }
        }
        return true;
    }

    public int uresOszlopokSzama() {
        int uresDb = 0;
        for (int oszlop = 0; oszlop < 8; oszlop++) {
            if (uresOszlop(oszlop)) {
                uresDb++;
            }
        }
        return uresDb;
    }

    public int uresSorokSzama() {
        int uresDb = 0;
        for (int sor = 0; sor < 8; sor++) {
            if (uresSor(sor)) {
                uresDb++;
            }
        }
        return uresDb;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Az üres tábla: ");
        final Tabla T = new Tabla('#');
        T.megjelenit();

        System.out.println("A feltöltött tábla: ");
        T.elhelyez(8);
        T.megjelenit();

        System.out.println("Üres oszlopok és sorok száma: ");
        System.out.println("Oszlopok: " + T.uresOszlopokSzama());
        System.out.println("Sorok: " + T.uresSorokSzama());

        final String fajlNeve = "tablak64.txt";
        File file = new File(fajlNeve);
        if (file.exists()) file.delete();
        
         System.out.println(fajlNeve);
        FileWriter writer = null;
        try {
            writer = new FileWriter(fajlNeve);
            for (int i = 1; i < 65; i++) {
                Tabla aktTábla = new Tabla('*');
                aktTábla.elhelyez(i);
                aktTábla.fajlbair(writer);
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl írásakor!");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Hiba a fájl írásakor!");
            }
        }
    }

}
