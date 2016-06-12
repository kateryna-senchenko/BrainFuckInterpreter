package com.teamdev.javaclasses.brainfuck;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;

public class InterpreterImpl implements Interpreter{

    public String execute(String inputProgram, Memory memory) {

        char[] program = inputProgram.toCharArray();
        int commandPointer = 0;

        OutputStream result = new ByteArrayOutputStream();

        Deque<Integer> cyclesStack = new ArrayDeque<Integer>();

        for (; commandPointer < program.length; commandPointer++) {

            char command = program[commandPointer];

            switch (command) {

                case '>':
                    memory.movePointerRight();
                    break;

                case '<':
                    memory.movePointerLeft();
                    break;

                case '+':
                    memory.incrementCurrentCellValue();
                    break;

                case '-':
                    memory.decrementCurrentCellValue();
                    break;

                case '.':
                    try {
                        result.write(memory.getCurrentCellValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case '[':
                    if (memory.getCurrentCellValue() == 0) {
                        int i = 1;
                        while (i > 0) {
                            char element = program[++commandPointer];
                            if (element == '[')
                                i++;
                            else if (element == ']')
                                i--;
                        }
                    } else {
                        cyclesStack.push(commandPointer);
                    }
                    break;

                case ']':
                    if (cyclesStack.size() == 0)
                        throw new IllegalStateException("Commands '[' and ']' does not match");

                    if (memory.getCurrentCellValue() > 0){
                        commandPointer = cyclesStack.peek();}
                    else cyclesStack.pop();
                    break;

                default:
                    throw new IllegalArgumentException("Program contains illegal character " + command);
            }
        }

        if (cyclesStack.size() != 0)
                throw new IllegalStateException("Commands '[' and ']' does not match");

        return result.toString();

    }

}
