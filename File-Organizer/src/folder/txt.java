/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author kevin
 */
public class txt extends Files{
    int wordCount;
    
    public txt(String t, String d, long s, String n) throws FileNotFoundException{
        super(t,d,s,n);
        countWord();
        
    }
    
    public void countWord() throws FileNotFoundException{
        FileReader fr = new FileReader("C:/Users/kevin/Desktop/test/" + name);
        Scanner sc = new Scanner(fr);
        int count=0;
        while(sc.hasNext()){
            sc.next();
            count++;
        }
        wordCount = count;
    }
}

