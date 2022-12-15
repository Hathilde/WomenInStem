import CustomExceptions.AddToFavoritesException;
import CustomExceptions.NoMediaFoundException;

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

public class View {

    JFrame frame;
    Container container;
    Font font;
    JTextField output;
    JPanel buttonPanel;

    List<Medier> medier;

    List<Medier> singleMedie;

    List<Medier> MineFavoritter;

    List<Medier> genreMedier;

    boolean alleBoolean;
    boolean filmBoolean;
    boolean serieBoolean;
    boolean singleBoolean;

    boolean genreBoolean;

    boolean favoritKnap;
    boolean mineFavoritterBoolean;


    public List<Medier> getListOfAllMedia() {
        ReadData dataReader = new ReadData();
        dataReader.createSortedMediaObjectList();

        if (filmBoolean) {
            return dataReader.getSortedFilmObjects();

        } else if (serieBoolean) {
            return dataReader.getSortedSerierObjects();

        } else if (singleBoolean) {
            return singleMedie;

        } else if (genreBoolean) {

            return genreMedier;

        } else if (mineFavoritterBoolean) {
            return MineFavoritter;


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
        genreBoolean = false;
        singleBoolean = false;

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

        MineFavoritter = new ArrayList<>();


        buildView();

    }


    private void buildView() {
        buttonPanel.removeAll();

        buttonPanel.removeAll();

        medier = getListOfAllMedia();
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
                String specificMediaInfo = medier.get(i).toString();


                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JFrame frameSpecifikMedia = new JFrame(title);
                        container = frame.getContentPane();
                        font = new Font("Sans-Serif", Font.PLAIN, 60);
                        output = new JTextField("0");
                        JPanel buttonPanelIndre = new JPanel();
                        frameSpecifikMedia.setSize(750, 750);

                        buttonPanelIndre.setLayout(new GridLayout(2, 2, 2, 5));

                        JLabel label1 = new JLabel(("<html>" + specificMediaInfo + "</html>"));

                        // Start ændring
                        boolean favoritKnap = true;

                        JButton addToFavoritesButton = new JButton("Tilføj til favoritter");

                        JButton removeFromFavoritesButton = new JButton("Fjern fra favoritter");

                        addToFavoritesButton.setSize(5, 5);
                        JButton playButton = new JButton("Afspil");

                        removeFromFavoritesButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (int i = 0; i < medier.size(); i++) {
                                    String titlecurrent = medier.get(i).getTitle();

                                    if (titlecurrent.equals(title)) {
                                        MineFavoritter.remove(medier.get(i));

                                    }
                                }
                            }
                        });

                        addToFavoritesButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                try {
                                    for (int i = 0; i < medier.size(); i++) {
                                        String titlecurrent = medier.get(i).getTitle();

                                        if (titlecurrent.equals(title)) {

                                            if (MineFavoritter.contains(medier.get(i))) {
                                                throw new AddToFavoritesException("Hej med dig throw new");

                                            } else {
                                                MineFavoritter.add(medier.get(i));
                                            }


                                        }
                                    }
                                } catch(AddToFavoritesException ex) {
                                    AddToFavortitesMethod();
                                }


                            }
                        });

                        playButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                playButton.setBackground(Color.RED);
                                playButton.setOpaque(true);
                                playButton.setBorderPainted(false);
                            }
                        });
                        //Slut ændring

                        buttonPanelIndre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

                        JLabel billede = null;
                        try {
                            billede = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        buttonPanelIndre.add(billede);
                        buttonPanelIndre.add(label1);
                        buttonPanelIndre.add(addToFavoritesButton);
                        buttonPanelIndre.add(removeFromFavoritesButton);
                        buttonPanelIndre.add(playButton);

                        frameSpecifikMedia.add(buttonPanelIndre);
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

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JButton favoritButton = new JButton("Mine Favoritter");
        menuBar.add(favoritButton);
        menuBar.add(createMedierMenu());
        menuBar.add(createGenreMenu());

        favoritButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allBooleansFalse();
                trueMineFavoritter();

                buildView();
            }
        });

        menuBar.add(new JSeparator());
        menuBar.add(Box.createHorizontalGlue());
        JTextField txt;
        txt = new JTextField("   Søg her...  ", 25);
        txt.setMaximumSize(txt.getPreferredSize());
        menuBar.add(txt);

        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                singleMedie = new ArrayList<>();
                singleMedie.removeAll(singleMedie);

                allBooleansFalse();
                truealle();

              //  getListOfAllMedia();

                buildView();

                int optæller = 0;

                try {

                    for (int i = 0; i < medier.size(); i++) {
                        String title = medier.get(i).getTitle();


                        if (txt.getText().equalsIgnoreCase(title)) {
                            singleMedie.add(medier.get(i));

                            allBooleansFalse();
                            trueSingle();

                            buildView();

                        } else {
                            optæller = optæller + 1;

                            if (optæller == 200) {
                                throw new NoMediaFoundException("Hej");
                            }
                        }

                    }
                } catch(NoMediaFoundException ex) {
                  NoMediaFoundMethod();
                }

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

    public boolean truealle() {
        return alleBoolean = true;
    }

    public boolean truefilm() {
        return filmBoolean = true;
    }

    public boolean trueserier() {
        return serieBoolean = true;
    }

    public boolean trueSingle() {
        return singleBoolean = true;
    }

    public boolean trueMineFavoritter() {
        return mineFavoritterBoolean = true;
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

    public boolean falseTestSingle() {
        return singleBoolean = false;
    }

    public boolean falseMineFavoritter() {
        return mineFavoritterBoolean = false;
    }

    public boolean addedToFavorites() {

        return favoritKnap = true;
    }

    public boolean notAddedToFavorites() {

        return favoritKnap = false;
    }

    public void allBooleansFalse() {

        falsetestAlle();        //1
        falsetestFilm();        //2
        falseTestSingle();      //3
        falseTestGenre();       //4
        falseMineFavoritter();  //5
        falsetestSerier();      //6

    }

    public boolean trueGenre() {
        return genreBoolean = true;
    }

    public boolean falseTestGenre() {
        return genreBoolean = false;
    }


    public void NoMediaFoundMethod() {

        JFrame frameNoMediaException = new JFrame("Hovsa!");
        //container = frame.getContentPane();
        //container = frameNoMediaException.getContentPane();
        font = new Font("Sans-Serif", Font.PLAIN, 60);
        output = new JTextField("0");
        JPanel panelNoMediaException = new JPanel();
        //container.setSize(100, 100);

        JLabel label2 = new JLabel(("<html>" + "Der findes ingen medier der matchede din søgning" + "</html>"));
        Button buttonOk = new Button("Ok");
        buttonOk.setSize(10, 10);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameNoMediaException.setVisible(false);
            }
        });

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

    public void AddToFavortitesMethod() {

        JFrame addToFavortitesExceptionFrame  = new JFrame("Hovsa!");
       // container = frame.getContentPane();
        //container = AddToFavortitesException.getContentPane();
        font = new Font("Sans-Serif", Font.PLAIN, 60);
        output = new JTextField("0");
        JPanel panelAddToFavortitesEx = new JPanel();
        //container.setSize(100, 100);

        JLabel label2 = new JLabel(("<html>" + "Mediet er allerede tilføjet til favoritlisten" + "</html>"));
        Button buttonOk = new Button("Ok");
        buttonOk.setSize(10, 10);

        panelAddToFavortitesEx.add(label2);
        panelAddToFavortitesEx.add(buttonOk);



       buttonOk.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               addToFavortitesExceptionFrame.setVisible(false);
           }
       });

        panelAddToFavortitesEx.setLayout(new FlowLayout());

        addToFavortitesExceptionFrame.setPreferredSize(new Dimension(400, 125));

        addToFavortitesExceptionFrame.add(panelAddToFavortitesEx);
        addToFavortitesExceptionFrame.pack();
        addToFavortitesExceptionFrame.setLocationRelativeTo(null);

        //Lav TRYk PÅ OK, OG DEN LUKKER. - MANGLER.
        //

        //AddToFavortitesException.setCloseOperation(EXIT_ON_CLOSE);

        addToFavortitesExceptionFrame.setVisible(true);
    }

}
