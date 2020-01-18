import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    JButton btnEnter;
    MainFrame mainFrame;
    String string;
    public MainPanel(MainFrame mf,String str){
        mainFrame = mf;
        string = str;
        this.setName("mainPanel");
        this.setLayout(null);
        this.setSize(700,500);

        btnEnter = new JButton("依頼内容入力");
        btnEnter.setBounds(350,400,300,30);

        btnEnter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                mainFrame.reloadPanel(mainFrame.PanelNames[1]);
                panelChangeToSubPanel(mainFrame.PanelNames[1]);
            }
        });
        this.add(btnEnter);
    }
    public void panelChangeToSubPanel(String toPanelName){
        mainFrame.showSubPanel((JPanel)this);
    }
}