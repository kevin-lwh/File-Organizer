/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;



/**
 *
 * @author kevin
 */
public class MP3 extends Files {
    int length;
    
    public MP3(String t, String d, long s, String n, int l){
        super(t,d,s,n);
        length = l;
    }
}
