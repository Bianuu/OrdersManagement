package BusinessLogicBLL;

import BusinessLogicBLL.validari.Validator;
import DataAccessDAO.comandaDAO;
import Model.comanda;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class comandaBLL {

    private final List<Validator<comanda>> validari;
    private final comandaDAO comandaDAO;

    /**
     * Construcotrul clasei
     */
    public comandaBLL() {
        validari = new ArrayList<>();
        comandaDAO = new comandaDAO();
    }

    /**
     * Cauta comanda dupa id
     *
     * @param id - comenzi
     * @return - comanda cautata sau eroare in cazul in care aceasta nu a fost gasita
     */
    public comanda cautaComandaDupaId(int id) {
        comanda comanda = comandaDAO.cautaDupaId(id);
        if (comanda == null) {
            throw new NoSuchElementException("Comanda cu id = " + id + " nu a fost găsita");
        }
        return comanda;
    }

    /**
     * Cauta comenzile in lista de comenzi
     *
     * @return - lista de comenzi sau eroare in cazul in care acestea nu au fost gasite
     */
    public List<comanda> cautaToateComenzile() {
        List<comanda> listaComanda = comandaDAO.cautaTot();
        if (listaComanda == null) {
            throw new NoSuchElementException("Nu exista comenzi!");
        }
        return listaComanda;
    }

    /**
     * Insereaza comanda
     */
    public int inserareComanda(comanda comanda) {
        return comandaDAO.inserare(comanda);
    }

    /**
     * Stergea comanda
     */
    public void stergeComanda(comanda comanda) {
        comandaDAO.sterge(comanda);
    }

    /**
     * Valideaza comanda
     */
    public void valideaza(comanda comanda) {
        for (Validator<comanda> validator : validari) {
            validator.validate(comanda);
        }
    }

    /**
     * Initializaza tabelul cu comenzi
     */
    public DefaultTableModel initializareTabelComanda() {
        return comandaDAO.initializareTabel();
    }
}
