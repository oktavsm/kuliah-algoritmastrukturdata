package praktikum.bab11.livecoding.datastruct;

class MyStack {
    private NodeStack top;

    public MyStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int data) {
        NodeStack newNodeStack = new NodeStack(data);
        newNodeStack.next = top;
        top = newNodeStack;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        } else {
            int popped = top.data;
            top = top.next;
            return popped;
        }
    }

    public void clear() {
        top = null;
    }

    @Override
    public String toString() {
        String res = "";
        NodeStack cur = top;
        if (isEmpty())
            return res;
        while (cur != null) {
            res = cur.data + res;
            cur = cur.next;
        }
        return res;
    }
}

class NodeStack {
    int data;
    NodeStack next;

    NodeStack(int data) {
        this.data = data;
        this.next = null;
    }
}
