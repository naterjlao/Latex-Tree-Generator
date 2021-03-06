package latex_tree;

import java.io.*;
import java.util.*;

/**
 * This class includes methods which out Latex code for binary tree visualization. Needed with this class is the
 * LatexTreeNode interface which the Node class of the Tree should implement. Included in this class are methods that
 * should be called within the Tree class since the methods need access to the root Node of the tree. The Latex
 * packages
 * used for this implementation are the amssymb, tikz, tikz-qtree packages.
 *
 * @author Nathaniel Lao
 */
public class LatexTree {
    private final static String FILE_HEADER = "\\documentclass{article}\n\\usepackage{tikz}\n\\usepackage{amssymb}\n" +
            "\\usepackage{tikz-qtree}\n\\begin{document}\n\\begin{center}\n\\textbf{LATEX TREE " +
            "GENERATOR}\\newline\\textit{author: Nathaniel Lao}\\newline\\newline\n";
    private final static String TREE_HEADER = "\\begin{tikzpicture}\n\\Tree ";
    private final static String NULL_SYMBOL = "[ .$\\varnothing$ ]";
    private final static String TREE_FOOTER = "\n\\end{tikzpicture}\\newline\\newline\n";
    private final static String FILE_FOOTER = "\\end{center}\n\\end{document}\n";
    private final static String DEFAULT_FILE_NAME = "latex_tree.tex";

    /**
     * Generates a Latex file named "latex_tree.tex" in the directory "latex". Included in the file are the necessary
     * file headers for the Latex file and the tree diagram representation of the Tree.
     *
     * @param root The root Node of the Tree
     * @throws IOException
     */
    public static void makeFile(LatexTreeNode root) throws IOException {
        makeFile(root, DEFAULT_FILE_NAME);
    }

    /**
     * Generates a Latex file named fileName in the directory "latex". Included in the file are the necessary
     * file headers for the Latex file and the tree diagram representation of the Tree.
     *
     * @param root The root Node of the Tree
     * @param fileName The specified file name of the output. User must include the ".tex" suffix
     * @throws IOException
     */
    public static void makeFile(LatexTreeNode root, String fileName) throws IOException {
        makeFile(generateTreeCode(root),fileName);
    }

    /**
     * Generates a Latex file named "latex_tree.tex" in the directory "latex". Included in the file are the necessary
     * file headers for the Latex file and the tree diagram representation of the Tree.
     *
     * @param treeCode the Latex code inserted for the tree diagram. Note that the TREE_HEADER and TREE_FOOTER must
     *                 be surrounding the code generated by the generateTreeCode() method
     * @throws IOException
     */
    public static void makeFile(String treeCode) throws IOException {
        makeFile(treeCode,DEFAULT_FILE_NAME);
    }

    /**
     * Generates a Latex file named fileName in the directory "latex". Included in the file are the necessary
     * file headers for the Latex file and the tree diagram representation of the Tree.
     *
     * @param treeCode the Latex code inserted for the tree diagram. Note that the TREE_HEADER and TREE_FOOTER must
     *                 be surrounding the code generated by the generateTreeCode() method
     * @param fileName The specified file name of the output. User must include the ".tex" suffix
     * @throws IOException
     */
    public static void makeFile(String treeCode, String fileName) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("mkdir latex");
        rt.exec("touch latex/" + fileName);
        PrintWriter file = new PrintWriter(new FileWriter("latex/" + fileName));
        file.print(FILE_HEADER + treeCode + FILE_FOOTER);
        file.close();
    }

    /**
     * Generates a String representation of the Latex code representation of the tree. This used for standard Binary
     * Search Trees, where ever Node has at most 2 children.
     *
     * @param root The root Node of the Tree.
     * @return A String representation of the Latex file.
     */
    public static String generateTreeCode(LatexTreeNode root) {
        return TREE_HEADER + generateTreeCodeHelp(root) + TREE_FOOTER;
    }

    /**
     * Recursively generates the code for the tree structure. Note that the TREE_HEADER must be concatenated before
     * the String can be used in the document.
     *
     * @param current the Node that is being processed
     * @return A String representation of the Node and its children
     */
    private static String generateTreeCodeHelp(LatexTreeNode current) {
        if (current == null) {
            return NULL_SYMBOL;
        } else {
            List<LatexTreeNode> children = current.getChildren();
            String childrenCode = "";

            for (LatexTreeNode child : children) {
                childrenCode += generateTreeCodeHelp(child);
            }

            return "[ ." + current.getDataString() + " " + childrenCode + " ]";
        }
    }
}
