import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Operation_1matrix extends JPanel {
    // declaration of variables //////////////
    Matrix p1;
    btn_help p2;
    opreation_panel p3;
    JPanel calcpanel;
    JButton btn_calc;
    JButton btn_back;
    //end declaration of variables //////////



    // constructor /////////////////////////
    public Operation_1matrix() {
        setLayout(new BorderLayout());



        p1 = new Matrix();
        p2 = new btn_help();
        p3 = new opreation_panel();
        btn_calc = new JButton("OK");
        btn_back = new JButton("Back");
        calcpanel = new JPanel();

        calcpanel.add(btn_calc);
        calcpanel.add(btn_back);
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.EAST);
        add(p3,BorderLayout.WEST);
        add(calcpanel, BorderLayout.SOUTH);
        btn_calc.addActionListener(new btn_calcAction());



    }
    //end of constructor //////////

    // inner classes ///////////////////////
    public class btn_calcAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //operations

            // operation1 test symmetry
            try {
                if (p3.rbtn_Testymmetric.isSelected()) {
                    // check symmetry
                    boolean symmetric =true;
                    for(int i=0; i<p1.matrix.length; i++) {
                        for (int j=0 ; j<p1.matrix.length ; j++){
                            if(p1.matrix[i][j]!=p1.matrix[j][i]){
                                symmetric=false;
                                break;
                            }
                        }
                        if(!symmetric){
                            break;
                        }
                    }
                    if (symmetric){
                        JOptionPane.showMessageDialog(null,"Matrix is symmetric");
                    }else {
                        JOptionPane.showMessageDialog(null,"Matrix is not symmetric");
                    }

                }

                //operation 2 invert matrix
                if (p3.rbtn_inverse.isSelected()) {
                    String inverse_out = "inverse matrix is:\n";
                    int inverse[][] = new int[p1.matrix.length][p1.matrix.length];
                    for (int i = 0; i < p1.matrix.length; i++) {
                        for (int j = 0; j < p1.matrix.length; j++) {
                            inverse[i][j] = p1.matrix[j][i];
                        }
                    }
                    for (int i = 0; i < p1.matrix.length; i++) {
                        for (int j = 0; j < p1.matrix.length; j++) {
                          inverse_out+=inverse[i][j] + " ";
                        }
                        inverse_out+="\n";
                    }


                    JOptionPane.showMessageDialog(null,inverse_out);


                }

                //operation 3 check Equivalence
                if (p3.rbtn_checkEquivalence.isSelected()) {
                    boolean ref = true;
                    for (int r = 0; r < p1.matrix.length; r++) {
                        for (int c = 0; c < p1.matrix.length; c++) {
                            if (r == c && p1.matrix[r][c] != 1) {
                                ref = false;
                                break;
                            }
                        }
                        if (!ref)
                            break;
                    }

                    boolean sym = true;
                    for (int r = 0; r < p1.matrix.length; r++) {
                        for (int c = 0; c < p1.matrix.length; c++) {
                            if (p1.matrix[r][c] != p1.matrix[c][r]) {
                                sym = false;
                                break;
                            }
                        }
                        if (!sym)
                            break;
                    }

                    boolean tran = true;
                    for (int r = 0; r < p1.matrix.length; r++) {
                        for (int c = 0; c < p1.matrix.length; c++) {
                            if (p1.matrix[r][c] == 1) {
                                for (int k = 0; k < p1.matrix.length; k++) {
                                    if (p1.matrix[c][k] == 1 && p1.matrix[r][k] != 1) {
                                        tran = false;
                                        break;
                                    }
                                }
                            }

                        }
                        if (!tran)
                            break;
                    }
                    if (ref && sym && tran == true)
                        JOptionPane.showMessageDialog(null,"The relation is equivalence.");
                    else
                        JOptionPane.showMessageDialog(null,"The relation is not equivalence.");


                }

                // operation 4 check partial ordering
                if (p3.rbtn_partialOrdered.isSelected()) {
                    boolean ref=true;
                    for(int r=0;r<p1.matrix.length;r++){
                        for(int c=0;c<p1.matrix.length;c++){
                            if(r==c && p1.matrix[r][c]!=1){
                                ref=false;
                                break;
                            }
                        }
                        if (!ref)
                            break;
                    }

                    boolean antisym=true;
                    for(int r=0;r<p1.matrix.length;r++){
                        for(int c=0;c<p1.matrix.length;c++){
                            if(p1.matrix[r][c]==1&&p1.matrix[c][r]==1&&r!=c){
                                antisym=false;
                                break;
                            }
                        }
                        if (!antisym)
                            break;
                    }

                    boolean tran=true;
                    for(int r=0;r<p1.matrix.length;r++){
                        for(int c=0;c<p1.matrix.length;c++){
                            if (p1.matrix[r][c]==1){
                                for(int k=0;k<p1.matrix.length;k++){
                                    if (p1.matrix[c][k]==1 && p1.matrix[r][k]!=1){
                                        tran=false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(!tran)
                            break;
                    }
                    if(ref&&antisym&&tran==true)
                        JOptionPane.showMessageDialog(null,"The relation is partial ordered.");
                    else
                        JOptionPane.showMessageDialog(null,"The relation is not partial ordered.");
                }

                //operation 5 check reflexive or irreflexive
                if (p3.rbtn_checkReflexive.isSelected()) {

                    boolean ref=true;
                    boolean irref=true;

                    for(int r=0;r<p1.matrix.length;r++){
                        for(int c=0;c<p1.matrix.length;c++){
                            if(r==c && p1.matrix[r][c]!=1){
                                ref=false;
                                break;
                            }
                        }
                        if (!ref)
                            break;
                    }
                    for(int r=0;r<p1.matrix.length;r++){
                        for(int c=0;c<p1.matrix.length;c++){
                            if(r==c && p1.matrix[r][c]!=0){
                                irref=false;break;
                            }
                        }
                        if (!irref)
                            break;
                    }

                    if(ref==true && irref==false)
                        JOptionPane.showMessageDialog(null,"The relation is reflexive.");
                    else if(irref==true && ref==false)
                        JOptionPane.showMessageDialog(null,"The relation is irreflexive.");
                    else
                        JOptionPane.showMessageDialog(null,"The relation is neither reflexive nor irreflexive.");


                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"please,Enter Matrix");
            }
        }
    }
}