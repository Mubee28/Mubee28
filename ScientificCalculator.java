package com.mubee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private String input = "";

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);

        displayField = new JTextField();
        displayField.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sqrt", "sin", "cos", "clear"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            input += command;
            displayField.setText(input);
        } else if ("/+-*".contains(command)) {
            input += " " + command + " ";
            displayField.setText(input);
        } else if ("sqrt sin cos".contains(command)) {
            double value = Double.parseDouble(input);
            if (command.equals("sqrt")) {
                value = Math.sqrt(value);
            } else if (command.equals("sin")) {
                value = Math.sin(value);
            } else if (command.equals("cos")) {
                value = Math.cos(value);
            }
            input = String.valueOf(value);
            displayField.setText(input);
        } else if (command.equals("=")) {
            input = evaluateExpression(input);
            displayField.setText(input);
        } else if (command.equals("clear")) {
            input = "";
            displayField.setText("");
        }
    }

    private String evaluateExpression(String expression) {
        try {
            String[] tokens = expression.split(" ");
            double result = Double.parseDouble(tokens[0]);

            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                double operand = Double.parseDouble(tokens[i + 1]);

                if (operator.equals("+")) {
                    result += operand;
                } else if (operator.equals("-")) {
                    result -= operand;
                } else if (operator.equals("*")) {
                    result *= operand;
                } else if (operator.equals("/")) {
                    result /= operand;
                }
            }

            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator());
    }
}
