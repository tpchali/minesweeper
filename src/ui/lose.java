package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class lose extends JFrame {
    JButton restart=new JButton("重开一把");
    JButton exit=new JButton("退出游戏");
    Runnable closeoldwindow;
      public lose(Runnable handler) {
          setFrame();
          setrestart();
          setexit();
          setVisible(true);
          closeoldwindow = handler;
      }

    private void setexit() {
        exit.setSize(200,50);
        exit.setLocation(575,295);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);
    }

    private void setrestart() {
        restart.setSize(200,50);
        restart.setLocation(0,300);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeoldwindow.run();
                new game();
                dispose();
            }
        });
        add(restart);
    }

    private void setFrame() {
        setSize(800,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("您失败了");//标题
        setAlwaysOnTop(true);//置顶
        setDefaultCloseOperation(3);
        setLayout(null);
    }
}
