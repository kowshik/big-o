module Sorting
  class << self
    
    def merge_sort(array, &comparator)
      return [] if array.size == 0
      sorted = do_merge_sort(array, 0, array.size - 1, &comparator)
      array.replace(sorted)
    end

    def do_merge_sort(array, start_index, end_index, &comparator)
      return [array[start_index]] if start_index == end_index
      
      left = do_merge_sort(array, start_index,
                           (start_index + end_index) / 2,
                           &comparator)
      
      right = do_merge_sort(array, (start_index + end_index) / 2 + 1,
                            end_index, &comparator)

      merge(left, right, &comparator)
    end
    
    def merge(foo, bar, &comparator)
      merged = []
      
      foo_index = bar_index = 0
      while foo_index < foo.size && bar_index < bar.size 
        compare = yield(foo[foo_index], bar[bar_index])
        if compare < 0
          merged << foo[foo_index]
          foo_index += 1
        else
          merged << bar[bar_index]
          bar_index += 1
        end
      end

      while foo_index < foo.size
        merged << foo[foo_index]
        foo_index += 1
      end

      while bar_index < bar.size
        merged << bar[bar_index]
        bar_index += 1
      end

      merged
    end
  end
end
