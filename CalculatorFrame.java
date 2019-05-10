import java.awt.Component;
import javax.swing.JFrame;

public class CalculatorFrame extends JFrame {
    //public String data;

    public CalculatorFrame() {
    }

    public static void createAndShowUI() {
        RPN rpn = new RPN();
        CalculatorGUI gui = new CalculatorGUI(rpn);
        CalculatorMenu menu = new CalculatorMenu(gui);
        JFrame frame = new JFrame("Reverse Polish Notation Calculator");
        frame.getContentPane().add(gui.getMainPanel());
        frame.setJMenuBar(menu.getJMenuBar());
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setLocationRelativeTo((Component)null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowUI();
    }
}
