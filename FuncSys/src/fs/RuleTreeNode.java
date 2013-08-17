package fs;

import java.util.LinkedList;
import java.util.List;

public class RuleTreeNode {

    private PredicateSet _condition = null;
    private IAction _action = null;
    private double _probability = 0;
    private List<RuleTreeNode> _children = null;
    private long _eventsCount;

    RuleTreeNode(PredicateSet condition, IAction action, double probability, long eventsCount) {
        _condition = condition;
        _action = action;
        _probability = probability;
        _eventsCount = eventsCount;
    }

    public boolean hasChildren() {
        return _children != null && _children.size() > 0;
    }

    public List<RuleTreeNode> getChildren() {
        return _children;
    }

    public void addChildren(List<RuleTreeNode> value) {
        if(_children == null)
            _children = new LinkedList<RuleTreeNode>();
        _children.addAll(value);
    }

    public PredicateSet getCondition() {
        return _condition;
    }

    public IAction getAction() {
        return _action;
    }

    public double getProbability() {
        return _probability;
    }

    public long getEventsCount() {
        return _eventsCount;
    }
}
