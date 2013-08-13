package ru.nsu.alife.fs;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Khlebnikov Sergey
 * Date: 22.10.12
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class EventNode {
    private PredicateSet startState;
    private IAction action;
    private Set<EventResultNode> results;
    private long occurrence;

    public EventNode(PredicateSet startState, IAction action, Set<EventResultNode> results, long occurrence) {
        this.startState = startState;
        this.action = action;
        this.results = results;
        this.occurrence = occurrence;
    }

    public PredicateSet getStartState() {
        return startState;
    }

    public IAction getAction() {
        return action;
    }

    public Set<EventResultNode> getResults() {
        return results;
    }

    public long getOccurrence() {
        return occurrence;
    }
}
