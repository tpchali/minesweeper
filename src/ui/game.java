package ui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

public class game extends JFrame implements MouseListener {
    final int xm = 9;
    final int ym = 9;
    final int mine = 10;
    final int[][] block = new int[xm][ym];
    final boolean[][] visible = new boolean[xm][ym];
    final boolean[][] flagStatus = new boolean[xm][ym];// 记录旗子状态
    int showblocks = 0;
    long time;

    public game() {
        Frameset();//设置屏幕
        setMenu();//设置目录
        Imageset();//设置图像
        Mouseset();//设置鼠标
        setVisible(true);//可见
        time = System.currentTimeMillis();

    }

    private void setMenu() {
        JMenuBar Bar = new JMenuBar();
        JMenu Game = new JMenu("游戏");
        JMenu Help = new JMenu("帮助");
        JMenuItem restart = new JMenuItem("重启游戏");
        JMenuItem count = new JMenuItem("统计信息");
        JMenuItem exit = new JMenuItem("退出");

        JMenuItem director = new JMenuItem("作者信息");
        JMenuItem about = new JMenuItem("关于扫雷");

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new game();
            }
        });
        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JDialog count = new JDialog();
               JLabel label=new JLabel("这个还不会做,等之后再说吧");
               label.setBounds(0,0,300,100);
               count.getContentPane().add(label);
               count.setSize(300,100);
               count.setAlwaysOnTop(true);
               count.setLocationRelativeTo(null);
               count.setModal(true);//弹框不消失不让你操作下面
                count.setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        director.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog director = new JDialog();
                JLabel label=new JLabel("作者:tp_茶梨  微信:");
                label.setFont(new Font("宋体", Font.PLAIN, 24));
                label.setForeground(new Color(0, 100, 0));
                JLabel img1=new JLabel(new ImageIcon(getClass().getResource("/picture/img.png")));
                JLabel text=new JLabel("特别鸣谢,技术支持FANCC 微信:");
                text.setFont(new Font("宋体", Font.PLAIN, 24));
                text.setForeground(Color.BLUE);
                JLabel img2=new JLabel(new ImageIcon(getClass().getResource("/picture/img2.png")));
                Box box = Box.createVerticalBox();
                box.add(label);
                box.add(img1);
                box.add(text);
                box.add(img2);
                director.add(box);
                director.setSize(800,400);
                director.setAlwaysOnTop(true);
                director.setLocationRelativeTo(null);
                director.setModal(true);//弹框不消失不让你操作下面
                director.setVisible(true);
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog about = new JDialog();
                JLabel label=new JLabel("Minesweeper茶梨版,版本号1.0.0");
                label.setFont(new Font("宋体", Font.PLAIN, 24));
                JLabel img1=new JLabel(new ImageIcon(getClass().getResource("/picture/img3.png")));
                Box box = Box.createVerticalBox();
                box.add(label);
                box.add(img1);
                about.add(box);
                about.setSize(1200,400);
                about.setAlwaysOnTop(true);
                about.setLocationRelativeTo(null);
                about.setModal(true);//弹框不消失不让你操作下面
                about.setVisible(true);
            }
        });
        Game.add(restart);
        Game.add(count);
        Game.add(exit);
        Help.add(director);
        Help.add(about);
        Bar.add(Game);
        Bar.add(Help);
        setJMenuBar(Bar);

    }


    private void Frameset() {
        setSize(918, 960);
        setTitle("扫雷单机版");//标题
        setAlwaysOnTop(true);//置顶
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(3);//关闭模式
        setLayout(null);
        for (int i = 0; i < 9; i++) {
            for (int i1 = 0; i1 < 9; i1++) {
                visible[i][i1] = false;
            }
        }
    }

    private void Imageset() {
        Random rand = new Random();
        int x1, y1;
        List<Integer> plane_nums = IntStream.range(0, xm * ym).boxed().collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < mine; ++i) {
            int pick = plane_nums.remove(rand.nextInt(plane_nums.size()));
            x1 = pick % xm;
            y1 = pick / ym;
            block[x1][y1] = 9;
            judge(x1, y1);
        }
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.setLayout(null);
        for (x1 = 0; x1 < 9; x1++) {
            for (y1 = 0; y1 < 9; y1++) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/picture/10.png"));
                JLabel label = new JLabel(icon);
                label.setBounds(100 * y1, 100 * x1, 100, 100);
                add(label);

            }
        }
/*        for (int i = 0; i < 9; i++) {
            for (int i1 = 0; i1 < 9; i1++) {
                System.out.println(block[i][i1]);
            }
        }*///检测格子的东西
    }

    private void judge(int x, int y) {//标记数字
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (x + dx >= 0 && y + dy >= 0 && x + dx < 9 && y + dy < 9) {
                    if (dx == 0 && dy == 0) {
                        continue;
                    }
                    if (block[x + dx][y + dy] == 9) {
                        continue;
                    }
                    block[x + dx][y + dy]++;
                }
            }
        }
    }

    private void Mouseset() {
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            sleep(1);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }//提高效率用的(其实我也不知道能不能提高效率)
        if (e.getButton() == MouseEvent.BUTTON1) {

            int tempx = e.getX() - 9;
            int tempy = e.getY() - 51;
            int x = tempx / 100;
            int y = tempy / 100;
            if (flagStatus[x][y]) {
                return;
            }
            if (block[x][y] == 11) {
            } else {
                if (block[x][y] == 9) {
                    JLayeredPane layeredPane = getLayeredPane();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/picture/redboom.png"));
                    JLabel label = new JLabel(icon);
                    label.setBounds(100 * x, 100 * y + 23, 100, 100);
                    layeredPane.add(label, 0);
                    visible[x][y] = true;
                    showallboom();
                    new lose(this::dispose);
                } else {
                    reveal(x, y);
                }
            }

        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            int tempx = e.getX() - 9;
            int tempy = e.getY() - 51;
            int x = tempx / 100;
            int y = tempy / 100;
            if (visible[x][y] == true) {
                return;
            }
            if (flagStatus[x][y]) {
                show(x, y, 10);
            } else {
                show(x, y, 11);
            }
            flagStatus[x][y] = !flagStatus[x][y];

        }
    }

    private void reveal(int x, int y) {
        if (visible[x][y]) {
            return;
        } else {
            show(x, y);
            if (block[x][y] == 11) {
            } else {
                showblocks++;
                setwin();
                if (block[x][y] == 0) {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (x + dx >= 0 && y + dy >= 0 && x + dx < 9 && y + dy < 9) {
                                reveal(x + dx, y + dy);
                            }
                        }
                    }
                }
            }

        }
    }

    private void show(int x, int y, int tile) {
        JLayeredPane layeredPane = getLayeredPane();
        ImageIcon icon = new ImageIcon(getClass().getResource("/picture/" + tile + ".png"));
        JLabel label = new JLabel(icon);
        label.setBounds(100 * x, 100 * y + 23, 100, 100);
        layeredPane.add(label, 0);
    }

    private void show(int x, int y) {
        show(x, y, block[x][y]);
        visible[x][y] = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
/*    private void mousemoved() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
            }
        });
    }*/

    //    protected void closegame() {
//        dispose();
//    }
    private void showallboom() {
        for (int i = 0; i < 9; i++) {
            for (int i1 = 0; i1 < 9; i1++) {
                if (visible[i][i1] == false && block[i][i1] == 9) {
                    show(i, i1);
                }
            }
        }
    }

    private void setwin() {
        if (showblocks == 71) {
            new win(this::dispose, time);
        }
    }
}




