require 'set'

module General
  class << self
    # You are given an array of size N containing *only* non-negative integers (i.e. >= 0)
    # From any index in the array, a hop can be either of the following:
    #  * Jump array[index] positions
    #  * Jump 1 position
    # The target of this question is to find the number of hops required to
    # reach the last element in the array from the first element.
    def find_num_hops(array)
      return unless array
      return 0 if array.empty?

      steps = Set.new
      last_seen_step = array.length - 1
      steps.add(last_seen_step)
      (array.length - 2).downto(0) do |index|
        hop_size = array[index]
        reachable_step = hop_size + index
        if steps.include?(reachable_step)
          steps.select! {|step| step >= (reachable_step)}
          steps << index
          last_seen_step = index
        end
      end

      (steps.length - 1) + last_seen_step
    end
  end
end

