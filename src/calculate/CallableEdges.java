package calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;

public class CallableEdges implements Callable<List<Edge>>, Observer {

    private KochFractal koch = null;
    private int index = 0;
    private Observable o;
    private List<Edge> edges = new ArrayList<Edge>();

    public CallableEdges(KochFractal koch, int index){
        this.koch = koch;
        this.index = index;
        o = new Observable();
        setObservable(koch);
    }

    /**
     *
     */
    private void setObservable(Observable observable) {
        this.o = observable;
        this.o.addObserver(this);
    }


    /**
     *
     * @param o
     * @param arg
     */
    @Override
    public void update (Observable o, Object arg) {
        edges.add((Edge) arg);
    }

    @Override
    public List<Edge> call() throws Exception {
        switch (index)
        {
            case 1:
                koch.generateBottomEdge();
                break;

            case 2:
                koch.generateLeftEdge();
                break;
            case 3:
                koch.generateRightEdge();
                break;
        }
        return edges;
    }
}
