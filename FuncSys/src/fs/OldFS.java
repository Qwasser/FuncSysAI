package fs;

import java.util.*;

/**
 * Primary class that provides mechanism of adjusted goal reaching
 */
public class OldFS implements IFunctionalSystem
{
    private static final String	TAG	            = OldFS.class.getName();

    /**
     * Lowest limit of rule probability in the set Rule with lower probability
     * will be removed
     */
    private static double	    MIN_PROBABILITY	= 0.95;

    /**
     * Generate the rules from a set of events having a certain eventResult
     *
     * @param events
     *            input set of events
     * @param eventResult
     *            result criteria to rule generation
     * @return generated set of rules
     *
     */
    private static Set< Rule > generateRules( Set< Event > events, PredicateSet eventResult )
    {
        if( events == null )
            throw new InvalidArgumentException( TAG, "generateRules", "events",
                    "null" );
        if( eventResult == null )
            throw new InvalidArgumentException( TAG, "generateRules",
                    "eventResult", "null" );

        Set< Rule > resultRules = new HashSet< Rule >();
        for( Event event : events )
        {
            PredicateSet rulePredicates = event.getStartState();
            IAction ruleAction = event.getAction();
            Statistics statistics = event.getResultStatistics( eventResult );

            if( statistics.calcProbability() > MIN_PROBABILITY )
                resultRules.add( new Rule( rulePredicates, ruleAction, statistics ) );
        }

        return resultRules;
    }

    /**
     * Merge two FSs to one by generalization FS' goals and merging rules and
     * sub-goal sets
     *
     * @param fsA
     *            first FS
     * @param fsB
     *            second FS
     * @param goal
     *            already generalized goal
     * @return merged FS
     */
    private OldFS mergeFs(OldFS fsA, OldFS fsB, PredicateSet goal)
    {
        OldFS resultFS = null;
        if( fsA != null && fsB != null )
        {
            resultFS = new OldFS( goal, fsA.depth, fsA.parent );

            resultFS.rules.putAll(fsA.rules);
            resultFS.rules.putAll( fsB.rules );
        }
        else if( fsA != null )
        {
            resultFS = new OldFS( goal, fsA.depth, fsA.parent );

            resultFS.rules.putAll( fsA.rules );
        }
        else if( fsB != null )
        {
            resultFS = new OldFS( goal, fsB.depth, fsB.parent );

            resultFS.rules.putAll( fsB.rules );
        }

        return resultFS;
    }

    /**
     * Primary goal of FS
     */
    private final PredicateSet	      goal;

    /**
     * Map that represents set of rules usable in different situations mapped to
     * set of FS, which represents sub-goals
     */
    private final HashMap< Rule, OldFS > rules;

    /**
     * Depth of current FS in the hierarchy. Descending
     */
    private final int	              depth;

    private final OldFS                parent;

    /**
     * Constructor. Creates FS with new goal
     *
     * @param goal
     *            primary FS goal
     * @param depth
     *            depth in the hierarchy
     */
    public OldFS( PredicateSet goal, int depth )
    {
        this.goal = goal;
        this.depth = depth;
        this.parent = null;

        this.rules = new HashMap< Rule, OldFS >();
    }

    private OldFS( PredicateSet goal, int depth, OldFS parent){
        this.goal = goal;
        this.depth = depth;
        this.parent = parent;

        this.rules = new HashMap< Rule, OldFS >();
    }

    /**
     * Find the most appropriate rule in the rules set
     *
     * @param situation
     *            input situation criteria
     * @return founded rule
     */
    private Rule findRule( PredicateSet situation )
    {
        double maxProbability = 0;
        Set< Rule > rulesToRemove = new HashSet< Rule >();
        Rule bestRule = null;
        for( Rule rule : this.rules.keySet() )
        {
            if( rule.matches( situation ) )
            {
                double probability = rule.getProbability();
                if( probability > MIN_PROBABILITY )
                {
                    if( rule.isBetter(bestRule) )
                    {
                        bestRule = rule;
                        maxProbability = probability;
                    }
                }
                else
                {
                    rulesToRemove.add( rule );
                }
            }
        }

        for( Rule rule : rulesToRemove )
            this.rules.remove( rule );

        return bestRule;
    }

    /**
     * Find the most appropriate sub-goal to achieve
     *
     * @param situation
     *            input situation criteria
     * @return founded FS
     */
    private OldFS findSubFs( PredicateSet situation )
    {
        double maxProbability = 0;
        OldFS bestFs = null;
        for( OldFS fs : this.rules.values() )
        {
            if( fs == null )
                continue;

            double probability = fs.predictProbability( situation );
            if( probability > MIN_PROBABILITY && probability > maxProbability )
            {
                bestFs = fs;
                maxProbability = probability;
            }
        }

        return bestFs;
    }

