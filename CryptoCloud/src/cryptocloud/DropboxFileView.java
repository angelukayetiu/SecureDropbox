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
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

/**
 * This application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DropboxFileView extends JPanel
                      implements TreeSelectionListener {

    private static DbxClient authenticate() throws DbxException, InterruptedException{
        
        DbxAppInfo appInfo = new DbxAppInfo("icviln0csjt8a7d", "hedsgcy80purr59");
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
        String accessToken = "47WOsIRFKIsAAAAAAAAFy4KPfef95PDgRfABstggWX6ElA4dmOMV6KyAd1_qrMIW";
        DbxClient client1 = new DbxClient(config, accessToken);
        System.out.println("Linked account: " + client1.getAccountInfo().displayName); 
        return client1;
    }
    private JTree tree;
    private static boolean DEBUG = false;

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    private DbxClient client;
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    private FileInfo recentValue;
    
/*    public DropboxFileView() throws DbxException{
       super(new GridLayout(1,0));
        //Create the root node.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("root folder");
        createNodes(top);
        System.out.println("finished creating foldertree");
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
        add(treeView);        
    }
*/    
    public DropboxFileView() throws DbxException, InterruptedException {
        super(new GridLayout(1,0));
        DbxClient client1 = authenticate();
        this.client = client1;

        //Create the root node.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode(new FileInfo(client1.getAccountInfo().displayName, "/"));
        createNodes(top);
        recentValue = null;
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
        add(treeView);
    }

    /** Required by TreeSelectionListener interface. */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;
        Object nodeInfo = node.getUserObject();
        FileInfo file = (FileInfo)nodeInfo;
        System.out.println(file.toString());
        
        recentValue = file;
    }

    public DbxEntry getValue(){
        return recentValue==null? null:recentValue.child;
    }
    
    private class FileInfo {
        public DbxEntry child;
        String name, path;
        
        public FileInfo(DbxEntry child) {
            this.child = child;
            this.name = child.name;
            this.path = child.path;
        }

        private FileInfo(String name, String path) {
            this.child = null;
            this.name = name;
            this.path = path;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private void createNodes(DefaultMutableTreeNode top) throws DbxException {
        Object nodeInfo = top.getUserObject();
        FileInfo folder = (FileInfo)nodeInfo;
        System.out.println(folder.toString());
        
        DbxEntry.WithChildren listing = client.getMetadataWithChildren(folder.path);
        System.out.println("Files in the path: " +folder.path);
        for (DbxEntry child : listing.children) {
            System.out.println("	" + child.name);
            if(child.isFile()){
                FileInfo file = new FileInfo(child);
                DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
                top.add(fileNode);
            } else{
                FileInfo subFolder = new FileInfo(child);
                DefaultMutableTreeNode subFolderNode = new DefaultMutableTreeNode(subFolder);
                top.add(subFolderNode);
                createNodes(subFolderNode);
           }
        }
    }
        
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() throws DbxException, InterruptedException {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new DropboxFileView());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (DbxException ex) {
                    Logger.getLogger(DropboxFileView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DropboxFileView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}