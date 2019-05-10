class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
    }

    public void printNode() {
        System.out.print(this.data);
    }

    public String getNode() {
        return this.data;
    }
}
