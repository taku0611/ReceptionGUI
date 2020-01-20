import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SubSubPanel extends JPanel{

    JLabel label;
    MainFrame mainFrame;
    String string;
    JButton newRequest;

    public SubSubPanel(MainFrame mf,String str){
        mainFrame=mf;
        string=str;
        this.setName("subsubPanel");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        label = new JLabel("ご依頼ありがとうございました");
        label.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 35));
        gridBagConstraints.anchor=GridBagConstraints.CENTER;
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.insets=new Insets(150,0,100,0);
        add(label,gridBagConstraints);

        newRequest = new JButton("新規ご依頼はこちら");
        gridBagConstraints.anchor=GridBagConstraints.WEST;
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;

        newRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.reloadPanel(mainFrame.PanelNames[1]);
                panelChangeToSubPanel(mainFrame.PanelNames[1]);

            }
        });
        add(newRequest,gridBagConstraints);

        JButton button = new JButton("終了する");
        gridBagConstraints.anchor=GridBagConstraints.EAST;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "終了しますか？",
                        "最終確認", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (option == JOptionPane.YES_OPTION){
                    mainFrame.dispose();
                    System.exit(0);
                }else if (option == JOptionPane.NO_OPTION){

                }
            }
        });
        add(button,gridBagConstraints);




    }
    public void panelChangeToSubPanel(String str) {
        mainFrame.showSubPanel((JPanel) this);
    }
}