require 'set'
require 'pry'

def part1(declarations)
 declarations.map do |declaration|
  declaration.split("\n").map(&:chars).flatten.to_set
 end.sum(&:size)
end

def part2(declarations)
  declarations.map do |declaration|
    declaration.split("\n").map(&:chars).reduce(&:&)
  end.sum(&:size)
end

declarations = File.read("day6.txt").split("\n\n")

puts part1(declarations)
puts part2(declarations)
