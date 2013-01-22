module Arrays
  class << self
    def sort_012(array)
      ones_pointer = nil
      twos_pointer = nil

      array.each_with_index do |value, index|
        case value
        when 0
            if twos_pointer
              ArrayUtils.swap(array, twos_pointer, index)
              if ones_pointer
                ArrayUtils.swap(array, ones_pointer, twos_pointer)
                ones_pointer += 1
              end

              twos_pointer += 1
            elsif ones_pointer
              ArrayUtils.swap(array, ones_pointer, index)
              ones_pointer += 1
            end
        when 1
            if twos_pointer
              ArrayUtils.swap(array, index, twos_pointer)
              ones_pointer = twos_pointer unless ones_pointer
              twos_pointer += 1
            elsif !ones_pointer
              ones_pointer = index
            end
        else
          twos_pointer = index unless twos_pointer
        end
      end
    end
  end
end
