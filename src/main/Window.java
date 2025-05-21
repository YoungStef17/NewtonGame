package src.main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Window{

    public Window(int w , int h, String title, Game game){


        game.setPreferredSize(new Dimension(w,h));
        game.setMaximumSize(new Dimension(w,h));
        game.setMinimumSize(new Dimension(w,h));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //le due righe di codice seguenti generano l'icona dell'applicazione
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\resource\\images\\iconWindow.png");
        frame.setIconImage(icon);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //game.startGameLoop();
    }
}