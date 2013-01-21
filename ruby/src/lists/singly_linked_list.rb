
class SinglyLinkedList
  private

  class Node
    attr_accessor :value, :next

    def initialize(opts = {})
      @value = opts[:value]
      @next = opts[:next]
    end
  end

  public

  attr_reader :size

  def initialize
    @size = 0
  end

  def add(value)
    new_node = Node.new(:value => value)

    unless @head
      @head = new_node
      @size = 1
      return
    end

    iterator = @head
    while iterator
      last = iterator
      iterator = iterator.next
    end

    last.next = new_node
    @size += 1
    return
  end

  def reverse
    current = @head
    return unless current

    next_node = current.next
    current.next = nil
    while next_node
      tmp = next_node.next
      next_node.next = current
      current = next_node
      next_node = tmp
    end

    @head = current
    return
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