    /**
     * Rules and sub-goals generalization mechanism
     *
     * @param history
     */
    private void generalizeRules( History history )
    {
        Set< Rule > rulesToRemove = new HashSet< Rule >();
        HashMap< Rule, OldFS > rulesToAdd = new HashMap< Rule, OldFS >();

        do
        {
            rulesToAdd.clear();
            rulesToRemove.clear();
            LinkedList< Rule > ruleList = new LinkedList< Rule >( this.rules.keySet() );
            for( Iterator< Rule > iteratorA = ruleList.iterator(); iteratorA.hasNext(); )
            {
                Rule ruleA = iteratorA.next();
                for( Iterator< Rule > iteratorB = ruleList.descendingIterator(); iteratorB
                        .hasNext(); )
                {
                    Rule ruleB = iteratorB.next();

                    if( ruleA == ruleB )
                        break;

                    if( !ruleA.hasSameAction( ruleB ) )
                    {
                        continue;
                    }

                    PredicateSet newSituation = ruleA.getGeneralizedPredicates( ruleB );
                    if( !newSituation.hasActivePredicates() )
                        continue;

                    if( !ruleA.partiallyContains( ruleB ) && !ruleB.partiallyContains( ruleA ) )
                        continue;

                    IAction action = ruleA.getAction();

                    Statistics statistics = history.getStatistics( newSituation, action, this.goal );
                    double statCalculated = statistics.calcProbability();
                    if( statCalculated >= ruleA.getProbability()
                            && statCalculated >= ruleB.getProbability() )
                    {
                        OldFS newFS;
                        if( rules.get( ruleA ) == null && rules.get( ruleB ) == null )
                            newFS = generateSubFS(history, newSituation);
                        else
                            newFS = mergeFs(rules.get( ruleA ), rules.get( ruleB ),
                                    newSituation );
                        Rule newRule = new Rule( newSituation, action, statistics );

                        rulesToRemove.add( ruleA );
                        rulesToRemove.add( ruleB );

                        for( Rule rule : this.rules.keySet() )
                        {
                            if( rule.getAction() == action && rule.matches( newSituation ) )
                            {
                                rulesToRemove.add( rule );
                                OldFS fs = this.rules.get( rule );
                                if( fs != null )
                                    newFS = mergeFs(newFS, fs, newSituation );
                            }
                        }
                        rulesToAdd.put( newRule, newFS );
                    }
                }
            }

            for( Rule rule : rulesToRemove )
                this.rules.remove( rule );

            this.rules.putAll( rulesToAdd );

        } while( rulesToAdd.size() > 0 || rulesToRemove.size() > 0 );

        rulesToRemove.clear();
        for( Rule entry : rules.keySet() )
            if( entry.getProbability() < MIN_PROBABILITY )
                rulesToRemove.add( entry );

        for( Rule rule : rulesToRemove )
            this.rules.remove( rule );
    }

    /**
     * Sub-goals generation mechanism.
     *
     * @param history
     *            History
     *
     */
    private OldFS generateSubFS( History history, PredicateSet goal )
    {
        OldFS resultFs;
        if( depth > 0 )
            resultFs = new OldFS( goal, depth - 1, this);
        else
            return null;

        Set< Event > events = history.getEventsWithResult( goal );
        Set< Rule > rules = generateRules( events, goal );

        for( Rule rule : rules )
            resultFs.rules.put( rule, null );

        // resultFs.generalizeRules( history );

        filterRulesLowEventCount();

        return resultFs;
    }

    /**
     * Get a rule for a given subFS
     *
     * @param fs
     *            - an fs to find a rule for
     * @return rule associated with given fs if any, null otherwise
     */
    private Rule getRuleForFs( OldFS fs )
    {
        Rule result = null;
        for( Rule item : this.rules.keySet() )
        {
            if( rules.get( item ) == fs )
            {
                result = item;
                break;
            }
        }

        return result;
    }

    /**
     * Predict goal reaching probability that can be retrieved using this FS
     *
     * @param situation
     *            input situation
     * @return probability
     */
    private double predictProbability( PredicateSet situation )
    {
        Rule rule = findRule( situation );
        OldFS subFs = findSubFs( situation );

        double ruleProbability = rule != null ? rule.getProbability() : 0;
        double subFsProbability;

        if( subFs == null )
            subFsProbability = 0;
        else
        {
            Rule tRule = null;
            for( Rule item : this.rules.keySet() )
            {
                if( rules.get( item ) == subFs )
                {
                    tRule = item;
                    break;
                }
            }

            subFsProbability = subFs.predictProbability( situation )
                    * (tRule == null ? 1 : tRule.getProbability());
        }

        return Math.max( ruleProbability, subFsProbability );
    }

