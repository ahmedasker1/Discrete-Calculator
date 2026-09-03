


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Matrix extends JPanel {
    public static int[][] matrix;
    private static JLabel matrixLabel;

    public Matrix() {


        JButton button = new JButton("Enter Matrix");
        matrixLabel = new JLabel("Matrix: ");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows:"));
                int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of columns:"));

                matrix = new int[rows][cols];

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        String input = JOptionPane.showInputDialog("Enter value at position (" + i + ", " + j + "):");
                        matrix[i][j] = Integer.parseInt(input);
                    }
                }

                updateMatrixLabel();
            }
        });


       add(button);
       add(matrixLabel);


    }

    private static void updateMatrixLabel() {
        StringBuilder matrixString = new StringBuilder("<html>Matrix:<br>");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrixString.append(matrix[i][j]).append(" ");
            }
            matrixString.append("<br>");
        }
        matrixString.append("</l>");

        matrixLabel.setText(matrixString.toString());
    }


}

