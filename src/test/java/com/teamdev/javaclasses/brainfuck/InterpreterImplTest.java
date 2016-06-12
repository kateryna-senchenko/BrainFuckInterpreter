package com.teamdev.javaclasses.brainfuck;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InterpreterImplTest {

    private static final String hello_world_program = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.";
    private static final String alphabet_program = "++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]";
    private static final String unclosed_loop_program1 = "++++++[>++++++++++>++++<<-]>+++++>++[-<.+>";
    private static final String unclosed_loop_program2 = "[[[]]]]";
    private static final String illegal_command_program = "+++[>>f++]-->";

    private Interpreter interpreter = new InterpreterImpl();

    @Test
    public void testInterpretString() {

        assertEquals("Program is not correctly interpreted", "Hello World!", interpreter.execute(hello_world_program, new Memory(100)));

        assertEquals("Program is not correctly interpreted", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", interpreter.execute(alphabet_program, new Memory(100)));

    }

    @Test
    public void testUnclosedLoop() {
        try {
            interpreter.execute(unclosed_loop_program1, new Memory(100));
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertEquals("Commands '[' and ']' does not match", e.getMessage());
        }

        try {
            interpreter.execute(unclosed_loop_program2, new Memory(100));
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertEquals("Commands '[' and ']' does not match", e.getMessage());
        }
    }

    @Test
    public void testIllegalCommand() {
        try {
            interpreter.execute(illegal_command_program, new Memory(100));
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertEquals("Program contains illegal character f", e.getMessage());
        }
    }

    @Test
    public void testMovePointerRight(){
        Memory memory = new Memory(100);
        interpreter.execute(">", memory);
        assertEquals("Pointer was not moved correctly", 1, memory.getCurrentPointer() );
    }

    @Test
    public void testMovePointerLeft(){
        Memory memory = new Memory(100);
        interpreter.execute("><", memory);
        assertEquals("Pointer was not moved correctly", 0, memory.getCurrentPointer() );
    }

    @Test
    public void testIncrementValue(){
        Memory memory = new Memory(100);
        interpreter.execute("+", memory);
        assertEquals("Value was not incremented", 1, memory.getCurrentCellValue() );
    }

    @Test
    public void testDecrementValue(){
        Memory memory = new Memory(100);
        interpreter.execute("+-", memory);
        assertEquals("Value was not decremented", 0, memory.getCurrentCellValue() );
    }
}
