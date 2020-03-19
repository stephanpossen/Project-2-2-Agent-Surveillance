/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author joel
 */

public class Explorer extends JFrame implements KeyListener, ActionListener {

    int offsetX = 10;
    int offsetY = 10;

    protected String mapDoc;
    protected Scenario scenario;

    final int width = 1000;
    final int height = 800;

    double[][] guardsPositions;

    public Area spawnAreaGuards = new Area();
    public static ArrayList<Area> walls = new ArrayList<Area>();
    public static ArrayList<Area> shaded = new ArrayList<Area>();
    public static ArrayList<CustomArea> target = new ArrayList<CustomArea>();

    ArrayList<Ellipse2D> guards = new ArrayList<Ellipse2D>();

    ExGamePlayer p;

    int angle = 0;

    int pointX, pointY, oldX, oldY;

    Integer moveCounter = 0;
    public Ellipse2D.Double circle;
    private ArrayList<Area> targetArea;


    public Explorer(String scn) {


        addKeyListener(this);

        pointX = offsetX;
        pointY = offsetY;


        JPanel objMainPanel = new JPanel(new GridLayout(1, 1));
        JPanel objBtnPanel = new JPanel(new GridLayout(1, 0));

        // Create the buttons.
        JButton btnLandmarks = new JButton("Load Components");
        JButton btnWaypoints = new JButton("Load Agents");
        JButton btnResetGUI = new JButton("Reset World");
        JButton btnViewStats = new JButton("View Statistics");

        // Set the actions.
        //btnLandmarks.addActionListener((ActionListener) this);
        btnLandmarks.setActionCommand("landmarks");
        //btnWaypoints.addActionListener((ActionListener) this);
        btnWaypoints.setActionCommand("waypoints");
        //btnViewStats.addActionListener((ActionListener) this);
        btnViewStats.setActionCommand("stats");
        //btnResetGUI.addActionListener((ActionListener) this);
        btnResetGUI.setActionCommand("reset");

        objBtnPanel.add(btnLandmarks);
        objBtnPanel.add(btnWaypoints);
        objBtnPanel.add(btnViewStats);
        objBtnPanel.add(btnResetGUI);


        mapDoc = scn;
        scenario = new Scenario(mapDoc);
        //p = new ExGamePlayer(scenario);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);


        this.initComponents();

        getContentPane().add(objBtnPanel, BorderLayout.PAGE_END);

        System.out.println("----Hey------");
        System.out.println(walls);
        System.out.println("-------End here----");

    }

    private void initComponents() {

        Ellipse2D e = new Ellipse2D.Double(300, 400, 0.05 * width, 0.05 * height);
        //guards.add(e);

        spawnAreaGuards = Scenario.getSpawnAreasGuards();
        walls = Scenario.getWalls();
        shaded = Scenario.getShaded();
        targetArea = Scenario.getTargetArea();

        guardsPositions = Scenario.spawnGuard();


        for (int i = 0; i < Scenario.getNumGuards; i++) {

            System.out.println(guardsPositions[i][0] + "   -    " + guardsPositions[i][1] + "  -   " + guardsPositions[2]);

        }


    }

    public void paint(Graphics g) {


        g.setColor(Color.RED);
        g.drawOval(pointX + 100, pointY + 100, 100, 100);

        g.setColor(Color.BLACK);
        g.drawOval(pointX + 150, pointY + 150, 6, 6);


        for (int i = 0; i < walls.size(); i++) {

            System.out.println(walls.get(i));

        }

        System.out.println("00000000000000");


        g.setColor(Color.RED);
        for (Area s : shaded) {
            s.draw(g);
        }


        g.setColor(Color.BLUE);
        for (Area a : targetArea) {
            a.fill(g);
        }


        g.setColor(Color.CYAN);
        spawnAreaGuards.draw(g);


        g.setColor(Color.BLACK);
        for (Ellipse2D e : guards) {
            ((Graphics2D) g).draw(e);
        }


        g.setColor(Color.CYAN);
        for (int i = 0; i < Scenario.numGuards; i++) {
            Ellipse2D f = new Ellipse2D.Double(guardsPositions[i][0], guardsPositions[i][1], 5, 5);
            guards.add(f);
            ((Graphics2D) g).fill(f);

        }


        g.setColor(Color.GREEN);
        //System.out.println("Finally got to the Walls");
        for (Area a : walls) {
            System.out.println("Finally got to the Walls");
            a.fill(g);
        }

        g.setColor(Color.BLACK);
        Line2D line = new Line2D.Double(pointX + 150, pointY + 150, pointX + 150, pointY);


        //agent is static
        //the generation area right now is commented - from Scenario

        //get angle method to be implemented from the Agent class


        AffineTransform at =
                AffineTransform.getRotateInstance(
                        Math.toRadians(angle), line.getX1(), line.getY1());


        ((Graphics2D) g).draw(at.createTransformedShape(line));


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent key) {

        oldX = pointX;
        oldY = pointY;

        if (key.getKeyCode() == key.VK_SPACE) {
            angle += 5;
            if (angle == 360)
                angle = 0;
            System.out.println("Angle is:" + angle);
        }

        if (key.getKeyCode() == key.VK_DOWN) {
            if (pointY > getBounds().height) {
                pointY = getBounds().height;
            }
        }

        if (key.getKeyCode() == key.VK_UP) {
            pointY = pointY;
            if (pointY < 0) {
                pointY = 0;
            }
        }

        if (key.getKeyCode() == key.VK_LEFT) {
            angle += 5;
            if (angle == 360)
                angle = 0;
            System.out.println("Angle is:" + angle);
        }

        if (key.getKeyCode() == key.VK_RIGHT) {

            if (angle == 90) {
                if (pointX > getBounds().width) {
                    pointX = getBounds().width;
                }
            }
        }

        //if "P" is pressed print the maze

    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        // the mapscenario should be passed as a parameter
        String mapD = "C:\\Users\\Tudor\\IdeaProjects\\project2\\src\\testInput.txt";
        Explorer game = new Explorer(mapD);
        game.setVisible(true);
        //game.writeGameFile();
        //game.p.start()

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}






