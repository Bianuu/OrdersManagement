package View;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ViewPrincipal extends JFrame {
    private final JButton exitButton = new JButton("Exit");
    public JPanel panouContinut;
    public JButton viewClienti;
    public JButton viewProduse;
    public JButton viewComenzi;

    /**
     * Creaza trei butoane : view Clienti, view Produse, view Comenzi
     */
    public ViewPrincipal() {

        super("BIRTUTU' LUI BIANU");

        setSize(600, 600);

        panouContinut = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon imageIcon = new ImageIcon("resources/birt.png");
                Image backgroundImage = imageIcon.getImage();

                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(panouContinut);

        viewClienti = new JButton("Fidelitatea");
        ImageIcon poz1 = new ImageIcon("resources/clienti.png");
        Image img1 = poz1.getImage();
        Image new1 = img1.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(new1);
        viewClienti.setIcon(icon1);
        viewClienti.setBounds(20, 100, 190, 90);
        panouContinut.add(viewClienti);

        viewProduse = new JButton("Ametitoare");
        ImageIcon poz2 = new ImageIcon("resources/produse.png");
        Image img2 = poz2.getImage();
        Image new2 = img2.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(new2);
        viewProduse.setIcon(icon2);
        viewProduse.setBounds(180, 355, 310, 125);
        panouContinut.add(viewProduse);

        viewComenzi = new JButton("Datorii");
        ImageIcon poz3 = new ImageIcon("resources/comenzi.png");
        Image img3 = poz3.getImage();
        Image new3 = img3.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(new3);
        viewComenzi.setIcon(icon3);
        viewComenzi.setBounds(200, 435, 300, 125);
        panouContinut.add(viewComenzi);

        exitButton.setBounds(30, 510, 100, 30);
        panouContinut.add(exitButton);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addActionListeners();
    }

    /**
     * In functie de butonul apasat seteza care fereastra va fi vizibila
     */
    private void addActionListeners() {
        viewClienti.addActionListener(e -> {
            setVisible(false);
            new ViewOperatiiClienti().setVisible(true);
        });

        viewProduse.addActionListener(e -> {
            dispose();
            new ViewOperatiiProduse().setVisible(true);
        });

        viewComenzi.addActionListener(e -> {
            dispose();
            new ViewOperatiiComenzi().setVisible(true);
        });

        exitButton.addActionListener(e -> {
            this.dispose();
        });
    }

}
