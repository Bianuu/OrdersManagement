package BusinessLogicBLL;

import BusinessLogicBLL.validari.Validator;
import BusinessLogicBLL.validari.verificareProduseStoc;
import DataAccessDAO.produseDAO;
import Model.produse;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class produseBLL {

    private final List<Validator<produse>> validari;
    private final produseDAO produseDAO;

    /**
     * Construtorul clasei
     */
    public produseBLL() {
        validari = new ArrayList<>();
        validari.add(new verificareProduseStoc());
        produseDAO = new produseDAO();
    }

    /**
     * Cauta produsul dupa id
     *
     * @param id - produsului
     * @return - produsul cautat sau eroare daca acesta nu a fost gasit
     */
    public produse cautaProdusDupaId(int id) {
        produse produse = produseDAO.cautaDupaId(id);
        if (produse == null) {
            throw new NoSuchElementException("Produsul cu id = " + id + " nu a fost gasit");
        }
        return produse;
    }

    /**
     * Cauta produsele in lista de produse
     *
     * @return - lista de produse sau eroare in cazul in care nu au fost gasite
     */
    public List<produse> cautaToateProdusele() {
        List<produse> listaProduse = produseDAO.cautaTot();
        if (listaProduse == null) {
            throw new NoSuchElementException("Nu exista produse!");
        }
        return listaProduse;
    }

    /**
     * Insereaza produs
     */
    public int insereazaProdus(produse produse) {
        return produseDAO.inserare(produse);
    }

    /**
     * Updateaza produs
     */
    public void updateProdus(produse produse) {
        produseDAO.update(produse);
    }

    /**
     * Sterge produse
     */
    public void stergeProdus(produse produse) {
        produseDAO.sterge(produse);
    }

    /**
     * Valideaza produse
     */
    public void valideaza(produse produse) {
        for (Validator<produse> validator : validari) {
            validator.validate(produse);
        }
    }

    /**
     * Initializeaza tabela produse
     */
    public DefaultTableModel initializareTabelProduse() {
        return produseDAO.initializareTabel();
    }
}
