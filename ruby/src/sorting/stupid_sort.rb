
module Sorting
  class << self
    def swap(array, i, j)
      temp = array[i]
      array[i] = array[j]
      array[j] = temp
    end

    def stupid_sort(array, &comparator)
      (0..array.size - 1).each do |i|
        (i + 1..array.size - 1).each do |j|
          swap(array, i, j) if (yield(array[i], array[j]) > 0)
        end
      end

      array
    end
  end
end

