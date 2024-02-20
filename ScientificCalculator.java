import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ScientificCalculator extends JFrame implements ActionListener {

    private JTextField inputField;
    @SuppressWarnings("unused")
    private double storedValue = 0;
    @SuppressWarnings("unused")
    private boolean radiansMode = false;
    private StringBuilder currentInput = new StringBuilder();

double firstNum;
double secondNum;
double constantValue;
private JTextField calculatingTf;
DecimalFormat df = new DecimalFormat("#.##########"); // Adjust the format as needed
boolean sinSelected = false;
boolean cosSelected = false;
boolean tanSelected = false;
int operator;


    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.darkGray);

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        add(inputField, BorderLayout.NORTH);

        calculatingTf = new JTextField(); // Initialize calculatingTf
        calculatingTf.setEditable(false);
        calculatingTf.setFont(new Font("Arial", Font.PLAIN, 16));
        calculatingTf.setHorizontalAlignment(JTextField.RIGHT);
        add(calculatingTf, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 10, 1, 1));
        String[] buttonLabels = {
                "(", ")", "mc", "m+", "m-", "mr", "c", "+/-", "%", "÷",
                "xʸ", "x²", "x³", "sqrt", "eˣ", "10ˣ", "7", "8", "9", "x",
                "1/x", "²√x", "∛x", "ʸ√ₓ", "ln", "log₁₀", "4", "5", "6", "-",
                "!", "sin", "cos", "tan", "e", "EE", "1", "2", "3", "+",
                "Rad", "sinh", "cosh", "tanh", "π", "RAND", "0", "", ".", "="
        };

        for (int i = 0; i < buttonLabels.length; i++) {
    JButton button = new JButton(buttonLabels[i]);
    button.setFont(new Font("Arial", Font.PLAIN, 20));

    // Set button colors
    if (i % 10 == 9 && (i < 70 || i > 79)) {
        button.setBackground(new Color(255, 165, 0));  // Orange color for last column, excluding specified buttons
        button.setForeground(Color.white);
    } else if (buttonLabels[i].matches("[0-9]||\\.")) { 
        button.setBackground(Color.GRAY);  // Light grey color
        button.setForeground(Color.black);
    } else {
        button.setBackground(Color.darkGray);
        button.setForeground(Color.white);
    }

    button.addActionListener(this);
    buttonPanel.add(button);
}

        buttonPanel.setBackground(Color.darkGray);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand().toString();
       switch (command) {
           case ".":
               if (!inputField.getText().contains(".")) {
                   inputField.setText(inputField.getText() + command);
               }
               break;
           case "0":
           case "1":
           case "2":
           case "3":
           case "4":
           case "5":
           case "6":
           case "7":
           case "8":
           case "9":
               inputField.setText(inputField.getText() + command);
               break;
           case "÷":
           case "x":
           case "-":
           case "+":
           case "+/-":
           case "c":
               ArithmeticOperations(command);
               break;
            case "sin":
             case "cos":
             case "tan":
               selectTrigonometricFunction(command);
               break;
               case "tanh":
            case "cosh":
            case "sinh":
            selectHyperbolicFunction(command);
            case "%":
             ArithmeticOperations(command);
             break;

           case "=":
               performCalculation();
               break;
           case "π":
               constantValue = Math.PI;
               inputField.setText(Double.toString(constantValue));
               break;
           case "e":
               constantValue = Math.E;
               inputField.setText(Double.toString(constantValue));
               break;
           case "log₁₀":
               performLogarithm();
               break;
           case "x²":
               double squareValue = Double.parseDouble(inputField.getText());
               double squareResult = Math.pow(squareValue, 2);
               inputField.setText(Double.toString(squareResult));
               break;
           case "x³":
               double cubeValue = Double.parseDouble(inputField.getText());
               double cubeResult = Math.pow(cubeValue, 3);
               inputField.setText(Double.toString(cubeResult));
               break;
           case "xʸ":
               firstNum = Double.parseDouble(inputField.getText());
               operator = 5;
               inputField.setText("");
               break;
           case "10ˣ":
               double powTenValue = Double.parseDouble(inputField.getText());
               double powTenResult = Math.pow(10, powTenValue);
               inputField.setText(Double.toString(powTenResult));
               break;
           case "eˣ":
               double expValue = Double.parseDouble(inputField.getText());
               double expResult = Math.exp(expValue);
               inputField.setText(Double.toString(expResult));
               break;

           case "1/x":
               double reciprocalValue = Double.parseDouble(inputField.getText());
               double reciprocalResult = 1 / reciprocalValue;
               inputField.setText(Double.toString(reciprocalResult));
               break;
           case "²√x":
               double sqrtValue = Double.parseDouble(inputField.getText());
               double sqrtResult = Math.sqrt(sqrtValue);
               inputField.setText(Double.toString(sqrtResult));
               break;
           case "∛x":
               double cbrtValue = Double.parseDouble(inputField.getText());
               double cbrtResult = Math.cbrt(cbrtValue);
               inputField.setText(Double.toString(cbrtResult));
               break;
           case "ʸ√ₓ":
               double yRootValue = Double.parseDouble(inputField.getText());
               double yRootResult = Math.pow(firstNum, 1 / yRootValue);
               inputField.setText(Double.toString(yRootResult));
               calculatingTf.setText(firstNum + "√" + yRootValue + " = " + yRootResult);
               break;
           case "!":
               performFactorial();
               break;
               case "ln":
               performNaturalLogarithm();
             break;

           

           default:
       }
   }

   

   @SuppressWarnings("unused")
