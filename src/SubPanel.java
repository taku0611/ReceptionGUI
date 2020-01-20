

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class SubPanel extends JPanel {

    static String btnFinalCheck_code = "Final Check";

    static JLabel labelReceivername = new JLabel("<お届け先>");
    static JLabel labelClientname = new JLabel("<ご依頼主>");

    static JLabel labelAddress01 = new JLabel("住所: ");
    static JLabel labelAddress02 = new JLabel("住所: ");

    static JLabel labelNames01 = new JLabel("氏名: ");
    static JLabel labelNames02 = new JLabel("氏名: ");

    static JLabel labelPhone01 = new JLabel("電話番号: ");
    static JLabel labelPhone02 = new JLabel("電話番号: ");

    static String btnFinalCheck_title = "依頼内容を確認する";
    static JButton btnFinalCheck = new JButton();

    static String btnBack_title = "戻る";
    static JButton btnBack = new JButton();

    //フレームを作成
    MainFrame mainFrame;
    String string;

    TextGuide textReceivername;
    TextGuide textClientname;
    static TextGuide textAddress01 = new TextGuide("お届け先の住所を入力");

    TextGuide textAddress02;
    TextGuide textPhoneNum01;
    TextGuide textPhoneNum02;

    public SubPanel(MainFrame mf, String str) {
        mainFrame = mf;
        string = str;

        //パネルの名前をセット
        this.setName("subPanel");

        //レイアウトを追加
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);

        //ラベル・テキストフィールドを追加
        //1列目
        //お届け先
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(labelReceivername, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(labelAddress01, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(labelNames01, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(labelPhone01, gridBagConstraints);


        //ご依頼主
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        add(labelClientname, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        add(labelAddress02, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        add(labelNames02, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        add(labelPhone02, gridBagConstraints);

        //2列目
        //お届け先
        textAddress01 = new TextGuide("お届け先の住所を入力");
        textAddress01.setColumns(15);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(textAddress01, gridBagConstraints);

        textReceivername = new TextGuide("例) 上田 智大");
        textReceivername.setColumns(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(textReceivername, gridBagConstraints);

        textPhoneNum01 = new TextGuide("例) 090△□○×△□○×");
        textPhoneNum01.setColumns(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        add(textPhoneNum01, gridBagConstraints);

        //ご依頼主
        textAddress02 = new TextGuide("ご依頼主の住所を入力");
        textAddress02.setColumns(15);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        add(textAddress02, gridBagConstraints);

        textClientname = new TextGuide("例) 佐藤 陽");
        textClientname.setColumns(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        add(textClientname, gridBagConstraints);

        textPhoneNum02 = new TextGuide("例) 090△□○×△□○×");
        textPhoneNum02.setColumns(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        //gridBagConstraints.insets=new Insets(3,3,100,3);
        add(textPhoneNum02, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 7;
        ImageIcon MapImage = new ImageIcon("./map.png");
        JLabel jLabel = new JLabel(MapImage);
        add(jLabel, gridBagConstraints);

        //戻るボタンを追加
        btnBack = new JButton(btnBack_title);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new Insets(40, 3, 3, 3);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.reloadPanel(mainFrame.PanelNames[0]);
                panelChangeToMainPanel(mainFrame.PanelNames[0]);
            }
        });
        add(btnBack, gridBagConstraints);

        //確認ボタンを追加
        btnFinalCheck = new JButton(btnFinalCheck_title);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        add(btnFinalCheck, gridBagConstraints);

        //ボタンが押されたときの処理
        btnFinalCheck.setActionCommand(SubPanel.btnFinalCheck_code);
        btnFinalCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String strNum = "0123456789";
                final String str1to16 = "12345678910111213141516";
                final String title = "エラー";
                final String selectValues[] = {"確定", "再入力"};
                final StringBuilder stringBuilder = new StringBuilder("※ 以下の入力に誤りがあります"+
                        System.getProperty("line.separator")+
                        System.getProperty("line.separator"));

                int errorCount = 0;

                //住所欄の確認(お届け先)
                final String receiverAdd = SubPanel.this.textAddress01.getText();
                if (receiverAdd.indexOf("お届け先の住所を入力") == 0) {

                    stringBuilder.append("<お届け先> 住所欄が空欄です");
                    stringBuilder.append(System.getProperty("line.separator"));
                    errorCount++;

                }
                else if (receiverAdd.indexOf("お届け先の住所を入力") != 0) {

                    int receiverAddCount=0;
                    if (receiverAdd.length() == 1 || receiverAdd.length() == 2) {
                        for (int i = 0; i < receiverAdd.length(); i++) {

                            if (strNum.indexOf(receiverAdd.substring(i, i + 1)) == -1) {
                                receiverAddCount++;
                                errorCount++;
                            }
                        }
                        if(receiverAddCount>0){
                            stringBuilder.append("<お届け先> 住所欄には数字を入力してください");
                            stringBuilder.append(System.getProperty("line.separator"));

                        } else if (str1to16.indexOf(receiverAdd) == -1) {

                            stringBuilder.append("<お届け先> 不正な住所です");
                            stringBuilder.append(System.getProperty("line.separator"));

                            errorCount++;
                        }
                    }else if (receiverAdd.length() != 1 && receiverAdd.length() != 2) {

                        stringBuilder.append("<お届け先> 不正な住所です");
                        stringBuilder.append(System.getProperty("line.separator"));

                        errorCount++;
                    }
                }


                //氏名欄の確認(お届け先)
                final String receiverName = SubPanel.this.textReceivername.getText();

                if (receiverName.indexOf("例) 上田 智大") == 0) {
                    stringBuilder.append("<お届け先> 氏名欄が空欄です");
                    stringBuilder.append(System.getProperty("line.separator"));

                    errorCount++;
                }

                //電話番号欄の確認(お届け先)
                final String receiverPhone = SubPanel.this.textPhoneNum01.getText();

                if (receiverPhone.indexOf("例) 090△□○×△□○×") == 0) {

                    stringBuilder.append("<お届け先> 電話番号欄が空欄です");
                    stringBuilder.append(System.getProperty("line.separator"));

                    errorCount++;


                } else if (receiverPhone.indexOf("例) 090△□○×△□○×") != 0) {

                    int receiverPhoneCount = 0;

                    if(receiverPhone.length() == 10 || receiverPhone.length() == 11) {

                        for (int i = 0; i < receiverPhone.length(); i++) {
                            if (strNum.indexOf(receiverPhone.substring(i, i + 1)) == -1) {
                                receiverPhoneCount++;
                                errorCount++;
                            }
                        }
                        if (receiverPhoneCount > 0) {
                            stringBuilder.append("<お届け先> 電話番号は数字を入力してください");
                            stringBuilder.append(System.getProperty("line.separator"));

                        }
                    }else if (receiverPhone.length() != 10 && receiverPhone.length() != 11) {
                        receiverPhoneCount++;
                        errorCount++;
                        if (receiverPhoneCount > 0) {
                            stringBuilder.append("<お届け先> 不正な電話番号です");
                            stringBuilder.append(System.getProperty("line.separator"));

                        }
                    }

                }

                //住所欄の確認(ご依頼主)
                final String clientAdd = SubPanel.this.textAddress02.getText();
                if (clientAdd.indexOf("ご依頼主の住所を入力") == 0) {

                    stringBuilder.append("<ご依頼主> 住所欄が空欄です");
                    stringBuilder.append(System.getProperty("line.separator"));

                    errorCount++;

                } else if (clientAdd.indexOf("ご依頼主の住所を入力") != 0) {

                    int clientAddCount=0;
                    if (clientAdd.length() == 1 || clientAdd.length() == 2) {
                        for (int i = 0; i < clientAdd.length(); i++) {

                            if (strNum.indexOf(clientAdd.substring(i, i + 1)) == -1) {
                                clientAddCount++;
                                errorCount++;
                            }
                        }
                        if(clientAddCount>0){
                            stringBuilder.append("<ご依頼主> 住所欄には数字を入力してください");
                            stringBuilder.append(System.getProperty("line.separator"));


                        }else if (str1to16.indexOf(clientAdd) == -1) {

                            stringBuilder.append("<ご依頼主> 不正な住所です");
                            stringBuilder.append(System.getProperty("line.separator"));

                            errorCount++;

                        }
                    }else if (clientAdd.length() != 1 && clientAdd.length() != 2) {

                        stringBuilder.append("<ご依頼主> 不正な住所です");
                        stringBuilder.append(System.getProperty("line.separator"));


                        errorCount++;
                    }
                }


                //氏名欄の確認(ご依頼主)
                final String clientName = SubPanel.this.textClientname.getText();

                if (clientName.indexOf("例) 佐藤 陽") == 0) {
                    stringBuilder.append("<ご依頼主> 氏名欄が空欄です");
                    stringBuilder.append(System.getProperty("line.separator"));

                    errorCount++;
                }

                //電話番号欄の確認(ご依頼主)
                final String clientPhone = SubPanel.this.textPhoneNum02.getText();

                if (clientPhone.indexOf("例) 090△□○×△□○×") == 0) {

                    stringBuilder.append("<ご依頼主> 電話番号欄が空欄です");
                    stringBuilder.append(System.getProperty("line.separator"));

                    errorCount++;


                } else if (clientPhone.indexOf("例) 090△□○×△□○×") != 0) {

                    int clientPhoneCount = 0;

                    if(clientPhone.length() == 10 || clientPhone.length() == 11) {

                        for (int i = 0; i < clientPhone.length(); i++) {
                            if (strNum.indexOf(clientPhone.substring(i, i + 1)) == -1) {
                                clientPhoneCount++;
                                errorCount++;
                            }
                        }
                        if (clientPhoneCount > 0) {
                            stringBuilder.append("<ご依頼主> 電話番号は数字を入力してください");
                            stringBuilder.append(System.getProperty("line.separator"));

                        }
                    }else if (clientPhone.length() != 10 && clientPhone.length() != 11) {
                        clientPhoneCount++;
                        errorCount++;
                        if (clientPhoneCount > 0) {
                            stringBuilder.append("<ご依頼主> 不正な電話番号です");
                            stringBuilder.append(System.getProperty("line.separator"));

                        }
                    }

                }
                if (errorCount != 0) {

                    String aaa = stringBuilder.toString();
                    JOptionPane.showMessageDialog(null, aaa, title, JOptionPane.WARNING_MESSAGE);
                    return;

                } else if (errorCount == 0) {
                    //最終確認
                    int select = JOptionPane.showOptionDialog(
                            null,
                            "<お届け先>" + "\n" +
                                    "住所: " + textAddress01.getText() + "\n" +
                                    "氏名: " + textReceivername.getText() + " 様" + "\n" +
                                    "電話番号: " + textPhoneNum01.getText() + "\n" +
                                    "\n" +
                                    "<ご依頼主>" + "\n" +
                                    "住所: " + textAddress02.getText() + "\n" +
                                    "氏名: " + textClientname.getText() + " 様" + "\n" +
                                    "電話番号: " + textPhoneNum02.getText() + "\n" +
                                    "\n" +
                                    "※ 確定するとキャンセルはできません",
                            "最終確認画面",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            selectValues,
                            selectValues[0]);

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

    public void panelChangeToMainPanel(String str) {
        mainFrame.showMainPanel((JPanel) this);
    }

    static class TextGuide extends JTextField implements FocusListener {
        //private static final long serialVersionUID = 1L;
        String helpmsg;
        String bakstr = "";

        TextGuide(String msg) {
            helpmsg = msg;
            addFocusListener(this);
            drawmsg();
        }

        void drawmsg() {
            setForeground(Color.LIGHT_GRAY);
            setText(helpmsg);
        }

        @Override
        public void focusGained(FocusEvent arg0) {
            setForeground(Color.BLACK);
            setText(bakstr);
        }

        @Override
        public void focusLost(FocusEvent arg0) {
            bakstr = getText();
            if (bakstr.equals("")) {
                drawmsg();
            }
        }
    }
}



