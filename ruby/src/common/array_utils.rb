module ArrayUtils
    class << self
      def swap(array, fooIndex, barIndex)
        tmp = array[fooIndex]
        array[fooIndex] = array[barIndex]
        array[barIndex] = tmp
      end
    end
end
