package ru.nsu.alife.fs;

/**
 * Class describes structure with indexed
 * set of predicates with adjusted states
 */
public class PredicateSet {
    private static final String TAG = PredicateSet.class.getName();

    /**
     * Binary mask that describes active predicates
     */
    private long predicates;

    /**
     * Binary mask that describes states (true/false) of active predicates
     */
    private long states;

    /**
     * Constructor. Creates instance with no active predicates
     */
    public PredicateSet() {
        this.reset();
    }

    /**
     * Constructor. Creates instance with adjusted predicates
     *
     * @param predicates describes active predicates
     * @param states     describes values of appropriate predicates (true/false)
     */
    public PredicateSet(long predicates, long states) {
        this.predicates = predicates;
        this.states = states;
    }

    /**
     * Reset current instance's predicates and states to 0
     */
    public void reset() {
        this.predicates = 0;
        this.states = 0;
    }

    /**
     * Set state to predicate at index position
     *
     * @param index predicate position
     * @param value value to set to requested predicate
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public void set(int index, boolean value) throws InvalidArgumentException {
        if (index < 0 || index >= 64)
            throw new InvalidArgumentException(TAG, "set", "index", String.valueOf(index));

        long pattern = 1 << index;

        this.predicates |= pattern;
        if (value)
            this.states |= pattern;
        else
            this.states &= ~pattern;
    }

    public void disable(int index) throws InvalidArgumentException {
        if (index < 0 || index >= 64)
            throw new InvalidArgumentException(TAG, "set", "index", String.valueOf(index));

        long pattern = 1 << index;

        this.predicates &= ~pattern;
        this.states &= ~pattern;
    }

    /**
     * Returns true if pattern predicate set is a subset of this predicate set (all pattern active predicates
     * are active in this) AND those predicates have the same state in both.
     *
     * @param pattern input predicate structure
     * @return true if current set contains input set of predicates,
     *         false if not
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public boolean contains(PredicateSet pattern) throws InvalidArgumentException {
        if (pattern == null)
            throw new InvalidArgumentException(TAG, "matches", "pattern", "null");

        return ~(~pattern.predicates | (this.predicates & ~(this.states ^ pattern.states))) == 0;
    }

    /**
     * Return true if PredicateSet partially contains the pattern.
     * 'Partially matches' means that some (>0 but not all) pattern predicates are
     * active in this PredicateSet and have the same state
     *
     * @param pattern input predicate structure
     * @return true if current set partially contains input set of predicates,
     *         false if not
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public boolean partiallyContains(PredicateSet pattern) throws InvalidArgumentException {
        if (pattern == null)
            throw new InvalidArgumentException(TAG, "matches", "pattern", "null");
        int value = Long.bitCount(~(~pattern.predicates | (this.predicates & ~(this.states ^ pattern.states))));
        return value > 0 && value < Long.bitCount(pattern.predicates);
    }

    /**
     * Get state of predicate at index position
     *
     * @param index predicate position
     * @return state (true/false) of requested predicate,
     *         null if predicate is inactive
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public Boolean getPredicateValue(int index) throws InvalidArgumentException {
        if (index < 0 || index >= 64)
            throw new InvalidArgumentException(TAG, "getPredicateValue", "index", String.valueOf(index));

        long pattern = 1L << index;

        if ((this.predicates & pattern) == 0)
            return null;

        return (this.states & pattern) != 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object.getClass().equals(PredicateSet.class)))
            return false;

        PredicateSet input = (PredicateSet) object;

        return this.predicates == input.predicates &&
                (this.states & this.predicates) == (input.states & input.predicates);
    }

    @Override
    public int hashCode() {
        int result = 23;
        result = hash(result, this.predicates);
        result = hash(result, this.states);
        return result;
    }

    /**
     * Calculate hash for input long
     *
     * @param seed
     * @param value
     * @return hash for input long
     */
    private static int hash(int seed, long value) {
        return 37 * seed + (int) (value ^ (value >>> 32));
    }

    /**
     * Get a copy of current instance
     * (necessary if we want pass this object as immutable)
     *
     * @return copy of current instance
     */
    public PredicateSet copy() {
        return new PredicateSet(this.predicates, this.states);
    }

    /**
     * Bitwise operation 'AND' (&)
     *
     * @param input another instance of PredicateSet
     * @return new PredicateSet with processed predicates and states
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public PredicateSet and(PredicateSet input) throws InvalidArgumentException {
        if (input == null)
            throw new InvalidArgumentException(TAG, "and", "input", "null");

        long predicatesAnd = this.predicates & input.predicates;
        long statesAnd = this.states & input.states;

        return new PredicateSet(predicatesAnd, statesAnd);
    }

    /**
     * Bitwise operation 'AND' (^)
     *
     * @param input another instance of PredicateSet
     * @return new PredicateSet with processed predicates and states
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public PredicateSet xor(PredicateSet input) throws InvalidArgumentException {
        if (input == null)
            throw new InvalidArgumentException(TAG, "xor", "input", "null");

        long predicatesXor = this.predicates ^ input.predicates;
        long statesXor = this.states ^ input.states;

        return new PredicateSet(predicatesXor, statesXor);
    }

    /**
     * Bitwise operation 'OR' (|)
     *
     * @param input another instance of PredicateSet
     * @return new PredicateSet with processed predicates and states
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public PredicateSet or(PredicateSet input) {
        if (input == null)
            throw new InvalidArgumentException(TAG, "or", "input", "null");

        long predicatesOr = this.predicates | input.predicates;
        long statesOr = this.states | input.states;

        return new PredicateSet(predicatesOr, statesOr);
    }

    /**
     * Bitwise operation 'NOT' (~)
     * Invert instance's predicates and states
     *
     * @return new set with inverted predicates and states
     */
    public PredicateSet not() {
        long predicatesNot = ~this.predicates;
        long statesNot = ~this.states;

        return new PredicateSet(predicatesNot, statesNot);
    }

    /**
     * Process current instance's predicates and states
     * with input binary mask
     *
     * @param mask input binary mask
     * @return new set with masked predicates and states
     */
    public PredicateSet andMask(long mask) {
        long predicatesMasked = this.predicates & mask;
        long statesMasked = this.states & mask;

        return new PredicateSet(predicatesMasked, statesMasked);
    }

    /**
     * Merge current instance's predicates and states by bitwise 'AND' (&)
     *
     * @return processed result
     */
    public long merge() {
        return this.predicates & this.states;
    }

    /**
     * Check if current instance has at least one active predicate
     *
     * @return true if it has active predicates
     *         false if not
     */
    public boolean hasActivePredicates() {
        return this.predicates != 0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 64; i++) {
            Boolean val = getPredicateValue(i);
            if (val == null) {
                sb.append(" ");
                continue;
            }
            if (val == true) {
                sb.append("1");
                continue;
            }
            if (val == false) {
                sb.append("0");
                continue;
            }
        }
        return sb.reverse().toString().trim().replace(' ', 'X');
    }
}
