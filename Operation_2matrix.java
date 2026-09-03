import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.dnd.*;

public class Operation_2matrix {
    private static int[][] matrix1;
    private static int[][] matrix2;
    private static JTable matrixTable1;
    private static JTable matrixTable2;
    private static JTable resultTable;

    public static void main() {
        JFrame frame = new JFrame("Discrete Mathematics Calculator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1100, 250);
        frame.setLocationRelativeTo(null);

        JButton enterButton = new JButton("Enter Matrices");
        JButton processButton = new JButton("Process Matrices");

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Matrix 1
                int rows1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows for Matrix 1:"));
                int cols1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of columns for Matrix 1:"));

                matrix1 = new int[rows1][cols1];

                for (int i = 0; i < rows1; i++) {
                    for (int j = 0; j < cols1; j++) {
                        String input = JOptionPane.showInputDialog("Enter value at position (" + i + ", " + j + ") for Matrix 1:");
                        matrix1[i][j] = Integer.parseInt(input);
                    }
                }

                // Update table for Matrix 1
                updateMatrixTable(matrixTable1, matrix1);

                // Matrix 2
                int rows2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows for Matrix 2:"));
                int cols2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of columns for Matrix 2:"));

                matrix2 = new int[rows2][cols2];

                for (int i = 0; i < rows2; i++) {
                    for (int j = 0; j < cols2; j++) {
                        String input = JOptionPane.showInputDialog("Enter value at position (" + i + ", " + j + ") for Matrix 2:");
                        matrix2[i][j] = Integer.parseInt(input);
                    }
                }

                // Update table for Matrix 2
                updateMatrixTable(matrixTable2, matrix2);
            }
        });

        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = {
                        "Add Matrices", "Subtract Matrices", "Multiply Matrices",
                        "Determinant of Matrix 1", "Inverse of Matrix 1", "Check Symmetric Matrix 1",
                        "Check Equivalence", "Check Partial Order", "Check Reflexive"
                };

                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose an operation",
                        "Matrix Operations",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == -1) return;

                switch (choice) {
                    case 0: // Add Matrices
                        if (matricesHaveSameDimensions(matrix1, matrix2)) {
                            int[][] result1 = addMatrices(matrix1, matrix2);
                            int[][] result2 = addMatrices(matrix2, matrix1);
                            updateMatrixTable(resultTable, result1);
                            // Optionally show the result for matrix2 addition as well
                            updateMatrixTable(resultTable, result2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrices must have the same dimensions for addition.");
                        }
                        break;
                    case 1: // Subtract Matrices
                        if (matricesHaveSameDimensions(matrix1, matrix2)) {
                            int[][] result1 = subtractMatrices(matrix1, matrix2);
                            int[][] result2 = subtractMatrices(matrix2, matrix1);
                            updateMatrixTable(resultTable, result1);
                            // Optionally show the result for matrix2 subtraction as well
                            updateMatrixTable(resultTable, result2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrices must have the same dimensions for subtraction.");
                        }
                        break;
                    case 2: // Multiply Matrices
                        if (matrix1[0].length == matrix2.length) {
                            int[][] result1 = multiplyMatrices(matrix1, matrix2);
                            int[][] result2 = multiplyMatrices(matrix2, matrix1);
                            updateMatrixTable(resultTable, result1);
                            // Optionally show the result for matrix2 multiplication as well
                            updateMatrixTable(resultTable, result2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Number of columns in Matrix 1 must be equal to number of rows in Matrix 2 for multiplication.");
                        }
                        break;
                    case 3: // Determinant of Matrix 1
                        if (matrix1.length == matrix1[0].length) {
                            int det = determinant(matrix1);
                            JOptionPane.showMessageDialog(null, "Determinant of Matrix 1 is " + det);
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrix 1 must be square to calculate determinant.");
                        }
                        break;
                    case 4: // Inverse of Matrix 1
                        if (matrix1.length == matrix1[0].length) {
                            double[][] inverse = inverse(matrix1);
                            if (inverse != null) {
                                updateMatrixTable(resultTable, inverse);
                            } else {
                                JOptionPane.showMessageDialog(null, "Matrix 1 is singular and cannot be inverted.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrix 1 must be square to calculate inverse.");
                        }
                        break;
                    case 5: // Check Symmetric Matrix 1
                        if (checkSymmetric(matrix1)) {
                            JOptionPane.showMessageDialog(null, "Matrix 1 is symmetric.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrix 1 is not symmetric.");
                        }
                        break;
                    case 6: // Check Equivalence
                        if (matrix1.length == matrix1[0].length) {
                            boolean isReflexive = checkReflexive(matrix1);
                            boolean isSymmetric = checkSymmetric(matrix1);
                            boolean isTransitive = checkTransitive(matrix1);

                            boolean isEquivalence = isReflexive && isSymmetric && isTransitive;
                            JOptionPane.showMessageDialog(null, "Matrix 1 is " + (isEquivalence ? "an Equivalence Relation" : "not an Equivalence Relation"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrix 1 must be square to check equivalence.");
                        }
                        break;
                    case 7: // Check Partial Order
                        if (matrix1.length == matrix1[0].length) {
                            boolean isReflexive = checkReflexive(matrix1);
                            boolean isAntisymmetric = checkAntisymmetric(matrix1);
                            boolean isTransitive = checkTransitive(matrix1);

                            boolean isPartialOrder = isReflexive && isAntisymmetric && isTransitive;
                            JOptionPane.showMessageDialog(null, "Matrix 1 is " + (isPartialOrder ? "a Partial Order" : "not a Partial Order"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrix 1 must be square to check partial order.");
                        }
                        break;
                    case 8: // Check Reflexive
                        if (matrix1.length == matrix1[0].length) {
                            boolean isReflexive = checkReflexive(matrix1);
                            JOptionPane.showMessageDialog(null, "Matrix 1 is " + (isReflexive ? "Reflexive" : "not Reflexive"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrix 1 must be square to check reflexive.");
                        }
                        break;
                }
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enterButton);
        buttonPanel.add(processButton);

        // Matrix 1 panel
        matrixTable1 = new JTable();
        matrixTable1.setDragEnabled(true);
        matrixTable1.setDropMode(DropMode.INSERT_ROWS);
        new TableDropTarget(matrixTable1);

        JScrollPane scrollPane1 = new JScrollPane(matrixTable1);
        JPanel matrixPanel1 = new JPanel(new BorderLayout());
        matrixPanel1.setBorder(BorderFactory.createTitledBorder("Matrix 1"));
        matrixPanel1.add(scrollPane1, BorderLayout.CENTER);

        // Matrix 2 panel
        matrixTable2 = new JTable();
        matrixTable2.setDragEnabled(true);
        matrixTable2.setDropMode(DropMode.INSERT_ROWS);
        new TableDropTarget(matrixTable2);

        JScrollPane scrollPane2 = new JScrollPane(matrixTable2);
        JPanel matrixPanel2 = new JPanel(new BorderLayout());
        matrixPanel2.setBorder(BorderFactory.createTitledBorder("Matrix 2"));
        matrixPanel2.add(scrollPane2, BorderLayout.CENTER);

        // Result panel
        resultTable = new JTable();
        JScrollPane resultScrollPane = new JScrollPane(resultTable);
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        mainPanel.add(matrixPanel1, BorderLayout.WEST);
        mainPanel.add(matrixPanel2, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static boolean matricesHaveSameDimensions(int[][] matrix1, int[][] matrix2) {
        return matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length;
    }

    private static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    private static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;
        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    private static void updateMatrixTable(JTable table, int[][] matrix) {
        String[] columnNames = new String[matrix[0].length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = "Col " + (i + 1);
        }

        String[][] data = new String[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                data[i][j] = String.valueOf(matrix[i][j]);
            }
        }

        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private static void updateMatrixTable(JTable table, double[][] matrix) {
        String[] columnNames = new String[matrix[0].length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = "Col " + (i + 1);
        }

        String[][] data = new String[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                data[i][j] = String.format("%.2f", matrix[i][j]);
            }
        }

        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private static int determinant(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        int det = 0;
        for (int i = 0; i < n; i++) {
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor(matrix, 0, i));
        }
        return det;
    }

    private static int[][] minor(int[][] matrix, int row, int column) {
        int n = matrix.length;
        int[][] minor = new int[n - 1][n - 1];

        for (int i = 0, minorRow = 0; i < n; i++) {
            if (i == row) continue;
            for (int j = 0, minorCol = 0; j < n; j++) {
                if (j == column) continue;
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }

        return minor;
    }

    private static double[][] inverse(int[][] matrix) {
        int n = matrix.length;
        double[][] result = new double[n][n];
        int det = determinant(matrix);

        if (det == 0) return null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j)) / (double) det;
            }
        }

        return result;
    }

    private static boolean checkSymmetric(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows != cols) return false;

        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
                if (matrix[i][j] != matrix[j][i]) return false;
            }
        }
        return true;
    }

    private static boolean checkReflexive(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] != 1) return false;
        }
        return true;
    }

    private static boolean checkAntisymmetric(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != 0 && matrix[j][i] != 0) return false;
            }
        }
        return true;
    }

    private static boolean checkTransitive(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    for (int k = 0; k < n; k++) {
                        if (matrix[j][k] != 0 && matrix[i][k] == 0) return false;
                    }
                }
            }
        }
        return true;
    }

    // Class to handle drag and drop functionality for tables
    private static class TableDropTarget implements DropTargetListener {
        private JTable table;

        TableDropTarget(JTable table) {
            this.table = table;
            new DropTarget(table, DnDConstants.ACTION_COPY, this);
        }

        @Override
        public void dragEnter(DropTargetDragEvent e) {}

        @Override
        public void dragOver(DropTargetDragEvent e) {}

        @Override
        public void dropActionChanged(DropTargetDragEvent e) {}

        @Override
        public void dragExit(DropTargetEvent e) {}

        @Override
        public void drop(DropTargetDropEvent e) {

        }
    }
}