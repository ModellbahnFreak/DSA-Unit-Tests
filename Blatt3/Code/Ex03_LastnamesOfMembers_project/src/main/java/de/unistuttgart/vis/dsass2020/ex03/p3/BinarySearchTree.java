package de.unistuttgart.vis.dsass2020.ex03.p3;

import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

	private IBinaryTreeNode<T> nullNode;

    /**
     * Returns the number of elements in the tree
     */
    public int size() {
        return 0;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return this.toStringPreorder();
    }

    /**
     * Stringifies this {@link BinarySearchTree} in preorder notation
     *
     * @return A String representation of all nodes in preorder notation or BinarySearchTree without root if the tree
     * is empty
     */
    public String toStringPreorder() {
        final IBinaryTreeNode<T> rootNode = this.getRootNode();
        if (rootNode != null) {
            final StringBuilder out = new StringBuilder("BinarySearchTree<>(preorder)[");
            final Stack<IBinaryTreeNode<T>> callStack = new Stack<>();
            IBinaryTreeNode<T> currentNode = rootNode;
            callStack.push(currentNode);
            while (callStack.size() > 0) {
                currentNode = callStack.pop();
                if (currentNode != this.nullNode && currentNode != null) {
                    out.append(currentNode.getValue().toString());
                    out.append(',');
                    callStack.push(currentNode.getRightChild());
                    callStack.push(currentNode.getLeftChild());
                }
            }
            out.append(']');
            return out.toString();
        }
        return "BinarySearchTree without root";
    }

    /**
     * Turns this binary search tree into a string which can pe pasted into a latex document and rendered using the
     * tikz package.
     * <p>
     * WARNING: This function is only suitable for smaller trees as this is a recursive function which will run into
     * a stack overflow if the tree is to high.
     *
     * @return The current tree as a latex string which can be parsed by tikz
     * @throws IllegalStateException If there is no element in this tree yet
     */
    public String toStringLatex() {
        if (this.getRootNode() == null || this.getRootNode() == this.nullNode) {
            throw new IllegalStateException("The tree is empty");
        }
        return "\\begin{figure}[!h]\n" +
                "    \\centering\n" +
                "    \\begin{tikzpicture}[level/.style ={sibling distance=\\treeWidth{#1}}]\n" +
                "        \\" + this.toLatexFromNode(this.getRootNode()) + ";\n" +
                "    \\end{tikzpicture}\n" +
                "    \\caption{Generierter binärer Suchbaum}\n" +
                "    \\label{fig:tree_b1}\n" +
                "\\end{figure}";
    }

    /**
     * Private helper method for creating The latex string from the binary tree.
     * <p>
     * This will create the tikz structure for trees with nodes and childs for the given node and return the latex
     * code as string. This latex code isn't compilable yet
     * <p>
     * WARNING: This function is only suitable for smaller trees as this is a recursive function which will run into
     * a stack overflow if the tree is to high.
     *
     * @param node The node which (and whose children) to convert into tikz syntax
     * @return Thegiven node converted into tikz latex code (not compilable yet)
     */
    private String toLatexFromNode(final IBinaryTreeNode<T> node) {
        if (node == null || node == this.nullNode) {
            return "edge from parent [draw=none]";
        }
        return "node [state] {" + node.getValue() + "}\n" +
                "    child {" + this.toLatexFromNode(node.getLeftChild()) +
                "\n    }\n" +
                "    child {" + this.toLatexFromNode(node.getRightChild()) +
                "\n    }";
    }
}
