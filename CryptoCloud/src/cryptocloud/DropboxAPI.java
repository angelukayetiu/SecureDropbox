/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptocloud;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author issa
 */
public class DropboxAPI extends javax.swing.JFrame {
    
    String filename = null;
    File file = null;
    
    public DropboxAPI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        Upload = new javax.swing.JButton();
        Encrypt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Upload.setText("Upload");
        Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadActionPerformed(evt);
            }
        });

        Encrypt.setLabel("Encrypt");
        Encrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncryptActionPerformed(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        File.setText("File");

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        File.add(Open);

        jMenuBar1.add(File);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Encrypt)
                        .addGap(38, 38, 38)
                        .addComponent(Upload)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Upload)
                    .addComponent(Encrypt))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadActionPerformed
       
    }//GEN-LAST:event_UploadActionPerformed

    private void EncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncryptActionPerformed
        String policy = "student and comsci";
        String[] attributes = {"comsci_student", "Cavite"};
        final String APP_KEY = "vr9ab1d9xftq0no";
        final String APP_SECRET = "rzecxkjqqwev0wn";
        
        try {
            textArea.append("Preparing to encrypt file.");
                                   
            ExecuteCLT com = new ExecuteCLT();
            System.out.println("Setting up master key and public key..." + "\n");
            System.out.println(com.executeCommand("cpabe-setup") + "\n");
            System.out.println("Generating private key..." + "\n");
            System.out.println(com.keygenCommand("new_priv_key", attributes));
            System.out.println("Encrypting" + filename + "...");
            System.out.println(com.encryptCommand(filename, policy));
            System.out.println("File encryption complete. \n"
                    + "Preparing to upload file to Dropbox...");
            	
            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
            DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
                        
            String authorizeUrl = webAuth.start();
            System.out.println("1. Go to: " + authorizeUrl);
            System.out.println("2. Click \"Allow\" (you might have to log in first)");
            System.out.println("3. Copy the authorization code.");
            String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
            
            System.out.println("Code is" + code);
            DbxAuthFinish authFinish = webAuth.finish(code);
            String accessToken = authFinish.accessToken;
            
            DbxClient client = new DbxClient(config, accessToken);
            System.out.println("Linked account: " + client.getAccountInfo().displayName);
            
            File new_file = new File(filename + ".cpabe");
            FileInputStream inputStream = new FileInputStream(new_file);
            try {
                DbxEntry.File uploadedFile = client.uploadFile(filename + ".cpabe",
                    DbxWriteMode.add(),new_file.length(), inputStream);
                System.out.println("Uploaded: " + uploadedFile.toString());
            } finally {
                inputStream.close();
            }
            
            //System.out.println(com.decryptCommand("new_priv_key", filename + ".cpabe"));
            
            
        } catch (IOException ex) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxException ex) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_EncryptActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            filename = file.getAbsolutePath();
            System.out.println("Opened file: " + filename + "\n");
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_OpenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DropboxAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DropboxAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DropboxAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DropboxAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DropboxAPI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Encrypt;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem Open;
    private javax.swing.JButton Upload;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
