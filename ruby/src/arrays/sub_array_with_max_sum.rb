
module Arrays
  class << self
    # Kadane's algorithm => http://en.wikipedia.org/wiki/Maximum_subarray_problem
    def sub_array_with_max_sum(array)
      answer_struct = Struct.new(:start_index, :end_index, :sum)

      negative_answer = nil
      answer = nil
      current_sum = max_sum = 0
      current_start = nil

      array.each_with_index do |value, index|
        current_sum += value
        if current_sum > max_sum
          answer ||= answer_struct.new
          current_start ||= index

          answer.start_index = current_start
          answer.end_index = index
          answer.sum = current_sum

          max_sum = current_sum
        elsif current_sum > 0
          current_start = index unless current_start
        elsif current_sum < 0
          negative_answer ||= answer_struct.new

          if !negative_answer.sum || current_sum > negative_answer.sum
            negative_answer.start_index = (current_start || index)
            negative_answer.end_index = index
            negative_answer.sum = current_sum
          end

          current_start = nil
          current_sum = 0
        end
      end

      answer || negative_answer
    end
  end
end
