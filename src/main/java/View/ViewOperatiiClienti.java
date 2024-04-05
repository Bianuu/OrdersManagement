package View;

import BusinessLogicBLL.clientiBLL;
import Model.clienti;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.regex.Pattern;

public class ViewOperatiiClienti extends JFrame {
    private final JPanel panouContinut;

    private final JPanel panouClienti;
    private final JTable tabelClienti;
    private final DefaultTableModel clientiTabelModel;
    private final JScrollPane tabelScrollPane;

    private final JPanel panouAdauga;
    private final JLabel adaugaNume;
    private final JTextField adaugaNumee;
    private final JLabel adaugaAdresa;
    private final JTextField adaugaAdresaa;
    private final JLabel adaugaEmail;
    private final JTextField adaugaEmaill;
    private final JButton adauga;

    private final JPanel panouModifica;
    private final JLabel modificaNume;
    private final JTextField modificaNumee;
    private final JLabel modificaAdresa;
    private final JTextField modificaAdresaa;
    private final JLabel modificaEmail;
    private final JTextField modificaEmaill;
    private final JButton modifica;

    private final JButton sterge;
    private final JButton back;

    /**
     * Constructorul clasei care creaza un tabel cu datele din baza de date, textfield-uri pentru nume, adresa, email, un buton care adauga datele din textfield-uri in baza de date, un buton de modificare instanta selectata din baza de date pe baza textfield-urilor asociate lui, un buton care sterge instanta selectata din baza de date
     */
    public ViewOperatiiClienti() {
        super("Fidelitatea");

        setSize(650, 500);

        panouContinut = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon imageIcon = new ImageIcon("resources/bet.png");
                Image backgroundImage = imageIcon.getImage();

                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(panouContinut);


        panouClienti = new JPanel(new BorderLayout());
        panouContinut.setBackground(Color.RED);

        clientiTabelModel = new clientiBLL().initializareTabelClienti();
        tabelClienti = new JTable(clientiTabelModel);
        tabelClienti.setDefaultEditor(Object.class, null);
        tabelScrollPane = new JScrollPane(tabelClienti);
        panouClienti.add(tabelScrollPane);
        panouClienti.setBounds(10, 10, 300, 200);
        panouContinut.add(panouClienti);

        panouAdauga = new JPanel(null);
        adaugaNume = new JLabel("Nume");
        adaugaNume.setBounds(15, 45, 50, 15);
        adaugaNumee = new JTextField();
        adaugaNumee.setBounds(75, 45, 200, 20);
        adaugaAdresa = new JLabel("Adresa");
        adaugaAdresa.setBounds(15, 70, 50, 15);
        adaugaAdresaa = new JTextField();
        adaugaAdresaa.setBounds(75, 70, 200, 20);
        adaugaEmail = new JLabel("Email");
        adaugaEmail.setBounds(15, 95, 50, 15);
        adaugaEmaill = new JTextField();
        adaugaEmaill.setBounds(75, 95, 200, 20);
        adauga = new JButton("ADAUGA_client");

        Icon poz1 = new ImageIcon("resources/add.png");
        Image img1 = ((ImageIcon) poz1).getImage();
        Image new1 = img1.getScaledInstance(210, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(new1);
        adauga.setIcon(icon1);


        adauga.setEnabled(false);
        adauga.setBounds(75, 125, 200, 70);
        panouAdauga.add(adaugaNume);
        panouAdauga.add(adaugaNumee);
        panouAdauga.add(adaugaAdresa);
        panouAdauga.add(adaugaAdresaa);
        panouAdauga.add(adaugaEmail);
        panouAdauga.add(adaugaEmaill);
        panouAdauga.add(adauga);
        panouContinut.add(panouAdauga);
        panouAdauga.setBounds(10, 220, 300, 210);

        panouModifica = new JPanel(null);
        modificaNume = new JLabel("Nume");
        modificaNume.setBounds(15, 45, 50, 15);
        modificaNumee = new JTextField();
        modificaNumee.setBounds(75, 45, 200, 20);
        modificaAdresa = new JLabel("Adresa");
        modificaAdresa.setBounds(15, 70, 50, 15);
        modificaAdresaa = new JTextField();
        modificaAdresaa.setBounds(75, 70, 200, 20);
        modificaEmail = new JLabel("Email");
        modificaEmail.setBounds(15, 95, 50, 15);
        modificaEmaill = new JTextField();
        modificaEmaill.setBounds(75, 95, 200, 20);
        modifica = new JButton("UPDATE_client");

        Icon poz2 = new ImageIcon("resources/update.png");
        Image img2 = ((ImageIcon) poz2).getImage();
        Image new2 = img2.getScaledInstance(210, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(new2);
        modifica.setIcon(icon2);


        modifica.setBounds(75, 125, 200, 70);
        modifica.setEnabled(false);
        panouModifica.add(modificaNume);
        panouModifica.add(modificaNumee);
        panouModifica.add(modificaAdresa);
        panouModifica.add(modificaAdresaa);
        panouModifica.add(modificaEmail);
        panouModifica.add(modificaEmaill);
        panouModifica.add(modifica);
        panouContinut.add(panouModifica);
        panouModifica.setBounds(320, 220, 300, 210);

        sterge = new JButton("STERGE_client");

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
        tabelClienti.getSelectionModel().addListSelectionListener(e -> {
            int[] rows = tabelClienti.getSelectedRows();
            if (rows.length == 1) {
                modifica.setEnabled(true);
                modificaNumee.setEditable(true);
                modificaAdresaa.setEditable(true);
                modificaEmaill.setEditable(true);
                modificaNumee.setText(tabelClienti.getValueAt(rows[0], 1).toString());
                modificaAdresaa.setText(tabelClienti.getValueAt(rows[0], 2).toString());
                modificaEmaill.setText(tabelClienti.getValueAt(rows[0], 3).toString());
            } else {
                modifica.setEnabled(false);
                modificaNumee.setEditable(false);
                modificaAdresaa.setEditable(false);
                modificaEmaill.setEditable(false);
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
                        adaugaAdresaa.getText().equals("") ||
                        adaugaEmaill.getText().equals("")));
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
        adaugaAdresaa.getDocument().addDocumentListener(documentListener);
        adaugaEmaill.getDocument().addDocumentListener(documentListener);


        /**
         *
         * Adauga datele conform criteriilor si condtiilor date : numele sa aiba primul carater litera si in rest litere sau cifre sau caracterul spatiu sau -, adresa sa aiba primul caracter litera si in rest litere sau cifre sau spatiu sau -, email sa respecte formatul regex stabilit ???@???.???
         *
         */
        adauga.addActionListener(e -> {
            String Nume = adaugaNumee.getText();
            String Adresa = adaugaAdresaa.getText();
            String Email = adaugaEmaill.getText();

            try {
                if (Nume.isEmpty() || Adresa.isEmpty() || Email.isEmpty())
                    throw new NumberFormatException();
                if (!Character.isLetter(Nume.charAt(0)))
                    throw new NumberFormatException();
                for (int i = 0; i < Nume.length(); i++)
                    if (!Character.isLetter(Nume.charAt(i)) && Nume.charAt(i) != ' ' && Nume.charAt(i) != '-')
                        throw new NumberFormatException();

                if (!Character.isLetter(Adresa.charAt(0)))
                    throw new NumberFormatException();
                for (int i = 0; i < Adresa.length(); i++)
                    if (!Character.isLetterOrDigit(Adresa.charAt(i)) && Adresa.charAt(i) != ' ' && Adresa.charAt(i) != '-' && Adresa.charAt(i) != ','
                            && Adresa.charAt(i) != ';' && Adresa.charAt(i) != '.')
                        throw new NumberFormatException();

                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                Pattern pattern = Pattern.compile(emailRegex);
                if (!pattern.matcher(Email).matches())
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f,
                        "Invalid Input! Nume incorect sau Adresa incorecta sau Email incorect !");
                return;
            }

            clientiBLL clientiBLL = new clientiBLL();
            int idValoare = clientiBLL.inserareClienti(new clienti(Nume, Adresa, Email));
            clientiTabelModel.addRow(new Object[]{idValoare, Nume, Adresa, Email});

            adaugaNumee.setText("");
            adaugaAdresaa.setText("");
            adaugaEmaill.setText("");
        });
        /**
         *
         * Modifica date conform criteriilor si condtiilor date : numele sa aiba primul carater litera si in rest litere sau cifre sau caracterul spatiu sau -, adresa sa aiba primul caracter litera si in rest litere sau cifre sau spatiu sau -, email sa respecte formatul regex stabilit ???@???.???
         *
         */
        modifica.addActionListener(e -> {
            int row = tabelClienti.getSelectedRow();
            int id = Integer.parseInt(clientiTabelModel.getValueAt(row, 0).toString());
            String Numee = modificaNumee.getText();
            String Adresaa = modificaAdresaa.getText();
            String Emaill = modificaEmaill.getText();

            try {
                if (Numee.isEmpty() || Adresaa.isEmpty() || Emaill.isEmpty())
                    throw new NumberFormatException();
                if (!Character.isLetter(Numee.charAt(0)))
                    throw new NumberFormatException();
                for (int i = 0; i < Numee.length(); i++)
                    if (!Character.isLetter(Numee.charAt(i)) && Numee.charAt(i) != ' ' && Numee.charAt(i) != '-')
                        throw new NumberFormatException();

                if (!Character.isLetter(Adresaa.charAt(0)))
                    throw new NumberFormatException();
                for (int i = 0; i < Adresaa.length(); i++)
                    if (!Character.isLetterOrDigit(Adresaa.charAt(i)) && Adresaa.charAt(i) != ' ' && Adresaa.charAt(i) != '-' && Adresaa.charAt(i) != ','
                            && Adresaa.charAt(i) != ';' && Adresaa.charAt(i) != '.')
                        throw new NumberFormatException();

                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                Pattern pattern = Pattern.compile(emailRegex);
                if (!pattern.matcher(Emaill).matches())
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f,
                        "Invalid Input! Nume incorect sau Adresa incorecta sau Email incorect !");
                return;
            }


            clientiBLL clientiBLL = new clientiBLL();
            clientiBLL.updateClienti(new clienti(id, Numee, Adresaa, Emaill));
            clientiTabelModel.setValueAt(Numee, row, 1);
            clientiTabelModel.setValueAt(Adresaa, row, 2);
            clientiTabelModel.setValueAt(Emaill, row, 3);
            curataCampurileModificate();
        });
        /**
         *
         * Sterge instanta selectata din tabel
         *
         */
        sterge.addActionListener(e -> {
            int[] rows = tabelClienti.getSelectedRows();
            clientiBLL clientiBLL = new clientiBLL();
            for (int i = 0; i < rows.length; i++) {
                int id = Integer.parseInt(clientiTabelModel.getValueAt(rows[i] - i, 0).toString());
                clientiBLL.stergeClienti(new clienti(id));
                clientiTabelModel.removeRow(rows[i] - i);
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
        modificaAdresaa.setText("");
        modificaEmaill.setText("");
    }

}
