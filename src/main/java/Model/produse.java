package Model;

public class produse {
    private int id;
    private String Nume;
    private int Stoc;
    private double Pret;

    /**
     * Contruscotrul clasei cu parametrul id
     *
     * @param id
     */
    public produse(int id) {
        super();
        this.id = id;
    }

    /**
     * Contructorul clasei cu parametri id , Nume , Stoc, Pret
     *
     * @param id
     * @param Nume
     * @param Stoc
     * @param Pret
     */
    public produse(int id, String Nume, int Stoc, double Pret) {
        this(Nume, Stoc, Pret);
        this.id = id;
    }

    /**
     * Contructorul clasei cu parametri Nume , Stoc, Pret
     *
     * @param Nume
     * @param Stoc
     * @param Pret
     */
    public produse(String Nume, int Stoc, double Pret) {
        super();
        this.Nume = Nume;
        this.Stoc = Stoc;
        this.Pret = Pret;
    }

    /**
     * Contructorul clasei cu parametri Nume , Stoc
     *
     * @param Nume
     * @param Stoc
     */
    public produse(String Nume, int Stoc) {
        this();
        this.Nume = Nume;
        this.Stoc = Stoc;
    }

    /**
     * Contructorul default al clasei
     */
    public produse() {
        this.id = -1;
        this.Nume = "";
        this.Stoc = -1;
        this.Pret = -1;
    }

    /**
     * Returneaza id-ul curent
     *
     * @return - retureaza id-ul curent
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
     * Returneaza numele produsului
     *
     * @return - id-ul produsului
     */
    public String getNume() {
        return Nume;
    }

    /**
     * Seteaza numele produsului
     */
    public void setNume(String nume) {
        this.Nume = nume;
    }

    /**
     * Returneaza stocul produsului
     *
     * @return - returneaza adresa clientului
     */
    public int getStoc() {
        return Stoc;
    }

    /**
     * Seteaza stocul produsului
     */
    public void setStoc(int stoc) {
        this.Stoc = stoc;
    }

    /**
     * Returneaza pretul produsului
     *
     * @return - returneaza pretul clientului
     */
    public double getPret() {
        return Pret;
    }

    /**
     * Seteaza pretul produsului
     */
    public void setPret(double pret) {
        this.Pret = pret;
    }
}
