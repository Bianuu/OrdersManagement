package Model;

public class clienti {
    private int id;
    private String Nume;
    private String Adresa;
    private String Email;

    /**
     * Contruscotrul clasei cu parametrul id
     */
    public clienti(int id) {
        this();
        this.id = id;
    }

    /**
     * Contructorul default al clasei
     */
    public clienti() {
        this.id = -1;
        this.Nume = "";
        this.Adresa = "";
        this.Email = "";
    }

    public clienti(int id, String Nume, String Adresa, String email) {
        this(Nume, Adresa, email);
        this.id = id;
    }

    /**
     * Contructorul clasei cu parametri Nume , Adresa , email
     */
    public clienti(String Nume, String Adresa, String email) {
        this();
        this.Nume = Nume;
        this.Adresa = Adresa;
        this.Email = email;
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

    public String getNume() {
        return Nume;
    }

    /**
     * Seteaza numele clientului
     */

    public void setNume(String nume) {
        this.Nume = nume;
    }

    /**
     * @return - adresa clientului
     */

    public String getAdresa() {
        return Adresa;
    }

    /**
     * Seteaza adresa clientului
     */

    public void setAdresa(String adresa) {
        this.Adresa = adresa;
    }

    /**
     * @return - emailul clientului
     */

    public String getEmail() {
        return Email;
    }

    /**
     * Seteaza emailul clientului
     */

    public void setEmail(String email) {
        this.Email = email;
    }
}
