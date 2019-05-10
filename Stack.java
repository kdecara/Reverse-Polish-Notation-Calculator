class Stack {
    public int maxSize;
    public String[] stackArray;
    public int top;

    public Stack(int s) {
        this.maxSize = s;
        this.stackArray = new String[this.maxSize];
        this.top = -1;
    }

    public void push(String j) {
        this.stackArray[++this.top] = j;
    }

    public String pop() {
        return this.stackArray[this.top--];
    }

    public String peek() {
        return this.stackArray[this.top];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    public String getData(int i) {
        return this.stackArray[i];
    }

    public void displayStack() {
        for(int i = this.maxSize - 1; i >= 0; --i) {
            System.out.println(this.stackArray[i]);
        }

    }

    public String deleteData(int index) {
        Stack tempStack = new Stack(this.maxSize);
        if (this.isEmpty()) {
            return null;
        } else {
            for(int i = 0; i < index; ++i) {
                tempStack.push(this.pop());
            }

            String removedElement = tempStack.pop();

            while(!tempStack.isEmpty()) {
                this.push(tempStack.pop());
            }

            return removedElement;
        }
    }

    public String deleteData2(int index) {
        Stack tempStack = new Stack(this.maxSize);
        String removedElement = this.getData(index);

        int i;
        for(i = this.top; i > index; --i) {
            tempStack.push(this.pop());
        }

        for(i = index - 1; i >= 0; --i) {
            tempStack.push(this.pop());
        }

        while(!tempStack.isEmpty()) {
            this.push(tempStack.pop());
        }

        return removedElement;
    }

    public void setData(int i, String data) {
        this.stackArray[i] = data;
    }

    public Stack insertData(int i, String data, Stack oldStack) {
        Stack newStack = new Stack(this.maxSize++);
        newStack.setData(i, data);

        int n;
        for(n = 0; n < i; ++n) {
            newStack.push(oldStack.getData(i));
        }

        for(n = i + 1; n < oldStack.maxSize - 1; ++n) {
            newStack.push(oldStack.getData(n));
        }

        return newStack;
    }

    public void setEquals(Stack stack1) {
        this.maxSize = stack1.maxSize;

        for(int i = 0; i <= stack1.maxSize - 1; ++i) {
            this.stackArray[i] = stack1.getData(i);
        }

    }

    public Stack removeBlankSpaces(Stack oldStack) {
        for(int i = 0; i < oldStack.maxSize; ++i) {
            if (oldStack.getData(i).equals("")) {
                ;
            }
        }

        Stack newStack = new Stack(oldStack.maxSize);
        return newStack;
    }

    public void insertData(String data, int n) {
        this.stackArray[n] = data;
    }
}