package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubPanel extends JPanel {
    private static JButton bntBack = new JButton("前の画面に戻る");

    panels.MainFrame mainFrame;
    String string;

    public SubPanel(panels.MainFrame mf, String str){
        mainFrame = mf;
        string = str;
        this.setName(str);
        this.setLayout(null);
        this.setSize(400, 200);
        JLabel paneltitle = new JLabel("これは"
                +getClass().getCanonicalName()+"クラスのパネルです");
        paneltitle.setBounds(0, 5, 400, 40);
        this.add(paneltitle);
        bntBack.setBounds(150, 50, 200, 40);
        bntBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pc();
            }
        });
        this.add(bntBack);
        Float f = (float) Math.random();
        this.setBackground(Color.getHSBColor(f, 0.5f, 0.8f));
    }
    public void pc(){
        mainFrame.PanelChange((JPanel)this, mainFrame.PanelNames[0]);
    }
}