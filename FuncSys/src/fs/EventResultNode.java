package fs;

/**
 * Created with IntelliJ IDEA.
 * User: Khlebnikov Sergey
 * Date: 22.10.12
 * Time: 23:49
 * To change this template use File | Settings | File Templates.
 */
public class EventResultNode {
    PredicateSet result;
    long occurrence;

    public EventResultNode(PredicateSet result, long occurrence) {

        this.result = result;
        this.occurrence = occurrence;
    }

    public PredicateSet getResult() {
        return result;
    }

    public long getOccurrence() {
        return occurrence;
    }
}
