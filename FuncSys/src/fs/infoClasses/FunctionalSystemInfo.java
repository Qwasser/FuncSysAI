package fs.infoClasses;

import fs.IFunctionalSystem;
import fs.PredicateSet;
import fs.Rule;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 24.09.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public class FunctionalSystemInfo {

    public class FsNode
    {
        int depth;
        PredicateSet goal;
        Map<Rule, FsNode> ruleToChild;
    }

    FsNode rootNode;

    public FunctionalSystemInfo(IFunctionalSystem fs)
    {

    }

}
