package com.gabriel.drawfx.command;

import java.util.Stack;
public class CommandService {
    static Stack<Command> undoStack = new Stack<Command>();
    static Stack<Command> redoStack = new Stack<Command>();

    public static void ExecuteCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack when new command is executed
        System.out.println("Added to undoStack: " + command.getClass().getSimpleName() + " - " + command);
    }

    public static void undo() {
        if (undoStack.empty())
            return;
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
        System.out.println("Moved to redoStack: " + command.getClass().getSimpleName() + " - " + command);
    }

    public static void redo() {
        if (redoStack.empty())
            return;
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
        System.out.println("Redone and added to undoStack: " + command.getClass().getSimpleName() + " - " + command);
    }

    public static boolean canUndo() {
        return !undoStack.empty();
    }

    public static boolean canRedo() {
        return !redoStack.empty();
    }
}