private void performModulus() {
    if (!inputField.getText().isEmpty()) {
        double modulusValue = Double.parseDouble(inputField.getText());
        inputField.setText(String.valueOf(modulusValue % 2));  // Replace 2 with the desired modulus value
    }
}
private void selectHyperbolicFunction(String command) {
    switch (command) {
        case "tanh":
            tanSelected = true;
            break;
        case "cosh":
            cosSelected = true;
            break;
        case "sinh":
            sinSelected = true;
            break;
        default:
            break; 
    }
    inputField.setText("");
    calculatingTf.setText(command);
}

private void performNaturalLogarithm() {
    if (!inputField.getText().isEmpty()) {
        double logValue = Double.parseDouble(inputField.getText());
        if (logValue > 0) {
            double lnResult = Math.log(logValue);
            inputField.setText(String.valueOf(lnResult));
            calculatingTf.setText("ln(" + logValue + ") = " + lnResult);
        } else {
            inputField.setText("Invalid input");
        }
    }
}

private void ArithmeticOperations(String command) {
    if (!inputField.getText().isEmpty()) {
        switch (command) {
            case "÷":
            case "x":
            case "-":
            case "+":
                firstNum = Double.parseDouble(inputField.getText());
                if ("÷".equals(command)) {
                    operator = 1;
                } else if ("x".equals(command)) {
                    operator = 2;
                } else if ("-".equals(command)) {
                    operator = 3;
                } else if ("+".equals(command)) {
                    operator = 4;
                } else {
                    operator = 0;
                }
                inputField.setText("");
                break;
            case "+/-":
                double value = Double.parseDouble(inputField.getText());
                value *= -1;
                inputField.setText(String.valueOf(value));
                break;
            case "%":
                value = Double.parseDouble(inputField.getText());
                value /= 100; // Perform percentage operation
                inputField.setText(String.valueOf(value));
                break;
            case "c":
                inputField.setText("");
                break;
            
        }
    }
}

   private void selectTrigonometricFunction(String command) {
       switch (command) {
           case "sin":
               sinSelected = true;
               break;
           case "sinh":
               sinSelected = true;
               break;
           case "cos":
               cosSelected = true;
               break;
           case "cosh":
               cosSelected = true;
               break;
           case "tan":
               tanSelected = true;
               break;
           case "tanh":
               tanSelected = true;
               break;
           default:
       }
       inputField.setText("");
       calculatingTf.setText(command);
   }

   private void performCalculation() {
       if (!inputField.getText().isEmpty()) {
           secondNum = Double.parseDouble(inputField.getText());
           if (sinSelected || cosSelected || tanSelected) {
               double num = Double.parseDouble(inputField.getText());
               double result = 0;
               if (sinSelected) {
                   result = Math.sin(Math.toRadians(num));
               } else if (cosSelected) {
                   result = Math.cos(Math.toRadians(num));
               } else if (tanSelected) {
                   result = Math.tan(Math.toRadians(num));
               }
               inputField.setText(String.valueOf(result));
               calculatingTf.setText(calculatingTf.getText() + "(" + num + ") = " + result);

               sinSelected = false;
               cosSelected = false;
               tanSelected = false;
               
                operator = 0;
           currentInput.setLength(0);
           } else {
               if (operator == 1) {
               double result = firstNum / secondNum;
               inputField.setText(String.valueOf(result));
               calculatingTf.setText(String.valueOf(firstNum + "÷" + secondNum + "=" + (df.format(result))));
                } else if (operator == 2) {
                double result = firstNum * secondNum;
                inputField.setText(String.valueOf(result));
                calculatingTf.setText(String.valueOf(firstNum + "x" + secondNum + "=" + (df.format(result))));
                } else if (operator == 3) {
                 double result = firstNum - secondNum;
                 inputField.setText(String.valueOf(result));
                calculatingTf.setText(String.valueOf(firstNum + "-" + secondNum + "=" + (df.format(result))));
                 } else if (operator == 4) {
                 double result = firstNum + secondNum;
                inputField.setText(String.valueOf(result));
                calculatingTf.setText(String.valueOf(firstNum + "+" + secondNum + "=" + (df.format(result))));
                } else if (operator == 5) {
                 double result = Math.pow(firstNum, secondNum);
                 inputField.setText(String.valueOf(result));
                 calculatingTf.setText(String.valueOf(firstNum + "^" + secondNum + "=" + result));
                } else {
   
                 }
                  operator = 0;
           currentInput.setLength(0);
           }
           operator = 0;
           currentInput.setLength(0);
       }
   }
   private void performFactorial() {
       if (!inputField.getText().isEmpty()) {
           int num = Integer.parseInt(inputField.getText());
           long result = factorial(num);
           inputField.setText(String.valueOf(result));
           calculatingTf.setText(num + "! = " + result);
       }
   }
   private long factorial(int n) {
       if (n == 0 || n == 1) {
           return 1;
       }
       long result = 1;
       for (int i = 2; i <= n; i++) {
           result *= i;
       }
       return result;
   }
  
   private void performLogarithm() {
       if (!inputField.getText().isEmpty()) {
           double logValue = Double.parseDouble(inputField.getText());
           if (logValue > 0) {
               double lnResult = Math.log(logValue);
               double log10Result = Math.log10(logValue);

               inputField.setText("ln(" + logValue + ") = " + lnResult + "\nlog(" + logValue + ") = " + log10Result);
               calculatingTf.setText("ln(" + logValue + ") = " + lnResult);
           } else {
               inputField.setText("Invalid input");
           }
       }
   }
   @SuppressWarnings("unused")
private void performXInverse() {
       if (!inputField.getText().isEmpty()) {
           double xValue = Double.parseDouble(inputField.getText());
           if (xValue != 0) {
               double inverseResult = 1 / xValue;
               calculatingTf.setText("1 / " + xValue + " = " + inverseResult);
           } else {
               inputField.setText("Cannot divide by zero");
           }
       }
   }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScientificCalculator calculator = new ScientificCalculator();
            calculator.setVisible(true);
        });
    }
}