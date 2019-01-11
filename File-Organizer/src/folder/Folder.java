/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 *
 * @author kevin
 */


public class Folder {
    int numFiles;
    String sortType;
    String directoryLocation;
    String [] fileNames;
    long [] fileSizes;
    long maxSize;
    long minSize;
    String [] fileTypes;
    String [] fileDate;
    
    public Folder(int n, String s, String loc){
        scanFolder(loc);
        sortType = s;
    }
    
    //scans through the folder to obtain information about the files inside
    public void scanFolder(String directoryName){
        
        File directory = new File(directoryName);
        
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int count = 0;
        for (File file : fList){
            count ++;
            //TESTING
        }
        numFiles = count;
        
        //get all the files names from the directory
        fileNames = new String [numFiles];
        
        for (int i = 0; i <numFiles; i ++){
            fileNames[i] = fList[i].getName();
        } 
        
        //get all the files size from the diretory
        fileSizes = new long [numFiles];
        
        for (int i = 0; i <numFiles; i ++){
            fileSizes[i] = fList[i].length();
        } 
        maxSize = fileSizes[0];
        for (int i = 0; i < numFiles; i++){
            if (fileSizes[i] > maxSize){
                maxSize = fileSizes[i];
            }
        }
        maxSize = maxSize/1024;
        
        minSize = fileSizes[0];
        for (int i =0 ;i < numFiles; i++){
            if (fileSizes[i] < minSize){
                minSize = fileSizes[i];
            }       
        }
        minSize = minSize/1024;
    }
    
    //creates different folders based on the user's input
    public void createFolder(String t, String directoryName){
        int numFolders = 5;
        String [] folderNames;
        
        //sort by names
        if (t.equalsIgnoreCase("n")){
            numFolders = 5;
            folderNames = new String [numFolders];
            folderNames[0] = "A-G";
            folderNames[1] = "H-N";
            folderNames[2] = "O-T";
            folderNames[3] = "U-Z";
            folderNames[4] = "0-9";
        }
        
        //sort by size
        else if(t.equalsIgnoreCase("s")){
            numFolders = 4;
            folderNames = new String [numFolders];
            long sizeRange = maxSize - minSize;
            int r1 = (int)(minSize+sizeRange/4);
            int r2 = (int)(minSize+sizeRange/2);
            int r3 = (int)(maxSize-sizeRange/4);
            folderNames[0] = "0KB-"+r1+"KB";
            folderNames[1] = r1+"KB-"+r2+"KB";
            folderNames[2] = r2+"KB-"+r3+"KB";
            folderNames[3] = r3+"KB-"+maxSize+"KB";
        }
        
        //sort by type
        else if(t.equalsIgnoreCase("t")){
            int typeCount = 0;
            fileTypes = new String [numFiles];
            List<String> typeList = Arrays.asList(fileTypes);
            for (int i = 0; i < numFiles; i++){
                int index = fileNames[i].indexOf(".");
                if (!typeList.contains(fileNames[i].substring(index))){
                    fileTypes[typeCount] = fileNames[i].substring(index);
                    typeCount++;
                }
            }
            numFolders = typeCount;
            System.out.println(numFolders);
            folderNames = new String [numFolders];
            System.arraycopy(fileTypes, 0, folderNames, 0, numFolders);
            for (int j = 0; j <numFolders; j ++){
                System.out.println(folderNames[j]);
            }
            
        }
        
        //sort by date
        else if(t.equalsIgnoreCase("d")){
            SimpleDateFormat sdf = new SimpleDateFormat("MM yyyy");
            File directory = new File(directoryName);
            File[] fList = directory.listFiles();
            int i = 0;
            int dateCount = 0;
            fileDate = new String[numFiles];
            List<String> dateList = Arrays.asList(fileDate);
            for (File file : fList){
                if(!dateList.contains(sdf.format(file.lastModified()))){
                    fileDate[dateCount] = sdf.format(file.lastModified());
                    dateCount++;
                }
                i++;
            }
            numFolders = dateCount;
            folderNames = new String [numFolders];
            System.arraycopy(fileDate, 0, folderNames, 0, numFolders);
        }
        
        else{
            folderNames = new String [4];
        }
        
        //creating folders
        for (int i = 0; i < numFolders; i ++ ){
            new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/"+folderNames[i]).mkdir();
        }
    }
    
    public void sortByDate(String directoryName){
        
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        SimpleDateFormat sdf = new SimpleDateFormat("MM yyyy");
        for (File file :fList){
            file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/"+sdf.format(file.lastModified())+"/"+file.getName()));
        }
    }
    
    public void sortByType(String directoryName){
        
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file :fList){
            int index = file.getName().indexOf(".");
            file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/"+file.getName().substring(index)+"/"+file.getName()));
        }
    }
    
    public void sortByName(String directoryName){
        
        File directory = new File (directoryName);
        File[] fList = directory.listFiles();
        for (File file :fList){
            char name = file.getName().charAt(0);
            if ('a' <= name && name <= 'g'){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/A-G/"+file.getName()));
            }
            else if ('h' <= name && name <= 'n'){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/H-N/"+file.getName()));
            }
            else if ('o' <= name && name <= 't'){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/O-T/"+file.getName()));
            }
            else if ('u' <= name && name <= 'z'){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/U-Z/"+file.getName()));
            }
            else{
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/0-9/"+file.getName()));
            }
        }
    }
    
    public void soryBySize(String directoryName){
        
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        long sizeRange = maxSize - minSize;
        int r1 = (int)(minSize+sizeRange/4);
        int r2 = (int)(minSize+sizeRange/2);
        int r3 = (int)(maxSize-sizeRange/4);    
        
        for (File file :fList){
            
            long size = file.length()/1024;
            
            if (0 <= size && size < r1){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/0KB-"+r1+"KB"+"/"+file.getName()));
            }
            else if (r1 <= size && size < r2){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/"+r1+"KB-"+r2+"KB"+"/"+file.getName()));
            }
            else if (r2 <= size && size < r3){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/"+r2+"KB-"+r3+"KB"+"/"+file.getName()));
            }
            else if (r3 <= size && size <= maxSize){
                file.renameTo(new File("C:/Users/"+System.getProperty("user.name")+
                    "/Desktop/testResult/"+r3+"KB-"+maxSize+"KB"+"/"+file.getName()));
            }   
        }
    }
    
    
}