    /**
     * <p>
     * Main method. Tries to reach FS' goal, performing steps:
     * </p>
     * <p>
     * 1. get current situation from acceptor
     * </p>
     * <p>
     * 2. find a rule appropriate in current situation
     * </p>
     * <p>
     * find a sub-goal to reach appropriate in current situation
     * </p>
     * <p>
     * + if no rule and subFS found -> perform random action
     * </p>
     * <p>
     * + if rule execution has higher probability to reach goal than subFS
     * reaching -> execute found rule
     * </p>
     * <p>
     * + if rule execution has lower probability to reach goal than subFS
     * reaching -> try to reach sub-goal
     * </p>
     * <p>
     * 3. get changed situation from acceptor
     * </p>
     * <p>
     * 4. compare goal and changed situations
     * </p>
     * <p>
     * 5. if matches -> increase probability of a rule
     * </p>
     * <p>
     * 6. if not matches -> decrease probability of a rule
     * </p>
     *
     * @param acceptor
     *            callback interface to retrieve data
     * @return true if goal reached, false if not
     *
     */

    public boolean reachGoal( IAcceptor acceptor )
            throws InvalidArgumentException
    {
        if( acceptor == null )
            throw new InvalidArgumentException( TAG, "reachGoal", "acceptor", "null" );

        // MIN_PROBABILITY = 1.0 - acceptor.getProactivity();
        IAction actionPerformed = null;
        PredicateSet endState;
        PredicateSet startState = acceptor.getCurrentSituation();

        if( startState.contains( this.goal ) )
            return true;

        Rule rule = this.findRule( startState );
        OldFS subFs = this.findSubFs( startState );
        double ruleProbability = rule != null ? rule.getProbability() : 0;
        double subFsProbability;

        if( subFs == null )
            subFsProbability = 0;
        else
        {
            Rule tRule = getRuleForFs( subFs );

            subFsProbability = subFs.predictProbability( startState )
                    * tRule.getProbability();
        }

        if( ruleProbability < subFsProbability )
        {
            rule = getRuleForFs( subFs );

            if( subFs.reachGoal( acceptor ) )
            {
                Rule rule1 = this.findRule( acceptor.getCurrentSituation() );
                if( rule1 != null && rule1.getProbability() > ruleProbability )
                    rule = rule1;
                rule.execute( acceptor );
                actionPerformed = rule.getAction();
            }
            else
            {
                return false;
            }
        }
        else
        {
            if( rule == null )
            {
                actionPerformed = acceptor.performRandomAction();
            }
            else
            {
                rule.execute( acceptor );
                actionPerformed = rule.getAction();
            }
        }

        endState = acceptor.getCurrentSituation();
        this.fireEvent(acceptor, startState, actionPerformed, endState);
        boolean goalReached = endState.contains( this.goal );

        if( goalReached )
        {
            if( rule == null )
            {
                rule = new Rule( startState, actionPerformed, new Statistics() );
            }

            if( !rules.containsKey( rule ) || rules.get( rule ) == null )
                rules.put( rule, generateSubFS( acceptor.getHistoryInstance(), startState ) );

            rule.encourage();

            this.generalizeRules( acceptor.getHistoryInstance() );
        }
        else
        {
            if( rule != null )
                rule.punish();
            if( subFs != null && subFsProbability < MIN_PROBABILITY )
                rules.remove( getRuleForFs( subFs ) );
        }

        return goalReached;
    }

    private void fireEvent(IAcceptor acceptor, PredicateSet startSituation, IAction action, PredicateSet endSituation){
        if(parent != null){
            parent.fireEvent(acceptor, startSituation, action, endSituation);
        } else {
            this.updateRules(acceptor, startSituation, action, endSituation);
        }
    }

    /**
     * Update set of rules of current FS and all it's subFS if endSituation
     * contains current FS' goal
     *
     * @param acceptor
     *             callback interface to retrieve data
     * @param startSituation
     *            start situation to generate rule
     * @param action
     *            action to generate rule
     * @param endSituation
     *            situation to check
     */
    private void updateRules( IAcceptor acceptor, PredicateSet startSituation, IAction action,
                              PredicateSet endSituation )
    {
        History history = acceptor.getHistoryInstance();

        if( endSituation.contains( this.goal ) )
        {
            Statistics statistics = history.getStatistics( startSituation,
                    action, endSituation );
            if( statistics.calcProbability() > MIN_PROBABILITY )
            {
                Rule rule = new Rule( startSituation, action, statistics );
                rules.put( rule, null );

                this.generalizeRules( history );

                filterRulesLowEventCount();
            }
        }

        for(Rule rule : rules.keySet()){
            PredicateSet rulePredicates = rule.getPredicates();
            IAction ruleAction = rule.getAction();
            Statistics statistics = acceptor
                    .getHistoryInstance()
                    .getStatistics(rulePredicates, ruleAction, goal);

            rule.updateStatistics(statistics);
        }

        for( OldFS fs : rules.values() )
        {
            if( fs != null )
                fs.updateRules(acceptor, startSituation, action, endSituation);
        }
    }

