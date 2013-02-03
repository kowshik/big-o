require 'spec_helper'

describe Sorting do
  describe ".quick_sort" do
    it "should sort descending elements in ascending order" do
      array = [5, 4, 3, 2, 1]
      array = Sorting.quick_sort(array) { |a, b| a <=> b }
      array.should == [1, 2, 3, 4, 5]
    end

    it "should sort ascending elements in descending order" do
      array = [1, 2, 3, 4, 5]
      array = Sorting.quick_sort(array) { |a, b| b <=> a }
      array.should == [5, 4, 3, 2, 1]
    end

    it "should sort elements arranged in random order into ascending order" do
      array = [5, 1, 3, 2, 4]
      array = Sorting.quick_sort(array) { |a, b| a <=> b }
      array.should == [1, 2, 3, 4, 5]
    end
  end

  describe ".partition" do
    it "should work when there are only pivots in the array" do
      array = [3, 3, 3, 3, 3]
      returned = Sorting.partition(array, :pivot_index => 3) { |a, b| a <=> b }
      returned.should == 4
      array.should == [3, 3, 3, 3, 3]
    end

    it "should work when only smaller elements and pivots are in the array" do
      pivot_index = 3
      array = [3, 0, 2, 3, -1, -2, 0, 3]
      returned = Sorting.partition(array, :pivot_index => pivot_index) { |a, b| a <=> b }
      returned.should == 7
      array.should == [3, 0, 2, 3, -1, -2, 0, 3]
    end

    it "should work when only larger elements and pivots are in the array" do
      pivot_index = 3
      array = [3, 10, 4, 3, 5, 7, 7, 3]
      returned = Sorting.partition(array, :pivot_index => pivot_index) { |a, b| a <=> b }
      returned.should == 2
      array.should == [3, 3, 3, 10, 5, 7, 7, 4]
    end
  end
end
