package ui;

import javax.swing.*;

public class about extends JFrame {
    public about() {
        setFrame();
        setVisible(true);
    }

    private void setFrame() {
        setSize(500, 700);
        setTitle("关于作者");//标题
        setAlwaysOnTop(true);//置顶
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(1);//关闭模式
        setLayout(null);
    }
}
