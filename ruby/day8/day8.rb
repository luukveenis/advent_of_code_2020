require 'pry'

class Computer
  InfiniteLoopError = Class.new(StandardError)

  attr_reader :accumulator

  def initialize(program)
    @program = program
    @accumulator = 0
    @pointer = 0
    @previous_instructions = []
  end

  # Runs the program until it terminates
  def run
    while (@pointer < program.size)
      raise InfiniteLoopError if previous_instructions.include?(@pointer)

      previous_instructions << @pointer
      process_instruction(program[@pointer])
    end
  end

  # Runs the program, halting if an instruction is encountered that has
  # already been run.
  def safe_run
    while (@pointer < program.size)
      break if previous_instructions.include?(@pointer)

      previous_instructions << @pointer
      process_instruction(program[@pointer])
    end
  end

  def repair_and_run
    (0..program.size - 1).each do |instruction_number|
      original_instruction = program[instruction_number]
      command, value = original_instruction.split

      begin
        case command
        when "nop"
          program[instruction_number] = ["jmp", value].join(" ")
          run
        when "jmp"
          program[instruction_number] = ["nop", value].join(" ")
          run
        when "acc"
          next
        end
      rescue InfiniteLoopError
        program[instruction_number] = original_instruction
        @pointer = 0
        @accumulator = 0
        @previous_instructions = []
        next
      end
    end
  end

  private

  attr_reader :program
  attr_accessor :previous_instructions

  def process_instruction(instruction)
    command, value = instruction.split

    case command
    when "nop"
      @pointer += 1
    when "acc"
      @accumulator += value.to_i
      @pointer += 1
    when "jmp"
      @pointer += value.to_i
    end
  end
end

program = File.readlines(ARGV[0], chomp: true)

computer = Computer.new(program)
computer.safe_run
puts "Part 1: " + computer.accumulator.to_s

computer = Computer.new(program)
computer.repair_and_run
puts "Part 2: " + computer.accumulator.to_s
