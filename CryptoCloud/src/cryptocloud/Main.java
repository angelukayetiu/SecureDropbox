/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptocloud;
import java.io.IOException;

/**
 *
 * @author issa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        DropboxAPI fbapi = new DropboxAPI();
        fbapi.setVisible(true); 
                                              
        /*ExecuteCLT com = new ExecuteCLT();
        String[] attributes = {"comsci_student", "gwa<1", "Cavite"};
        System.out.println(com.executeCommand("cpabe-setup"));   
        System.out.println(com.keygenCommand("issa_priv_key", attributes));
        System.out.println(com.encryptCommand("test.txt", "comsci_student and gwa<3"));
        System.out.println(com.executeCommand("ls"));
        System.out.println(com.decryptCommand("issa_priv_key", "test.txt.cpabe"));
        System.out.println(com.executeCommand("ls")); 
        */
    } 
}
