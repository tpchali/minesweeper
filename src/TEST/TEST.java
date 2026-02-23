package TEST;

import java.util.Random;

public class TEST {
    public static void main(String[] args) {
        int[][] block = new int[9][9];
        Random r=new Random();
        int x1=0,y1=0;
        for (int i = 0; i < 10; i++) {
            x1=r.nextInt(9);
            y1=r.nextInt(9);
            if(block[x1][y1]==9){
                i--;
                continue;
            }
            block[x1][y1]=9;//设置10个雷
            if(0<x1&&8>x1&&0<y1&&8>y1){
                LD(block, x1, y1);
                L(block, x1, y1);
                LU(block, x1, y1);
                D(block, x1, y1);
                U(block, x1, y1);
                RD(block, x1, y1);
                R(block, x1, y1);
                RU(block, x1, y1);
                //给非边角的地方设雷
            }
            if(x1==0&&y1!=0&&y1!=8){
                U(block, x1, y1);
                RU(block, x1, y1);
                RD(block, x1, y1);
                R(block, x1, y1);
                D(block, x1, y1);
            }//右包围
            if(x1==8&&y1!=0&&y1!=8){
               U(block, x1, y1);
               LU(block, x1, y1);
               L(block, x1, y1);
               LD(block, x1, y1);
               D(block, x1, y1);
            }//左包围
            if(y1==0&&x1!=0&&x1!=8){
                U(block, x1, y1);
                LU(block, x1, y1);
                RU(block, x1, y1);
                L(block, x1, y1);
                R(block, x1, y1);
            }//上包围
            if(y1==8&&x1!=0&&x1!=8){
                D(block, x1, y1);
                LD(block, x1, y1);
                RD(block, x1, y1);
                L(block, x1, y1);
                R(block, x1, y1);
            }//下包围
            if(x1==0&&y1==0){
                RU(block, x1, y1);
                R(block, x1, y1);
                U(block, x1, y1);
            }
            if(x1==0&&y1==8){
                D(block, x1, y1);
                RD(block, x1, y1);
                R(block, x1, y1);
            }
            if(y1==0&&x1==8){
                L(block, x1, y1);
                LD(block, x1, y1);
                D(block, x1, y1);
            }
            if(y1==8&&x1==8){
                L(block, x1, y1);
                LD(block, x1, y1);
                D(block, x1, y1);

            }
        }
        for (int i = 0; i < block.length; i++) {
            for (int i1 = 0; i1 < block.length; i1++) {
                System.out.print(block[i1][i]+" ");
            }
            System.out.println();
        }//检测
    }

    private static void RU(int[][] block, int x1, int y1) {
        if(block[x1 +1][y1 +1]!=9){
            block[x1 +1][y1 +1]++;
        }//右上
    }

    private static void R(int[][] block, int x1, int y1) {
        if(block[x1 +1][y1]!=9) {
            block[x1 +1][y1]++;
        }//右
    }

    private static void RD(int[][] block, int x1, int y1) {
        if(block[x1 +1][y1 -1]!=9){
            block[x1 +1][y1 -1]++;
        }//右下
    }

    private static void U(int[][] block, int x1, int y1) {
        if(block[x1][y1 +1]!=9){
            block[x1][y1 +1]++;
        }//上
    }

    private static void D(int[][] block, int x1, int y1) {
        if(block[x1][y1 -1]!=9){
            block[x1][y1 -1]++;
        }//下
    }

    private static void LU(int[][] block, int x1, int y1) {
        if(block[x1 -1][y1 +1]!=9){
            block[x1 -1][y1 +1]++;
        }//左上
    }

    private static void L(int[][] block, int x1, int y1) {
        if(block[x1 -1][y1]!=9){
            block[x1 -1][y1]++;
        }//左
    }

    private static void LD(int[][] block, int x1, int y1) {
        if(block[x1 -1][y1 -1]!=9){
            block[x1 -1][y1 -1]++;
        }//左下
    }
}


