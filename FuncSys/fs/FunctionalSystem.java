package ru.nsu.alife.fs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Primary class that provides mechanism of adjusted goal reaching
 */
public class FunctionalSystem implements IFunctionalSystem
{
    // Goal of this FS
    PredicateSet goal;

    //minimum prob
    public static final double  MIN_PROBABILITY = 0.4;

    //List of states, that can be managed with this FS
    HashMap<FunctionalSystem, Rule> rulesToFs;
    Set<Rule> rules;

    //Table of probabilities
    HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>> probabilityTable;

    //Parent FS
    FunctionalSystem parentFs;

    //Maximum depth
    int depth;

    public FunctionalSystem(PredicateSet goal, int  depth)
    {
        this.goal = goal;
        this.parentFs = null;
        HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>> probabilityTable = new HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>>();
        Set<Rule> rules = new HashSet<Rule>();
        HashMap<FunctionalSystem, Rule> rulesToFs = new HashMap<FunctionalSystem, Rule>();
        this.depth = depth;
    }

    public FunctionalSystem(PredicateSet goal, int depth, FunctionalSystem parentFs)
    {
        this.goal = goal;
        this.parentFs = parentFs;
        HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>> probabilityTable = new HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>>();
        Set<Rule> rules = new HashSet<Rule>();
        HashMap<FunctionalSystem, Rule> rulesToFs = new HashMap<FunctionalSystem, Rule>();
        this.depth = depth;
    }

    @Override
    public void performAction(IAcceptor acceptor) {

        PredicateSet startState = acceptor.getCurrentSituation();

        Rule matchingRule = null;
        Double ruleProb = null;
        for (Rule rule: rules)
        {
            if (rule.getPredicates().contains(startState))
            {
                matchingRule = rule;
                ruleProb = rule.getProbability();
                break;
            }
        }

        Double bestFSProb = 0.0;
        FunctionalSystem bestFS = null;
        if (probabilityTable.containsKey(startState))
        {
            HashMap<FunctionalSystem, Statistics> subProbabilities = probabilityTable.get(startState);
            for (FunctionalSystem subFs: subProbabilities.keySet())
            {
                Statistics prob = subProbabilities.get(subFs);
                if (prob.calcProbability()* this.rulesToFs.get(subFs).getProbability() > bestFSProb)
                {
                    bestFSProb = prob.calcProbability() * this.rulesToFs.get(subFs).getProbability();
                    bestFS = subFs;
                }
            }
        }

        IAction action = null;

        if (matchingRule == null && bestFS == null)
        {
            action = acceptor.getRandomAction();
            action.doAction();
            acceptor.memory.isRandom = Boolean.TRUE;
        }
        else if (matchingRule == null && bestFSProb == 0.0)
        {
            Random random = new Random();
            int item = random.nextInt(this.rulesToFs.keySet().size());
            for (int i =0; i < item; i++)
            {
                bestFS =  this.rulesToFs.keySet().iterator().next();
            }
            if (!this.probabilityTable.containsKey(startState))
            {
                this.probabilityTable.put(startState, new HashMap<FunctionalSystem, Statistics>());
            }
            bestFS.performAction(acceptor);
            return;
        }

        else if (ruleProb >= bestFSProb)
        {
            action = matchingRule.getAction();
            action.doAction();
            acceptor.memory.isRandom = Boolean.FALSE;
        }

        else
        {
            if (!this.probabilityTable.containsKey(startState))
            {
                this.probabilityTable.put(startState, new HashMap<FunctionalSystem, Statistics>());
            }
            bestFS.performAction(acceptor);
            return;
        }

        acceptor.memory.lastFs = this;
        acceptor.memory.lastAction = action;
        acceptor.memory.initialSituation = startState;
    }

    @Override
    public void seeResult(IAcceptor acceptor) {
        PredicateSet endState = acceptor.getCurrentSituation();
        History history = acceptor.getHistoryInstance();
        history.addEvent(acceptor.memory.initialSituation, acceptor.memory.lastAction, endState);
        if (!acceptor.memory.isRandom)
        {
            acceptor.memory.lastFs.updateFS(acceptor);
        }
        else
        {
            acceptor.memory.lastFs.addRule(acceptor, endState);
        }

    }

    @Override
    public RuleTreeNode getRuleTreeRoot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void addRule( IAcceptor acceptor, PredicateSet endSituation )
    {
        PredicateSet startSituation = acceptor.memory.initialSituation;
        IAction action = acceptor.memory.lastAction;

        History history = acceptor.getHistoryInstance();

        if( endSituation.contains( this.goal ) )
        {
            Statistics statistics = history.getStatistics( startSituation,
                    action, endSituation );
            if( statistics.calcProbability() > MIN_PROBABILITY )
            {
                Rule rule = new Rule( startSituation, action, statistics );
                rules.add( rule );
            }
        }
    }

    private void updateFS(IAcceptor acceptor)
    {
        Rule ruleToUpdate = null;
        for (Rule rule: rules)
        {
            if (rule.getPredicates().contains(acceptor.memory.initialSituation) && acceptor.memory.lastAction == rule.getAction())
            {
                ruleToUpdate = rule;
                break;
            }
        }

        HashMap<FunctionalSystem, Statistics> subProbabilities = parentFs.probabilityTable.get(acceptor.memory.initialSituation);
        if (!subProbabilities.containsKey(this))
        {
            subProbabilities.put(this, new Statistics());
        }
        Statistics stats = subProbabilities.get(this);

        if (this.goal.contains(acceptor.getCurrentSituation()))
        {
            ruleToUpdate.encourage();
            stats.addTestResult(Boolean.TRUE);
        }
        else
        {
            ruleToUpdate.punish();
            stats.addTestResult(Boolean.FALSE);
            FunctionalSystem fs = this.parentFs;
            while (fs.parentFs != null)
            {
                subProbabilities = fs.parentFs.probabilityTable.get(acceptor.memory.initialSituation);
                stats = subProbabilities.get(this);
                stats.addTestResult(Boolean.FALSE);
                fs = fs.parentFs;
            }
        }
    }

    private FunctionalSystem createSubFS(PredicateSet goal, FunctionalSystem parentFS, IAcceptor acceptor)
    {
        FunctionalSystem fs = new FunctionalSystem(goal, this.depth - 1, parentFS);
        History history = acceptor.getHistoryInstance();
        Set< Event > events = history.getEventsWithResult(goal);
        Set< Rule > rules = generateRules( events, goal );

        for( Rule rule : rules )
            fs.rules.add(rule);
        return fs;
    }

    /**
     * Generate the rules from a set of events having a certain eventResult
     *
     * @param events
     *            input set of events
     * @param eventResult
     *            result criteria to rule generation
     * @return generated set of rules
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    private static Set< Rule > generateRules( Set< Event > events, PredicateSet eventResult )
    {
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
                rules.add(rule);
            }
        }

        for(Rule rule : rules){
            PredicateSet rulePredicates = rule.getPredicates();
            IAction ruleAction = rule.getAction();
            Statistics statistics = acceptor
                    .getHistoryInstance()
                    .getStatistics(rulePredicates, ruleAction, goal);

            rule.updateStatistics(statistics);
        }

        for( FunctionalSystem fs : rulesToFs.keySet() )
        {
            if( fs != null )
                fs.updateRules(acceptor, startSituation, action, endSituation);
        }
    }

}
