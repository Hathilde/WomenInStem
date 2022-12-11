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

    boolean alleBoolean;
    boolean filmBoolean;
    boolean serieBoolean;

    public List<Medier> getListOfAllMedia() {
        ReadData dataReader = new ReadData();
        dataReader.createSortedMediaObjectList();

        if (filmBoolean == true) {
        return dataReader.getSortedFilmObjects();

        } else if (serieBoolean == true) {
        return dataReader.getSortedSerierObjects();

        } else {
            return dataReader.getSortedMediaObjects();
        }

    }

    public List<String> returnListOfImages() {
        List<String> imgPathList = new ArrayList<>();

        for (Medier mediaObject : getListOfAllMedia()) {
                imgPathList.add(mediaObject.getImgPath());
            }

        return imgPathList;
    }
    public View() {
        alleBoolean = true;
        filmBoolean = false;
        serieBoolean = false;

        frame = new JFrame("PseudoFlix");
        container = frame.getContentPane();
        font = new Font("Sans-Serif", Font.PLAIN, 60);
        output = new JTextField("0");
        buttonPanel = new JPanel();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1650,1080);

        buttonPanel.setPreferredSize(new Dimension(1000, 5000));
        buttonPanel.setLayout(new FlowLayout());
        makeMenuBar(frame);

        frame.getContentPane().add(new JScrollPane(buttonPanel));

        buildView();


    }


    private void buildView() {
        buttonPanel.removeAll();
        //JPanel panel = new JPanel();
        //remove all components in panel.
        buttonPanel.removeAll();


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

        buttonPanel.revalidate();
        buttonPanel.repaint();

        // refresh the panel.
        buttonPanel.updateUI();

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

        return genreMenu;
    }
    private JMenu createMedierMenu() {
        JMenu medieMenu = new JMenu("Medier");
        JMenuItem alleItem = new JMenuItem("Alle");
        medieMenu.add(alleItem);


        alleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                falsetestFilm();
                falsetestSerier();
                truealle();
                buildView();

            }
        });

        JMenuItem filmItem = new JMenuItem("Film");
        medieMenu.add(filmItem);
        filmItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                falsetestSerier();
                falsetestAlle();
                truefilm();
                buildView();

            }
        });

        JMenuItem serieItem = new JMenuItem("Serier");
        medieMenu.add(serieItem);
        serieItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                falsetestAlle();
                falsetestFilm();
                trueserier();
                buildView();

            }
        });

        return medieMenu;
    }

    public boolean truealle(){
        return alleBoolean = true;
    }

    public boolean truefilm(){
        return filmBoolean = true;
    }

    public boolean trueserier(){
        return serieBoolean = true;
    }


    public boolean falsetestAlle(){
        return alleBoolean = false;
    }

    public boolean falsetestFilm(){
        return filmBoolean = false;
    }

    public boolean falsetestSerier(){

        return serieBoolean = false;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //frame.setBackground(Color.red);

    }

}