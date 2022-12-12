/*import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SpecificMediaClass () {

    JFrame frame;
    Container container;
    Font font;
    JTextField output;
    JPanel buttonPanel;

    public SpecificMediaClass extends JFrame {

        List<Medier> medier = getListOfAllMedia();

        for (int i = 0; i < medier.size(); i++) {
            String path = medier.get(i).getImgPath();

            String title = medier.get(i).getTitle();
            JFrame frameSpecifikMedia = new JFrame(title);
            container = frame.getContentPane();
            font = new Font("Sans-Serif", Font.PLAIN, 60);
            output = new JTextField("0");
            buttonPanel = new JPanel();
            frameSpecifikMedia.setSize(750, 750);
            buttonPanel.setPreferredSize(new Dimension(1000, 5000));
            buttonPanel.setLayout(new GridLayout(0, 2, 5, 10));
            JLabel label1 = new JLabel("Hello");
            Button addToFavoritesButton = new Button("Add to favorites");
            Button playButton = new Button("Play");
            buttonPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            JLabel billede = null;
            try {
                billede = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //frameSpecifikMedia.add(billede);
            buttonPanel.add(billede);
            buttonPanel.add(label1);
            buttonPanel.add(addToFavoritesButton);
            buttonPanel.add(playButton);

            frameSpecifikMedia.add(buttonPanel);
            frameSpecifikMedia.setVisible(true);


        }

        return frameSpecifikMedia;

    }
    public List<Medier> getListOfAllMedia () {
        ReadData dataReader = new ReadData();
        dataReader.createSortedMediaObjectList();
        return dataReader.getSortedMediaObjects();

    }
}




//Lavet af Mathilde og Annika
/*
public class SpecifikMediaClass extends JDialog {
    private String name;
    private JButton favoritesBtn;

    public SpecifikMediaClass(Frame owner, String title){
        super(owner,title);
        this.name = name;
        this.favoritesBtn = favoritesBtn;
        setBounds(500, 100, 500, 600);

        add(new JLabel("Titlen er"));
        add(favoritesBtn);
        setModal(true);

        setVisible(true);

    }

    @Override
    public String getName() {
        return name;
    }

    public JButton getFavoritesBtn() {
        return favoritesBtn;
    }
}

 */
