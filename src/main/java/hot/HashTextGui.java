package hot;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@DependsOn(value = {"fileSystemModel", "actionFacade"})
public class HashTextGui extends javax.swing.JFrame {

    @Inject
    private FileSystemModel fsModel;
    @Inject
    private ActionFacade actionFacade;
    @Inject
    private CheckReceiveTask checkReceiveTask; 
    @Inject
    @Qualifier("loggerBean")
    private Logger logBean;       

    @Value("${skin}")
    private String currentLAF;
    @Value("${top}")
    private String top;

    public static HashTextGui frame;
    private static Dimension frameDimension = new Dimension(640, 450);
    public ImageIcon FrameIcon = new ImageIcon(getClass().getResource("/img/SubFrameIcon.png"));

    public HashTextGui() {
        initComponents();
        this.setIconImage(FrameIcon.getImage());
    }

    @PostConstruct
    public void afterBirn() {
        System.out.println(currentLAF);
        this.bcomboDevice.setModel(new DefaultComboBoxModel<>(fsModel.getAllrootsString()));
        this.fsjTree.setModel(fsModel);
        this.fsjTree.setRootVisible(true);
        this.fsjTree.setShowsRootHandles(true);
        this.fsjTree.setEditable(false);
        this.setTitle(top);
        this.mainSplitPane.setDividerLocation(0.9);
        checkReceiveTask.receiveTaskChecker(outTextArea);
    }

    public void setLF(JFrame frame) {
        try {
            UIManager.setLookAndFeel(currentLAF);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logBean.log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topjToolBar = new javax.swing.JToolBar();
        bAbout = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jLabel2 = new javax.swing.JLabel();
        bcomboDevice = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        mainSplitPane = new javax.swing.JSplitPane();
        fsScrollPane = new javax.swing.JScrollPane();
        fsjTree = new javax.swing.JTree();
        imgPanel = new javax.swing.JPanel();
        imgScrollPane = new javax.swing.JScrollPane();
        outTextArea = new javax.swing.JTextArea();
        outTF = new javax.swing.JTextField();
        topjMenuBar = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        mExit = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        mAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HashText");
        setUndecorated(true);

        topjToolBar.setBorder(javax.swing.BorderFactory.createTitledBorder("Tools"));
        topjToolBar.setFloatable(false);

        bAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/24x24/info-book-green.png"))); // NOI18N
        bAbout.setToolTipText("About");
        bAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAboutActionPerformed(evt);
            }
        });
        topjToolBar.add(bAbout);
        topjToolBar.add(jSeparator2);

        jLabel2.setText("Device = ");
        topjToolBar.add(jLabel2);

        bcomboDevice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bcomboDevice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bcomboDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcomboDeviceActionPerformed(evt);
            }
        });
        topjToolBar.add(bcomboDevice);
        topjToolBar.add(jSeparator4);

        getContentPane().add(topjToolBar, java.awt.BorderLayout.NORTH);

        mainSplitPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Viewer"));
        mainSplitPane.setDividerLocation(99);

        fsScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Files"));
        fsScrollPane.setToolTipText("Use Mouse Click or UP/DOWN keys");
        fsScrollPane.setName("File"); // NOI18N

        fsjTree.setModel(fsModel);
        fsjTree.setToolTipText("Use Mouse Click or UP/DOWN keys");
        fsScrollPane.setViewportView(fsjTree);

        mainSplitPane.setLeftComponent(fsScrollPane);

        imgPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tasks"));

        outTextArea.setColumns(20);
        outTextArea.setRows(5);
        imgScrollPane.setViewportView(outTextArea);

        javax.swing.GroupLayout imgPanelLayout = new javax.swing.GroupLayout(imgPanel);
        imgPanel.setLayout(imgPanelLayout);
        imgPanelLayout.setHorizontalGroup(
            imgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        imgPanelLayout.setVerticalGroup(
            imgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        mainSplitPane.setRightComponent(imgPanel);

        getContentPane().add(mainSplitPane, java.awt.BorderLayout.CENTER);

        outTF.setEditable(false);
        outTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outTF.setBorder(javax.swing.BorderFactory.createTitledBorder("Out TF"));
        getContentPane().add(outTF, java.awt.BorderLayout.SOUTH);
        outTF.getAccessibleContext().setAccessibleName("Information");

        mFile.setText("File");

        mExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/quit.png"))); // NOI18N
        mExit.setText("Exit");
        mExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExitActionPerformed(evt);
            }
        });
        mFile.add(mExit);

        topjMenuBar.add(mFile);

        mHelp.setText("Info");

        mAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/16x16/help-green-16.png"))); // NOI18N
        mAbout.setText("About");
        mAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAboutActionPerformed(evt);
            }
        });
        mHelp.add(mAbout);

        topjMenuBar.add(mHelp);

        setJMenuBar(topjMenuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAboutActionPerformed
        actionFacade.about(frame);
    }//GEN-LAST:event_bAboutActionPerformed

    private void mAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAboutActionPerformed
        actionFacade.about(frame);
    }//GEN-LAST:event_mAboutActionPerformed

    private void mExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExitActionPerformed
            System.exit(0);
    }//GEN-LAST:event_mExitActionPerformed

    private void bcomboDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcomboDeviceActionPerformed
        fsModel.setRoot(fsModel.getAllroots()[bcomboDevice.getSelectedIndex()].getPath());
        fsjTree.setModel(fsModel);
        frame.outTF.setText("");
        SwingUtilities.updateComponentTreeUI(fsjTree);
    }//GEN-LAST:event_bcomboDeviceActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
                // Shutdown Spring container gracefully in non-web applications !!
                AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
                //ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
                ctx.registerShutdownHook();
                // app runs here...
                // main method exits, hook is called prior to the app shutting down...
                // define @PreDestroy methods for your beans !!! - it is called before close App !!! 
                System.out.println("main potok = " + Thread.currentThread().getName());
                System.out.println(" CPU cores = 0 - " + ForkJoinPool.getCommonPoolParallelism());                  
                frame = ctx.getBean(HashTextGui.class);
                frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                frame.setLF(frame);
                frame.setSize(frameDimension);
                frame.setMinimumSize(frameDimension);
                frame.setAlwaysOnTop(false);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAbout;
    public javax.swing.JComboBox<String> bcomboDevice;
    private javax.swing.JScrollPane fsScrollPane;
    private javax.swing.JTree fsjTree;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JScrollPane imgScrollPane;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JMenuItem mAbout;
    private javax.swing.JMenuItem mExit;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mHelp;
    private javax.swing.JSplitPane mainSplitPane;
    public static javax.swing.JTextField outTF;
    public javax.swing.JTextArea outTextArea;
    private javax.swing.JMenuBar topjMenuBar;
    private javax.swing.JToolBar topjToolBar;
    // End of variables declaration//GEN-END:variables
}
