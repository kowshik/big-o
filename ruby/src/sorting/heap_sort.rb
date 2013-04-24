require_relative '../common/array_utils'

module Sorting
  class << self

    def heap_sort(array, &comparator)
      array = heapify_all(array, &comparator)

      array.length.downto(2) do |length|
        ArrayUtils.swap(array, 0, length - 1)
        array = heapify(array, 0, length - 1, &comparator)
      end

      array
    end

    def heapify_all(array, &comparator)
      parent_index(array.length - 1).downto(0) do |root_index|
        array = heapify(array, root_index, array.length, &comparator)
      end

      array
    end

    def heapify(array, root_index, length = array.length, &comparator)
      return array if length <= 1

      index_to_be_swapped = root_index

      if left_child_index(root_index) < length
        compared = yield(array[left_child_index(root_index)],
                         array[index_to_be_swapped])
        if compared > 0
          index_to_be_swapped = left_child_index(root_index)
        end
      end

      if right_child_index(root_index) < length
        compared = yield(array[right_child_index(root_index)],
                         array[index_to_be_swapped])
        if compared > 0
          index_to_be_swapped = right_child_index(root_index)
        end
      end

      if root_index != index_to_be_swapped
        ArrayUtils.swap(array, root_index, index_to_be_swapped)
        array = heapify(array, index_to_be_swapped, length, &comparator)
      end

      array
    end

    def parent_index(root_index)
      ((root_index - 1)/2).floor
    end

    def left_child_index(root_index)
      2 * root_index + 1
    end

    def right_child_index(root_index)
      2 * root_index + 2
    end
  end
end
