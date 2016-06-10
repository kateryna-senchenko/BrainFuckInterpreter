package com.teamdev.javaclasses.brainfuck;

public class Memory {

    private int[] cells;
    private int pointer;

    public Memory(int size) {
        this.cells = new int[size];
    }

    public int getCurrentPointer(){
        return pointer;
    }

    public int getCellValue(int index){
        return cells[index];
    }

    public int getCurrentCellValue(){
        return cells[pointer];
    }

    public void movePointerRight(){
        pointer++;
    }

    public void movePointerLeft(){
        pointer--;
    }

    public void incrementCurrentCellValue(){
        cells[pointer]++;
    }

    public void decrementCurrentCellValue(){
        cells[pointer]--;
    }
}
