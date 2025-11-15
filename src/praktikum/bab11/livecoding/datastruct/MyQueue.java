package praktikum.bab11.livecoding.datastruct;

class MyQueue {
    private int maxSize;
    private int front;
    public int rear;
    private int[] queue;

    public MyQueue(int size) {
        maxSize = size;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public void enqueue(int item) {
        if (isFull())
            return;
        else {
            queue[++rear] = item;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            return -1;
        } else {
            int removed = queue[0];
            for (int i = 1; i <= rear; i++) {
                queue[i - 1] = queue[i];
            }
            rear--;
            return removed;
        }
    }

    public int get(int index) {
        if (isEmpty() || index < front || index > rear) {
            return -1;
        } else {
            return queue[index];
        }
    }

    public void print() {
        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

}