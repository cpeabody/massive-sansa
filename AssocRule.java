package assocrule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.uncommons.maths.combinatorics.*;
/**
 * AssocRule
 * @author Peabody
 * @since Jul 22, 2014 
 */
public class AssocRule {

private final String filePath = "src//assocrule//data1.txt";
private final double minSup = 0.14;
private final double minConf = 0.90;
int numTrans = 0;
int numTransactions = 0;
    ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();
DecimalFormat d3 = new DecimalFormat("0.00#");
    public AssocRule() {
     BufferedReader reader = getReader(filePath);   
     Stack firstLine;

    while((firstLine = getLineStack(reader)) != null){
    addStackToMap(firstLine,map);
    }
    closeReader(reader);
numTransactions = numTrans;  
    cleanForSupport(map);
     printMap(map);
   

CombinationGenerator permGen = new CombinationGenerator(map.keySet(),2);
long x = permGen.getTotalCombinations();
System.out.println("Total Combinations: "+x);
String[][] combos = new String[(int)x][];

        for (int i = 0; i < combos.length; i++) {
            Object[] nextCombo = permGen.nextCombinationAsArray();
            String[] nextStringCombo = new String[]{(String)nextCombo[0],(String)nextCombo[1]};
        combos[i]= nextStringCombo;
        }

//        for (int i = 0; i < combos.length; i++) {           
//            System.out.println("["+combos[i][0]+" , "+combos[i][1]+"]");
//        }
        ConcurrentHashMap<String,Integer> doublesMap = new ConcurrentHashMap<>();
     
          for (int i = 0; i < combos.length; i++) { 
              int count = 0;
              String line = null;
              reader = getReader(filePath);          
              while((line = getNextLine(reader)) != null){
                    if(line.contains(combos[i][0]) && line.contains(combos[i][1])){
                      count++;         
                }
          }
              String doubleKey = combos[i][0]+" "+combos[i][1];   
              doublesMap.put(doubleKey,count);
              closeReader(reader);
          }
          
          printMap(doublesMap);
          cleanForSupport(doublesMap);
          System.out.println("\n=======\n");
          printMap(doublesMap);
          System.out.println("\n=======\n");
        System.exit(0);
    }

    private synchronized void addStackToMap(Stack firstLine,ConcurrentHashMap<String,Integer> map) {
        while(!firstLine.empty()){
            String next = (String)firstLine.pop();
            
            if(map.containsKey(next)){
                Integer n = map.remove(next) ;  
                n++;
                map.put(next,n);
            }else{
                map.put(next,1);
            }
            
        }
    }
    
    private synchronized void cleanForSupport(ConcurrentHashMap<String,Integer> map) {
    try {
        Thread.sleep(20);
    } catch (InterruptedException ex) {
        Logger.getLogger(AssocRule.class.getName()).log(Level.SEVERE,null,ex);
    }
        
        Iterator it = map.keySet().iterator();
        System.out.println("\n====\n"+numTransactions+"\n====\n");
       double highSup = 0.00;
       String highKey = "";

        while(it.hasNext()){   
           String k =(String)it.next();
            int val = map.get(k);            
            double sup = (double)val/(double)numTransactions;
           // System.out.println(k + " = " + d3.format(sup));
            if(sup < minSup){
            map.remove(k);
            }
            if(sup>highSup){
            highSup = sup;
            highKey = k;
            }
        }
        System.out.println("High Key: " + highKey+" \t Support: " + d3.format(highSup ));
    }

    private void printMap(ConcurrentHashMap<String,Integer> map) {
        Iterator it = map.keySet().iterator();
        
        while(it.hasNext()){
            
            String k = (String)it.next();
            System.out.println(k+" = " + (int)map.get(k));
            
        }
    }

 private Stack getLineStack(BufferedReader br){
 String line = null;
 Stack strStack = new Stack();
    if((line = getNextLine(br)) != null){
     int nextInt;
        String[] strA = line.split(" ",-1);
        
        for (int i = 0; i < strA.length; i++) {          
         String str = strA[i].replaceAll(" ","").trim();
         strStack.push(str);
 
         
        }
     return strStack;
     }
 return null;
 }   


private String getNextLine(BufferedReader br){
    try {
        String nextLine = null;
        
        if((nextLine = br.readLine()) != null){
            numTrans++;
             return nextLine.trim();
        }     
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null,"Read Line Error!\n" + ex);
    }
   return null;
}
private BufferedReader getReader(String path) {
        BufferedReader br = null;
        try {

            String currentLine;
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"File Not Found!\n" + e);
        }
        return br;
    }
private void closeReader(BufferedReader br){
    try {
        br.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null,"Reader Not closed!\n" + ex);
    }
}
private void testLines(BufferedReader reader) {
        String str1 = getNextLine(reader);
        String str2 = getNextLine(reader);
        
        System.out.println(str1);
        System.out.println(str2);
    }

    public static void main(String[] args) {
        AssocRule app = new AssocRule();
    }//end main

}//end Class
