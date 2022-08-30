package ImageProcessing;
/*
filter

        int[][] laplacianFilterl = {{-1, -1, -1},
            {-1, -8, -1},
            {-1, -1, -1}
        };

        int[][] laplacianEnhanceFilter = {{-1, -1, -1},
            {-1, 9, -1},
            {-1, -1, -1}
        };

        int[][] verticalSobelFilter ={
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
        };
        
        int[][] verticalDifFilter = {
            {0, 1, 0},
            {0, 1, 0},
            {0, -1, 0}
        };

        int[][] horizontalSobelFilter = {
            {  -1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
        };

*/

/*

erosion image 

int n = 12;
        int[] image = nf.getArray(n*n);
        int[][] imaged = nf.toTwoDArray(image, n, n);
        int[][] image2d = nf.getZeroPadding(imaged, 1);
        int[][] morphologyKernel = {{1, 1,1 },
                {1, 1,0},
                {0, 1, 0}};
        
//        nf.printImage(nf.getZeroPadding(morphologyKernel, 3));

        nf.printImage(image2d);
//        nf.printImage(ifl.erosionFilter(image2d, erosionKernel, 10*10), 10);
        int[][] erosionImage = nf.toTwoDArray(ifl.erosionFilter(image2d, morphologyKernel, n*n), n, n);
        int[][] dilationImage = nf.toTwoDArray(ifl.dilationFilter(image2d, morphologyKernel, n*n), n, n);
        
        //running opening
        int[][] dilationAfterErosionImage = nf.toTwoDArray(ifl.dilationFilter(nf.getZeroPadding(erosionImage, 1),  morphologyKernel, n*n), n, n);
        int[][] erosionAfterDilationImage = nf.toTwoDArray(ifl.erosionFilter(nf.getZeroPadding(dilationImage, 1), morphologyKernel, n*n), n,n);
        
        System.out.println("erosion Image");
        nf.printImage(erosionImage);
        System.out.println("dialtion Image");
        nf.printImage(dilationImage);
        
        System.out.println("In openning: Dialation after erosion( Erosion--> dialation --> output):");
        nf.printImage(dilationAfterErosionImage);
//        
        System.out.println("In clossing:  erosion after dilation(Dialation--> erosion --> output):");
        nf.printImage(erosionAfterDilationImage);
*/


/*
int N = 5;
        int[][] image = nf.toTwoDArray(nf.getArray(N*N), N, N);
        System.out.println("original image: ");
        nf.printImage(image);
        
        
        System.out.println("Gaussian blur:");
        System.out.println("------------------------");
        System.out.println("replication Image");
//        int[][] repImage = nf.getReplication(image);
//        nf.printImage(repImage);
        
        System.out.println("weight average filter:\n\n------");
        double[][] weightKerner = ifl.getWeightAvgKernel();
        N = 3;
        System.out.println("Blur Image:");
        int[][] blurImage = nf.toTwoDArray(ifl.meanFiltering(image, weightKerner, N*N), N, N);
        nf.printImage(blurImage);
        
        System.out.println("zero padding blur image:");
        int[][] zeroPadBlurImage = nf.getReplication(blurImage);
        nf.printImage(zeroPadBlurImage);
        System.out.println("vertical sobel:");
        int[][] verticalKernel = {{-1,-2,-1},
            {0,0,0},
            {1,2,1}
        };
        nf.printImage(verticalKernel);
        System.out.println("horizontal sobel:");
        int[][] horizontalKernel = {
            {-1, 0, 1}  ,
            {-2, 0, 2},
            {-1, 0, 1}
        };
        nf.printImage(horizontalKernel);
        System.out.println("gy image");
        int[][] gyImage = nf.toTwoDArray(ifl.meanFiltering(zeroPadBlurImage, verticalKernel, N*N), N, N);
        nf.printImage(gyImage);
        System.out.println("gx image");
        int[][] gxImage = nf.toTwoDArray(ifl.meanFiltering(zeroPadBlurImage, horizontalKernel, N*N), N, N);
        nf.printImage(gxImage);
        System.out.println("Magnitude image:");
        int[][] magnitudeImage = itf.getMagnitudeImage(gxImage, gyImage);
        nf.printImage(magnitudeImage);
        System.out.println("Tanh Image:");
        int[][] tanImage = itf.getTanImage(gxImage, gyImage);
        nf.printImage(tanImage);
        
        System.out.println("directed Image");
        nf.printDirection(tanImage);
        
        System.out.println("Suspression Image");
        int[][] suspressionImage = itf.getSuspressionImage(tanImage, magnitudeImage);
        nf.printImage(suspressionImage);










canny edges
int N = 5;
        int[][] image = nf.toTwoDArray(nf.getArray(N*N), N, N);
        System.out.println("original image: ");
        nf.printImage(image);
        
        
        System.out.println("Gaussian blur:");
        System.out.println("------------------------");
        System.out.println("replication Image");
        int[][] repImage = nf.getReplication(image);
        nf.printImage(repImage);
        
        System.out.println("weight average filter:\n\n------");
        double[][] weightKerner = ifl.getWeightAvgKernel();
        
        System.out.println("Blur Image:");
        int[][] blurImage = nf.toTwoDArray(ifl.meanFiltering(repImage, weightKerner, N*N), N, N);
        nf.printImage(blurImage);
        
        System.out.println("zero padding blur image:");
        int[][] zeroPadBlurImage = nf.getZeroPadding(blurImage, 1);
        nf.printImage(zeroPadBlurImage);
        System.out.println("vertical sobel:");
        int[][] verticalKernel = {{-1,-2,-1},
            {0,0,0},
            {1,2,1}
        };
        nf.printImage(verticalKernel);
        System.out.println("horizontal sobel:");
        int[][] horizontalKernel = {
            {-1, 0, 1}  ,
            {-2, 0, 2},
            {-1, 0, 1}
        };
        nf.printImage(horizontalKernel);
        System.out.println("gy image");
        int[][] gyImage = nf.toTwoDArray(ifl.meanFiltering(zeroPadBlurImage, verticalKernel, N*N), N, N);
        nf.printImage(gyImage);
        System.out.println("gx image");
        int[][] gxImage = nf.toTwoDArray(ifl.meanFiltering(zeroPadBlurImage, horizontalKernel, N*N), N, N);
        nf.printImage(gxImage);
        System.out.println("Magnitude image:");
        int[][] magnitudeImage = itf.getMagnitudeImage(gxImage, gyImage);
        nf.printImage(magnitudeImage);
        System.out.println("Tanh Image:");
        int[][] tanImage = itf.getTanImage(gxImage, gyImage);
        nf.printImage(tanImage);
        
        System.out.println("directed Image");
        nf.printDirection(tanImage);
        
        System.out.println("Suspression Image");
        int[][] suspressionImage = itf.getSuspressionImage(tanImage, magnitudeImage);
        nf.printImage(suspressionImage);
*/

