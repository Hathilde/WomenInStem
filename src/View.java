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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class View {

    JFrame frame;
    Container container;
    Font font;
    JTextField output;
    JPanel buttonPanel;

    List<Medier> medier;

    List<Medier> singleMedie;

    List<Medier> myFavouritesList;

    List<Medier> genreMedier;

    boolean alleBoolean;
    boolean filmBoolean;
    boolean serieBoolean;
    boolean singleBoolean;
    boolean genreBoolean;
    boolean favouriteButton;
    boolean myFavouritesBoolean;



    public List<Medier> getListOfAllMedia() {
        ReadData dataReader = new ReadData();
        dataReader.createAllMediaObjectsList();

        if (filmBoolean) {
            return dataReader.getAllFilmObjects();

        } else if (serieBoolean) {
            return dataReader.getAllSerierObjects();

        } else if (singleBoolean){
            return singleMedie;

        } else if (genreBoolean) {

            return genreMedier;

        } else if (myFavouritesBoolean){
            return myFavouritesList;


        } else {
            return dataReader.getAllMediaObjects();

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
        frame.setSize(1650,1080);

        buttonPanel.setPreferredSize(new Dimension(1000, 5000));
        buttonPanel.setLayout(new FlowLayout());
        makeMenuBar(frame);

        frame.getContentPane().add(new JScrollPane(buttonPanel));

        myFavouritesList = new ArrayList<>();

        buildView();

    }


    private void buildView() {
        buttonPanel.removeAll();

        buttonPanel.removeAll();

        medier = getListOfAllMedia();

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

                // Gør knappen usynlig. Hvad gør disse?
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);

                String title = medier.get(i).getTitle();
                String specificMediaInfo = medier.get(i).toString();

                //ACTIONLISTENER VED TRYK PÅ SPECIFIKT MEDIE - POP-UP DANNES //

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JFrame frameSpecifikMedia = new JFrame(title);
                        container = frame.getContentPane();
                        font = new Font("Sans-Serif", Font.PLAIN, 60);
                        output = new JTextField("0");
                        JPanel buttonPanelIndre = new JPanel();
                        frameSpecifikMedia.setSize(750,750);

                        buttonPanelIndre.setLayout(new GridLayout(2,2, 2 ,5 ));

                        JLabel specifikMediaData = new JLabel(("<html>" + specificMediaInfo + "</html>"));

                        boolean favouriteButton = true;

                        JButton addToFavoritesButton = new JButton("Tilføj til favoritter");

                        JButton removeFromFavoritesButton = new JButton("Fjern fra favoritter");

                        addToFavoritesButton.setSize(5,5);
                        JButton playButton = new JButton("Afspil");

                        // ACTIONLISTENER VED TRYK PÅ "Afspil"-knap //

                        playButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                playButton.setBackground(Color.RED);
                                playButton.setOpaque(true);
                                playButton.setBorderPainted(false);
                            }
                        });

                        // ACTIONLISTENER VED TRYK PÅ "Tilføj til favoritter"-knap //

                        addToFavoritesButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                for (int i = 0; i < medier.size(); i++) {
                                    String titlecurrent = medier.get(i).getTitle();

                                    if (titlecurrent.equals(title)) {

                                        if(myFavouritesList.contains(medier.get(i))){
                                            System.out.println("MÅ MAN IKKE!!!");
                                        } else {
                                            myFavouritesList.add(medier.get(i));
                                        }

                                    }
                                }
                            }
                        });

                        // ACTIONLISTENER VED TRYK PÅ "Fjern fra favoritter"-KNAP //

                        removeFromFavoritesButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (int i = 0; i < medier.size(); i++) {
                                    String titlecurrent = medier.get(i).getTitle();

                                    if (titlecurrent.equals(title)) {
                                        myFavouritesList.remove(medier.get(i));
                                    }
                                }
                            }
                        });

                        //FÆRDIGGØRELSE AF POP-UP //
                        buttonPanelIndre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

                        JLabel billede = null;
                        try {
                            billede = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        buttonPanelIndre.add(billede);
                        buttonPanelIndre.add(specifikMediaData);
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
    private void makeMenuBar (JFrame frame) {
        frame.setJMenuBar(createMenuBar());
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JButton myFavouritesButton = new JButton("Mine Favoritter");
        menuBar.add(myFavouritesButton);
        menuBar.add(createMedierMenu());
        menuBar.add(createGenreMenu());

        // ACTIONLISTENER VED TRYK PÅ KNAPPEN "Mine favoritter" //
        myFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allBooleansFalse();
                trueMyFavourites ();

                buildView();
            }
        });

        // SØGEFELT //
        menuBar.add(new JSeparator());
        menuBar.add(Box.createHorizontalGlue());
        JTextField txt;
        txt = new JTextField("   Søg her...  ",25);
        txt.setMaximumSize(txt.getPreferredSize());
        menuBar.add(txt);

        // ACTIONLISTENER VED SØGNING I SØGEFELTET //
        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                singleMedie = new ArrayList<>();
                singleMedie.removeAll(singleMedie);

                allBooleansFalse();
                truealle();

                getListOfAllMedia();

                buildView();

                for (int i = 0; i < medier.size(); i++) {
                    String title = medier.get(i).getTitle();

                    if (txt.getText().equalsIgnoreCase(title)) {
                        singleMedie.add(medier.get(i));
                        //System.out.println("IF - TEKST : " + txt.getText() + " + " + title);

                        allBooleansFalse();
                        trueSingle();

                        buildView();
                    }
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
    // GENRE-MENU //
    private JMenu createGenreMenu() {
        JMenu genreMenu = new JMenu("Genre");

        ReadData dataReaderGenre = new ReadData();
        dataReaderGenre.createAllMediaObjectsList();

        Arrays.toString(dataReaderGenre.getGenreArray());

        JMenuItem alleGenreItm = new JMenuItem("Alle");
        genreMenu.add(alleGenreItm);
        // ACTIONLISTENER VED TRYK PÅ "Alle" //
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

            // ACTIONLISTENER VED TRYK PÅ SPECIFIK GENRE
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

    // Medier menu //
    private JMenu createMedierMenu() {
        JMenu medieMenu = new JMenu("Medier");
        JMenuItem alleItem = new JMenuItem("Alle");
        medieMenu.add(alleItem);

        //ACTION LISTENER VED TRYK PÅ "Alle" //
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

        //ACTION LISTENER VED TRYK PÅ "Film" //

        filmItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                allBooleansFalse();
                truefilm();

                buildView();

            }
        });

        //ACTION LISTENER VED TRYK PÅ "Serier" //

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


    // BOOLEANS //
    public boolean truealle(){
        return alleBoolean = true;
    }

    public boolean truefilm(){
        return filmBoolean = true;
    }

    public boolean trueserier(){
        return serieBoolean = true;
    }

    public boolean trueSingle() {
        return singleBoolean = true;
    }

    public boolean trueMyFavourites () {
        return myFavouritesBoolean = true;
    }


    public boolean falsetestAlle(){
        return alleBoolean = false;
    }

    public boolean falsetestFilm(){
        return filmBoolean = false;
    }

    public boolean falsetestSerier(){ return serieBoolean = false; }

    public boolean falseTestSingle() {
        return singleBoolean = false;
    }

    public boolean falseMineFavoritter() {
        return myFavouritesBoolean = false;
    }

    public boolean addedToFavorites() {

        return favouriteButton = true;
    }

    public boolean notAddedToFavorites() {

        return favouriteButton = false;
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


    /*
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

     */

    }
