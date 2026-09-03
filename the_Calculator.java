import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class the_Calculator extends JFrame {
    Operation_1matrix Opr_1matrix_panel;
    Operation_2matrix Opr_2matrix_panel;

    JPanel panel_home;
    JLabel lbl_message;
    JPanel panel_btn;
    JButton btn_opr_1mat;
    JButton btn_opr_2mat;

    public the_Calculator() {

        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        Opr_1matrix_panel = new Operation_1matrix();
        Opr_2matrix_panel = new Operation_2matrix();

        panel_btn =new JPanel();
        panel_home =new JPanel();
        lbl_message = new JLabel("choose the number of matrixes");
        btn_opr_1mat = new JButton("one matrix");
        btn_opr_2mat = new JButton("two matrix");



        panel_btn.add(btn_opr_1mat);
        panel_btn.add(btn_opr_2mat);
        btn_opr_1mat.addActionListener(new btn_opr_1matAction());

        panel_home.add(lbl_message);
        panel_home.add(panel_btn);
        add(panel_home);
        btn_opr_2mat.addActionListener(new btn_opr_2matAction());

        Opr_1matrix_panel.btn_back.addActionListener(new btn_backAction() );



        setVisible(true);

    }
    public class btn_opr_1matAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            add(Opr_1matrix_panel);
            Opr_1matrix_panel.setVisible(true);

            panel_home.setVisible(false);
            remove(panel_home);

        }
    }
    public class btn_opr_2matAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Opr_2matrix_panel.main();


        }
    }
    public class btn_backAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Opr_1matrix_panel.setVisible(false);
            remove(Opr_1matrix_panel);
            add(panel_home);
            panel_home.setVisible(true);



        }
    }


    public static void main(String[] args) {
        new the_Calculator()
        ;

    }

}
        