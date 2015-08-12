package bitcounting;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitCounting {

    int[] input; //LO HI X

    public BitCounting(int[] input) {
        this.input = input;
        getAppValue();
    }

    public int getAppValue() {
        int output = 0;
        for (int i = input[0]; i <= input[1]; i++) {
            int k = getKValue(i);
            if (k == input[2]) {
                output++;
            }
        }
        System.out.println("K = " + input[2] + "  ------> " + output);
        return output;
    }

    private int getKValue(int in) {
        int k = 0;
        while (in > 1) {
            in = getNumOneBits(in);
            //in = NumberOfSetBits(in);
            k++;
        }
        return k;
    }

    private int getNumOneBits(int in) {
        String binary = Integer.toBinaryString(in);
        int count = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }

        return count;
    }

//    private int NumberOfSetBits(int i) {
//        i = i - ((i >> 1) & 0x55555555);
//        i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
//        return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
//    }
    public static void main(String[] args) {

        String filePath = "src//files//BitCountInput.txt";
        FileInput fi = null;
        
        try {
            fi = new FileInput(filePath);
        } catch (IOException ex) {
            System.out.println("File Read Error!\n" + ex.getMessage());
            System.exit(1);
        }
        
        
        String inputStr = fi.getSingleString();
        
        System.out.println(inputStr);
        System.out.println("Number of lines:\t" + fi.getLineArray().length);
        
//        int[] test_0 = new int[]{31, 31, 3};
//        int[] test_1 = new int[]{31, 31, 1};
//        int[] test_2 = new int[]{27, 31, 1};
//        int[] test_3 = new int[]{27, 31, 2};
//        int[] test_4 = new int[]{1023, 1025, 1};
//        int[] test_5 = new int[]{1023, 1025, 2};
//
//        BitCounting app_0 = new BitCounting(test_0);
//        BitCounting app_1 = new BitCounting(test_1);
//        BitCounting app_2 = new BitCounting(test_2);
//        BitCounting app_3 = new BitCounting(test_3);
//        BitCounting app_4 = new BitCounting(test_4);
//        BitCounting app_5 = new BitCounting(test_5);
        
        
    }

}
