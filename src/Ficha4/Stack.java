package Ficha4;

import java.util.ArrayList;
import java.util.Objects;

public class Stack {
    private ArrayList<String> stack;


    public Stack () {
        this.stack = new ArrayList<String>();

    }

    public Stack(ArrayList<String> stack) {
        this.stack = new ArrayList<String>(stack);
    }

    public Stack (Stack s) {
        setStack(s.stack);
    }

    public ArrayList<String> getStack() {
        return stack;
    }

    public void setStack(ArrayList<String> stack) {
        this.stack = stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stack stack1 = (Stack) o;
        return Objects.equals(stack, stack1.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stack);
    }


    @Override
    public String toString() {
        return "Stack {" + stack + '}';
    }

    public Stack clone() {
        return new Stack(this);
    }

    public String top () {
        return this.stack.get(this.stack.size()-1);
    }

    public void push(String s) {
        this.stack.add(s);      // é feito o acrescimo no fim da string ja por definicao
    }

    public void pop(){
        this.stack.remove(this.stack.size() -1);
    }

    public boolean empty() {
        return this.stack.isEmpty();
    }

    public int length() {
        return this.stack.size();
    }

}
