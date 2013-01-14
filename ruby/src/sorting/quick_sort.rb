require_relative '../common/array_utils'

module Sorting
  class << self

    def quick_sort(array, opts = {}, &comparator)
      start_index = opts[:start_index] || 0
      end_index = opts[:end_index] || array.length - 1

      return array if start_index >= end_index

      length = end_index - start_index + 1
      random_pivot_index = Random.rand(length) + start_index
      pivot = array[random_pivot_index]

      actual_pivot_index = partition(array,
                                     :start_index => start_index,
                                     :end_index => end_index,
                                     :pivot => pivot,
                                     &comparator)
      array = quick_sort(array,
                         :start_index => start_index,
                         :end_index => actual_pivot_index - 1,
                         &comparator)
      array = quick_sort(array,
                         :start_index => actual_pivot_index + 1,
                         :end_index => end_index,
                         &comparator)
      array
    end

    def partition(array, opts = {}, &comparator)
      start_index = opts[:start_index] || 0
      end_index = opts[:end_index] || array.length - 1
      pivot = opts[:pivot] || array[start_index]

      next_pivot_index = nil
      next_greater_index = nil
      (start_index..end_index).each do |next_index|
        comparison = yield(array[next_index], pivot)
        if comparison < 0
          if next_pivot_index
            ArrayUtils.swap(array, next_pivot_index, next_index)
            next_pivot_index += 1
          end

          if next_greater_index
            ArrayUtils.swap(array, next_greater_index, next_index)
            next_greater_index += 1
          end
        elsif comparison == 0
          if next_greater_index
            ArrayUtils.swap(array, next_greater_index, next_index)
            next_pivot_index = next_greater_index unless next_pivot_index
            next_greater_index += 1
          elsif !next_pivot_index
            next_pivot_index = next_index
          end
        elsif !next_greater_index
          next_greater_index = next_index
        end

        next_index += 1
      end

      next_pivot_index
    end
  end
end
