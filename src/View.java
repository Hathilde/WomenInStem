import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JList;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class View implements ActionListener {

    JFrame frame;
    Container container;
    Font font;
    JTextField output;
    JPanel buttonPanel;


    public List<Medier> getListOfAllMedia() {
        ReadData dataReader = new ReadData();
        dataReader.createSortedMediaObjectList();
        return dataReader.getSortedMediaObjects();

    }

    public List<String> returnListOfImages() {
        List<String> imgPathList = new ArrayList<>();

        for (Medier mediaObject : getListOfAllMedia()) {
                imgPathList.add(mediaObject.getImgPath());
            }

        return imgPathList;
    }
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

        buttonPanel.setPreferredSize(new Dimension(1000, 5000));
        buttonPanel.setLayout(new FlowLayout());
        makeMenuBar(frame);

        List<Medier> medier = getListOfAllMedia();
        int count = 0;

        for (int i = 0; i < medier.size(); i++) {
            String path = medier.get(i).getImgPath();
            if (path.endsWith("jpg")) {
                ImageIcon ii = null;
                try {
                    ii = new ImageIcon(ImageIO.read(new File(path)));

                } catch (IOException e) {
                    System.out.println(path);
                    throw new RuntimeException(e);
                }
                JButton button = new JButton();
                button.setIcon(ii);

                buttonPanel.add(button);

                // Gør knappen usynlig.
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                String title = medier.get(i).getTitle();
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDialog dialog = new JDialog(frame, title);

                        // create a label
                        JLabel label = new JLabel(title);

                        dialog.add(label);
                        try {
                            dialog.add(new JLabel(new ImageIcon(ImageIO.read(new File(path)))));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        // setsize of dialog
                        dialog.setSize(500, 500);

                        // set visibility of dialog
                        dialog.setVisible(true);
                    }
                });

            }

        }
        frame.getContentPane().add(new JScrollPane(buttonPanel));
        frame.pack();
        frame.setVisible(true);
    }


    // MENU-BAR //
    private void makeMenuBar (JFrame frame) {
        frame.setJMenuBar(createMenuBar());
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JButton favoritButton = new JButton("Mine Favoritter!!");
        menuBar.add(favoritButton);
        menuBar.add(createMedierMenu());
        menuBar.add(createGenreMenu());

        // TEST:
        menuBar.add(new JSeparator());
        menuBar.add(Box.createHorizontalGlue());
        JTextField txt;
        txt = new JTextField("   Søg her...  ",25);
        txt.setMaximumSize(txt.getPreferredSize());
        menuBar.add(txt);

        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TEKST : " + txt.getText());
            }
        });

        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                txt.setText("");
            }
        });
        return menuBar;

    }
    private JMenu createGenreMenu() {
        JMenu genreMenu = new JMenu("Genre");

        ReadData genreHalløj = new ReadData();
        genreHalløj.reader("./film.txt");
        genreHalløj.reader("./serier.txt");

        Arrays.toString(genreHalløj.getGenreArray());

        JMenuItem nyGenreItem;
        JMenuItem alleGenreItm = new JMenuItem("Alle");
        genreMenu.add(alleGenreItm);

        for (String currentGenre : genreHalløj.getGenreArray()) {
            nyGenreItem = new JMenuItem(currentGenre);
            genreMenu.add(currentGenre);
        }

        /*JMenuItem cutItem = new JMenuItem("Cut");
        genreMenu.add(cutItem);

        JMenuItem copyItem = new JMenuItem("Copy");
        genreMenu.add(copyItem);
        JMenuItem pasteItem = new JMenuItem("Paste");
        genreMenu.add(pasteItem); */

        return genreMenu;
    }
    private JMenu createMedierMenu() {
        JMenu medieMenu = new JMenu("Medier");
        JMenuItem newItem = new JMenuItem("Alle");
        medieMenu.add(newItem);
        JMenuItem openItem = new JMenuItem("Film");
        medieMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Serier");
        medieMenu.add(saveItem);

        return medieMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //frame.setBackground(Color.red);

    }

}