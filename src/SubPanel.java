

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SubPanel extends JPanel {
    //static final String window_title = "配達依頼";
    private static final String btnNumberCheck_code = "Integer Check";
    private static final String btnFinalCheck_code = "Final Check";

    static JTextField textReceivername = new JTextField();
    static JTextField textClientname = new JTextField();
    static JTextField textAddress01 = new JTextField();
    static JTextField textAddress02 = new JTextField();

    static JLabel labelReceivername = new JLabel("<お届け先>");
    static JLabel labelClientname = new JLabel("<ご依頼主>");

    static JLabel labelAddress01 = new JLabel("住所: ");
    static JLabel labelAddress02 = new JLabel("住所: ");

    static JLabel labelNames01 = new JLabel("氏名");
    static JLabel labelNames02 = new JLabel("氏名");

    //フレームを作成
    MainFrame mainFrame;
    String string;

    public SubPanel(MainFrame mf, String str) {
        mainFrame = mf;
        string = str;

        //パネルの名前をセット
        this.setName("subPanel");

        //レイアウトを追加
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        //gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        //ラベル・テキストフィールドを追加
        //1列目
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(labelReceivername, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        add(labelAddress01,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        add(labelNames01,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(labelClientname, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(labelAddress02, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=5;
        add(labelNames02,gridBagConstraints);

        //2列目
        textAddress01.setColumns(10);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(textAddress01, gridBagConstraints);

        textReceivername.setColumns(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(textReceivername, gridBagConstraints);

        textAddress02.setColumns(10);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        add(textAddress02, gridBagConstraints);

        textClientname.setColumns(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        add(textClientname, gridBagConstraints);

        //住所確認ボタンを追加
        String btnNumberCheck_title = "住所を確認する";
        JButton btnNumberCheck = new JButton(btnNumberCheck_title);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;

        //ボタンが押されたときの処理
        btnNumberCheck.setActionCommand(SubPanel.btnNumberCheck_code);
        btnNumberCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String strnum = "0123456789";
                final String str1to16 = "12345678910111213141516";
                final String title = "エラー";

                if (e.getActionCommand() == SubPanel.btnNumberCheck_code) {
                    final String str = SubPanel.this.textAddress02.getText();

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
            }
        });
        add(btnNumberCheck, gridBagConstraints);

        //最終確認ボタンを追加
        String btnFinalCheck_title="依頼内容を確認する";
        JButton btnFinalCheck = new JButton(btnFinalCheck_title);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;

        //ボタンが押された時の処理
        btnFinalCheck.setActionCommand(SubPanel.btnFinalCheck_code);
        btnFinalCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String selectvalues[] = {"OK", "再入力"};

                if (e.getActionCommand() == SubPanel.btnFinalCheck_code) {
                    int select = JOptionPane.showOptionDialog(
                            null,
                            "<お届け先>" +"\n"+
                                    "氏名: "+SubPanel.textReceivername.getText() + "\n" +
                                    "住所: "+SubPanel.textAddress01.getText()+"\n"+
                                    "<ご依頼主>" + "\n" +
                                    "氏名: "+SubPanel.textClientname.getText() + "\n" +
                                    "住所: "+SubPanel.textAddress02.getText(),
                            "最終確認画面",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            selectvalues,
                            selectvalues[0]);

                    if (select == JOptionPane.YES_OPTION) {
                        mainFrame.reloadPanel(mainFrame.PanelNames[2]);
                        panelChangeToSubSubPanel(mainFrame.PanelNames[2]);

                    } else if (select == JOptionPane.NO_OPTION) {

                    }
                }
            }
        });
        add(btnFinalCheck, gridBagConstraints);
    }

    public void panelChangeToSubSubPanel(String str) {
        mainFrame.showSubSubPanel((JPanel) this);
    }
}



