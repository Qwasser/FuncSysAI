package ru.nsu.alife.fs;

/**
 * Class represents an event defined as a set of the following parameters: start
 * state (before performing an action), action performed, collection of the
 * resulting states, a number of events
 */
class Event
{
	private static final String	 TAG	    = Event.class.getName();

	/**
	 * Start state before performing action
	 */
	private final PredicateSet	 startState;

	/**
	 * Action performed
	 */
	private final IAction	     action;

	/**
	 * Set of results that could be obtained by performing action
	 */
	private final EventResultSet	results	= new EventResultSet();

	/**
	 * Total number of events in a set
	 */
	private long	             occurrence;

	/**
	 * Constructor. Creates an instance with start state, action performed and
	 * resulting state. Occurrence is set to 1;
	 * 
	 * @param startState
	 *            start state (before performing action)
	 * @param action
	 *            action performed
	 * @param result
	 *            resulting state (after performing action)
	 * @throws ru.nsu.alife.fs.InvalidArgumentException
	 */
	public Event( PredicateSet startState, IAction action, PredicateSet result )
	        throws InvalidArgumentException
	{
		if( startState == null )
			throw new InvalidArgumentException( TAG, "Event", "startState", "null" );
		if( action == null )
			throw new InvalidArgumentException( TAG, "Event", "action", "null" );
		if( result == null )
			throw new InvalidArgumentException( TAG, "Event", "result", "null" );

		this.startState = startState;
		this.action = action;
		this.occurrence = 1L;
		this.results.put( result );
	}

	/**
	 * Add new resulting state that was obtained after performing an action
	 * 
	 * @param result
	 *            new result
	 * @throws ru.nsu.alife.fs.InvalidArgumentException
	 */
	public void addResult( PredicateSet result ) throws InvalidArgumentException
	{
		if( result == null )
			throw new InvalidArgumentException( TAG, "addResult", "result", "null" );

		this.results.put( result );
		this.occurrence++;
	}

	/**
	 * Getter for performed action
	 * 
	 * @return reference of performed action (action structure has just a
	 *         behavior)
	 */
	public IAction getAction()
	{
		return action;
	}

	/**
	 * Get statistics of event with current instance's start state and performed
	 * action and input result
	 * 
	 * @param result
	 *            result to choose and calculate statistics
	 * @return calculates statistics for requested event
	 * @throws ru.nsu.alife.fs.InvalidArgumentException
	 * 
	 */
	public Statistics getResultStatistics( PredicateSet result ) throws InvalidArgumentException
	{
		if( result == null )
			throw new InvalidArgumentException( TAG, "getResultStatistics", "result", "null" );

		long occurrenceTotal = this.occurrence;
		long occurrencePositive = this.results.contains( result ) ? this.results
		        .getOccurrence( result ) : 0;

		return new Statistics( occurrenceTotal, occurrencePositive );
	}

	/**
	 * Getter for start state.
	 * 
	 * @return copy of start state (because is should be immutable)
	 */
	public PredicateSet getStartState()
	{
		return this.startState.copy();
	}

	/**
	 * Check if an event has an entry with a given result
	 * 
	 * @param result
	 *            result to check
	 * @return true if event contains the result, false if not
	 * @throws ru.nsu.alife.fs.InvalidArgumentException
	 * 
	 */
	public boolean hasResult( PredicateSet result ) throws InvalidArgumentException
	{
		if( result == null )
			throw new InvalidArgumentException( TAG, "hasResultPattern", "result", "null" );

		return this.results.contains( result );
	}

	/**
	 * Check if an event has an entry with a given start state
	 * 
	 * @param startState
	 *            start state
	 * @return true | false
	 * @throws ru.nsu.alife.fs.InvalidArgumentException
	 * 
	 */
	public boolean hasStartState( PredicateSet startState )
	{
		if( startState == null )
			throw new InvalidArgumentException( TAG, "hasStartState", "startState", "null" );

		return this.startState.contains( startState );
	}

	/**
	 * Checks if an event has an entry with given start state and action
	 * 
	 * @param startState
	 *            input start state
	 * @param action
	 *            input action
	 * @return true if it has false if not
	 * @throws ru.nsu.alife.fs.InvalidArgumentException
	 * 
	 */
	public boolean hasStartStateAndAction( PredicateSet startState, IAction action )
	        throws InvalidArgumentException
	{
		if( startState == null )
			throw new InvalidArgumentException( TAG, "hasStartStateAndAction", "startState", "null" );
		if( action == null )
			throw new InvalidArgumentException( TAG, "hasStartStateAndAction", "action", "null" );

		return this.action == action && this.startState.contains( startState );
	}

    public EventNode getEventNode() {
        return new EventNode(startState, action, results.getEventResultNodes(), occurrence);
    }
}
