require 'set'
require 'pry'

def get_questions(declarations)
 declarations.map do |declaration|
  declaration.split("\n").map(&:chars).flatten.to_set
 end
end

def part1(declarations)
  get_questions(declarations).sum(&:size)
end

declarations = File.read("day6.txt").split("\n\n")

puts part1(declarations)
