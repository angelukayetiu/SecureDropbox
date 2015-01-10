/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptocloud;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author issa
 */
public class DropboxAPI extends javax.swing.JFrame {
    FileComposition fileComposition;
    AttributeManager attributeManager = null;
    String filename = null;
    File file = null;
    private final String APP_KEY = "2gljsdvv0whija4";
    private final String APP_SECRET = "kuw1l5rhux1q2pp";
    private String accessToken; // TODO try make accessToken global to lessen authentication steps for upload and download
    public DropboxAPI() {
        initComponents();
        accessToken = null;
        attributeManager = new AttributeManager();
        attributeManager.setVisible(false);
        
        
        System.out.println(System.getProperty("user.dir"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        Upload = new javax.swing.JButton();
        Encrypt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        downloadButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Attributes = new javax.swing.JMenu();

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

        downloadButton.setText("Download");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        File.setText("File");

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        File.add(Open);

        Attributes.setText("Manage Attributes");
        Attributes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttributesActionPerformed(evt);
            }
        });
        File.add(Attributes);

        jMenuBar1.add(File);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(downloadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(Encrypt)
                    .addComponent(downloadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadActionPerformed
        try {
            System.out.println("Prepare to upload...");
            upload_file(filename, filename);
            upload_file("pub_key", fileComposition.toHiddenString()+"pubkey");
            upload_file("master_key", fileComposition.toHiddenString()+"masterkey");
            System.out.println("files uploaded.");
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(this, "File does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
           Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Check Internet Connection", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (DbxException ex) {
            JOptionPane.showMessageDialog(this, "Check Internet Connection", "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_UploadActionPerformed

    private void EncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncryptActionPerformed
        String policy = "student and comsci";
        String[] attributes = {"comsci_student", "Cavite"};
        
        try {
            textArea.append("Preparing to encrypt file...");
                                   
            ExecuteCLT com = new ExecuteCLT();
            System.out.println("Setting up master key and public key..." + "\n");
            System.out.println(com.executeCommand("cpabe-setup") + "\n");
            System.out.println("Generating private key..." + "\n");
            System.out.println(com.keygenCommand("new_priv_key", attributes));
            System.out.println("Encrypting" + filename + "...");
            policy = JOptionPane.showInputDialog(this, "Input policy of the file:", "Policy Information", JOptionPane.PLAIN_MESSAGE);
            System.out.println(com.encryptCommand(filename, policy));
            System.out.println("File encryption complete. \n");
            	
            textArea.append("file encrypted.\n\n");
           
            fileComposition = new FileComposition(filename+".cpabe");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Check filename. File does not exists.", "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Check Internet Connection", "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_EncryptActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            filename = file.getAbsolutePath();
            fileComposition = new FileComposition(filename);
            System.out.println("Opened file: " + filename + "\n");
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_OpenActionPerformed
    
    private DbxClient authenticate() throws DbxException, InterruptedException{
        
        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

/*            String authorizeUrl = webAuth.start();
            System.out.println("1. Go to: " + authorizeUrl);
            System.out.println("2. Click \"Allow\" (you might have to log in first)");
            System.out.println("3. Copy the authorization code.");
            String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
            
            System.out.println("Code is" + code);
            DbxAuthFinish authFinish = webAuth.finish(code);
            //String accessToken = authFinish.accessToken;
*/          
        String accessToken = "IS3HsK4J0YUAAAAAAAAM2q2eqeU2e4toyPAy6-U6YD08fhlaT9GWxQ8Y8jTIzzC3";
        DbxClient client = new DbxClient(config, accessToken);
        System.out.println("Linked account: " + client.getAccountInfo().displayName); 
        return client;
    }
    
    /* open filechoose where to save the file*/
    private String downloadFileLocation(String fileName){
        String downloadedFilename = null;
        fileChooser.setSelectedFile(new File(fileName));
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            downloadedFilename = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Save file in: " + downloadedFilename + "\n");
            } else {
                System.out.println("File access cancelled by user.");
            }
        return downloadedFilename;
    }
    
        
    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        String downloadFrom = JOptionPane.showInputDialog(this, "Download Path", "Please input the download path:", JOptionPane.PLAIN_MESSAGE);
        FileComposition downloadFromFile = new FileComposition(downloadFrom);
        String downloadTo = null;
        try {
            textArea.append("Preparing to download a file to decrypt.\n\n");
            downloadTo = downloadFileLocation(downloadFrom.substring(downloadFrom.lastIndexOf("/")+1));            
            System.out.println("Download file save in: "+downloadTo);

            /* download_file (from, to) */
            download_file(downloadFrom, downloadTo);
            download_file(downloadFromFile.toHiddenString()+"pubkey", downloadTo+"pubkey");
            
            /* decrypt the file*/
            decryptFile(downloadTo, "new_priv_key");
        } catch (IOException ex){
            JOptionPane.showMessageDialog(this, "Check download pathname", "Invalid FileName", JOptionPane.ERROR_MESSAGE);
            try{
                (new File(downloadTo)).delete();
            } catch(Exception ex1){
                System.err.println(downloadTo+ " DNE");
            }
        } catch( DbxException | InterruptedException ex3) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex3);
            JOptionPane.showMessageDialog(this, "Check Internet Connection", "ERROR", JOptionPane.ERROR_MESSAGE);
            try{
                (new File(downloadTo)).delete();
            } catch(Exception ex1){
                System.err.println(downloadTo+ " DNE");
            }
        } finally {
            try{
                (new File(downloadTo+"pubkey")).delete();
            } catch(Exception ex2){
                System.err.println(downloadTo+"pubkey DNE");
            }
        }

    }//GEN-LAST:event_downloadButtonActionPerformed

    private void AttributesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttributesActionPerformed
        attributeManager.setVisible(true);
    }//GEN-LAST:event_AttributesActionPerformed

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
    private javax.swing.JMenu Attributes;
    private javax.swing.JButton Encrypt;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem Open;
    private javax.swing.JButton Upload;
    private javax.swing.JButton downloadButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    private void decryptFile(String filename, String privkeyLocation) {
        ExecuteCLT com = new ExecuteCLT();
        System.out.println("Decrypting" + filename + "...");
        System.out.println(com.decryptCommand(privkeyLocation,filename));
        System.out.println("File decryption complete. \n"
            + "You can now view the file in "+filename);
    }

    private void upload_file(String uploadFrom, String uploadTo) throws FileNotFoundException, DbxException, InterruptedException, IOException {
        File uploadFile = new File(uploadFrom);
        try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
            DbxEntry.File uploadedFile = authenticate().uploadFile(uploadTo,
                    DbxWriteMode.add(),uploadFile.length(), inputStream);
                System.out.println("Uploaded: " + uploadedFile.toString());
            }
    }

    private void download_file(String downloadFrom, String downloadTo) throws FileNotFoundException, DbxException, InterruptedException, IOException {
        try (FileOutputStream outputStream = new FileOutputStream(downloadTo)) {
                DbxEntry.File downloadedFile = authenticate().getFile(downloadFrom, null,
                    outputStream);
            if(downloadedFile !=null)
                System.out.println("Metadata: " + downloadedFile.toString());
            else throw new FileNotFoundException();
            } 
    }
    
    private class FileComposition{
        String path; // /user/folder/
        String filename; // filename
        String extension; // .extension
        public FileComposition(String file){
            path = file.substring(0, file.lastIndexOf("/")+1);
            filename = file.substring(file.lastIndexOf("/")+1, file.lastIndexOf("."));
            extension = file.substring(file.lastIndexOf("."));
            
            System.out.println("path "+path);
            System.out.println("filename "+filename);
            System.out.println("extension "+extension);
        }
        public String toString(){
            return path+filename+extension;
        }
        public String toHiddenString(){
            return path+"."+filename+extension;
        }
    }
}
