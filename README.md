# Latex-Tree-Generator
Utility Java class used for generating Latex diagram trees.

## Implementation

There are two files that are used: `LatexTreeNode.java` interface and `LatexTree.java` static class. Both must be implemented
within the program code for the key:

### `LatexTreeNode.java`

The `Node` class for the tree program must `implement` this interface. The two required methods are as follows:

* 1 `getDataString()` which requires the `Node` to return the `String` representation of the current `Node`. This is what will
be outputted when the `Latex` file draws the tree.
* 2 `getChildren()` which requires the 'Node' to return a `List` of children `Node`s of the current `Node`. Note that casting
may be needed and in the case that children are already stored as a list, the following code can be used:
```java
return new LinkedList<LatexTreeNode>(children);
```

### `LatexTree.java`

This class only contains static methods that performs the generation of the `Latex` tree. The methods included in this class _need_
to be wrapped within the `Tree` class itself and _must_ public in order to call. The
reason for this is that the `LatexTree.java` needs access to the _root_ `Node` of the
tree in order to generate the diagram properly. There are two methods that need to 
be wrapped:

* 1 `makeFile()` which generates the `Latex` file given the _root_ `Node` of the tree.
* 2 `generateTreeCode()` which returns _only_ the `Latex` code of a given tree, without
the necessary headers and footers of the file. This can be used to generate multiple
trees in the same document.

An example of wrapping the methods are as follows, note that an IOException may be 
thrown:
```java
public void generateLatexFile(String fileName) throws IOException {
  LatexTree.makeFile(root, fileName);
}
```

## Use
Once implemented, simply calling the `generateLatexFile()` wrapper method on the tree
will produce a `Latex` file in the `/latex` folder with the current tree diagram.


