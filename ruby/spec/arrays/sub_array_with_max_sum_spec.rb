require 'spec_helper'

describe Arrays do
  describe ".sub_array_with_max_sum" do
    context "empty array" do
      it "should work" do
        Arrays.sub_array_with_max_sum([]).should be_nil
      end
    end

    context "array with negative numbers only" do
      it "should work" do
        answer = Arrays.sub_array_with_max_sum([-2, -3, -1])

        answer.start_index.should == 2
        answer.end_index.should == 2
        answer.sum.should == -1
      end
    end

    context "array with positive numbers only" do
      it "should work" do
        answer = Arrays.sub_array_with_max_sum([1, 3, 0, 2, 4])

        answer.start_index.should == 0
        answer.end_index.should == 4
        answer.sum.should == 10
      end
    end

    context "array with positive and negative numbers" do
      it "should work when sum does not drop below zero at any point" do
        answer = Arrays.sub_array_with_max_sum([1, 2, 3, -1, -5 , 4, 5])

        answer.start_index.should == 0
        answer.end_index.should == 6
        answer.sum.should == 9
      end

      it "shoyld work when sym drops below zero at some point" do
        answer = Arrays.sub_array_with_max_sum([1, 2, 3, -1, -6 , 4, 5])

        answer.start_index.should == 5
        answer.end_index.should == 6
        answer.sum.should == 9
      end
    end
  end
end
