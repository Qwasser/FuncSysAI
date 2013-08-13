package ru.nsu.alife.fs;

/**
 * Class describes statistic stricture
 * with counter of positive results
 * and counter of total results of,
 * for example, some event
 */
class Statistics {
    private static final String TAG = Statistics.class.getName();

    /**
     * Counter of total results
     */
    private long resultsTotal;

    /**
     * Counter of positive results
     */
    private long resultsPositive;

    /**
     * Constructor. Creates instance with no statistics
     */
    public Statistics() {
        this.resultsTotal = 0;
        this.resultsPositive = 0;
    }

    /**
     * Constructor. Creates instance with adjusted statistics
     */
    public Statistics(long resultsTotal, long resultsPositive) {
        if (resultsTotal < resultsPositive)
            throw new InvalidArgumentException(TAG, "EventProbability", resultsTotal + "<" + resultsPositive);

        this.resultsTotal = resultsTotal;
        this.resultsPositive = resultsPositive;
    }

    /**
     * Calculate actual probability of event from stored statistics
     *
     * @return probability of event
     */
    public double calcProbability() {
        if (resultsTotal == 0)
            return 0;

        return (double) resultsPositive / (double) resultsTotal;
    }

    /**
     * Calculate actual dispersion of event from stored statistics
     *
     * @return dispersion of event
     */
    public double calcDispersion() {
        double P = calcProbability();

        return P * (1 - P);
    }

    /**
     * Getter for total result count
     *
     * @return total result count
     */
    public long getResultsTotal(){
        return resultsTotal;
    }

    /**
     * Add new test result to statistics (positive/negative)
     *
     * @param result true if positive, false if negative
     */
    public void addTestResult(boolean result) {
        if (result)
            this.resultsPositive++;

        this.resultsTotal++;
    }

    /**
     * Join another statistics to current
     *
     * @param statistics input statistics
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public void join(Statistics statistics) throws InvalidArgumentException {
        if (statistics == null)
            throw new InvalidArgumentException(TAG, "add", "statistics", "null");

        this.resultsTotal += statistics.resultsTotal;
        this.resultsPositive += statistics.resultsPositive;
    }

    public boolean isBetter(Statistics statistics){
        return this.calcProbability() > statistics.calcProbability() ||
                (this.calcProbability() == statistics.calcProbability() || this.resultsTotal >= statistics.resultsTotal);
    }

    /**
     * Merge current and input statistics and return new instance
     *
     * @param statistics input statistics
     * @return merged statistics
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public Statistics merge(Statistics statistics) throws InvalidArgumentException {
        if (statistics == null)
            throw new InvalidArgumentException(TAG, "merge", "statistics", "null");

        long resultsTotal = this.resultsTotal + statistics.resultsTotal;
        long resultsPositive = this.resultsPositive + statistics.resultsPositive;

        return new Statistics(resultsTotal, resultsPositive);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "resultsTotal=" + resultsTotal +
                ", resultsPositive=" + resultsPositive +
                '}';
    }
}
