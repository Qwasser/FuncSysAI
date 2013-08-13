package ru.nsu.alife.fs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class describes structure that stores a map
 * of event results and occurrence counter of them
 */
class EventResultSet {
    private static final String TAG = EventResultSet.class.getName();

    /**
     * HashMap of event results and occurrence counter of them.
     * Event results as keys, occurrence as value.
     */
    private Map<PredicateSet, Long> eventResults = new HashMap<PredicateSet, Long>();

    /**
     * Check if collection contains input result
     *
     * @param result result to check
     * @return true if contains
     *         false if not
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public boolean contains(PredicateSet result) throws InvalidArgumentException {
        if (result == null)
            throw new InvalidArgumentException(TAG, "contains", "result", "null");

        for (PredicateSet key : this.eventResults.keySet())
            if (key.contains(result))
                return true;

        return false;
    }

    /**
     * Put result into collection with occurrence == 1 if there is no such result.
     * Increase occurrence if result is already in collection
     *
     * @param result result to add
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public void put(PredicateSet result) throws InvalidArgumentException {
        if (result == null)
            throw new InvalidArgumentException(TAG, "put", "result", "null");

        Long occurrenceCount = this.eventResults.get(result);

        if (occurrenceCount == null)
            this.eventResults.put(result, 1L);
        else
            this.eventResults.put(result, occurrenceCount + 1);
    }

    /**
     * Get occurrence count of input result
     *
     * @param result result to occurrence retrieve
     * @return occurrence count
     * @throws ru.nsu.alife.fs.InvalidArgumentException
     *
     */
    public long getOccurrence(PredicateSet result) throws InvalidArgumentException {
        if (result == null)
            throw new InvalidArgumentException(TAG, "getOccurrence", "result", "null");

        long occurrence = 0;

        for (PredicateSet key : this.eventResults.keySet())
            if (key.contains(result))
                occurrence += this.eventResults.get(key);

        return occurrence;
    }

    public Set<EventResultNode> getEventResultNodes() {
        Set<EventResultNode> toReturn = new HashSet<EventResultNode>();
        for(Map.Entry<PredicateSet, Long> entry : eventResults.entrySet()){
            toReturn.add(new EventResultNode(entry.getKey(), entry.getValue()));
        }
        return toReturn;
    }
}
