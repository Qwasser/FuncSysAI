package fs;

import java.util.HashMap;
import java.util.Set;

/**
 * Interface for FS Used to fast switch between Fake and Real FS'
 */
public interface IFunctionalSystem
{

	/**
	 * Method performs most suitable action
	 * 
	 * @param acceptor
	 *            acceptor to get data from upper system
	 */
	public void performAction( IAcceptor acceptor );

    /**
     * Method tells fs to look at action result and update itself
     *
     * @param acceptor
     *            acceptor to get data from upper system
     */
    public void seeResult( IAcceptor acceptor );

    /**
     * Method should provide complete tree of rules
     * (including all subFSes)
     *
     * @return root element of created tree
     */
    public RuleTreeNode getRuleTreeRoot();

    public Set<FunctionalSystem> getLinkToSubFS();

    public String getRulesToString();

    public Set <Rule> getRules();
    public String probTableToString();
}
