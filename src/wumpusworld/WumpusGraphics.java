package wumpusworld;

import javax.swing.*;
import java.awt.*;

/**
 * Created by matt on 11/30/14.
 */
public class WumpusGraphics extends JFrame {

    private JPanel mapPanel;
    private String[] serializedMap;
    private String BREEZE_IMG_PATH = "img/breeze.png",
                          PIT_IMG_PATH = "img/pit.png",
                          HERO_IMG_PATH = "img/hero.png",
                          WUMPUS_IMG_PATH = "img/wumpus.png",
                          GLITTER_IMG_PATH = "img/gold.png",
                          STENCH_IMG_PATH = "img/stench.png",
                          EMPTY_IMG_PATH = "img/space.png";

    WumpusGraphics(Cell[][] map){
        mapPanel = new JPanel(new GridLayout(4, 4, 3, 3));
        renderMap(map);
        this.setContentPane(mapPanel);

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void renderMapToScreen(String[] mapDetails){
        mapPanel.removeAll();
        for (int i = 0; i < 16; i++) {
            JLabel l = new JLabel(new ImageIcon(mapDetails[i]), JLabel.CENTER);
            //l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            l.setFont(l.getFont().deriveFont(20f));
            mapPanel.add(l);
        }
    }

    public void renderMap(Cell[][] map){
        mapPanel.removeAll();

        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                JPanel internalGridPanel = new JPanel(new GridLayout(4,4,1,1));

                Cell currentCell = map[i][j];
                if(currentCell.contains(Cell.BREEZE)){
                    JLabel l = new JLabel(new ImageIcon(BREEZE_IMG_PATH), JLabel.CENTER);
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.EMPTY)){
                    JLabel l = new JLabel(new ImageIcon(EMPTY_IMG_PATH), JLabel.CENTER);
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.GLITTER)){
                    JLabel l = new JLabel(new ImageIcon(GLITTER_IMG_PATH), JLabel.CENTER);
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.PIT)){
                    JLabel l = new JLabel(new ImageIcon(PIT_IMG_PATH), JLabel.CENTER);
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.STENCH)){
                    JLabel l = new JLabel(new ImageIcon(STENCH_IMG_PATH), JLabel.CENTER);
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.WUMPUS)){
                    JLabel l = new JLabel(new ImageIcon(WUMPUS_IMG_PATH), JLabel.CENTER);
                    internalGridPanel.add(l);
                }
                mapPanel.add(internalGridPanel);
            }
        }

        mapPanel.updateUI();
    }

}
