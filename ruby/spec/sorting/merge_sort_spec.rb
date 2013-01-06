require 'spec_helper'

describe Sorting do
  describe ".merge_sort" do
    it "should sort descending elements in ascending order" do
      array = [4,3,2,1]
      Sorting.merge_sort(array) { |a, b| a <=> b }
      array.should == [1,2,3,4]
    end
    
    it "should sort ascending elements in descending order" do
      array = [1,2,3,4]
      Sorting.merge_sort(array) { |a, b| b <=> a }
      array.should == [4,3,2,1]      
    end

    it "should sort elements arranged in random order into ascending order" do
      array = [1,4,3,2]
      Sorting.merge_sort(array) { |a, b| a <=> b }
      array.should == [1,2,3,4]
    end
  end
end
