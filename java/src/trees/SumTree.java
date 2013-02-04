package trees;
/**
 * A sum tree is a tree where the sum of it child nodes is equal to the current
 * node of a tree. Every node follows this property. Write a function to find if
 * a given n-ary tree is a sum tree or not.
 */
public class SumTree {
	public boolean isSumTree(NaryTreeNode<Integer> rootNode) {
		if (rootNode == null) {
			return false;
		}

		long sum = 0;
		for (NaryTreeNode<Integer> child : rootNode.getChildren()) {
			if (!isSumTree(child)) {
				return false;
			}

			sum += child.getValue();
		}

		if (sum == rootNode.getValue()) {
			return true;
		}

		return false;
	}
}
