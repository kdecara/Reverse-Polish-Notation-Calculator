import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class CalculatorMenu {
    private static final String STANDARD = "Standard";
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem standardView = new JMenuItem("Standard", 84);

    CalculatorMenu(CalculatorGUI gui) {
        CalculatorMenu.ViewAction viewAction = new CalculatorMenu.ViewAction();
        this.standardView.addActionListener(viewAction);
        this.standardView.setEnabled(false);
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(86);
        viewMenu.add(this.standardView);
        this.menuBar.add(new JMenu("Edit"));
        this.menuBar.add(viewMenu);
        this.menuBar.add(new JMenu("Help"));
    }

    public JMenuBar getJMenuBar() {
        return this.menuBar;
    }

    private class ViewAction implements ActionListener {
        private ViewAction() {
        }

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Standard")) {
                CalculatorMenu.this.standardView.setEnabled(false);
            }

        }
    }
}
