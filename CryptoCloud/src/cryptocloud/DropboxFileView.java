package cryptocloud;

/**
 *
 * @author angelukayetiu
 */

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;

public class DropboxFileView extends JPanel
                      implements TreeSelectionListener {
    boolean hasProxy = false;String password;
    public HttpRequestor getProxy(){
            String ip = "proxy7.upd.edu.ph";
            int port = 8080;

            final String authUser = "aptiu1";
            final String authPassword = password;

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
    private String accessToken = null;
    public String getAccessToken() throws DbxException, InterruptedException{
        if (accessToken==null)
            authenticate();
        return accessToken;
    }
    private DbxClient authenticate() throws DbxException, InterruptedException{
        
        DbxAppInfo appInfo = new DbxAppInfo("2gljsdvv0whija4", "kuw1l5rhux1q2pp");
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
            client = new DbxClient(config, accessToken);
            System.out.println("Linked account: " + client.getAccountInfo().displayName); 
        return client;
    }
    private JTree tree;
    private static boolean DEBUG = false;

    private DbxClient client;
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    private FileInfo recentValue;
    private DefaultTreeModel modelTree;

    public DropboxFileView() throws DbxException, InterruptedException {
        super(new GridLayout(1,0));
        DbxClient client1 = authenticate();
        this.client = client1;

        //Create the root node.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode(new FileInfo(client1.getAccountInfo().displayName, "/"));
        createNodes(top);
        modelTree = new DefaultTreeModel(top);
        modelTree.addTreeModelListener(new RefreshFolderListener());        
        
        //Create a tree that allows one selection at a time.
        tree = new JTree(modelTree);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setEditable(true);
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
        add(treeView);
    }
    
    public void refreshFolders() throws DbxException{
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode(new FileInfo(client.getAccountInfo().displayName, "/"));
        createNodes(top);
        modelTree = new DefaultTreeModel(top);
        modelTree.addTreeModelListener(new RefreshFolderListener());        
        tree.setModel(modelTree);
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

    void addNewFile(String uploadTo) {
/*        DefaultMutableTreeNode root = (DefaultMutableTreeNode) modelTree.getRoot();
        
        for (root.)*/
    }

    private static class RefreshFolderListener implements TreeModelListener {
        @Override
        public void treeNodesChanged(TreeModelEvent e) {    }
        
        @Override
        public void treeNodesInserted(TreeModelEvent e) {
        }
        
        @Override
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        
        @Override
        public void treeStructureChanged(TreeModelEvent e) {
        }

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
            if (!child.name.startsWith(".")){
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
    }
        
    private static void createAndShowGUI() throws DbxException, InterruptedException{
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
                
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowGUI();
                } catch (DbxException | InterruptedException ex) {
                    Logger.getLogger(DropboxFileView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}