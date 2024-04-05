package View;

import BusinessLogicBLL.clientiBLL;
import BusinessLogicBLL.comandaBLL;
import BusinessLogicBLL.produseBLL;
import Model.comanda;
import Model.produse;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewOperatiiComenzi extends JFrame {
    private final DefaultTableModel clientiTabelModel = new clientiBLL().initializareTabelClienti();
    private final JPanel panouContinut;
    private final JPanel panouClienti;
    private final JLabel clienti;
    private final JTable tabelClienti;
    private final JScrollPane clientiTabelScrollPane;

    private final JPanel panouProduse;
    private final JLabel produse;
    private final JTable tabelProduse;
    private final DefaultTableModel produseTabelModel;
    private final JScrollPane produseTabelScrollPane;

    private final JPanel panouComnezi;
    private final JLabel comenzi;
    private final JTable tabelComenzi;
    private final DefaultTableModel comenziTabelModel;
    private final JScrollPane comenziTabelScrollPane;

    private final JLabel cantitate;
    private final JTextField cantitatee;
    private final JButton adauga;
    private final JButton sterge;
    private final JButton back;

    /**
     * Constructorul clasei care creaza un tabel cu datele din baza de date comenzi, clienti si produse, buton pentru a adauga o comanda cu textfield-urile asociate lui, buton petru a sterge o comanda data si buton pentru a modifica o coamnda textfield-urile asociate lui
     */
    public ViewOperatiiComenzi() {
        super("Datorii");

        setSize(650, 530);

        panouContinut = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon imageIcon = new ImageIcon("resources/datorii.png");
                Image backgroundImage = imageIcon.getImage();

                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(panouContinut);

        panouClienti = new JPanel(new BorderLayout());

        panouContinut.setBackground(Color.BLUE);

        clienti = new JLabel("Clienti");
        clienti.setBounds(10, 10, 50, 20);
        tabelClienti = new JTable(clientiTabelModel);
        tabelClienti.setDefaultEditor(Object.class, null);
        clientiTabelScrollPane = new JScrollPane(tabelClienti);
        panouClienti.add(clientiTabelScrollPane);
        panouClienti.setBounds(10, 40, 300, 200);
        panouContinut.add(panouClienti);

        panouProduse = new JPanel(new BorderLayout());
        produse = new JLabel("Produse");
        produse.setBounds(10, 10, 50, 20);
        produseTabelModel = new produseBLL().initializareTabelProduse();
        tabelProduse = new JTable(produseTabelModel);
        tabelProduse.setDefaultEditor(Object.class, null);
        produseTabelScrollPane = new JScrollPane(tabelProduse);
        panouProduse.add(produseTabelScrollPane);
        panouProduse.setBounds(10, 280, 300, 200);
        panouContinut.add(panouProduse);

        panouComnezi = new JPanel(new BorderLayout());
        comenzi = new JLabel("Comenzi");
        comenzi.setBounds(10, 10, 50, 20);
        comenziTabelModel = new comandaBLL().initializareTabelComanda();
        tabelComenzi = new JTable(comenziTabelModel);
        tabelComenzi.setDefaultEditor(Object.class, null);
        comenziTabelScrollPane = new JScrollPane(tabelComenzi);
        panouComnezi.add(comenziTabelScrollPane);
        panouComnezi.setBounds(320, 40, 300, 200);
        panouContinut.add(panouComnezi);

        cantitate = new JLabel("Cantitate");
        cantitate.setBounds(320, 280, 50, 20);
        cantitate.setBackground(Color.white);
        panouContinut.add(cantitate);

        cantitatee = new JTextField();
        cantitatee.setEnabled(false);
        cantitatee.setBounds(380, 282, 85, 20);
        panouContinut.add(cantitatee);

        adauga = new JButton("ADAUGA_comanda");

        Icon poz1 = new ImageIcon("resources/add.png");
        Image img1 = ((ImageIcon) poz1).getImage();
        Image new1 = img1.getScaledInstance(130, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(new1);
        adauga.setIcon(icon1);

        adauga.setEnabled(false);
        adauga.setBounds(320, 340, 135, 50);
        panouContinut.add(adauga);

        sterge = new JButton("STERGE_comanda");
        sterge.setEnabled(false);
        sterge.setBounds(480, 340, 145, 50);
        panouContinut.add(sterge);

        back = new JButton("Back");

        Icon poz4 = new ImageIcon("resources/back.png");
        Image img4 = ((ImageIcon) poz4).getImage();
        Image new4 = img4.getScaledInstance(140, 45, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(new4);
        back.setIcon(icon4);

        back.setBounds(320, 400, 135, 50);
        panouContinut.add(back);

        initListeners();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Initializeaza date conform criteriilor si condtiilor date
     */
    private void initListeners() {
        ListSelectionListener listSelectionListener = e -> {
            int[] clientiRows = tabelClienti.getSelectedRows();
            int[] produseRows = tabelProduse.getSelectedRows();
            int[] comenziRows = tabelComenzi.getSelectedRows();

            cantitatee.setEnabled(clientiRows.length == 1 && produseRows.length == 1);
            sterge.setEnabled(comenziRows.length >= 1);
        };

        ///ascundem
        sterge.setVisible(false);

        tabelClienti.getSelectionModel().addListSelectionListener(listSelectionListener);
        tabelProduse.getSelectionModel().addListSelectionListener(listSelectionListener);
        tabelComenzi.getSelectionModel().addListSelectionListener(listSelectionListener);

        cantitatee.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validareCantitate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validareCantitate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validareCantitate();
            }

            void validareCantitate() {
                try {
                    Integer.parseInt(cantitatee.getText());
                    adauga.setEnabled(true);
                } catch (NumberFormatException e) {
                    adauga.setEnabled(false);
                }
            }

        });

        adauga.addActionListener(e -> {
            int clientiRow = tabelClienti.getSelectedRow();
            int produseRow = tabelProduse.getSelectedRow();

            int idClient = Integer.parseInt(tabelClienti.getValueAt(clientiRow, 0).toString());
            int idProdus = Integer.parseInt(tabelProduse.getValueAt(produseRow, 0).toString());
            int Cantitate = Integer.parseInt(cantitatee.getText());

            produseBLL produseBLL = new produseBLL();
            produse produse = produseBLL.cautaProdusDupaId(idProdus);
            produse.setStoc(produse.getStoc() - Cantitate);

            try {
                produseBLL.valideaza(produse);
            } catch (IllegalArgumentException exception) {
                new ViewEsec(exception.getMessage());
                return;
            }

            produseBLL.updateProdus(produse);
            comandaBLL comandaBLL = new comandaBLL();
            comanda comanda = new comanda(idClient, idProdus, Cantitate);
            int idValoare = comandaBLL.inserareComanda(comanda);
            comanda.setId(idValoare);
            tabelProduse.setValueAt(produse.getStoc(), tabelProduse.getSelectedRow(), 2);
            comenziTabelModel.addRow(new Object[]{idValoare, idClient, idProdus, Cantitate});
            comanda.exporteazaTxtBon();
        });

        sterge.addActionListener(e -> {
            int[] rows = tabelComenzi.getSelectedRows();
            comandaBLL comandaBLL = new comandaBLL();
            for (int i = 0; i < rows.length; i++) {
                int id = Integer.parseInt(comenziTabelModel.getValueAt(rows[i] - i, 0).toString());
                comandaBLL.stergeComanda(new comanda(id));
                comenziTabelModel.removeRow(rows[i] - i);
            }
        });

        back.addActionListener(e -> {
            dispose();
            new ViewPrincipal();
        });
    }
}
