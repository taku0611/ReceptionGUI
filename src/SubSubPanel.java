import javax.swing.*;

public class SubSubPanel extends JPanel{
    JLabel label;
    MainFrame mainFrame;
    String string;

    public SubSubPanel(MainFrame mf,String str){
        mainFrame=mf;
        string=str;
        this.setName("subsubPanel");

        label = new JLabel("ご依頼ありがとうございました");
        this.add(label);
    }
}