/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptocloud;

/**
 *
 * @author angelukayetiu
 */
public class FileComposition{
    String path; // /user/folder/
    String filename; // filename
    String extension; // .extension
    public FileComposition(String file){
        change(file);
        System.out.println("path "+path);
        System.out.println("filename "+filename);
        System.out.println("extension "+extension);
    }
    @Override
    public String toString(){
        return path+filename+extension;
    }
    public void change(String file){
        path = file.substring(0, file.lastIndexOf("/")+1);
        filename = file.substring(file.lastIndexOf("/")+1);
        if(filename.contains(".")){
            filename = file.substring(file.lastIndexOf("/")+1, file.lastIndexOf("."));
            extension = file.substring(file.lastIndexOf("."));
        } else extension = "";
    }
    public String toHiddenString(){
        return path+"."+filename+extension;
    }
}