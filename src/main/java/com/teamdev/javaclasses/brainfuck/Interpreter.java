package com.teamdev.javaclasses.brainfuck;

/**
 * @return program output in String
 */
public interface Interpreter {

    String execute(String program, Memory memory);

}
