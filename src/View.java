import CustomExceptions.NoMediaFoundException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.swing.JScrollPane;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class View {

    JFrame frame;
    Container container;
    Font font;
    JTextField output;
    JPanel buttonPanel;

    List<Medier> medier;

    List<Medier> mineFavoritter;

    List<Medier> singleMedie;

    List<Medier> genreMedier;



    boolean alleBoolean;
    boolean filmBoolean;
    boolean serieBoolean;
    boolean singleBoolean;

    boolean genreBoolean;

    boolean mineFavoritterBoolean;


    public List<Medier> getListOfAllMedia() {
        ReadData dataReader = new ReadData();
        dataReader.createSortedMediaObjectList();

        if (filmBoolean) {
            System.out.println("getListOfAllMedia() filmboolean");
            return dataReader.getSortedFilmObjects();

        } else if (serieBoolean) {
            System.out.println("getListOfAllMedia() serieBoolean");
            return dataReader.getSortedSerierObjects();

        } else if (singleBoolean) {
            System.out.println("getListOfAllMedia() singleBoolean");
            return singleMedie;

        } else if (genreBoolean) {
            System.out.println("getListOfAllMedia() genreBoolean");
            return genreMedier;

        } else if (mineFavoritterBoolean) {
            System.out.println("getListOfAllMedia() mineFavoritterBoolean");
            return mineFavoritter;

        } else {
            System.out.println(" getListOfAllMedia() alle");
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

        filmBoolean = false;
        serieBoolean = false;
        genreBoolean = false;
        singleBoolean = false;
        mineFavoritterBoolean = false;
        alleBoolean = true;

        mineFavoritter = new ArrayList<>();


        frame = new JFrame("PseudoFlix");
        container = frame.getContentPane();
        font = new Font("Sans-Serif", Font.PLAIN, 60);
        output = new JTextField("0");
        buttonPanel = new JPanel();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1650, 1080);

        buttonPanel.setPreferredSize(new Dimension(1000, 5000));
        buttonPanel.setLayout(new FlowLayout());
        makeMenuBar(frame);

        frame.getContentPane().add(new JScrollPane(buttonPanel));

        buildView();

    }


    private void buildView() {

        buttonPanel.removeAll();                //Fjerner (nulstiller) alle viste medier

        medier = getListOfAllMedia();           //Fanger den korrekte ArrayListe af medier, som skal vises

        int count = 0;

        for (int i = 0; i < medier.size(); i++) {
            String path = medier.get(i).getImgPath();
            instans = medier.get(i);
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

                //Kreation af pop-up-vinduet:
                String title = medier.get(i).getTitle();
                String specificMediaInfo = medier.get(i).toString();


                button.addActionListener(new ActionListener() {         //klikker på pop-up vindue
                    @Override






                        JButton playButton = new JButton("Afspil");
                        playButton.addActionListener(new ActionListener() {     //klikker på play-knap

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                playButton.setBackground(Color.RED);
                                playButton.setOpaque(true);
                                playButton.setBorderPainted(false);
                            }
                        });

                        jPanelIndre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

                        JLabel billede = null;
                        try {
                            billede = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        jPanelIndre.add(billede);

                        jPanelIndre.add(label1);
                        jPanelIndre.add(addToFavoritesButton);
                        jPanelIndre.add(playButton);

                        frameSpecifikMedia.add(jPanelIndre);
                        frameSpecifikMedia.setVisible(true);

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
    private void makeMenuBar(JFrame frame) {
        frame.setJMenuBar(createMenuBar());
    }

    private JButton favoritButton() {
        JButton favoritButton = new JButton("Mine Favoritter");
        favoritButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allBooleansFalse();
                trueMineFavoritter();
                buildView();
            }
        });

        return favoritButton;
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        //JButton favoritButton = new JButton("Mine Favoritter!!");
        menuBar.add(favoritButton());
        menuBar.add(createMedierMenu());
        menuBar.add(createGenreMenu());

        menuBar.add(new JSeparator());
        menuBar.add(Box.createHorizontalGlue());


            try {
                menuBar.add(createSearchMenu());
            } catch (NoMediaFoundException ex) {
               NoMediaFoundMethod(ex);
            }


        // SEARCH BAR AND ACTIONLISTENER:

        return menuBar;

    }

    private JTextField createSearchMenu() throws NoMediaFoundException {

        JTextField txt;
        txt = new JTextField("   Søg her...  ", 25);
        txt.setMaximumSize(txt.getPreferredSize());

        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    singleMedie = new ArrayList<>();
                    singleMedie.clear();

                    allBooleansFalse();
                    truealle();

                    buildView();

                    if {

                         for (int i = 0; i < medier.size(); i++) {

                                String title = medier.get(i).getTitle();
                                System.out.print(" I FOR ' " + txt.getText() + " ' " + title);

                                if (txt.getText().equalsIgnoreCase(title)) {
                                  singleMedie.add(medier.get(i));
                                 allBooleansFalse();
                                 trueSingle();

                                    buildView();
                             }

                    }

                    } else {
                        throw new NoMediaFoundException();              //FIX THIS.... PLZ.
                    }


            }
        });
        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt.setText("");
            }
        });
        return txt;
    }

    private JMenu createGenreMenu() {
        JMenu genreMenu = new JMenu("Genre");

        ReadData dataReaderGenre = new ReadData();
        dataReaderGenre.createSortedMediaObjectList();

        Arrays.toString(dataReaderGenre.getGenreArray());

        JMenuItem alleGenreItm = new JMenuItem("Alle");
        genreMenu.add(alleGenreItm);



        alleGenreItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                allBooleansFalse();
                truealle();

                buildView();
            }
        });

        for (String currentGenre : dataReaderGenre.getGenreArray()) {
            JMenuItem nyGenreItem = new JMenuItem(currentGenre);
            genreMenu.add(nyGenreItem);
            nyGenreItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    genreMedier = new ArrayList<>();
                    genreMedier.removeAll(genreMedier);

                    allBooleansFalse();
                    truealle();

                    getListOfAllMedia();

                    buildView();

                    for (int i = 0; i < medier.size(); i++) {
                        List<String> genreString = medier.get(i).getGenre();

                        if (genreString.toString().contains(nyGenreItem.getText())) {

                            //System.out.println("Er vi i IF???");
                            genreMedier.add(medier.get(i));

                        }

                    }
                    allBooleansFalse();
                    trueGenre();

                    buildView();
                }
            });
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

                allBooleansFalse();
                truealle();

                buildView();

            }
        });

        JMenuItem filmItem = new JMenuItem("Film");
        medieMenu.add(filmItem);
        filmItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                allBooleansFalse();
                truefilm();

                buildView();

            }
        });

        JMenuItem serieItem = new JMenuItem("Serier");
        medieMenu.add(serieItem);
        serieItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                allBooleansFalse();
                trueserier();

                buildView();

            }
        });

        return medieMenu;
    }

    public void allBooleansFalse() {

        falsetestAlle();        //1
        falsetestFilm();        //2
        falseTestSingle();      //3
        falseTestGenre();       //4
        falseMineFavoritter();  //5
        falsetestSerier();      //6

    }

    public void NoMediaFoundMethod(NoMediaFoundException ex) {

        JFrame frameNoMediaException = new JFrame("Hovsa!");
        container = frame.getContentPane();
        //container = frameNoMediaException.getContentPane();
        font = new Font("Sans-Serif", Font.PLAIN, 60);
        output = new JTextField("0");
        JPanel panelNoMediaException = new JPanel();
        container.setSize(100, 100);

        JLabel label2 = new JLabel(("<html>" + ex.getMessage() + "</html>"));
        Button buttonOk = new Button("Ok");
        buttonOk.setSize(10, 10);

        panelNoMediaException.add(label2);
        panelNoMediaException.add(buttonOk);
        panelNoMediaException.setLayout(new FlowLayout());

        frameNoMediaException.setPreferredSize(new Dimension(400, 125));

        frameNoMediaException.add(panelNoMediaException);
        frameNoMediaException.pack();
        frameNoMediaException.setLocationRelativeTo(null);

        //Lav TRYk PÅ OK, OG DEN LUKKER. - MANGLER.
        //

        //frameNoMediaException.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frameNoMediaException.setVisible(true);

    }

    public boolean truealle() {
        return alleBoolean = true;
    }

    public boolean truefilm() {
        return filmBoolean = true;
    }

    public boolean trueserier() {
        return serieBoolean = true;
    }


    public boolean falsetestAlle() {
        return alleBoolean = false;
    }

    public boolean falsetestFilm() {
        return filmBoolean = false;
    }

    public boolean falsetestSerier() {

        return serieBoolean = false;
    }

    public boolean trueSingle() {
        return singleBoolean = true;
    }

    public boolean falseTestSingle() {
        return singleBoolean = false;
    }

    public boolean trueGenre() {
        return genreBoolean = true;
    }

    public boolean falseTestGenre() {
        return genreBoolean = false;
    }

    public boolean trueMineFavoritter() {
        return mineFavoritterBoolean = true;
    }

    public boolean falseMineFavoritter() {
        return mineFavoritterBoolean = false;
    }


}




