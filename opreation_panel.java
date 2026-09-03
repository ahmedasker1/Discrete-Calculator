import javax.swing.*;
import java.awt.*;

public class opreation_panel extends JPanel {
    JRadioButton rbtn_Testymmetric;
    JRadioButton rbtn_inverse;
    JRadioButton rbtn_checkEquivalence;
    JRadioButton rbtn_partialOrdered;
    JRadioButton rbtn_checkReflexive;

    ButtonGroup buttonGroup;


    public opreation_panel (){
        setBorder(BorderFactory.createTitledBorder("opreations"));
        setLayout(new GridLayout(5,1));
        rbtn_inverse = new JRadioButton("Inverse");
        rbtn_Testymmetric = new JRadioButton("Testymmetric");
        rbtn_checkEquivalence = new JRadioButton("Check Equivalence");
        rbtn_partialOrdered = new JRadioButton("Check partial ordered ");
        rbtn_checkReflexive = new JRadioButton("Check reflexive");


        buttonGroup = new ButtonGroup();

        buttonGroup.add(rbtn_Testymmetric);
        buttonGroup.add(rbtn_inverse);
        buttonGroup.add(rbtn_checkEquivalence);
        buttonGroup.add(rbtn_partialOrdered);
        buttonGroup.add(rbtn_checkReflexive);

        add(rbtn_inverse);
        add(rbtn_Testymmetric);
        add(rbtn_checkEquivalence);
        add(rbtn_partialOrdered);
        add(rbtn_checkReflexive);
    }


}
