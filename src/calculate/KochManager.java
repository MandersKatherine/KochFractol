package calculate;

import calculate.Edges.BottomEdge;
import calculate.Edges.LeftEdge;
import calculate.Edges.RightEdge;
import javafx.application.Platform;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The KochManager is an observer of the KochFractal class.
 * It handles consuming and passing on changes to the application's KochFractal.
 */
public class KochManager implements Observer {
    /**
     * The JavaFX application that the KochFractal will be drawn on.
     */
    private JSF31KochFractalFX application;

    /**
     * The KochFractal that will be managed.
     */
    private KochFractal koch;


    /**
     * New threadpool.
     */
    private ExecutorService pool;

    /**
     * The list of edges of the fractal.
     */
    private List<Edge> edges = new ArrayList<>();

    /**
     * A counter to check how many threads are done calculating edges
     */
    private int counter;

    /**
     * The class's constructor.
     * @param application The application that the KochFractal will be drawn on.
     */
    public KochManager(JSF31KochFractalFX application) {
        this.application = application;
        this.koch = new KochFractal();
        koch.addObserver(this);
        //todo hier een threadpool aanmaken (fixed)


        pool = new Executors.newFixedThreadPool(3);

    }

    /**
     * Fires when the observed KochFractal is updated.
     * Draws the edges of the KochFractal.
     * @param o The Koch Fractal.
     * @param arg The edge that was updated.
     */
    @Override
    public void update(Observable o, Object arg) {

    }

    /**
     * Changes the level of the KochFractal.
     * Clears the edges as new edges will be drawn.
     * @param nxt The level of the fractal.
     */
    public void changeLevel(int nxt) {
        TimeStamp ts = new TimeStamp();
        ts.setBegin("Start generating edges");

        //pool.execute();

//        new Thread(new LeftEdge(this,nxt)).start();
//        new Thread(new RightEdge(this,nxt)).start();
//        new Thread(new BottomEdge(this,nxt)).start();

        ts.setEnd("Done generating edges");
        application.setTextCalc(ts.toString());
    }

    /**
     * Generates and draws the edges of the KochFractal.
     * Clears the Koch panel before doing so.
     */
    public void drawEdges() {
        application.clearKochPanel();
        application.setTextNrEdges(String.valueOf(edges.size()));

        drawApplicationEdges();
    }

    /**
     * Makes the KochFractal generate the edges.
     * Calculates the time taken to generate the edges.
     */
    private void generateEdges() {
        TimeStamp ts = new TimeStamp();
        ts.setBegin("Start generating edges");

        koch.generateRightEdge();
        koch.generateLeftEdge();
        koch.generateBottomEdge();

        ts.setEnd("Done generating edges");
        application.setTextCalc(ts.toString());
    }

    /**
     * Tells the application to draw the generated edges.
     * Calculates the time taken to draw the edges.
     */
    private synchronized void drawApplicationEdges() {
        TimeStamp ts = new TimeStamp();
        ts.setBegin("Start drawing edges");

        for(Edge edge : edges) {
            application.drawEdge(edge);
        }

        ts.setEnd("Done drawing edges");
        edges.clear();
        application.setTextDraw(ts.toString());
    }

    /**
     *  addEdge.
     *  Add new edge to the edges array
     * @param e the current edge
     */
    public synchronized void addEdge(Edge e){
        edges.add(e);
        //System.out.println(edges.size());
    }

    /**
     *  finished.
     *  Method called after all the threads are done calculating the edges
     *  synchronized to make sure the method called after ALL the threads are done.
     */
    public synchronized void finished(){
        counter++;
        if(counter == 3){
            application.requestDrawEdges();
            counter = 0;
        }
    }
}
