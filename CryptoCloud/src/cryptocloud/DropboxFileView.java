package cryptocloud;

/**
 *
 * @author angelukayetiu
 */

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

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

    private DbxClient client;
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    private FileInfo recentValue;
    
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
        tree.setEditable(true);
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
        add(treeView);
    }
    
    public void refreshFolders() throws DbxException{
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getModel().getRoot();
        
    }
    
    public void checkChildren(DefaultMutableTreeNode top)throws DbxException {
 /*       Object nodeInfo = top.getUserObject();
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
        }*/
        //TODO refresh file list configuration
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
        
    private static void createAndShowGUI() throws DbxException, InterruptedException {
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