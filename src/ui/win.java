package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class win extends JFrame {
    JButton restart=new JButton("再来一把");
    JButton exit=new JButton("退出游戏");
    Runnable closeoldwindow;
    long time0;
    public win(Runnable handler,long time){
        setFrame();
        setrestart();
        setexit();
        time0=time;
        setlabel();
        setVisible(true);
        
        closeoldwindow=handler;
    }

    private void setlabel() {
        /*timer timer = new timer();*/
        long time=System.currentTimeMillis();
        JLabel label=new JLabel("恭喜您,"+((time - time0)/1000.0)+"的时间内完成了游戏!");
        label.setSize(400,200);
        label.setHorizontalAlignment(JLabel.CENTER);//居中
        label.setVisible(true);
        add(label);
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
        setTitle("您胜利了");//标题
        setAlwaysOnTop(true);//置顶
        setDefaultCloseOperation(3);
        setLayout(null);
    }
}
