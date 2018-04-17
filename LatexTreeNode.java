package latex_tree;

import java.util.List;

/**
 * This is the necessary interface used for the LatexTree class. The Node class of the Tree should implement this
 * in order for the information of the Tree be used in the LatexTree class.
 *
 * @author Nathaniel Lao
 */
public interface LatexTreeNode {
    /**
     * Returns the String representation of the current Node. This String will be used to represent the current Node in
     * the Tree.
     *
     * @return A String that will be used to represent the Node's value in the tree diagram
     */
    String getDataString();

    /**
     * Returns an ordered List of the children of the Node. If child does not exist, null should be put in their place.
     * If there are no children, it is recommended to return an empty List, so as to not populate the Node with Null
     * children.
     *
     * @return A List of the children of the current Node.
     */
    List<LatexTreeNode> getChildren();
}
