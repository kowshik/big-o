require 'spec_helper'

describe Arrays do
  context "empty array" do
    it "should work" do
      array = []
      Arrays.sort_012(array)
      array.should == []
    end
  end

  context "only 0s" do
    it "should work" do
      array = [0, 0, 0, 0, 0]
      Arrays.sort_012(array)
      array.should == [0, 0, 0, 0, 0]
    end
  end

  context "only 1s" do
    it "should work" do
      array = [1, 1, 1, 1, 1]
      Arrays.sort_012(array)
      array.should == [1, 1, 1, 1, 1]
    end
  end

  context "only 2s" do
    it "should work" do
      array = [2, 2, 2, 2, 2]
      Arrays.sort_012(array)
      array.should == [2, 2, 2, 2, 2]
    end
  end

  context "0s and 1s" do
    it "should work" do
      array = [1, 0, 0, 1, 0]
      Arrays.sort_012(array)
      array.should == [0, 0, 0, 1, 1]
    end
  end

  context "1s and 2s" do
    it "should work" do
      array = [2, 1, 1, 2, 1]
      Arrays.sort_012(array)
      array.should == [1, 1, 1, 2, 2]
    end
  end

  context "0s and 2s" do
    it "should work" do
      array = [2, 0, 0, 2, 0]
      Arrays.sort_012(array)
      array.should == [0, 0, 0, 2, 2]
    end
  end

  context "0s, 1s and 2s" do
    it "should work" do
      array = [1, 1, 0, 2, 0, 0, 2, 1, 2, 0]
      Arrays.sort_012(array)
      array.should == [0, 0, 0, 0, 1, 1, 1, 2, 2, 2]
    end
  end
end
