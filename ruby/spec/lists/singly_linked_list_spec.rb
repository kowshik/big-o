require 'spec_helper'

describe SinglyLinkedList do
  describe "#reverse" do
    it "should work for an empty list" do
      SinglyLinkedList.new.size.should == 0
    end

    it "should work for a list with 1 element" do
      list = SinglyLinkedList.new

      list.append(0)
      list.reverse
      list.size.should == 1

      expected = [0]
      index = 0
      list.each do |item|
        item.should == expected[index]
        index += 1
      end
    end

    it "should work for  a list with more than 1 element" do
      list = SinglyLinkedList.new

      list.append(0)
      list.append(1)
      list.append(2)
      list.append(3)

      list.reverse

      list.size.should == 4

      index = 0
      expected = [3, 2, 1, 0]
      list.each do |value|
        value.should == expected[index]
        index += 1
      end
    end
  end
end
