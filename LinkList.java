
class LinkList {
    Node first = null;

    LinkList() {
    }

    public void insertFirst(String data) {
        Node newNode = new Node(data);
        newNode.next = this.first;
        this.first = newNode;
    }

    public Node deleteFirst() {
        Node temp = this.first;
        this.first = this.first.next;
        return temp;
    }

    public void printList() {
        for(Node current = this.first; current != null; current = current.next) {
            current.printNode();
        }

    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public String getNodes() {
        Node current = this.first;

        String data;
        for(data = ""; current != null; current = current.next) {
            data = current.getNode() + data;
        }

        return data;
    }
}