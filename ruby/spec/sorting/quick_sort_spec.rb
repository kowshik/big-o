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
      pivot = 3
      array = [pivot, pivot, pivot, pivot, pivot]
      pivot_index = Sorting.partition(array, :pivot => pivot) { |a, b| a <=> b }
      pivot_index.should == 0
      array.should == [pivot, pivot, pivot, pivot, pivot]
    end

    it "should work when there are no pivots in the array" do
      pivot = 3
      array = [5, 1, 2, 4, 6]
      pivot_index = Sorting.partition(array, :pivot => pivot) { |a, b| a <=> b }
      pivot_index.should be_nil
      array.should == [1, 2, 5, 4, 6]
    end

    it "should work when only smaller elements and pivots are in the array" do
      pivot = 3
      array = [3, 0, 2, 3, -1, -2, 0, 3]
      pivot_index = Sorting.partition(array, :pivot => pivot) { |a, b| a <=> b }
      pivot_index.should == 5
      array.should == [0, 2, -1, -2, 0, 3, 3, 3]
    end

    it "should work when only larger elements and pivots are in the array" do
      pivot = 3
      array = [3, 10, 4, 3, 5, 7, 7, 3]
      pivot_index = Sorting.partition(array, :pivot => pivot) { |a, b| a <=> b }
      pivot_index.should == 0
      array.should == [3, 3, 3, 10, 5, 7, 7, 4]
    end
  end
end
