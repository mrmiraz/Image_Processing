
package ImageProcessing;

import java.util.Arrays;

public class ImageFiltering {
    
     public int[][] zeroPadding(int[][] image, int padValue){
        int[][] filterImage = new int[image[0].length + 2*padValue][image.length + 2*padValue];
        for(int i = 0; i < padValue; i++){
            Arrays.fill(filterImage[i], 0);
        }
        
        for(int i = image.length+padValue-1; i < filterImage.length; i++){
            Arrays.fill(filterImage[i], 0);
        }
        
        for(int i = 0; i < filterImage.length; i++){
            for(int j =0; j < padValue; j++){
                filterImage[i][j] = 0;
            }
        }
        
        for(int i = 0; i < filterImage.length; i++){
            for(int j = padValue+filterImage[0].length -1; j < filterImage[0].length; j++){
                filterImage[i][j] = 0;
            }
        }
        return filterImage;
    }
     
    // return 3x3 kernel 
    public double[][] getWeightAvgKernel(){
        double[][] kernel = {{1.0/16, 2.0/16, 1.0/16},
            { 2.0/16, 4.0/16, 2.0/16},
            {1.0/16, 2.0/16, 1.0/16 }};
        return kernel;
    }
    
    public double[][] getMeanKernel(int n){ // n is the number of rows
        double[][] mean_kernel = new double[n][n];
        for(int i =0; i < n; i++){
            for(int j =0; j < n; j++){
                mean_kernel[i][j] = 1.0/(n*n);
            }
        }
        return mean_kernel;
     }
    
    public int[] meanFiltering(int[][] paddingImage, double[][] kernel, int imageSize){
        int result[] = new int[imageSize];
        int resultIndex = 0;
        int right = kernel[0].length;
        int botom = kernel.length;
//        System.out.println(right + " "+ botom);
        while(botom <= paddingImage.length){
            double sum = 0.0;
            for(int i = botom-kernel.length, ki = 0; i < botom; i++,ki++){
                for(int j = right-kernel[0].length, kj = 0; j < right; j++, kj++){
//                    System.out.print("("+i+","+j+")*"+"("+ki+","+kj+") ");
//                    System.out.print();
                    sum += paddingImage[i][j]*kernel[ki][kj];
                }
            }
//            System.out.println("");
//            System.out.println(right + " "+ botom);
            if(right == paddingImage[0].length){
                right = kernel[0].length;
                botom++;
            }
            else right++;
            result[resultIndex++]= (int)Math.round(sum);
        }
        
        return result;
    }
    
    public int[] meanFiltering(int[][] paddingImage, int[][] kernel, int imageSize){
        int result[] = new int[imageSize];
        int resultIndex = 0;
        int right = kernel[0].length;
        int botom = kernel.length;
        
        while(botom <= paddingImage.length){
            double sum = 0.0;
            for(int i = botom-kernel.length, ki = 0; i < botom; i++,ki++){
                for(int j = right-kernel[0].length, kj = 0; j < right; j++, kj++){
//                    System.out.print("("+i+","+j+")*"+"("+ki+","+kj+") ");
//                    System.out.print();
                    sum += paddingImage[i][j]*kernel[ki][kj];
                }
            }
//            System.out.println("");
//            System.out.println(right + " "+ botom);
            if(right == paddingImage[0].length){
                right = kernel[0].length;
                botom++;
            }
            else right++;
            result[resultIndex++]= (int)Math.round(sum);
        }
        return result;
    }
    
    public int[] medianFiltering(int[][] image,int kernelSize, int resultSize){
        int result[] = new int[resultSize];
        int resultIndex = 0;
        int right = kernelSize;
        int botom = kernelSize;
//        System.out.println(right + " "+ botom);
        while(botom <= image.length){
            int meadianArray[] =new int[kernelSize*kernelSize];
            int meadianIndex = 0;
            for(int i = botom-kernelSize; i < botom; i++){
                for(int j = right-kernelSize; j < right; j++){
                    meadianArray[meadianIndex++] = image[i][j];
                }
            }
            if(right == image[0].length){
                right = kernelSize;
                botom++;
            }
            else right++;
            Arrays.sort(meadianArray);
            result[resultIndex++]= meadianArray[meadianArray.length/2];
        }
        
        return result;
    }
    
