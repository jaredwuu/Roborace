package RoboRace;

import COSC3P40.midi.*;
import COSC3P40.sound.Sound;
import COSC3P40.sound.SoundManager;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.AudioFormat;

public class Player extends JFrame implements Runnable {

    private String name;
    private Board board;
    private Port port;
    private BoardCanvas boardCanvas;
    private CardPane cardPane;
    static SoundManager sound_manager;
    static Sound s_bump;
    static Sound s_vic;
    static Sound s_des;

    public Player(String name, Port port) {
        sound_manager = new SoundManager(new AudioFormat(8000, 8, 1, false, false));
        sound_manager.setSoundPath("./Sounds&Midi");
        s_bump = sound_manager.getSound("/bump.wav");
        s_des = sound_manager.getSound("/explosion.wav");
        s_vic = sound_manager.getSound("/fanfare.wav");
        this.name = name;
        board = null;
        this.port = port;
        setTitle(name);
        (new Thread(this)).start();
    }

    public void run() {
        board = Board.read(new StringReader(port.recieve()));
        boardCanvas = new BoardCanvas(board);
        cardPane = new CardPane();
        getContentPane().add("North", boardCanvas);
        getContentPane().add("South", cardPane);
        pack();
        setResizable(false);
        setVisible(true);
        boardCanvas.start();
        CardList cards;
        EventList events = new EventList();
        while (!events.containsVictory()) {
            board.revitalize();
            cards = CardList.read(new StringReader(port.recieve()));
            cards = cardPane.selectCards(cards);
            port.send(cards.toXMLString());
            events = EventList.read(new StringReader(port.recieve()));
            events.execute(board);
        }
        ;
        GameDialogs.showMessageDialog("End of Game", "The winner is " + events.getWinner() + "!!!");
        boardCanvas.stop();
        setVisible(false);
        dispose();
        try {
            port.close();
        } catch (Exception e) {
        }
        ;
    }

}