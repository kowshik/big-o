
class SinglyLinkedList
  private

  class Node
    attr_accessor :value, :next

    def initialize(opts = {})
      @value = opts[:value]
      @next = opts[:next]
    end

    def to_s
      @value.to_s
    end
  end

  public

  attr_reader :size

  def add(value)
    new_node = Node.new(:value => value)
    @size ||= 0
    @size += 1

    unless @head
      @head = @tail = new_node
      return
    end

    @tail.next = new_node
    @tail = new_node

    return
  end

  def size
    @size || 0
  end

  def reverse
    return if @head == @tail

    @tail = @head
    next_node = @head.next
    @head.next = nil

    while next_node
      tmp = next_node.next
      next_node.next = @head
      @head = next_node
      next_node = tmp
    end
  end

  def each(&block)
    return unless @head

    iterator = @head
    while iterator
      yield(iterator.value)
      iterator = iterator.next
    end
  end
end
