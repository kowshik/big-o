require 'spec_helper'

describe BinaryTrees do
  describe ".tree_to_circular_list" do
    it "should work for empty tree" do
      BinaryTrees.tree_to_circular_list(nil).should == nil
    end

    it "should work for tree with 1 node" do
      root_node = BinaryTrees::TreeNode.new(:value => 1)

      returned = BinaryTrees.tree_to_circular_list(root_node)

      returned.left_node.should == returned
      returned.right_node.should == returned
      returned.value.should == 1
      returned.object_id.should == root_node.object_id
    end

    it "should work for tree skewed to the left" do
      root_node = BinaryTrees::TreeNode.new(:value => 3)
      left_node_1 = BinaryTrees::TreeNode.new(:value => 2)
      left_node_2 = BinaryTrees::TreeNode.new(:value => 1)

      root_node.left_node = left_node_1
      left_node_1.left_node = left_node_2

      returned = BinaryTrees.tree_to_circular_list(root_node)

      returned.should == root_node

      returned.right_node.should == left_node_2
      left_node_2.right_node.should == left_node_1
      left_node_1.right_node.should == returned

      returned.left_node.should == left_node_1
      left_node_1.left_node.should == left_node_2
      left_node_2.left_node.should == returned
    end

    it "should work for tree skewed to the right" do
      root_node = BinaryTrees::TreeNode.new(:value => 3)
      right_node_1 = BinaryTrees::TreeNode.new(:value => 2)
      right_node_2 = BinaryTrees::TreeNode.new(:value => 1)

      root_node.right_node = right_node_1
      right_node_1.right_node = right_node_2

      returned = BinaryTrees.tree_to_circular_list(root_node)

      returned.should == right_node_2

      returned.right_node.should == root_node
      root_node.right_node.should == right_node_1
      right_node_1.right_node.should == right_node_2

      returned.left_node.should == right_node_1
      right_node_1.left_node.should == root_node
      root_node.left_node.should == returned
    end

    it "should work for a regular tree" do
      left_node_1_left = BinaryTrees::TreeNode.new(:value => 1)
      left_node_1_right = BinaryTrees::TreeNode.new(:value => 3)
      left_node_1 = BinaryTrees::TreeNode.new(:value => 2,
                                               :left_node => left_node_1_left,
                                               :right_node => left_node_1_right)

      right_node_1_left = BinaryTrees::TreeNode.new(:value => 5)
      right_node_1_right = BinaryTrees::TreeNode.new(:value => 7)
      right_node_1 = BinaryTrees::TreeNode.new(:value => 6,
                                                :left_node => right_node_1_left,
                                                :right_node => right_node_1_right)

      root_node = BinaryTrees::TreeNode.new(:value => 4,
                                             :left_node => left_node_1,
                                             :right_node => right_node_1)
    end
  end
end
