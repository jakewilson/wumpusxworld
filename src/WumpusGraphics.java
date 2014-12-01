import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by matt on 11/30/14.
 */
public class WumpusGraphics extends JFrame {

    public JPanel mapPanel;
    public JPanel knowledgePanel;
    public int[][] knowledgeMap;

    private static String BREEZE_IMG_PATH = "img/breeze.png",
                          PIT_IMG_PATH = "img/pit.png",
                          HERO_IMG_PATH = "img/hero.png",
                          WUMPUS_IMG_PATH = "img/wumpus.png",
                          GLITTER_IMG_PATH = "img/gold.png",
                          STENCH_IMG_PATH = "img/stench.png",
                          EMPTY_IMG_PATH = "img/space.png";

    WumpusGraphics(){
        mapPanel = new JPanel(new GridLayout(4, 4, 3, 3));
        knowledgePanel = new JPanel(new GridLayout(4,4,3,3));

        this.setContentPane(mapPanel);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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
                internalGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPanel.add(internalGridPanel);
            }
        }
        mapPanel.updateUI();
        this.revalidate();
    }

    public void renderKnowledgeBaseMap(int[][] map){
        knowledgeMap = map;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(isState(KnowledgeBase.PIT,i,j)){

                }
                if(isState(KnowledgeBase.POTENTIAL_PIT,i,j)){

                }
            }
        }

    }

    /**
     * Returns true if the map at (x,y) is p
     * @param p the state to test
     * @return true if the map at (x,y) is p
     */
    public boolean isState(int p, int x, int y) {
        return (knowledgeMap[x][y] & p) != 0;
    }

}
