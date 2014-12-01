import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by matt on 11/30/14.
 */
public class WumpusGraphics extends JFrame {

    public JPanel mainPanel;
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
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        mapPanel = new JPanel(new GridLayout(4, 4, 3, 3));
        knowledgePanel = new JPanel(new GridLayout(4,4,3,3));
        mapPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        knowledgePanel.setBorder(BorderFactory.createLoweredBevelBorder());


        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(new JLabel("Environment"), c);

        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        mainPanel.add(new JLabel("KnowledgeBase"), c);

        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(mapPanel, c);

        JPanel divider = new JPanel();
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        mainPanel.add(divider, c);

        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        mainPanel.add(knowledgePanel, c);


        this.setContentPane(mainPanel);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Renders the Environment Map to the screen with respective images
     * @param map
     */
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
                if(currentCell.getContent() == Cell.EMPTY){
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

                JPanel internalGridPanel = new JPanel(new GridLayout(2,2,1,1));
                internalGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if(isState(KnowledgeBase.PIT,i,j)){
                    JLabel l = new JLabel(new ImageIcon(PIT_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    internalGridPanel.add(l);
                }
                if(isState(KnowledgeBase.POTENTIAL_PIT,i,j)){
                    JLabel l = new JLabel("P ?");
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    internalGridPanel.add(l);
                }
                if(isState(KnowledgeBase.POTENTIAL_WUMPUS,i,j)){
                    JLabel l = new JLabel("W ?");
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    internalGridPanel.add(l);
                }
                if(isState(KnowledgeBase.SAFE,i,j)){
                    JLabel l = new JLabel(new ImageIcon(EMPTY_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    internalGridPanel.add(l);
                }
                if(map[i][j] == KnowledgeBase.UNKNOWN){
                    JLabel l = new JLabel("Unknown");
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    internalGridPanel.add(l);
                }
                if(isState(KnowledgeBase.WUMPUS,i,j)){
                    JLabel l = new JLabel(new ImageIcon(WUMPUS_IMG_PATH), JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    internalGridPanel.add(l);
                }
                knowledgePanel.add(internalGridPanel);
            }
        }
        knowledgePanel.updateUI();
        this.revalidate();
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
