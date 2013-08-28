package fs;

import java.text.AttributedString;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Primary class that provides mechanism of adjusted goal reaching
 */
public class FunctionalSystem implements IFunctionalSystem
{
    Double MIN_PROB = 0.1;
    // Goal of this FS
    PredicateSet goal;

    //minimum prob
    public static final double  MIN_PROBABILITY = 0.4;

    //List of states, that can be managed with this FS
    public HashMap<FunctionalSystem, Rule> rulesToFs;
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
        probabilityTable = new HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>>();
        rules = new HashSet<Rule>();
        rulesToFs = new HashMap<FunctionalSystem, Rule>();
        this.depth = depth;
    }

    public FunctionalSystem(PredicateSet goal, int depth, FunctionalSystem parentFs)
    {
        this.goal = goal;
        this.parentFs = parentFs;
        probabilityTable = new HashMap<PredicateSet, HashMap<FunctionalSystem, Statistics>>();
        rules = new HashSet<Rule>();
        rulesToFs = new HashMap<FunctionalSystem, Rule>();
        this.depth = depth;
    }

    @Override
    public void performAction(IAcceptor acceptor) {

        // Observe situation
        PredicateSet startState = acceptor.getCurrentSituation();

        // Choose the most suitable rule
        Rule matchingRule = null;
        Double ruleProb = MIN_PROB;
        for (Rule rule: rules)
        {
            // It is really an equality condition now
            if (rule.getPredicates().contains(startState) || startState.contains(rule.getPredicates()))
            {
                matchingRule = rule;
                ruleProb = rule.getProbability();
                break;
            }
        }

        // Create row in probTable for current situation
        if(!probabilityTable.containsKey(startState))
        {
            probabilityTable.put(startState, new HashMap<FunctionalSystem, Statistics>());
        }

        // Choose the most suitable fs
        Double bestFSProb = MIN_PROB;
        FunctionalSystem bestFS = null;

        HashMap<FunctionalSystem, Statistics> subProbabilities = probabilityTable.get(startState);
        for (FunctionalSystem subFs: subProbabilities.keySet())
        {
            Statistics prob = subProbabilities.get(subFs);
            if (prob.calcProbability()* this.rulesToFs.get(subFs).getProbability() > bestFSProb)
            {
                //Total probability of reaching main goal
                bestFSProb = prob.calcProbability() * this.rulesToFs.get(subFs).getProbability();
                bestFS = subFs;
            }
        }

        //Choose action or subfs
        IAction action = null;
        if (ruleProb >= bestFSProb && ruleProb > MIN_PROB)
        {
            action = matchingRule.getAction();
            acceptor.getMemory().isRandom = false;
        }
        else if (bestFSProb > ruleProb && bestFSProb > MIN_PROB)
        {
            bestFS.performAction(acceptor);
            return;
        }
        else
        {
            Random random = new Random();
            int choice = random.nextInt(10);
            if (choice < 8 || this.rulesToFs.keySet().size() == 0)
            {
                action = acceptor.getRandomAction();
                acceptor.getMemory().isRandom = true;
            }
            else
            {
                choice = random.nextInt(this.rulesToFs.keySet().size());
                int i = 0;
                for (FunctionalSystem fs: this.rulesToFs.keySet())
                {
                    if (i == choice)
                    {
                        if (!this.probabilityTable.get(startState).containsKey(fs))
                        {
                            this.probabilityTable.get(startState).put(fs, new Statistics());
                        }
                        fs.performAction(acceptor);
                        return;
                    }
                    i++;
                }
            }
        }

        acceptor.performAction(action);
        acceptor.getMemory().lastFs = this;
        acceptor.getMemory().lastAction = action;
        acceptor.getMemory().initialSituation = startState;
    }

    @Override
    public void seeResult(IAcceptor acceptor) {
        PredicateSet endState = acceptor.getCurrentSituation();
        History history = acceptor.getHistoryInstance();
        FunctionalSystem lastFs = (FunctionalSystem)acceptor.getMemory().lastFs;
        // Memorize an event
        history.addEvent(acceptor.getMemory().initialSituation, acceptor.getMemory().lastAction, endState);
        if (!acceptor.getMemory().isRandom)
        {
            // If an action was not random
            lastFs.updateFS(acceptor);
        }
        else
        {
            // If an action was random
            lastFs.addRule(acceptor, endState);
            for (Rule rule: lastFs.rules)
            {
                if (!lastFs.rulesToFs.containsValue(rule) && lastFs.depth > 0)
                {
                    FunctionalSystem fs = lastFs.createSubFS(rule.getPredicates(), lastFs, acceptor);
                    lastFs.rulesToFs.put(fs, rule);
                }
            }

            if(lastFs.goal.contains(endState))
            {
                lastFs.updateFS(acceptor);
            }

        }

    }

    @Override
    public RuleTreeNode getRuleTreeRoot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void addRule( IAcceptor acceptor, PredicateSet endSituation )
    {
        PredicateSet startSituation = acceptor.getMemory().initialSituation;
        IAction action = acceptor.getMemory().lastAction;

        History history = acceptor.getHistoryInstance();

        if( endSituation.contains( this.goal ) || (this.goal.contains(endSituation)))
        {
            Statistics statistics = history.getStatistics( startSituation,
                    action, endSituation );
            if( statistics.calcProbability() > MIN_PROBABILITY )
            {
                Rule rule = new Rule( startSituation, action, statistics );
                rules.add( rule );
            }
            //acceptor.getMemory().lastFs.updateFS(acceptor);
        }
    }

    private void updateFS(IAcceptor acceptor)
    {

        Rule ruleToUpdate = null;
        for (Rule rule: rules)
        {
            if (rule.getPredicates().contains(acceptor.getMemory().initialSituation) && acceptor.getMemory().lastAction == rule.getAction())
            {
                ruleToUpdate = rule;
                break;
            }
        }
        if(ruleToUpdate == null) return;

        Statistics stats = null;
        HashMap<FunctionalSystem, Statistics> subProbabilities;
        if (parentFs != null)
        {
            subProbabilities = parentFs.probabilityTable.get(acceptor.getMemory().initialSituation);
            if (!subProbabilities.containsKey(this))
            {
                subProbabilities.put(this, new Statistics());
            }
            stats = subProbabilities.get(this);
        }

        if (this.goal.contains(acceptor.getCurrentSituation()) || acceptor.getCurrentSituation().contains(this.goal))
        {
            ruleToUpdate.encourage();
            if (stats != null)
                stats.addTestResult(Boolean.TRUE);
            FunctionalSystem fs = this;
            while (fs.parentFs != null)
            {

                if(fs.parentFs.probabilityTable.containsKey(acceptor.getMemory().initialSituation))
                {
                    subProbabilities = fs.parentFs.probabilityTable.get(acceptor.getMemory().initialSituation);
                    stats = subProbabilities.get(fs);
                    stats.addTestResult(Boolean.TRUE);
                }
                fs = fs.parentFs;
            }
        }
        else
        {
            ruleToUpdate.punish();
            if (stats != null)
                stats.addTestResult(Boolean.FALSE);
            FunctionalSystem fs = this;
            while (fs.parentFs != null)
            {

                if(fs.parentFs.probabilityTable.containsKey(acceptor.getMemory().initialSituation))
                {
                    subProbabilities = fs.parentFs.probabilityTable.get(acceptor.getMemory().initialSituation);
                    stats = subProbabilities.get(fs);
                    stats.addTestResult(Boolean.FALSE);
                }
                fs = fs.parentFs;
            }
        }
        this.updateRules(acceptor);
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
     * @throws fs.InvalidArgumentException
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

    /*
     */
    private void updateRules( IAcceptor acceptor)
    {
        History history = acceptor.getHistoryInstance();
        Set< Event > events = history.getEventsWithResult(goal);
        Set< Rule > rules = generateRules( events, goal );
        Set< Rule > rulesToAdd = new HashSet<Rule>();
        for( Rule rule : rules )
        {
            if (!this.rules.contains(rule))
            {
                rulesToAdd.add(rule);
                if (this.depth > 0)
                {
                    FunctionalSystem fs = createSubFS(rule.getPredicates(), this, acceptor);
                    this.rulesToFs.put(fs, rule);
                }
            }
        }

        this.rules.addAll(rulesToAdd);
    }

    public String getRulesToString()
    {
        String str = "RULES: ";
        for( Rule rule : rules)
        {
            str = str.concat(rule.toString() + " || ");

        }
        return str;
    }

    public Set <FunctionalSystem> getLinkToSubFS()
    {
        return this.rulesToFs.keySet();
    }

    public Set <Rule> getRules()
    {
        return this.rules;
    }

    public String probTableToString(){
        String str = "";
        for (PredicateSet situation: this.probabilityTable.keySet())
        {
            str = str.concat(" " + situation.toString());
            HashMap<FunctionalSystem, Statistics> probs = probabilityTable.get(situation);
            for (FunctionalSystem fs: probs.keySet())
            {
                str = str.concat(" " + probs.get(fs).calcProbability());
            }

        }
        return str;
    }
}