/*
Lwz compression

int N = 5;
        int[][] image = nf.toTwoDArray(nf.getArray(N*N), N, N);
        nf.printImage(image);
        System.out.println("Compressed Image:");
        ic.lzwCompressed(image);

*/

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        NormalFunction nf = new NormalFunction();
        ImageTransformation itf = new ImageTransformation();
        ImageFiltering ifl = new ImageFiltering();
        ImageCompressed ic = new ImageCompressed();
        
        
       
        
        
        int N = 5;
        int[][] image = nf.toTwoDArray(nf.getArray(N*N), N, N);
        nf.printImage(image);
        System.out.println("Compressed Image:");
        ic.lzwCompressed(image);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        int[] image = {158, 158, 158, 158, 1, 5, 5, 158, 158, 158, 158, 1,5, 5, 159, 159, 0, 219, 0, 4, 4,172, 172, 7, 157, 6, 72, 72, 120, 120, 32, 126, 7, 6, 6, 157, 157, 157, 108, 53, 124, 124, 157, 157, 157, 108, 53, 124, 124};
////        int image[] = {217,217,217,217,0,0,0,217,217,217,217,0,0,0,218,218,54,162,3,7,7,141,141,2,216,1,39,39,16,16,79,121,2,1,1,216,216,216,51,24,145,145,216,216,216,51,24,145,145};
//        int[][] image2d = nf.toTwoDArray(image, 7, 7);
//        System.out.println(image.length);
        
//        int[][] laplacianFilter = {{-1, -1, -1},
//            {-1, 9, -1},
//            {-1, -1, -1}};
//        int[] filterImage = ifl.meanFiltering(image2d, laplacianFilter, 25);
//        System.out.println("Laplacian inhancement");
//        nf.printImage(filterImage, 5);
        
//        int[][] sobelFilter = {{-1, -2, -1},
//            {0, 0, 0},
//            {1, 2, 1}};
//        int[] filterImage = ifl.meanFiltering(image2d, sobelFilter, 25);
//        System.out.println("Sobel inhancement");
//        nf.printImage(filterImage, 5);
        
        
//        int[][] differenceFilter = {{0,1,0},
//            {0,1,0},
//            {0, -1, 0}};
//        int[] filterImage = ifl.meanFiltering(image2d, differenceFilter, 25);
//        System.out.println("Vertical Difference Filter");
//        nf.printImage(filterImage, 5);
//        
        
        
        }
}
