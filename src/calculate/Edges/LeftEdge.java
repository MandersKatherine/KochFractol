package calculate.Edges;

import calculate.Edge;
import calculate.KochFractal;
import calculate.KochManager;

import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeftEdge implements Runnable, Observer {

    private KochFractal koch;
    private KochManager kochManager;

    /**
     * LeftEdge.
     * Class constructor
     *
     * @param kochManager The KochManager
     * @param level level of kochFracta
     */
    public LeftEdge(KochManager kochManager, int level){
        this.koch = new KochFractal();
        koch.addObserver(this);
        koch.setLevel(level);
        this.kochManager = kochManager;
    }

    /**
     *  Run.
     *  The method that is called after the thread has been started
     *
     */
    @Override
    public void run() {
        koch.generateLeftEdge();
        kochManager.finished();
        try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            Logger.getLogger(LeftEdge.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    /**
     * Update.
     * Update called after the edges are generated
     * *
     * @param o the generated edge
     * @param arg the kochfracter class
     */
    @Override
    public void update(java.util.Observable o, Object arg) {
        Edge edge = (Edge) arg;
        kochManager.addEdge(edge);
        //System.out.println("Left");

    }
}
