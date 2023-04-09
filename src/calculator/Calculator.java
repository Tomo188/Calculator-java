/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author tomis
 */
public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numbers = new JButton[10];
    JButton[] functions = new JButton[9];
    JButton add, sub, division, multiplay;
    JButton dec, equa, del, clr, neg;
    JPanel panel;
    Font font = new Font("Ink Free", Font.BOLD, 30);
    double num1 = 0, num2 = 0, result = 0;
    boolean decBol = false;
    boolean useDecBol = false;
    String operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(font);
        textField.setEditable(false);
        add = new JButton("+");
        sub = new JButton("-");
        division = new JButton("/");
        multiplay = new JButton("*");
        dec = new JButton(".");
        equa = new JButton("=");
        del = new JButton("Delete");
        clr = new JButton("CLR");
        neg = new JButton("(-)");
        functions[0] = add;
        functions[1] = sub;
        functions[2] = division;
        functions[3] = multiplay;
        functions[4] = dec;
        functions[5] = equa;
        functions[6] = del;
        functions[7] = clr;
        functions[8] = neg;
        del.setBounds(50, 430, 145, 50);
        clr.setBounds(205, 430, 145, 50);
        neg.setBounds(125, 480, 145, 50);
        createButtons();
        addActionListenersToButtons(functions);
        addActionListenersToButtons(numbers);
        initPanel();
        frame.add(del);
        frame.add(clr);
        frame.add(neg);
        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

    }

    private void createButtons() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(String.valueOf(i));
        }
    }

    private void addActionListenersToButtons(JButton[] functions) {
        for (JButton button : functions) {
            button.addActionListener(this);
            button.setFont(font);
            button.setFocusable(false);
        }
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.add(numbers[1]);
        panel.add(numbers[2]);
        panel.add(numbers[3]);
        panel.add(add);
        panel.add(numbers[4]);
        panel.add(numbers[5]);
        panel.add(numbers[6]);
        panel.add(sub);
        panel.add(numbers[7]);
        panel.add(numbers[8]);
        panel.add(numbers[9]);
        panel.add(division);
        panel.add(multiplay);
        panel.add(numbers[0]);
        panel.add(dec);
        panel.add(equa);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dec) {
            if (!decBol) {
                setText(".");
                decBol = true;
                useDecBol = true;
            }

        } else if (e.getSource() == add) {
            setOperator("+");
            return;
        } else if (e.getSource() == sub) {
            setOperator("-");
            return;
        } else if (e.getSource() == multiplay) {
            setOperator("*");
            return;
        } else if (e.getSource() == division) {
            setOperator("/");
            return;
        } else if (e.getSource() == equa) {
            setNum2();
        } else if (e.getSource() == clr) {
            clearButton();
            return;
        } else if (e.getSource() == del) {
            deleteOneNumberInString();
            return;
        } else if (e.getSource() == neg) {
            changeSign();
        }
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numbers[i]) {
                setText(String.valueOf(i));
            }
        }

    }

    private void setText(String text) {
        textField.setText(textField.getText().concat(text));
    }

    private double getText() {
        return Double.parseDouble(textField.getText());
    }

    private void clearTextField() {
        textField.setText("");
    }

    private void setOperator(String operatorSign) {
        num1 = getText();
        operator = operatorSign;
        decBol = false;
        clearTextField();

    }

    private void setNum2() {
        num2 = Double.parseDouble(textField.getText());
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        if (useDecBol) {
            textField.setText(String.valueOf(result));
        } else {
            int resultNoDec = (int) result;
            textField.setText(String.valueOf(resultNoDec));
        }
        num1 = result;
    }

    private void clearButton() {
        num1 = 0;
        num2 = 0;
        decBol = false;
        useDecBol = false;
        clearTextField();
        operator = "";
    }

    private void deleteOneNumberInString() {
        String numbers = textField.getText();
        clearTextField();
        if (numbers.length() > 1) {
            setText(numbers.substring(0, numbers.length() - 1));
        } else {
            setText("");
        }

    }

    private void changeSign() {

        if (useDecBol) {
            double num;
            num = Double.parseDouble(textField.getText());
            num = num * -1;
            clearTextField();
            setText(String.valueOf(num));
        } else {
            int num = Integer.parseInt(textField.getText());
            clearTextField();
            setText(String.valueOf(num * -1));
        }

    }
}
