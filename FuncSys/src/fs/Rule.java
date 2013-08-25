package fs;

/**
 * Class describes structure of a rule with
 * set of predicates, action to perform, statistics
 * and an ordered list of sub-goals that should be reached
 * before performing action
 */
public class Rule {
    private static final String TAG = Rule.class.getName();

    /**
     * Set of predicates describes situation
     * that should be reached to perform action
     */
    private final PredicateSet predicates;

    /**
     * Action to perform after all conditions are fulfilled
     */
    private final IAction action;

    /**
     * Statistics of current instance
     */
    private Statistics statistics;

    /**
     * Constructor. Creates instance with
     * adjusted predicates-condition, action to perform,
     * and statistics
     *
     * @param predicates condition of rule execution
     * @param action     action to perform after condition fulfilled
     * @param statistics statistics
     */
    public Rule(PredicateSet predicates, IAction action, Statistics statistics) {
        if (predicates == null)
            throw new InvalidArgumentException(TAG, "Rule", "predicates", "null");
        if (action == null)
            throw new InvalidArgumentException(TAG, "Rule", "action", "null");
        if (statistics == null)
            throw new InvalidArgumentException(TAG, "Rule", "statistics", "null");

        this.predicates = predicates;
        this.action = action;
        this.statistics = statistics;
    }

    /**
     * Execute current instance's rule.
     *
     * @param acceptor callback interface
     * @return true if action was performed
     *         false if not
     * @throws fs.InvalidArgumentException
     *
     */
    public void execute(IAcceptor acceptor) {
        if (acceptor == null)
            throw new InvalidArgumentException(TAG, "execute", "acceptor", "null");

        acceptor.performAction(this.action);
    }

    /**
     * Improve rule statistics
     */
    public void encourage() {
        this.statistics.addTestResult(true);
    }

    /**
     * Impair rule statistics
     */
    public void punish() {
        this.statistics.addTestResult(false);
    }

    /**
     * Getter for current instance's action
     *
     * @return action
     */
    public IAction getAction() {
        return this.action;
    }

    /**
     * Getter for current instance's predicates
     *
     * @return predicates
     */
    public PredicateSet getPredicates() {
        return this.predicates;
    }

    /**
     * Get current instance's predicates and input's predicates common part
     *
     * @param rule rule with predicates to retrieve
     * @return common part predicates
     * @throws fs.InvalidArgumentException
     *
     */
    public PredicateSet getGeneralizedPredicates(Rule rule) throws InvalidArgumentException {
        if (rule == null)
            throw new InvalidArgumentException(TAG, "getCommonPredicates", "rule", "null");

        PredicateSet predicatesXor = (this.predicates.xor(rule.predicates)).not();
        long mask = predicatesXor.merge();

        PredicateSet predicatesAnd = (this.predicates.and(rule.predicates)).andMask(mask);

        return predicatesAnd;
    }

    /**
     * Retrieve probability of current instance from statistics
     *
     * @return rule probability
     */
    public double getProbability() {
        return this.statistics.calcProbability();
    }

    public long getEventsCount(){
        return this.statistics.getResultsTotal();
    }

    /**
     * Check if rule has the same action as input
     *
     * @param rule input rule to check
     * @return true if rules have same action
     *         false if them haven't
     */
    public boolean hasSameAction(Rule rule) {
        return this.action == rule.action;
    }

    /**
     * A Rule matches the situation if and only if the situation predicate set
     * contains all the rule predicates in the same state
     *
     * @param situation situation to match
     * @return true if situation is matched by rule
     *         false if not
     * @throws fs.InvalidArgumentException
     *
     */
    public boolean matches(PredicateSet situation)
            throws InvalidArgumentException {
        if (situation == null)
            throw new InvalidArgumentException(TAG, "matches", "predicates", "null");

        return situation.contains(this.predicates);
    }


    public boolean isBetter(Rule rule){
        return rule == null || this.statistics.isBetter(rule.statistics);
    }

    public void updateStatistics(Statistics statistics){
        this.statistics = statistics;
    }

    /**
     * The Rule partially contains another Rule if the rule predicate set
     * contains one or more predicates of another rule (not all of them) and
     * those predicates have the same state
     *
     * @param rule
     * @return true if current rule partially contains input
     *         false if not
     */
    public boolean partiallyContains(Rule rule) {
        return this.predicates.partiallyContains(rule.predicates);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object.getClass().equals(Rule.class)))
            return false;

        Rule input = (Rule) object;

        return this.predicates.equals(input.predicates) && this.action == input.action;
    }

    @Override
    public int hashCode() {
        int result = 23;
        result = hash(result, this.predicates.hashCode());
        result = hash(result, this.action.hashCode());
        return result;
    }

    private static int hash(int seed, long value) {
        return 37 * seed + (int) (value ^ (value >>> 32));
    }

    @Override
    public String toString() {
        return "Rule{ " +
                predicates + " + " +
                action + " [" +
                statistics.calcProbability() + "] " +
                '}';
    }
}
