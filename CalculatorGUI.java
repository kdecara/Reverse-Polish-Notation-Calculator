import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CalculatorGUI {
    public RPN rpn = new RPN();
    private boolean isDone = false;
    private static final Font BTN_FONT = new Font("Dialog", 1, 20);
    private static final String[][] STANDARD_BTN_TEXTS = new String[][]{{"7", "8", "9", "/"}, {"4", "5", "6", "*"}, {"1", "2", "3", "-"}, {"0", "%", "^", "+"}, {"(", ")", "ENTER"}, {"sqrt", "CLEAR ALL", "Does not support negative entries"}};
    private static final int GAP = 5;
    private JPanel mainPanel = new JPanel();
    private JTextField display = new JTextField();
    private JTextField postfixDisplay = new JTextField();

    CalculatorGUI(RPN rpn) {
        this.display.setFont(BTN_FONT);
        this.postfixDisplay.setFont(BTN_FONT);
        this.postfixDisplay.setLayout(new GridLayout());
        JPanel standardPanel = this.createBtnPanel(STANDARD_BTN_TEXTS, "Reverse Polish Notation");
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.mainPanel.add(standardPanel, "Center");
        this.mainPanel.add(this.display, "North");
        this.mainPanel.add(this.postfixDisplay, "South");
        this.rpn = rpn;
    }

    public void setDisplay(String string) {
        this.display.setText(string);
    }

    public void setPostfixDisplay(String postfixString) {
        this.postfixDisplay.setText(postfixString);
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JTextField getDisplay() {
        return this.display;
    }

    private JPanel createBtnPanel(String[][] texts, String title) {
        JPanel btnPanel = new JPanel();
        int rows = texts.length;
        int cols = texts[0].length;
        CalculatorGUI.ButtonAction command = new CalculatorGUI.ButtonAction();
        btnPanel.setLayout(new GridLayout(rows, cols, 5, 5));

        for(int row = 0; row < texts.length; ++row) {
            for(int col = 0; col < texts[row].length; ++col) {
                JButton btn = new JButton(texts[row][col]);
                btn.setFont(BTN_FONT);
                btn.addActionListener(command);
                btnPanel.add(btn);
            }
        }

        btnPanel.setBorder(BorderFactory.createTitledBorder(title));
        return btnPanel;
    }

    public void compute(String str) {
        new Stack(str.length());
        RPN var10000 = this.rpn;
        var10000 = this.rpn;
        var10000 = this.rpn;
        var10000 = this.rpn;
        Stack postfixStack = RPN.stringToStack(RPN.convertToPostfix(RPN.addToArrayStack(RPN.addToStack(str))));
        RPN var10001 = this.rpn;
        this.setPostfixDisplay(RPN.arrayStackToString(postfixStack));
        var10001 = this.rpn;
        var10001 = this.rpn;
        var10001 = this.rpn;
        this.setPostfixDisplay(RPN.convertToPostfix(RPN.addToArrayStack(RPN.addToStack(str))));
        var10000 = this.rpn;
        var10000 = this.rpn;
        var10000 = this.rpn;
        var10000 = this.rpn;
        var10000 = this.rpn;
        String result = String.valueOf(RPN.computePostfixExpression(RPN.stringToStack(RPN.convertToPostfix(RPN.addToArrayStack(RPN.addToStack(str))))));
        this.setDisplay(result);
    }

    private class ButtonAction implements ActionListener {
        private ButtonAction() {
        }

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            byte var4 = -1;
            switch(command.hashCode()) {
                case 33:
                    if (command.equals("!")) {
                        var4 = 20;
                    }
                    break;
                case 37:
                    if (command.equals("%")) {
                        var4 = 18;
                    }
                    break;
                case 40:
                    if (command.equals("(")) {
                        var4 = 10;
                    }
                    break;
                case 41:
                    if (command.equals(")")) {
                        var4 = 11;
                    }
                    break;
                case 42:
                    if (command.equals("*")) {
                        var4 = 13;
                    }
                    break;
                case 43:
                    if (command.equals("+")) {
                        var4 = 15;
                    }
                    break;
                case 45:
                    if (command.equals("-")) {
                        var4 = 16;
                    }
                    break;
                case 47:
                    if (command.equals("/")) {
                        var4 = 14;
                    }
                    break;
                case 48:
                    if (command.equals("0")) {
                        var4 = 0;
                    }
                    break;
                case 49:
                    if (command.equals("1")) {
                        var4 = 1;
                    }
                    break;
                case 50:
                    if (command.equals("2")) {
                        var4 = 2;
                    }
                    break;
                case 51:
                    if (command.equals("3")) {
                        var4 = 3;
                    }
                    break;
                case 52:
                    if (command.equals("4")) {
                        var4 = 4;
                    }
                    break;
                case 53:
                    if (command.equals("5")) {
                        var4 = 5;
                    }
                    break;
                case 54:
                    if (command.equals("6")) {
                        var4 = 6;
                    }
                    break;
                case 55:
                    if (command.equals("7")) {
                        var4 = 7;
                    }
                    break;
                case 56:
                    if (command.equals("8")) {
                        var4 = 8;
                    }
                    break;
                case 57:
                    if (command.equals("9")) {
                        var4 = 9;
                    }
                    break;
                case 94:
                    if (command.equals("^")) {
                        var4 = 12;
                    }
                    break;
                case 3458:
                    if (command.equals("ln")) {
                        var4 = 19;
                    }
                    break;
                case 66129592:
                    if (command.equals("ENTER")) {
                        var4 = 21;
                    }
                    break;
                case 1515088142:
                    if (command.equals("CLEAR ALL")) {
                        var4 = 17;
                    }
            }

            switch(var4) {
                case 0:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "0");
                    break;
                case 1:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "1");
                    break;
                case 2:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "2");
                    break;
                case 3:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "3");
                    break;
                case 4:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "4");
                    break;
                case 5:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "5");
                    break;
                case 6:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "6");
                    break;
                case 7:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "7");
                    break;
                case 8:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "8");
                    break;
                case 9:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "9");
                    break;
                case 10:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "(");
                    break;
                case 11:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + ")");
                    break;
                case 12:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "^");
                    break;
                case 13:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "*");
                    break;
                case 14:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "/");
                    break;
                case 15:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "+");
                    break;
                case 16:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "-");
                    break;
                case 17:
                    CalculatorGUI.this.display.setText("");
                    break;
                case 18:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "%");
                    break;
                case 19:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "ln");
                    break;
                case 20:
                    CalculatorGUI.this.display.setText(CalculatorGUI.this.display.getText() + "!");
                    break;
                case 21:
                    CalculatorGUI.this.compute(CalculatorGUI.this.display.getText());
            }

        }
    }
}