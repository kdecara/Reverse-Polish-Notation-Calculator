class LinkListStack {
    LinkList link = new LinkList();

    LinkListStack() {
    }

    public void push(String data) {
        this.link.insertFirst(data);
    }

    public void pop() {
        while(!this.link.isEmpty()) {
            this.link.deleteFirst();
        }

    }

    public void popFirst() {
        this.link.deleteFirst();
    }

    public void displayStack() {
        this.link.printList();
    }

    public Boolean confirmNode(String node) {
        Node first = null;

        for(Node current = (Node)first; current != null; current = current.next) {
            current.getNode();
            if (current.getNode() == node) {
                return true;
            }
        }

        return false;
    }

    public LinkListStack setEquals(String completeData) {
        LinkListStack newStack = new LinkListStack();

        for(int i = 0; i < completeData.length(); ++i) {
            char ch = completeData.charAt(i);
            String str = Character.toString(ch);
            newStack.push(str);
        }

        return newStack;
    }
}