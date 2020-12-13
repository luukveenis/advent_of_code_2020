require 'pry'

# The "content" of a bag is essentially a weighted, directional edge
# in the graph. It specifies the quantity of a specific colour bag that
# are contained within another bag.
class Content
  attr_reader :bag, :count

  def initialize(bag, count)
    @bag = bag
    @count = count
  end
end

# A "bag" is a node in our BagGraph. It has a colour and a collection of
# other bags it contains. The contents are represented as directed, weighted
# edges using the Content class.
class Bag
  attr_reader :colour, :contents

  def initialize(colour)
    @colour = colour
    @contents = []
  end

  def add_content(content)
    contents << content
  end

  def contains?(colour)
    contents.any? { |c| c.bag.colour == colour || c.bag.contains?(colour) }
  end

  def count_contents
    contents.sum do |content|
      content.count + content.count * content.bag.count_contents
    end
  end
end

# We can treat this problem as a graph and the solutions are simply traversals
# of that graph.
class BagGraph
  attr_reader :bags

  def initialize
    @bags = []
  end

  def add(bag)
    bags << bag
  end

  def find(colour)
    bags.find { |bag| bag.colour == colour }
  end

  def find_or_create(colour)
    find(colour) || Bag.new(colour).tap { |b| add(b) }
  end
end

graph = BagGraph.new

File.foreach(ARGV[0], chomp: true) do |line|
  colour, contents = line.match(/(\w+ \w+) bags contain (.*)./).captures
  bag = graph.find_or_create(colour)

  contents.scan(/(\d+) (\w+ \w+) bags?/).each do |count, inner_colour|
    inner_bag = graph.find_or_create(inner_colour)
    bag.add_content(Content.new(inner_bag, count.to_i))
  end
end

puts "Part 1: " + graph.bags.count { |b| b.contains?("shiny gold") }.to_s
puts "Part 2: " + graph.find("shiny gold").count_contents.to_s