    public int[] minFiltering(int[][] image,int kernelSize, int resultSize){
        int result[] = new int[resultSize];
        int resultIndex = 0;
        int right = kernelSize;
        int botom = kernelSize;
        System.out.println(right + " "+ botom);
        while(botom <= image.length){
            int min = Integer.MAX_VALUE;
            for(int i = botom-kernelSize; i < botom; i++){
                for(int j = right-kernelSize; j < right; j++){
                    min = Math.min(image[i][j], min);
                }
            }
            
            if(right == image[0].length){
                right = kernelSize;
                botom++;
            }
            else right++;
            result[resultIndex++]= min;
        }
        
        return result;
    }
    
    public int[] maxFiltering(int[][] image,int kernelSize, int resultSize){
        int result[] = new int[resultSize];
        int resultIndex = 0;
        int right = kernelSize;
        int botom = kernelSize;
        System.out.println(right + " "+ botom);
        while(botom <= image.length){
            int max = Integer.MIN_VALUE;
            for(int i = botom-kernelSize; i < botom; i++){
                for(int j = right-kernelSize; j < right; j++){
                    max = Math.max(image[i][j], max);
                }
            }
            
            if(right == image[0].length){
                right = kernelSize;
                botom++;
            }
            else right++;
            result[resultIndex++]= max;
        }
        
        return result;
    }
    
    public int[] erosionFilter(int[][] paddingImage, int[][] kernel, int resultSize){
        int result[] = new int[resultSize];
        int resultIndex = 0;
        int right = kernel[0].length;
        int botom = kernel.length;
        //count total one in kernel
        int countOne = 0;
        for(int i =0; i < kernel.length; i++){
            for(int j =0 ; j < kernel[0].length; j++){
                if(kernel[i][j] == 1)countOne++;
            }
        }
        
        while(botom <= paddingImage.length){
            int countSimilarOne = 0;
            for(int i = botom-kernel.length, ki = 0; i < botom; i++,ki++){
                for(int j = right-kernel[0].length, kj = 0; j < right; j++, kj++){
//                    System.out.print("("+i+","+j+")*"+"("+ki+","+kj+") ");
//                    System.out.print();
                    if(kernel[ki][kj] == 1 && paddingImage[i][j] == 1){
                        countSimilarOne++;
                    }
                    
                }
            }
//            System.out.println("");
//            System.out.println(right + " "+ botom);
            if(right == paddingImage[0].length){
                right = kernel[0].length;
                botom++;
            }
            else right++;
            if(countSimilarOne == countOne)
                result[resultIndex++]= 1;
            else
                result[resultIndex++]= 0;
        }
        return result;
    }
    
    public int[] dilationFilter(int[][] paddingImage, int[][] kernel, int resultSize){
        int result[] = new int[resultSize];
        int resultIndex = 0;
        int right = kernel[0].length;
        int botom = kernel.length;
        //count total one in kernel
        
        
        while(botom <= paddingImage.length){
            int r = 0;
            for(int i = botom-kernel.length, ki = 0; i < botom; i++,ki++){
                for(int j = right-kernel[0].length, kj = 0; j < right; j++, kj++){
//                    System.out.print("("+i+","+j+")*"+"("+ki+","+kj+") ");
//                    System.out.print();
                    if(kernel[ki][kj] == 1 && paddingImage[i][j] == 1){
                        r = 1;
                    }
                    
                }
            }
//            System.out.println("");
//            System.out.println(right + " "+ botom);
            if(right == paddingImage[0].length){
                right = kernel[0].length;
                botom++;
            }
            else right++;
            result[resultIndex++]= r;
        }
        return result;
    }
    
    
    
}
