package calculate.Edges;

import calculate.Edge;
import calculate.KochFractal;
import calculate.KochManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BottomEdge implements Callable, Observer {

    private KochFractal koch;
    private KochManager kochManager;

    /**
     * BottomEdge.
     * Class constructor
     *
     * @param kochManager The KochManager
     * @param level level of kochFracta
     */
    public BottomEdge(KochManager kochManager, int level){
        this.koch = new KochFractal();
        koch.addObserver(this);
        koch.setLevel(level);
        this.kochManager = kochManager;
    }

    /**
     *  Run.
     *  The method that is called after the thread has been started.
     */
//    @Override
//    public void run() {
//      koch.generateBottomEdge();
//        kochManager.finished();
//        try{
//            Thread.sleep(10000);
//        }catch (InterruptedException ex){
//            Logger.getLogger(BottomEdge.class.getName()).log(Level.SEVERE,null,ex);
//        }
//    }

    /**
     * Update.
     * Update called after the edges are generated
     * *
     * @param o the generated edge
     * @param arg the kochfractal class
     */
    @Override
    public void update(Observable o, Object arg) {
        Edge edge = (Edge) arg;
        kochManager.addEdge(edge);
        //System.out.println("Bottom");
    }

    @Override
    public Object call() throws Exception {
        return null;
    }


    //todo een eigen lijst maken binnen de callable
    // todo die lijst vullen met de update
    // todo wanneer je klaar bent met een return de lijst terug geven
    // rekening houden met de het annuleren
}
