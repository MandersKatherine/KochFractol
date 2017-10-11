package calculate.Edges;

import calculate.Edge;
import calculate.KochFractal;
import calculate.KochManager;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RightEdge implements Runnable,Observer {

    private KochFractal koch;
    private KochManager kochManager;

    /**
     * RightEdge.
     * Class constructor
     *
     * @param kochManager The KochManager
     * @param level level of kochFractal
     */
    public RightEdge(KochManager kochManager, int level){
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
        koch.generateRightEdge();
        kochManager.finished();
        try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            Logger.getLogger(RightEdge.class.getName()).log(Level.SEVERE,null,ex);
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
    public void update(Observable o, Object arg) {
        Edge edge = (Edge) arg;
        kochManager.addEdge(edge);

        //System.out.println("Right");
    }
}
