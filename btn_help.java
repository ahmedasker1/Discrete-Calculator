import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btn_help extends JPanel {
    private help_content_panel help_content_panel;
    JButton jButton1;
    JPanel jPanel1;
    JPanel jPanel2;
    JPanel jPanel3;


    public btn_help() {
        setLayout(new GridLayout(3,1));

        jButton1 = new javax.swing.JButton();
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("?");

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        help_content_panel = new help_content_panel();
        jPanel1.setBorder(BorderFactory.createTitledBorder("Help"));

        jButton1.addActionListener(new jButton1Action());



       jPanel1.add(jButton1);

        add(jPanel2);
        add(jPanel3);
        add(jPanel1);

    }
    public class jButton1Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            help_content_panel.main();
        }
    }


}


