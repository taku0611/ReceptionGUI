import javax.swing.*;
import java.awt.*;

public class SubSubPanel extends JPanel{

    JLabel label;
    MainFrame mainFrame;
    String string;

    public SubSubPanel(MainFrame mf,String str){
        mainFrame=mf;
        string=str;
        this.setName("subsubPanel");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        label = new JLabel("ご依頼ありがとうございました");
        label.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 35));
        gridBagConstraints.anchor=GridBagConstraints.CENTER;
        add(label,gridBagConstraints);
    }
}