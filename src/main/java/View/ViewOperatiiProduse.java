package View;

import BusinessLogicBLL.produseBLL;
import Model.produse;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewOperatiiProduse extends JFrame {
    private final JPanel panouContinut;

    private final JPanel panouProduse;
    private final JTable tabelProduse;
    private final DefaultTableModel tabelModel;
    private final JScrollPane tabelScrollPane;

    private final JPanel panouAdauga;
    private final JLabel adaugaNume;
    private final JTextField adaugaNumee;
    private final JLabel adaugaStoc;
    private final JTextField adaugaStocc;
    private final JLabel adaugaPret;
    private final JTextField adaugaPrett;
    private final JButton adauga;
    private final JButton sterge;
    private final JButton back;
    private final JPanel panouModifica;
    private final JLabel modificaNume;
    private final JTextField modificaNumee;
    private final JLabel modificaStoc;
    private final JTextField modificaStocc;
    private final JLabel modificaPret;
    private final JTextField modificaPrett;
    private final JButton modifica;

    /**
     * Constructorul clasei care creaza un tabel cu datele din baza de date, textfield-uri pentru nume, stoc, pret, un buton care adauga datele din textfield-uri in baza de date, un buton de modificare instanta selectata din baza de date pe baza textfield-urilor asociate lui, un buton care sterge instanta selectata din baza de date
     */
    public ViewOperatiiProduse() {

        super("Ametitoare");

        setSize(650, 500);
        panouContinut = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon imageIcon = new ImageIcon("resources/prod.png");
                Image backgroundImage = imageIcon.getImage();

                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(panouContinut);

        panouProduse = new JPanel(new BorderLayout());

        ///panouContinut.setBackground(Color.YELLOW);

        tabelModel = new produseBLL().initializareTabelProduse();
        tabelProduse = new JTable(tabelModel);
        tabelProduse.setDefaultEditor(Object.class, null);
        tabelScrollPane = new JScrollPane(tabelProduse);
        panouProduse.add(tabelScrollPane);
        panouProduse.setBounds(10, 10, 300, 200);
        panouContinut.add(panouProduse);

        panouAdauga = new JPanel(null);
        adaugaNume = new JLabel("Nume");
        adaugaNume.setBounds(15, 45, 50, 15);
        adaugaNumee = new JTextField();
        adaugaNumee.setBounds(75, 45, 200, 20);
        adaugaStoc = new JLabel("Stoc");
        adaugaStoc.setBounds(15, 70, 50, 15);
        adaugaStocc = new JTextField();
        adaugaStocc.setBounds(75, 70, 200, 20);
        adaugaPret = new JLabel("Pret");
        adaugaPret.setBounds(15, 95, 50, 15);
        adaugaPrett = new JTextField();
        adaugaPrett.setBounds(75, 95, 200, 20);

        adauga = new JButton("ADAUGA");

        Icon poz1 = new ImageIcon("resources/add.png");
        Image img1 = ((ImageIcon) poz1).getImage();
        Image new1 = img1.getScaledInstance(210, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(new1);
        adauga.setIcon(icon1);

        adauga.setEnabled(false);
        adauga.setBounds(75, 125, 200, 70);

        panouAdauga.add(adaugaNume);
        panouAdauga.add(adaugaNumee);
        panouAdauga.add(adaugaStoc);
        panouAdauga.add(adaugaStocc);
        panouAdauga.add(adaugaPret);
        panouAdauga.add(adaugaPrett);
        panouAdauga.add(adauga);
        panouContinut.add(panouAdauga);
        panouAdauga.setBounds(10, 220, 300, 210);

        panouModifica = new JPanel(null);
        modificaNume = new JLabel("Nume");
        modificaNume.setBounds(15, 45, 50, 15);
        modificaNumee = new JTextField();
        modificaNumee.setBounds(75, 45, 200, 20);
        modificaStoc = new JLabel("Stoc");
        modificaStoc.setBounds(15, 70, 50, 15);
        modificaStocc = new JTextField();
        modificaStocc.setBounds(75, 70, 200, 20);
        modificaPret = new JLabel("Pret");
        modificaPret.setBounds(15, 95, 50, 15);
        modificaPrett = new JTextField();
        modificaPrett.setBounds(75, 95, 200, 20);
        modifica = new JButton("UPDATE");

        Icon poz2 = new ImageIcon("resources/update.png");
        Image img2 = ((ImageIcon) poz2).getImage();
        Image new2 = img2.getScaledInstance(210, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(new2);
        modifica.setIcon(icon2);

        modifica.setBounds(75, 125, 200, 70);
        modifica.setEnabled(false);

        panouModifica.add(modificaNume);
        panouModifica.add(modificaNumee);
        panouModifica.add(modificaStoc);
        panouModifica.add(modificaStocc);
        panouModifica.add(modificaPret);
        panouModifica.add(modificaPrett);
        panouModifica.add(modifica);
        panouContinut.add(panouModifica);
        panouModifica.setBounds(320, 220, 300, 210);

        sterge = new JButton("STERGE_produs");

        Icon poz3 = new ImageIcon("resources/delete.png");
        Image img3 = ((ImageIcon) poz3).getImage();
        Image new3 = img3.getScaledInstance(210, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(new3);
        sterge.setIcon(icon3);

        sterge.setBounds(400, 130, 200, 70);
        sterge.setEnabled(false);
        panouContinut.add(sterge);
        back = new JButton("Back");

        Icon poz4 = new ImageIcon("resources/back.png");
        Image img4 = ((ImageIcon) poz4).getImage();
        Image new4 = img4.getScaledInstance(150, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(new4);
        back.setIcon(icon4);

        back.setBounds(450, 10, 150, 70);
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
        tabelProduse.getSelectionModel().addListSelectionListener(e -> {
            int[] rows = tabelProduse.getSelectedRows();
            if (rows.length == 1) {
                modifica.setEnabled(true);
                modificaNumee.setEditable(true);
                modificaStocc.setEditable(true);
                modificaPrett.setEditable(true);
                modificaNumee.setText(tabelProduse.getValueAt(rows[0], 1).toString());
                modificaStocc.setText(tabelProduse.getValueAt(rows[0], 2).toString());
                modificaPrett.setText(tabelProduse.getValueAt(rows[0], 3).toString());
            } else {
                modifica.setEnabled(false);
                modificaNumee.setEditable(false);
                modificaStocc.setEditable(false);
                modificaPrett.setEditable(false);
                curataCampurileModificate();
            }
            sterge.setEnabled(rows.length >= 1);

        });

        DocumentListener documentListener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validareInput();
            }

            void validareInput() {
                adauga.setEnabled(!(adaugaNumee.getText().equals("") ||
                        adaugaStocc.getText().equals("") ||
                        adaugaPrett.getText().equals("")));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validareInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validareInput();
            }
        };

        adaugaNumee.getDocument().addDocumentListener(documentListener);
        adaugaStocc.getDocument().addDocumentListener(documentListener);
        adaugaPrett.getDocument().addDocumentListener(documentListener);

        /**
         *
         * Initializeaza date conform criteriilor si condtiilor date : numele sa aiba primul carater litera si in rest litere sau cifre sau caracterul spatiu sau -, stocul sa fie numar intreg si cantitatea sa fie numar real
         *
         */
        adauga.addActionListener(e -> {
            String nume = adaugaNumee.getText();
            int stoc;
            double pret;
            try {
                if (nume.isEmpty())
                    throw new NumberFormatException();
                if (!Character.isLetter(nume.charAt(0)))
                    throw new NumberFormatException();
                for (int i = 0; i < nume.length(); i++)
                    if (!Character.isLetterOrDigit(nume.charAt(i)) && nume.charAt(i) != ' ' && nume.charAt(i) != '-' && nume.charAt(i) != '.')
                        throw new NumberFormatException();
                stoc = Integer.parseInt(adaugaStocc.getText());
                pret = Double.parseDouble(adaugaPrett.getText());
            } catch (NumberFormatException ex) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f,
                        "Invalid Input! Nume incorect, Stocul trebuie sa fie un numar intreg, Pretul trebuie sa fie numar real!");
                return;
            }

            produseBLL produseBLL = new produseBLL();
            int idValoare = produseBLL.insereazaProdus(new produse(nume, stoc, pret));
            tabelModel.addRow(new Object[]{idValoare, nume, stoc, pret});

            adaugaNumee.setText("");
            adaugaStocc.setText("");
            adaugaPrett.setText("");
        });
        /**
         *
         * Modifica date conform criteriilor si condtiilor date : numele sa aiba primul carater litera si in rest litere sau cifre sau caracterul spatiu sau - ,stocul sa fie numar intreg si cantitatea sa fie numar real
         *
         */
        modifica.addActionListener(e -> {
            int row = tabelProduse.getSelectedRow();
            int id = Integer.parseInt(tabelModel.getValueAt(row, 0).toString());

            String Nume = modificaNumee.getText();
            int stocc;
            double prett;

            try {
                if (Nume.isEmpty())
                    throw new NumberFormatException();
                if (!Character.isLetter(Nume.charAt(0)))
                    throw new NumberFormatException();
                for (int i = 0; i < Nume.length(); i++)
                    if (!Character.isLetterOrDigit(Nume.charAt(i)) && Nume.charAt(i) != ' ' && Nume.charAt(i) != '-' && Nume.charAt(i) != '.')
                        throw new NumberFormatException();
                stocc = Integer.parseInt(modificaStocc.getText());
                prett = Double.parseDouble(modificaPrett.getText());
            } catch (NumberFormatException ex) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f,
                        "Invalid Input! Nume incorect, Stocul trebuie sa fie un numar intreg, Pretul trebuie sa fie numar real!");
                return;
            }

            produseBLL produseBLL = new produseBLL();
            produseBLL.updateProdus(new produse(id, Nume, stocc, prett));
            tabelModel.setValueAt(Nume, row, 1);
            tabelModel.setValueAt(stocc, row, 2);
            tabelModel.setValueAt(prett, row, 3);
            curataCampurileModificate();
        });

        /**
         *
         * Sterge datele selectate din tabel
         *
         */
        sterge.addActionListener(e -> {
            int[] rows = tabelProduse.getSelectedRows();
            produseBLL produseBLL = new produseBLL();
            for (int i = 0; i < rows.length; i++) {
                int id = Integer.parseInt(tabelModel.getValueAt(rows[i] - i, 0).toString());
                produseBLL.stergeProdus(new produse(id));
                tabelModel.removeRow(rows[i] - i);
            }
        });

        back.addActionListener(e -> {
            dispose();
            new ViewPrincipal();
        });
    }

    /**
     * Curata campurile in urma modificari
     */
    private void curataCampurileModificate() {
        modificaNumee.setText("");
        modificaStocc.setText("");
        modificaPrett.setText("");
    }
}
