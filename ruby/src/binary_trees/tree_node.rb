module BinaryTrees
  class TreeNode
    attr_accessor :left_node, :right_node, :value

    def initialize(opts = {})
      @left_node = opts[:left_node]
      @right_node = opts[:right_node]
      @value = opts[:value]
    end

    def to_s
      value.to_s
    end
  end
end
