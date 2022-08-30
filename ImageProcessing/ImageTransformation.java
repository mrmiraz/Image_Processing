
package ImageProcessing;

import java.util.*;
public class ImageTransformation {
    
    static Scanner sc = new Scanner(System.in);
    public int getSusValue(int curNode, int n1, int n2){
        if(curNode < n1 || curNode < n2){
            return 0;
        }
        return curNode;
    }
    
    public int[][] getSuspressionImage(int[][] tanImage, int[][] magnitudeImage){
        NormalFunction nf = new NormalFunction();
        int n = tanImage.length;
        int m = tanImage[0].length;
        String[][] resultImage = new String[n][m];
        int[][] padImage = nf.getZeroPadding(magnitudeImage, 1);
        int[][] rImage = new int[n][m];
        /*
        dn = down
        lbc = left bottom corner
        lf = left
        luc = left upper corner
        ruc = righ upper corner
        rbc = right bottom corner
        */
        for(int  i = 0;i < n; i++){
            for(int j = 0; j < m ;j++){
                if(tanImage[i][j] > -22.5 && tanImage[i][j] < 22.5){
                    rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i+1-1][j+1], padImage[i+1+1][j+1]);
                    resultImage[i][j] = tanImage[i][j] + ":dn";
                }
                else if(tanImage[i][j] > -67.5 && tanImage[i][j] < -22.5){
                    rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i+1+1][j-1+1], padImage[i-1+1][j+1+1]);
                    resultImage[i][j] = tanImage[i][j] + ":lbc";
                }
                else if(tanImage[i][j] > -112.5 && tanImage[i][j] < -67.5){
                     rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i+1][j-1+1], padImage[i+1][j+1+1]);
                    resultImage[i][j] = tanImage[i][j] + ":lf";
                }
                else if(tanImage[i][j] > -157.5 && tanImage[i][j] < -112.5){
                    resultImage[i][j] = tanImage[i][j] + ":luc";
                    rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i-1+1][j-1+1], padImage[i+1+1][j+1+1]);
                }
                else if(tanImage[i][j] > 157.5 && tanImage[i][j] < -157.5){
                    resultImage[i][j] = tanImage[i][j] + ":up";
                    rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i-1+1][j+1], padImage[i+1+1][j+1]);
                }
                else if(tanImage[i][j] > 112.5 && tanImage[i][j] < 157.5){
                    resultImage[i][j] = tanImage[i][j] + ":ruc";
                     rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i+1+1][j-1+1], padImage[i-1+1][j+1+1]);
                }
                else if(tanImage[i][j] > 67.5 && tanImage[i][j] < 112.5){
                    resultImage[i][j] = tanImage[i][j] + ":rt";
                    rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i+1][j-1+1], padImage[i+1][j+1+1]);
                }
                else if(tanImage[i][j] > 22.5 && tanImage[i][j] < 67.5){
                    resultImage[i][j] = tanImage[i][j] + ":rbc";
                    rImage[i][j] = getSusValue(padImage[i+1][j+1],padImage[i-1+1][j-1+1], padImage[i+1+1][j+1+1]);
                }
                
            }
        }
        

        return rImage;
    }
    
    public int[][] getMagnitudeImage(int[][] image1, int[][] image2){
        int n = image1.length;
        int m = image1[0].length;
        int[][] resultImage = new int[n][m];
        for(int i = 0;i < n; i++){
            for(int j = 0; j < m; j++){
                resultImage[i][j] = (int)Math.round(Math.sqrt((image1[i][j]*image1[i][j]+image2[i][j]*image2[i][j])));
            }
        }
        return resultImage;
    }
  
    
    public int[][] getTanImage(int[][] gxImage, int[][] gyImage){
        int n = gxImage.length;
        int m = gyImage[0].length;
        int[][] resultImage = new int[n][m];
        for(int i =0; i < n; i++){
            for(int j = 0; j < m; j++){
                resultImage[i][j] = (int)Math.round((180.0/Math.PI)*Math.atan((double)gyImage[i][j]/gxImage[i][j]));
            }
        }
        return resultImage;
    }
    
    public  int[] negativeTransform(int a[], int k){
        int l = 1<<(k);
        for(int i =0; i < a.length; i++){
            a[i] = (l-1)-a[i];
        }
        return a;
    }
    
    // s = c*log(1+r),  r = image pixel, k = bit depth
    public int[] logTransform(int a[], int k, int c){
        int l = 1<<k;
        for(int i =0; i < a.length; i++){
            a[i] = (int)Math.round(c*Math.log10(1+a[i]));
        }
        return a;
    }
    
    public  int[] thresholdTransform(int a[], int th, int small, int big, int equal){
        for(int i =0; i < a.length;i++){
            if(a[i] > th)a[i] = small;
            else if(a[i] < th) a[i] = big;
            else a[i] = equal;
        }
        return a;
    }
    
    public  int[] reconstructWithMsb(int a[], int k){
        for(int i =0; i < a.length; i++){
            a[i] = a[i]&(1<<(k-1));
        }
        return a;
    }
    
    public int[] reconstructWithIthBit(int a[], int ithBit, int bitDepth){
        int mask = 1<<(ithBit-1);
        mask = mask ^ 1;
        for(int i =0; i < a.length; i++){
            a[i] = a[i]& mask;
        }
        return a;
    }
    
    public  int[] reconstructWithLsbMsb(int a[],int k){
        int mask = 1<<(k-1);
        mask = mask ^ 1;
        for(int i =0; i < a.length; i++){
            a[i] = a[i]& mask;
        }
        return a;
    }
    
    public  int[] reconstructWithoutLsbMsb(int a[], int k){
        int mask = (1<<k) - 1;
        mask = mask &(~1);
        mask = mask &(~(1<<k-1));
//        System.out.println(mask);
        
        for(int i =0; i < a.length; i++){
            a[i] = a[i] & mask;
        }
        return a;
    }
    
    public  void printArray(int[] a, int col){
        for(int i =0; i < a.length; i++){
            System.out.print(a[i] + " ");
            if((i+1) % col == 0)System.out.println();
        }
        System.out.println("");
    }
    
    
    
    public  void main(String[] args) {
        
        int n= 25, col = 5, k = 6;
        int a[] = new int[n];
        
        for(int i  = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
//        negativeTransform(a, k);
//        logTransform(a, k, 5);
        thresholdTransform(a, 24, 0, 100, 50);
        printArray(a, col);
//        bitDepth(a, k);
//        printArray(a, col);
//        decToBin(a, k, col);
//        reconstruct(a, k);
//        reconstructWithLsbMsb(a, k);
//        reconstructWithoutLsbMsb(a, k);
//        decToBin(a, k, col);
//        printArray(a, col);
    }
}
