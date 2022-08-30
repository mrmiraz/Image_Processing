
package ImageProcessing;

import java.util.*;

public class ImageCompressed {
    public HashMap<String, Integer> getDefaultDictionary(){
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i <= 255; i++){
            map.put("-"+i,i);
        }
        return map;
    }
    
    public ArrayList<String> getDefaultDicEntry(){
        ArrayList<String> list = new ArrayList<>();
        for(int i =0; i <= 255; i++){
            list.add(""+i);
        }
        return list;
    }
    public void lzwCompressed(int[][] image){
        int n = image.length;
        int m = image[0].length;
        ArrayList<String> curRegSeq = new ArrayList<>();
        HashMap<String, Integer> dictionary = getDefaultDictionary();
        ArrayList<Integer> encodeOutput = new ArrayList<>();
        ArrayList<String> dicEntry = new ArrayList<>();
        curRegSeq.add(" ");
//        encodeOutput.add(-1);
//        dicEntry.add("-");
        String newSeq = " ";
        String curSeq = " ";
        int z = 0;
        int seqLoc = 255;
        for(int i = 0; i < n; i++){
            for(int j = 0; j <  m ;j++){
                newSeq = (curRegSeq.get(z)+"-"+image[i][j]).trim();
                curSeq = "-"+image[i][j];
                if(dictionary.containsKey(newSeq)){
                    curSeq = newSeq;
                    encodeOutput.add(-1);
                    dicEntry.add("-1");
                }
                else{
                    dictionary.put(newSeq, ++seqLoc);
                    encodeOutput.add(dictionary.get(curRegSeq.get(z)));
                    dicEntry.add(newSeq);
                }
                curRegSeq.add(curSeq);
                z++;
            }
        }
        encodeOutput.add(dictionary.get(curSeq));
        z = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j <  m ;j++){
                System.out.print(curRegSeq.get(z) + "\t\t"+image[i][j]);
                if(encodeOutput.get(z) == -1){
                    System.out.print("\t\t-\t\t-\t\t-");
                }
                else{
                    System.out.print("\t\t"+encodeOutput.get(z) +"\t\t"+ dictionary.get(dicEntry.get(z)) +"\t\t"+dicEntry.get(z));
                }
                System.out.print("\n-----------------------------------------------------------------------------------\n");
                
                z++;
                
            }
        }
        
        System.out.print(curSeq+"\t\t-\t\t"+encodeOutput.get(z));
        System.out.print("\n-----------------------------------------------------------------------------------\n");
        System.out.println("decoding of: ");
        for(int i: encodeOutput){
            System.out.print(i != -1? i+",":"");
            
        }
    }
}
