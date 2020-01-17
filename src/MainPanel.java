package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainPanel extends JPanel{
    private static final String window_title = "配達依頼";
    private static final String btnNumberCheck_title = "住所を確認する";
    private static final String btnNumberCheck_code = "Integer Check";
    private static final String btnFinalCheck_title = "依頼内容を確認する";
    private static final String btnFinalCheck_code = "Final Check";
    private static final int tf01_size = 20;
    private static final int tf02_size = 20;
    private static final int tf03_size = 5;

    private static JTextField textClientname = new JTextField("", MainPanel.tf01_size);
    private static JTextField textReceivername = new JTextField("", MainPanel.tf02_size);
    private static JTextField textAddress = new JTextField("", MainPanel.tf03_size);

    private static JLabel labelClientname = new JLabel("Enter Client_name: ");
    private static JLabel labelReceivername = new JLabel("Enter Receiver_name: ");
    private static JLabel labelAddress = new JLabel("Enter Address ※1から16までの住所を入力してください: ");

    private static JButton btnNumberCheck = new JButton(MainPanel.btnNumberCheck_title);
    private static JButton btnFinalCheck = new JButton(MainPanel.btnFinalCheck_title);

    //フレームを作成
    panels.MainFrame mainFrame;
    String string;

    public MainPanel(panels.MainFrame mf, String str){
        mainFrame = mf;
        string = str;

        final JFrame frame = new JFrame(MainPanel.window_title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ボタンを追加
        btnNumberCheck.setActionCommand(MainPanel.btnNumberCheck_code);
        btnNumberCheck.addActionListener(new PushButtonActionListener());

        btnFinalCheck.setActionCommand(MainPanel.btnFinalCheck_code);
        btnFinalCheck.addActionListener(new PushButtonActionListener());

        // ボタンとテキストフィールドをレイアウト


            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(10, 10, 10, 10);

            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(labelClientname, constraints);

            constraints.gridx = 1;
            this.add(textClientname, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            this.add(labelReceivername, constraints);

            constraints.gridx = 1;
            this.add(textReceivername, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            this.add(labelAddress, constraints);

            constraints.gridx = 1;
            this.add(textAddress, constraints);

            constraints.gridx = 2;
            constraints.gridy = 2;
            this.add(btnNumberCheck, constraints);

            constraints.gridx = 2;
            constraints.gridy = 3;
            this.add(btnFinalCheck, constraints);
    }

    /**
     * ボタンクリック時処理
     */

    public class PushButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            final String strnum = "0123456789";
            final String str1to16 = "12345678910111213141516";
            final String title = "ErrorMessage";
            final String selectvalues[] = {"OK", "再入力"};

            if (e.getActionCommand() == MainPanel.btnNumberCheck_code) {
                final String str = MainPanel.this.textAddress.getText();

                if (str.length() == 0) {
                    JLabel label = new JLabel("数字を入力してください");
                    label.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, label, title, JOptionPane.WARNING_MESSAGE);
                    return;
                }
                for (int i = 0; i < str.length(); i++) {
                    if (strnum.indexOf(str.substring(i, i + 1)) == -1) {
                        JLabel label = new JLabel("数字を入力してください");
                        label.setForeground(Color.RED);
                        JOptionPane.showMessageDialog(null, label, title, JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }


                if (str.length() != 1 && str.length() != 2) {
                    JLabel label = new JLabel("正しい住所を入力してください");
                    label.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, label, title, JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (str1to16.indexOf(str) == -1) {
                    JLabel label = new JLabel("正しい住所を入力してください");
                    label.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, label, title, JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (e.getActionCommand() == MainPanel.btnFinalCheck_code) {
                //JLabel label = new JLabel(Reception.textAddress.getText());
                int select = JOptionPane.showOptionDialog(
                        null,
                        "Client: " + MainPanel.textClientname.getText() + "\n" +
                                "Receiver: " + MainPanel.textReceivername.getText() + "\n" +
                                "Address: " + MainPanel.textAddress.getText(),
                        "最終確認画面",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        selectvalues,
                        selectvalues[0]);

                if (select == JOptionPane.YES_OPTION) {
                    panelChange(mainFrame.PanelNames[1]);

                } else if (select == JOptionPane.NO_OPTION) {

                }
            }


        }
    }

    public void panelChange(String str){
        mainFrame.PanelChange(this,str);
    }
}