    private void filterRulesLowEventCount(){
        Set<Rule> rulesToRemove = new HashSet<Rule>();
        double maxEventCount = 0;
        for (Rule rule : this.rules.keySet()){
            maxEventCount =
                    maxEventCount < rule.getEventsCount()
                            ? rule.getEventsCount()
                            : maxEventCount;
        }
        for (Rule rule : this.rules.keySet()){
            if(rule.getEventsCount() / maxEventCount < 0.05){
                rulesToRemove.add(rule);
            }
        }
        for (Rule rule : rulesToRemove)
            this.rules.remove(rule);
    }

    @Override
    public void performAction(IAcceptor acceptor) {
        if( acceptor == null )
            throw new InvalidArgumentException( TAG, "reachGoal", "acceptor", "null" );

        IAction actionPerformed = null;
        PredicateSet startState = acceptor.getCurrentSituation();

        Rule rule = this.findRule( startState );
        OldFS subFs = this.findSubFs( startState );
        double ruleProbability = rule != null ? rule.getProbability() : 0;
        double subFsProbability;

        if( subFs == null )
            subFsProbability = 0;
        else
        {
            Rule tRule = getRuleForFs( subFs );

            subFsProbability = subFs.predictProbability( startState )
                    * tRule.getProbability();
        }

        if( ruleProbability < subFsProbability )
        {
            subFs.performAction(acceptor);
            return;

        }
        else
        {
            if( rule == null )
            {
                actionPerformed = acceptor.performRandomAction();
                acceptor.getMemory().isRandom = true;
            }
            else
            {
                rule.execute( acceptor );
                actionPerformed = rule.getAction();
            }
        }

        acceptor.getMemory().lastAction = actionPerformed;
        acceptor.getMemory().lastFs = this;
        acceptor.getMemory().initialSituation = startState;
        acceptor.getMemory().rule = rule;
    }

    @Override
    public void seeResult(IAcceptor acceptor) {
        PredicateSet endState = acceptor.getCurrentSituation();

        IAction actionPerformed = acceptor.getMemory().lastAction;
        OldFS lastFs = (OldFS)acceptor.getMemory().lastFs;
        PredicateSet startState = acceptor.getMemory().initialSituation;
        boolean wasRandom = acceptor.getMemory().isRandom;

        Rule rule = acceptor.getMemory().rule;
        lastFs.fireEvent(acceptor, startState, actionPerformed, endState);
        boolean goalReached = endState.contains( lastFs.goal );

        if( goalReached )
        {
            if( wasRandom )
            {
                rule = new Rule( startState, actionPerformed, new Statistics() );
            }

            if( !lastFs.rules.containsKey( rule ) || lastFs.rules.get( rule ) == null )
                lastFs.rules.put( rule, generateSubFS( acceptor.getHistoryInstance(), startState ) );

            rule.encourage();

            this.generalizeRules( acceptor.getHistoryInstance() );
        }
        else
        {
            if( rule != null )
                rule.punish();
        }
    }

    /**
     * Get complete tree of rules and their dependencies
     * for current FS instance
     *
     * @return root element
     */
    @Override
    public RuleTreeNode getRuleTreeRoot(){
        RuleTreeNode result = new RuleTreeNode(goal, null, 0, 0);
        result.addChildren(getRuleTreeChildren());
        return result;
    }

    @Override
    public Set<FunctionalSystem> getLinkToSubFS() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRulesToString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<Rule> getRules() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String probTableToString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("FS: ");
        for (Rule rule: this.rules.keySet())
        {
            str.append(rule.toString());
        }
        return str.toString();
    }

    /**
     * Recursive method. Building tree of rules and
     * their dependencies for current FS instance
     *
     * @return children elements
     */
    private List<RuleTreeNode> getRuleTreeChildren() {
        List<RuleTreeNode> result = new LinkedList<RuleTreeNode>();
        for(Rule item : rules.keySet()){
            RuleTreeNode child
                    = new RuleTreeNode(item.getPredicates(), item.getAction(), item.getProbability(), item.getEventsCount());

            if (rules.get(item) != null)
                child.addChildren(rules.get(item).getRuleTreeChildren());

            result.add(child);
        }

        return result;
    }
}

