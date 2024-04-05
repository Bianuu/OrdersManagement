package BusinessLogicBLL;

import BusinessLogicBLL.validari.Validator;
import DataAccessDAO.clientiDAO;
import Model.clienti;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class clientiBLL {


    private final List<Validator<clienti>> validari;
    private final clientiDAO clientiDAO;

    /**
     * Contrucorul clasei Clienti
     */
    public clientiBLL() {
        validari = new ArrayList<>();
        clientiDAO = new clientiDAO();
    }

    /**
     * Cauta clientul prin ID
     *
     * @param id - utilizatorului
     * @return - clientul in caz contrar returneaza o eroare cum ca acesta nu a fost gasit
     */
    public clienti cautaClientiDupaId(int id) {
        clienti clienti = clientiDAO.cautaDupaId(id);
        if (clienti == null) {
            throw new NoSuchElementException("Clientul cu id = " + id + " nu a fost gasit");
        }
        return clienti;
    }

    /**
     * Cauta toti clienti din lista
     *
     * @return - lista de clienti cautati in caz contrar returneaza o eroare cum ca aceastia nu au fost gasiti
     */
    public List<clienti> cautaTotiClientii() {
        List<clienti> listaClienti = clientiDAO.cautaTot();
        if (listaClienti == null) {
            throw new NoSuchElementException("Nu exista clienti!");
        }
        return listaClienti;
    }

    /**
     * Insereaza un client
     */
    public int inserareClienti(clienti clienti) {
        return clientiDAO.inserare(clienti);
    }

    /**
     * Updateaza un client
     */
    public void updateClienti(clienti clienti) {
        clientiDAO.update(clienti);
    }

    /**
     * Sterege un client
     */
    public void stergeClienti(clienti clienti) {
        clientiDAO.sterge(clienti);
    }


    /**
     * Valideaza o lista de clienti
     */
    public void valideaza(clienti clienti) {
        for (Validator<clienti> validator : validari) {
            validator.validate(clienti);
        }
    }

    /**
     * Initializeaza tabela clienti
     */
    public DefaultTableModel initializareTabelClienti() {
        return clientiDAO.initializareTabel();
    }
}
