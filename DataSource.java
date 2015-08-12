package assocrule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Peabody
 */
public class DataSource {
private String filePath;   
private File file;
int numTransactions;

    public DataSource(String filePath) {
        this.filePath = filePath;
        setNumTransactions();
    }


    public String getNextLine(BufferedReader br) {
        try {
            String nextLine = null;

            if ((nextLine = br.readLine()) != null) {

                return nextLine.trim();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Read Line Error!\n" + ex);
        }
        return null;
    }

    public int getNumTransactions() {
        return numTransactions;
    }

    private void setNumTransactions() {

        BufferedReader br;
        try {
            br = getReader(filePath);
            String nextLine = null;
            if ((nextLine = br.readLine()) != null) {
                numTransactions++;
            }
            closeReader(br);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Count Transaction Error!\n" + ex);

        }
      
    }

    public BufferedReader getReader(String path) {
        BufferedReader br = null;
        try {

        
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"File Not Found!\n" + e);
        }
        return br;
    }

    public void closeReader(BufferedReader br) {
        try {
            br.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Reader Not closed!\n" + ex);
        }
    }


}
