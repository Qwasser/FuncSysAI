package fs;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 04.09.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class RandomWalker implements IFunctionalSystem {

    @Override
    public void performAction(IAcceptor acceptor) {
        acceptor.performAction(acceptor.getRandomAction());
    }

    @Override
    public void seeResult(IAcceptor acceptor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RuleTreeNode getRuleTreeRoot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<FunctionalSystem> getLinkToSubFS() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRulesToString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<Rule> getRules() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String probTableToString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
