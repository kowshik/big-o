require_relative 'tree_node'

module BinaryTrees
  class << self
    def tree_to_circular_list(root_node)
      return unless root_node

      left_list = tree_to_circular_list(root_node.left_node)
      right_list = tree_to_circular_list(root_node.right_node)

      to_circular_list(root_node)

      joined = join_lists(left_list, root_node)
      join_lists(joined, right_list)
    end

    def to_circular_list(node)
      return unless node

      node.right_node = node
      node.left_node = node

      node
    end

    def join_lists(left_tail, right_tail)
      return right_tail unless left_tail
      return left_tail unless right_tail

      left_head = left_tail.right_node
      right_head = right_tail.right_node

      left_tail.right_node = right_head
      right_head.left_node = left_tail
      right_tail.right_node = left_head
      left_head.left_node = right_tail

      right_tail
    end
  end
end
