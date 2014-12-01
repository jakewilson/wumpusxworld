package wumpusworld;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by matt on 11/30/14.
 */
public class WumpusGraphics extends JFrame {

    private JPanel mapPanel;
    private String BREEZE_IMG_PATH = "img/breeze.png",
                          PIT_IMG_PATH = "img/pit.png",
                          HERO_IMG_PATH = "img/hero.png",
                          WUMPUS_IMG_PATH = "img/wumpus.png",
                          GLITTER_IMG_PATH = "img/gold.png",
                          STENCH_IMG_PATH = "img/stench.png",
                          EMPTY_IMG_PATH = "img/space.png";

    WumpusGraphics(){
        mapPanel = new JPanel(new GridLayout(4, 4, 3, 3));

        this.setContentPane(mapPanel);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void renderMapTest(){

        JPanel internalGridPanel = new JPanel(new GridLayout(2,2,1,1));
        JLabel l1 = new JLabel(new ImageIcon(BREEZE_IMG_PATH), JLabel.CENTER);
        JLabel l2 = new JLabel("2", JLabel.CENTER);
        JLabel l3 = new JLabel("3", JLabel.CENTER);
        JLabel l4 = new JLabel("4", JLabel.CENTER);
        internalGridPanel.add(l1);
        internalGridPanel.add(l2);
        internalGridPanel.add(l3);
        internalGridPanel.add(l4);
        JLabel l5 = new JLabel("5", JLabel.CENTER);
        JLabel l6 = new JLabel("6", JLabel.CENTER);
        JLabel l7 = new JLabel("7", JLabel.CENTER);
        mapPanel.add(internalGridPanel);
        mapPanel.add(l5);
        mapPanel.add(l6);
        mapPanel.add(l7);
    }

    public void renderMap(Cell[][] map){
        mapPanel.removeAll();

        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                JPanel internalGridPanel = new JPanel(new GridLayout(2,2,1,1));

                Cell currentCell = map[i][j];
                if(currentCell.contains(Cell.BREEZE)){
                    JLabel l = new JLabel(new ImageIcon(BREEZE_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.EMPTY)){
                    JLabel l = new JLabel(new ImageIcon(EMPTY_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.GLITTER)){
                    JLabel l = new JLabel(new ImageIcon(GLITTER_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.PIT)){
                    JLabel l = new JLabel(new ImageIcon(PIT_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.STENCH)){
                    JLabel l = new JLabel(new ImageIcon(STENCH_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    internalGridPanel.add(l);
                }
                if(currentCell.contains(Cell.WUMPUS)){
                    JLabel l = new JLabel(new ImageIcon(WUMPUS_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    internalGridPanel.add(l);
                }
                mapPanel.add(internalGridPanel);
            }
        }
        mapPanel.updateUI();
        this.revalidate();
    }

}
