
module Sorting
  class << self
    def swap(array, i, j)
      temp = array[i]
      array[i] = array[j]
      array[j] = temp
    end

    def stupid_sort(array)
      (0..array.size - 1).each do |i|
        (i + 1..array.size - 1).each do |j|
          swap(array, i, j) if array[i] > array[j]
        end
      end
    end
    
    def run
      array = [0,3,1,4,5]
      stupid_sort(array)
      puts array.inspect

      array = [5, 1, 4, 3, 2]
      stupid_sort(array)
      puts array.inspect
    end
  end
end

Sorting.run

