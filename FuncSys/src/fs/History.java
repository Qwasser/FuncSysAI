package fs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class describes event structure with set of Events and updatable statistics.
 */
class History
{
	private static final String	TAG	= History.class.getName();

	/**
	 * Set of events that occurred during application working
	 */
	private final Set< Event >	events;

	/**
	 * Constructor. Private - to prevent uncontrolled instance generation
	 */
	public History()
	{
		this.events = Collections.synchronizedSet( new HashSet< Event >() );
	}

	/**
	 * Add a new event into history's set of events or add new result into
	 * existed event.
	 * 
	 * @param startState
	 *            start state of input event
	 * @param action
	 *            action performed during input event
	 * @param result
	 *            result state that was obtained after action performing
	 * @throws fs.InvalidArgumentException
	 * 
	 */
	public void addEvent( PredicateSet startState, IAction action, PredicateSet result )
	        throws InvalidArgumentException
	{
		if( startState == null )
			throw new InvalidArgumentException( TAG, "addEvent", "startState", "null" );
		if( action == null )
			throw new InvalidArgumentException( TAG, "addEvent", "action", "null" );
		if( result == null )
			throw new InvalidArgumentException( TAG, "addEvent", "result", "null" );

		for( Event event : this.events )
		{
			if( event.hasStartStateAndAction( startState, action ) )
			{
				event.addResult( result.copy() );
				return;
			}
		}

		this.events.add( new Event( startState.copy(), action, result.copy() ) );
	}

	/**
	 * Get set of events by input result.
	 * 
	 * @param result
	 *            result pattern
	 * @return set of suitable events
	 * @throws fs.InvalidArgumentException
	 * 
	 */
	public Set< Event > getEventsWithResult( PredicateSet result ) throws InvalidArgumentException
	{
		if( result == null )
			throw new InvalidArgumentException( TAG, "getEventsWithResult", "result", "null" );

		Set< Event > resultEvents = new HashSet< Event >();

		for( Event event : this.events )
		{
			if( event.hasResult( result ) )
			{
				resultEvents.add( event );
			}
		}

		return resultEvents;
	}

	/**
	 * Get total statistics of events with input characteristics
	 * 
	 * @param startState
	 *            start state of requested event
	 * @param action
	 *            action performed during requested event
	 * @param result
	 *            result state that was obtained after action performing
	 * @return calculated total statistics of all suitable events
	 * @throws fs.InvalidArgumentException
	 * 
	 */
	public Statistics getStatistics( PredicateSet startState, IAction action, PredicateSet result )
	        throws InvalidArgumentException
	{
		if( startState == null )
			throw new InvalidArgumentException( TAG, "getStatistics", "startState", "null" );
		if( action == null )
			throw new InvalidArgumentException( TAG, "getStatistics", "action", "null" );
		if( result == null )
			throw new InvalidArgumentException( TAG, "getStatistics", "result", "null" );

		Statistics statistics = new Statistics();
		for( Event event : events )
		{
			if( event.hasStartStateAndAction( startState, action ) )
			{
				statistics.join( event.getResultStatistics( result ) );
			}
		}

		return statistics;
	}

    public Set<EventNode> getEventNodes() {
        HashSet<EventNode> result = new HashSet<EventNode>();

        for(Event event : events){
            result.add(event.getEventNode());
        }

        return result;
    }
}
