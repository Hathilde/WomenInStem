import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JList;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class View implements ActionListener {

    JFrame frame;
    Container container;
    Font font;
    JTextField output;
    JPanel buttonPanel;

    public View() {
        frame = new JFrame("PseudoFlix");
        container = frame.getContentPane();
        font = new Font("Sans-Serif", Font.PLAIN, 60);
        output = new JTextField("0");
        buttonPanel = new JPanel();

        buildView();
    }


    private void buildView() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1650,1080);

        File folder = new File("filmplakater");
        File[] listOfFiles = folder.listFiles();
        DefaultListModel listModel = new DefaultListModel();

        Box box = Box.createVerticalBox();

        int count = 0;
        for (int i = 0; i < listOfFiles.length; i++) {
            String name = listOfFiles[i].toString();
            if (name.endsWith("jpg")) {
                ImageIcon ii = null;
                try {
                    ii = new ImageIcon(ImageIO.read(listOfFiles[i]));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JButton button = new JButton(ii);
                box.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(name);
                    }
                });
                frame.add(box);

            }

        }

        frame.setLocationByPlatform(true);

        //box.setLayoutOrientation(Box.VERTICAL_WRAP);

        //box.setVisibleRowCount(4);


        //frame.add(new JScrollPane(lsm));

        frame.setPreferredSize(new Dimension(1000, 800));

        frame.pack();
        //fixRowCountForVisibleColumns(lsm);
        makeMenuBar(frame);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.add(output, BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(4,4));


       /* ImageIcon icon = new ImageIcon("filmplakater/12 Angry Men.jpg");
        frame.add(new JLabel(icon));
        frame.pack();
        frame.setVisible(true);
        */
    }

    private static void fixRowCountForVisibleColumns(JList lsm) {
        int nCols = computeVisibleColumnCount(lsm);
        int nItems = lsm.getModel().getSize();

        // Compute the number of rows that will result in the desired number of
        // columns
        int nRows = nItems / nCols;
        if (nItems % nCols > 0) nRows++;
        lsm.setVisibleRowCount(nRows);
    }

    private static int computeVisibleColumnCount(JList lsm) {
        // It's assumed here that all cells have the same width. This method
        // could be modified if this assumption is false. If there was cell
        // padding, it would have to be accounted for here as well.
        int cellWidth = lsm.getCellBounds(0, 0).width;
        int width = lsm.getVisibleRect().width;
        return width / cellWidth;
    }

    public void actionPerformed() {
        //  System.out.println("You clicked the image" + event.getActionCommand());
        System.out.println("hej");
        JFrame jf = new JFrame("New Frame");
        jf.setSize(new Dimension(200,70));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    // MENU-BAR //
    private void makeMenuBar (JFrame frame) {
        frame.setJMenuBar(createMenuBar());
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        return menuBar;
    }
    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        editMenu.add(cutItem);
        JMenuItem copyItem = new JMenuItem("Copy");
        editMenu.add(copyItem);
        JMenuItem pasteItem = new JMenuItem("Paste");
        editMenu.add(pasteItem);
        return editMenu;
    }
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        fileMenu.add(newItem);
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        return fileMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setBackground(Color.red);

    }
}