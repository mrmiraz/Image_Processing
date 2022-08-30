
package ImageProcessing;
import java.util.*;
public class NormalFunction {
    
    static Scanner sc = new Scanner(System.in);
    
    
    public void printDirection(int[][] tanImage){
        int n = tanImage.length;
        int m = tanImage[0].length;
        String[][] resultImage = new String[n][m];
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
                    resultImage[i][j] = tanImage[i][j] + ":dn";
                }
                else if(tanImage[i][j] > -67.5 && tanImage[i][j] < -22.5){
                    resultImage[i][j] = tanImage[i][j] + ":lbc";
                }
                else if(tanImage[i][j] > -112.5 && tanImage[i][j] < -67.5){
                    resultImage[i][j] = tanImage[i][j] + ":lf";
                }
                else if(tanImage[i][j] > -157.5 && tanImage[i][j] < -112.5){
                    resultImage[i][j] = tanImage[i][j] + ":luc";
                }
                else if(tanImage[i][j] > 157.5 && tanImage[i][j] < -157.5){
                    resultImage[i][j] = tanImage[i][j] + ":up";
                }
                else if(tanImage[i][j] > 112.5 && tanImage[i][j] < 157.5){
                    resultImage[i][j] = tanImage[i][j] + ":ruc";
                }
                else if(tanImage[i][j] > 67.5 && tanImage[i][j] < 112.5){
                    resultImage[i][j] = tanImage[i][j] + ":rt";
                }
                else if(tanImage[i][j] > 22.5 && tanImage[i][j] < 67.5){
                    resultImage[i][j] = tanImage[i][j] + ":rbc";
                }
                
                if(j == m-1){
                    System.out.print(resultImage[i][j] + "\n");
                }
                else{
                    System.out.print(resultImage[i][j] + "\t");
                }
            }
        }
        
    }
    
    public void printRotationImage(int[][] image){
        int m = image.length;
        int n = image[0].length;
        for(int p =0;p < 8; p++){
            int[][] copy = new int[m][n];
            for(int i = 0; i < m; i++){
                 for(int j = 0;j < n; j++){
                     copy[i][j] = image[i][j];
                }
            }
            
            copy[0][0] = image[1][0];
            copy[0][1] = image[0][0];
            copy[0][2] = image[0][1];
            copy[1][2] = image[0][2];
            copy[2][2] = image[1][2];
            copy[2][1] = image[2][2];
            copy[2][0] = image[2][1];
            copy[1][0] = image[2][0];
            for(int i = 0; i < m; i++){
                 for(int j = 0;j < n; j++){
                     image[i][j] = copy[i][j];
                }
            }
            printImage(image);
        }
    }
    public int[] getArray(int size){
        int a[] = new int[size];
        for(int i =0; i < size; i++){
            a[i] = sc.nextInt();
        }
        return a; // return a 1d array
    }
    
    // k is the bit size of each elements
    public int[] convertArrayWithBitDepth(int[] a, int k){
        int size = a.length;
        for(int i = 0; i < size; i++){
            a[i] = a[i]%(1<<k);
        }
        return a;
    }
    public int[][] getReplication(int[][] image){
        int n = image.length;
        int m = image[0].length;
        int[][] resultImage = new int[(n+2)][m+(2)];
        for(int i = 0;i < n; i++){
            for(int j = 0; j < m; j++){
                resultImage[i+1][j+1] = image[i][j];
            }
        }
        
        int nn = resultImage.length; //row
        int mm = resultImage[0].length; // column
        
        for(int i = 0; i < mm; i++){
            resultImage[0][i] = resultImage[1][i];
        }
        
        for(int j = 0; j < mm; j++){
            resultImage[nn-1][j] = resultImage[nn-2][j];
        }
        
        for(int i = 0; i < nn; i++){
            resultImage[i][0] = resultImage[i][1];
        }
        
        for(int i = 0; i < nn; i++){
            resultImage[i][mm-1] = resultImage[i][mm-2];
        }
        
        return resultImage;
        
    }
    public int[][] getZeroPadding(int[][] image, int paddingNumber){
        int n = image.length;
        int m = image[0].length;
        int[][] resultImage = new int[(n+paddingNumber*2)][m+(paddingNumber*2)];
        for(int i = 0;i < n; i++){
            for(int j = 0; j < m; j++){
                resultImage[i+paddingNumber][j+paddingNumber] = image[i][j];
            }
        }
        return resultImage;
    }
    public double[] toDoubleArray(int a[]){
        double dArray[] = new double[a.length];
        int n = a.length;
        for(int i =0; i < n; i++){
            dArray[i] = a[i];
        }
        return dArray;
    }
    
    // n = elements of the array
    // eqn = (x+s)%y
    public int[] getImageArray(int n, int x){
        int image[] = new int[n];
        for(int i =0; i < n; i++){
            image[i] = (x + sc.nextInt()) % sc.nextInt();
        }
        return image;
    }
     
    public int[][] toTwoDArray(int[] image, int r, int c){
        int convertImage[][] = new int[r][c];
        for(int i =0; i < r; i++){
            for(int j = 0; j < c; j++){
                convertImage[i][j] = image[(i*c+j)];
            }
        }
        return convertImage;
    }
    
    public void printArray(int[] image){
        int n = image.length;
        for(int i = 0; i < n; i++){
            System.out.print(image[i] + "\t");
        }
        System.out.println("");
    }
    
    public void printImage(int[] image, int col){
        for(int i =0; i < image.length; i++){
            System.out.print(image[i] + "\t");
            if((i+1) % col == 0)System.out.println();
        }
        System.out.println("");
    }
    
    public void printImage(int[][] image){
        for(int i =0;i < image.length; i++){
            for(int j = 0; j < image[0].length; j++){
                System.out.print(image[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public  void printDecToBin(int a[], int k, int col){
        int mask = (1<<(k-1));
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < k ;j++){
                if((a[i] & mask >> j) == 0){
                    System.out.print(0);
                }
                else{
                    System.out.print(1);
                }
            }
            System.out.print("("+a[i]+") ");
            if((i+1) % col == 0){
                System.out.println();
            }
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        int n = 25;
        int image[] = {217,217,217,0,0,218,54,162,3,7,141,2,216,1,39,16,79,121,2,1,216,216,51,24,145};
        int x = 24*201;
//        System.out.println(image.length);
//        imageArray(image, 25, x);
//        printImage(image, 5);
//        int cimg[][] = arrayToImage(image, 5, 5);
//        printArray(image);
//        printImage(image, 5);
//        printImage(cimg);
//        ImageFiltering ifl = new ImageFiltering();
//        int zPaddingImage[][] = ifl.zeroPadding(cimg, 1);
//        printImage(zPaddingImage);
    }
}
