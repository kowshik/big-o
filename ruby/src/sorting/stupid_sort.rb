require_relative '../common/array_utils'

module Sorting
  class << self
    def stupid_sort(array, &comparator)
      (0..array.size - 1).each do |i|
        (i + 1..array.size - 1).each do |j|
          ArrayUtils.swap(array, i, j) if (yield(array[i], array[j]) > 0)
        end
      end

      array
    end
  end
end

