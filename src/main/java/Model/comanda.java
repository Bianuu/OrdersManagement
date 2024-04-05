package Model;

import BusinessLogicBLL.clientiBLL;
import BusinessLogicBLL.produseBLL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class comanda {
    private int id;
    private int idClient;
    private int idProdus;
    private int Cantitate;

    /**
     * Contruscotrul clasei cu parametrul id
     */
    public comanda(int id) {
        this();
        this.id = id;
    }

    /**
     * Contructorul default al clasei
     */
    public comanda() {
        this.id = -1;
        this.idClient = -1;
        this.idProdus = -1;
        this.Cantitate = -1;
    }

    /**
     * Contructorul clasei cu parametri id , idClient, idProdus , Cantitate
     *
     * @param id        - curent
     * @param idClient  - clientului
     * @param idProdus  - produsului
     * @param Cantitate - cantitatea
     */
    public comanda(int id, int idClient, int idProdus, int Cantitate) {
        this(idClient, idProdus, Cantitate);
        this.id = id;
    }

    /**
     * Contructorul clasei cu parametri idClient, idProdus , Cantitate
     *
     * @param idClient  - clientului
     * @param idProdus  - produsului
     * @param Cantitate - cantitatea
     */
    public comanda(int idClient, int idProdus, int Cantitate) {
        this();
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.Cantitate = Cantitate;
    }

    /**
     * @return - id curent
     */
    public int getId() {
        return id;
    }

    /**
     * Seteaza id-ul obiectului
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return - id clientului
     */

    public int getIdClient() {
        return idClient;
    }

    /**
     * Seteaza id-ul clientului
     */

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return - id produsului
     */

    public int getIdProdus() {
        return idProdus;
    }

    /**
     * Seteaza id-ul produsului
     */

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    /**
     * @return - cantitatea
     */

    public int getCantitate() {
        return Cantitate;
    }

    /**
     * Seteaza cantitatea
     */

    public void setCantitate(int cantitate) {
        this.Cantitate = cantitate;
    }

    /**
     * Salveaza datele intr-un fisier text
     */
    public void exporteazaTxtBon() {
        String filename = "src/main/java/bonComanda/bonComanda_" + this.id + ".txt";
        try {
            File myFile = new File(filename);
            myFile.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(this.toString());
            myWriter.close();
        } catch (IOException ignored) {
        }

    }

    /**
     * Converteste inforamtiile intr-un format text
     *
     * @return - informatiile de pe bon
     */
    public String toString() {
        clienti clienti = new clientiBLL().cautaClientiDupaId(idClient);
        produse produse = new produseBLL().cautaProdusDupaId(idProdus);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String strDate = dateFormat.format(date);

        return "idComanda: " + id +
                "\nidClient: " + idClient + "; numeClient: " + clienti.getNume() +
                "\nidProdus: " + idProdus + "; numeProdus: " + produse.getNume() + "; Pret: " + produse.getPret() +
                "\nCantitate: " + Cantitate +
                "\n\npretTotal: " + produse.getPret() * Cantitate + " lei" +
                "\n\nData: " + strDate;
    }
}
