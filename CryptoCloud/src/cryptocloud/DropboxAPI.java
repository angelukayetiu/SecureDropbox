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
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;


/**
 *
 * @author issa
 * @author angelu kaye tiu
 */
public class DropboxAPI extends javax.swing.JFrame {
    FileComposition fileComposition;
    AttributeManager attributeManager;
    String filename = null;
    File file = null;
    JTree dropboxFileList;
    boolean hasProxy = false;
    private final String APP_KEY = "2gljsdvv0whija4";
    private final String APP_SECRET = "kuw1l5rhux1q2pp";
    private String accessToken; // TODO try make accessToken global to lessen authentication steps for upload and download
    public DropboxAPI() {
        initComponents();
        accessToken = null;
        attributeManager = new AttributeManager();
        attributeManager.setVisible(false);
        constructFileTree();
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
        folderTreeView = new javax.swing.JPanel();
        try {
            dropboxFileView1 = new cryptocloud.DropboxFileView();
        } catch (com.dropbox.core.DbxException e1) {
            e1.printStackTrace();
        } catch (java.lang.InterruptedException e2) {
            e2.printStackTrace();
        }
        label1 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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

        downloadButton.setText("Download");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        folderTreeView.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                folderTreeViewComponentAdded(evt);
            }
        });

        javax.swing.GroupLayout folderTreeViewLayout = new javax.swing.GroupLayout(folderTreeView);
        folderTreeView.setLayout(folderTreeViewLayout);
        folderTreeViewLayout.setHorizontalGroup(
            folderTreeViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        folderTreeViewLayout.setVerticalGroup(
            folderTreeViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label1.setText("Console:");

        jButton1.setText("Manage Attributes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh folder");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        File.setText("File");

        Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(downloadButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(folderTreeView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(dropboxFileView1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(Encrypt)
                                .addGap(54, 54, 54)
                                .addComponent(Upload)))))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dropboxFileView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(folderTreeView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(downloadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Encrypt)
                            .addComponent(Upload))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadActionPerformed
        try {
            System.out.println("Prepare to upload...");
            System.out.println(filename);
            upload_file(fileComposition.toString(), fileComposition.toString());
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
    
    public HttpRequestor getProxy(){
            String ip = "proxy7.upd.edu.ph";
            int port = 8080;

            final String authUser = "aptiu1";
            final String authPassword = "";

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(authUser, authPassword.toCharArray());
                }
            });

            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,port));


            HttpRequestor req = new StandardHttpRequestor(proxy);
            return req;
    }    
    private DbxClient authenticate() throws DbxException, InterruptedException{
            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
            DbxRequestConfig config;
            //check for proxy code
            if(hasProxy){
                HttpRequestor requ = getProxy();
                config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString(),requ);
            }else
                config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        if (accessToken == null){            

            //for authentication
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);        
            String authorizeUrl = webAuth.start();
            String message = "1. Go to: " + authorizeUrl+"\n";
            message += "2. Click \"Allow\" (you might have to log in first)\n";
            message += "3. Copy the authorization code.\n";
            System.out.println(message);
            String code = JOptionPane.showInputDialog(this, message, "AcccessToken", JOptionPane.PLAIN_MESSAGE);
            if (code.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Authentication Fails", "Error", JOptionPane.ERROR_MESSAGE);
            } else{
                System.out.println("Code is" + code);
                DbxAuthFinish authFinish = webAuth.finish(code);
                accessToken = authFinish.accessToken;
            }
//        String accessToken = "47WOsIRFKIsAAAAAAAAFy4KPfef95PDgRfABstggWX6ElA4dmOMV6KyAd1_qrMIW";
        }
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
        
        String downloadFrom = getDropboxFilePath();
        if (downloadFrom!=null){
            FileComposition downloadFromFile = new FileComposition(downloadFrom);
            String downloadTo = null;
            try {
                textArea.append("Preparing to download a file to decrypt.\n\n");
                downloadTo = downloadFileLocation(downloadFrom.substring(downloadFrom.lastIndexOf("/")+1));            
                System.out.println("Download file save in: "+downloadTo);

                /* download_file (from, to) */
                download_file(downloadFrom, downloadTo);
                if(downloadFromFile.extension.contains("cpabe")){
                    
                    download_file(downloadFromFile.toHiddenString()+"pubkey", downloadTo+"pubkey");

                    boolean decrypted = false;
                    /* decrypt the file*/
                    System.out.println("Files in the folder:");
                    //Get all private key in folder
                    List<String> result = new ArrayList<>();
                    File[] files = new File(downloadTo.substring(0, downloadTo.lastIndexOf("/"))).listFiles();
                    System.out.println("Files in the folder:"+files.toString());
                    for (File subFile : files) {
                        if (subFile.isFile() && subFile.getName().endsWith(".cpabeprivkey")) {
                            if (decryptFile(downloadTo, subFile.getAbsolutePath())){
                                decrypted = true; break;
                            }
                        }
                    }
                    if (decrypted)
                        JOptionPane.showMessageDialog(this, "File decrypted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    else{
                        JOptionPane.showMessageDialog(this, "File decryption unsuccessful.", "Error", JOptionPane.ERROR_MESSAGE);                        
                       (new File(downloadTo)).delete();
                        
                    }
                }
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
                    if (downloadFromFile.extension.contains("cpabe"))
                        (new File(downloadTo+"pubkey")).delete();
                } catch(Exception ex2){
                    System.err.println(downloadTo+"pubkey DNE");
                }
            }
        }
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void folderTreeViewComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_folderTreeViewComponentAdded
    }//GEN-LAST:event_folderTreeViewComponentAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        attributeManager.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            dropboxFileView1.refreshFolders();
        } catch (DbxException ex) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Check Internet Connection", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void constructFileTree(){
/*        try{
            JPanel jpanelTree = new DropboxFileView(authenticate());
            folderTreeView.add(jpanelTree);
            
//            jTree1.setModel(listing);
            
        } catch (InterruptedException|DbxException ex) {
            Logger.getLogger(DropboxAPI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Check Internet Connection", "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
*/
    }
    
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
    private javax.swing.JButton downloadButton;
    private cryptocloud.DropboxFileView dropboxFileView1;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JPanel folderTreeView;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    private boolean decryptFile(String filename, String privkeyLocation) {
        boolean success;
        ExecuteCLT com = new ExecuteCLT();
        System.out.println("Decrypting" + filename + "...with "+privkeyLocation );                
        success = com.decryptCommand(privkeyLocation,filename);        
        fileComposition = new FileComposition(filename);
        return success;
    }

    private void upload_file(String uploadFrom, String uploadTo) throws FileNotFoundException, DbxException, InterruptedException, IOException {
        File uploadFile = new File(uploadFrom);
        try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
            DbxEntry.File uploadedFile = authenticate().uploadFile(uploadTo,
                    DbxWriteMode.add(),uploadFile.length(), inputStream);
                System.out.println("Uploaded: " + uploadedFile.toString());
            dropboxFileView1.addNewFile(uploadTo);
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

    private String getDropboxFilePath() {
        DbxEntry entry = dropboxFileView1.getValue();
        if (entry!=null && entry.isFile())
            return entry.path;
        JOptionPane.showMessageDialog(this, "Please select a file to download", "ERROR", JOptionPane.ERROR_MESSAGE);
        return null;
    }

}
