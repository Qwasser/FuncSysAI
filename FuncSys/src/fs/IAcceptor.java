package fs;

import java.util.Set;

/**
 * Callback interface for functional systems. Provides getters of necessary
 * data: - current situation (as PredicateSet) - proactivity level (as double) -
 * random action (as implementation of IAction)
 */
public abstract class IAcceptor
{
	private static final String	TAG	= IAcceptor.class.getName();
	/**
	 * History instance bounded to current acceptor instance
	 */
	private History	            history;

	/**
	 * Method should return description of current situation in predicates
	 * terms. Predicates should have fixed places in set
	 * 
	 * @return predicates list that describes situation
	 */
	public abstract PredicateSet getCurrentSituation();

	/**
	 * Get history instance which is bounded to current acceptor instance
	 * 
	 * @return history instance
	 */

    //Short memory
    public class ShortMemory
    {
        public IAction lastAction;
        public IFunctionalSystem lastFs;
        public Boolean isRandom;
        public Rule rule;

        public PredicateSet initialSituation;

        @Override
        public String toString()
        {
            String str = "";
            str = str.concat("Last action was ");
            str = str.concat(memory.lastAction.toString() + ". ");
            str = str.concat("Last fs depth: ");
            str = str.concat(" Was random: " + memory.isRandom);
            return str;
        }
    }

    public ShortMemory memory;

    ShortMemory getMemory()
    {
        if( memory == null )
            memory = new ShortMemory();
        return memory;
    }



    History getHistoryInstance()
	{
		if( history == null )
			history = new History();
		return history;
	}

	/**
	 * Method should return current "proactivity" of external (to fs)
	 * system
	 * 
	 * @return "proactivity" as double from 0 to 1
	 */
	public abstract double getProactivity();

	/**
	 * Method should return a random action that can be performed at the moment.
	 * 
	 * @return action, that can be performed
	 */
	public abstract IAction getRandomAction();

	/**
	 * Perform action and save event into the history
	 * 
	 * @param action
	 *            to perform
	 */
	void performAction( IAction action )
	{

		PredicateSet startState = this.getCurrentSituation();
		boolean performed = action.doAction();
		PredicateSet endState = this.getCurrentSituation();

		if( performed )
			this.getHistoryInstance().addEvent( startState, action, endState );
	}

	/**
	 * Perform random action and save event into the history
	 * 
	 * @return performed action
	 */
	IAction performRandomAction()
	{
		IAction eventAction = this.getRandomAction();

		PredicateSet startState = this.getCurrentSituation();
		boolean performed = eventAction.doAction();
		PredicateSet endState = this.getCurrentSituation();

		if( performed )
			this.getHistoryInstance().addEvent( startState, eventAction, endState );

		return eventAction;
	}

    public Set<EventNode> getHistory(){
        return history.getEventNodes();
    }
}