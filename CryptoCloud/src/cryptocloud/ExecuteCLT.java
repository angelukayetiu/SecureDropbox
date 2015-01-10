/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptocloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author issa
 * @edited Angelu Kaye Tiu
 */
public class ExecuteCLT {
    
    private final String abeLocation;

    public ExecuteCLT() {
        this.abeLocation = System.getProperty("user.dir")+"/cpabe-0.11/";
    }
    
    // Executes bash commands in java
    // Used for executing CP-ABE Command Line Tool by Bethencourt.
    // See http://acsc.cs.utexas.edu/cpabe/ for more details.
    
    public String executeCommand(String command) throws IOException, InterruptedException{      
        StringBuffer output = new StringBuffer();
        Process p;
        
        try {
        //Initiates process
        p = Runtime.getRuntime().exec(abeLocation +command);
            
        BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader error = 
                        new BufferedReader(new InputStreamReader(p.getErrorStream()));
        
        //Read output of commands
        String line = "";           
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }   
        while ((line = error.readLine())!= null) {
            output.append(line + "\n");
        }
        
        //returns status code of commad; 0 if successful
        int status_code = p.waitFor();
        System.out.println(command + " exited with status code " + status_code);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return output.toString();
    }
    
    public String keygenCommand(String private_key, String[] attributes){
        StringBuffer output = new StringBuffer();
        String[] command = { abeLocation + "cpabe-keygen", "-o", private_key, "pub_key", "master_key"};
        String[] commandInput = new String[command.length + attributes.length];
        System.arraycopy(command, 0, commandInput, 0, command.length);
        System.arraycopy(attributes, 0, commandInput, command.length, attributes.length);
        
        /*for (int i = 0; i < commandInput.length; i++){
            System.out.println(commandInput[i]);
        }*/
        
        ProcessBuilder pb = new ProcessBuilder(commandInput);
                
        try{
        Process p = pb.start(); 
        
        BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader error = 
                        new BufferedReader(new InputStreamReader(p.getErrorStream()));
        
        //Read output of commands
        String line = "";           
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }   
        while ((line = error.readLine())!= null) {
            output.append(line + "\n");
        }
        
        //returns status code of commad; 0 if successful
        int status_code = p.waitFor();
        System.out.println("cpabe-keygen exited with status " + status_code);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    public String encryptCommand(String file, String policy){
        StringBuffer output = new StringBuffer();
        ProcessBuilder pb = new ProcessBuilder( abeLocation + "cpabe-enc", "pub_key", file, policy);
                
        try{
        Process p = pb.start(); 
        
        BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader error = 
                        new BufferedReader(new InputStreamReader(p.getErrorStream()));
        
        //Read output of commands
        String line = "";           
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }   
        while ((line = error.readLine())!= null) {
            output.append(line + "\n");
        }
        
        //returns status code of commad; 0 if successful
        int status_code = p.waitFor();
        System.out.println("cpabe-en exited with status " + status_code);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    public String decryptCommand(String priv_key, String enc_file){
        StringBuffer output = new StringBuffer();
        ProcessBuilder pb = new ProcessBuilder(abeLocation + "cpabe-dec", enc_file+"pubkey", priv_key, enc_file);
                
        try{
        Process p = pb.start(); 
        
        BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader error = 
                        new BufferedReader(new InputStreamReader(p.getErrorStream()));
        
        //Read output of commands
        String line = "";           
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }   
        while ((line = error.readLine())!= null) {
            output.append(line + "\n");
        }
        
        //returns status code of commad; 0 if successful
        int status_code = p.waitFor();
        System.out.println("cpabe-dec exited with status " + status_code);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    } 
}