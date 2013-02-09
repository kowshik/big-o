module General
  class << self

    # Given a string S, find the longest palindromic substring in S.
    def get_longest_palindromic_substring(str)
      return str if !str || str.size == 0

      max_start_index = max_end_index = -1
      (0..str.size - 1).each do |index|
        max_start_index, max_end_index = do_longest_palindromic_substring(str,
                                                                          index - 1, index + 1,
                                                                          max_start_index, max_end_index)
        max_start_index, max_end_index = do_longest_palindromic_substring(str,
                                                                          index, index + 1,
                                                                          max_start_index, max_end_index)
      end

      str[max_start_index..max_end_index]
    end

    def do_longest_palindromic_substring(str, start_index, end_index,
                                         max_start_index, max_end_index)
      while start_index >= 0 && end_index <= str.size - 1 &&
          str[start_index] == str[end_index]
        start_index -= 1
        end_index += 1
      end

      start_index += 1
      end_index -= 1
      return [start_index, end_index] if exceeded?(start_index, end_index,
                                                   max_start_index, max_end_index)
      [max_start_index, max_end_index]
    end

    def exceeded?(start_index, end_index, max_start_index, max_end_index)
      (max_end_index == -1 || max_start_index == -1) ||
        ((end_index - start_index + 1) > (max_end_index - max_start_index + 1))
    end
  end
end
