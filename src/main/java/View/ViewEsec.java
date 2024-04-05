package View;

import javax.swing.*;

public class ViewEsec extends JFrame {
    JPanel panouContinut;
    JLabel succes;

    public ViewEsec(String message) {
        super("Esec");

        setSize(300, 300);
        panouContinut = new JPanel();
        setContentPane(panouContinut);
        succes = new JLabel(message);
        panouContinut.add(succes);
        setVisible(true);
        setResizable(false);
    }
}
