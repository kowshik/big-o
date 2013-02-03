require_relative '../common/array_utils'

module Sorting
  class << self

    def quick_sort(array, opts = {}, &comparator)
      start_index = opts[:start_index] || 0
      end_index = opts[:end_index] || array.length - 1

      return array if start_index >= end_index

      pivot_index = partition(array,
                              :start_index => start_index,
                              :end_index => end_index,
                              &comparator)
      array = quick_sort(array,
                         :start_index => start_index,
                         :end_index => pivot_index - 1,
                         &comparator)
      array = quick_sort(array,
                         :start_index => pivot_index + 1,
                         :end_index => end_index,
                         &comparator)
      array
    end

    def random_pivot(start_index, end_index)
      length = end_index - start_index + 1
      Random.rand(length) + start_index
    end

    def partition(array, opts = {}, &comparator)
      start_index = opts[:start_index] || 0
      end_index = opts[:end_index] || array.length - 1
      pivot_index = opts[:pivot_index] || random_pivot(start_index, end_index)

      # Put the pivot in the end.
      ArrayUtils.swap(array, pivot_index, end_index)

      swap_index = start_index - 1

      # When the loop below terminates, we will have
      # all elements <= pivot in the range (0..swapindex).
      (start_index..end_index - 1).each do |index|
        comparison = yield(array[index], array[end_index])
        if comparison <= 0
          swap_index += 1
          ArrayUtils.swap(array, swap_index, index)
        end
      end

      # Put the pivot at the position where it deserves to be in.
      ArrayUtils.swap(array, swap_index + 1, end_index)
      swap_index + 1
    end
  end
end
