package panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    public String[] PanelNames = {"mainPanel", "SubPanel"};

    panels.MainPanel mainPanel = new panels.MainPanel(this, PanelNames[0]);
    panels.SubPanel subPanel = new panels.SubPanel(this, PanelNames[1]);

    public MainFrame() {
        this.add(mainPanel);
        mainPanel.setVisible(true);
        this.add(subPanel);
        subPanel.setVisible(false);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public void PanelChange(JPanel jp, String str) {
        System.out.println(jp.getName());
        String name = jp.getName();
        if (name == PanelNames[0]) {
            mainPanel = (panels.MainPanel) jp;
            mainPanel.setVisible(false);
        } else if (name == PanelNames[1]) {
            subPanel = (panels.SubPanel) jp;
            subPanel.setVisible(false);
        }
        if (str == PanelNames[0]) {
            mainPanel.setVisible(true);
        } else if (str == PanelNames[1]) {
            subPanel.setVisible(true);
        }
    }
}