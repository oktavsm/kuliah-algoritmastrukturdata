package praktikum.bab7.data_struct;

public class StackArray {
    private int maxSize; // kapasitas maksimum stack
    private int top; // indeks elemen paling atas
    private Object[] stack; // array untuk menyimpan data

    public StackArray(int size) {
        maxSize = size;
        stack = new Object[maxSize];
        top = -1; // kosong
    }

    // Mengecek apakah stack penuh
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // Mengecek apakah stack kosong
    public boolean isEmpty() {
        return top == -1;
    }

    // Menambahkan elemen ke atas stack
    public void push(int data) {
        if (isFull()) {
            System.out.println("Stack penuh, tidak bisa push " + data);
        } else {
            stack[++top] = data;
            System.out.println(data + " dimasukkan ke stack");
        }
    }

    // Menghapus elemen dari atas stack
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack kosong, tidak bisa pop");
            return null;
        } else {
            Object value = stack[top--];
            System.out.println(value + " dihapus dari stack");
            return value;
        }
    }

    // Melihat elemen paling atas tanpa menghapus
    public Object peek() {
        if (isEmpty()) {
            System.out.println("Stack kosong");
            return null;
        } else {
            return stack[top];
        }
    }

    // Menampilkan isi stack
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack kosong");
        } else {
            System.out.print("Isi stack: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }

    // Main buat demo
    public static void main(String[] args) {
        StackArray s = new StackArray(5);
        s.push(10);
        s.push(20);
        s.push(30);
        s.printStack();
        s.pop();
        s.printStack();
        System.out.println("Elemen teratas: " + s.peek());
    }
}
