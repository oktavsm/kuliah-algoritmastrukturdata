package praktikum.bab8.laporan.latihan;

import java.util.Scanner;

public class queue_array {
    Scanner masuk = new Scanner(System.in);

    int choice, i, MAX_SIZE = 10;
    char item;
    char[] arr_stack = new char[MAX_SIZE];
    int count = 0;
    int keluar = 0;
    int front = 0, rear = 0;

    // menambahkan ke indeks 0 dan menggeser elemen lainnya ke kanan
    public void enqueue(char item) {
        if (rear == MAX_SIZE) {
            System.out.print("\n# Queue Penuh");
        } else {
            for (i = rear; i > 0; i--) {
                arr_stack[i] = arr_stack[i - 1];
            }
            arr_stack[0] = item;
            rear++;
            System.out.print("\n# Queue No urut/index : " + 0 + ", Queue :" + item);

        }
    }

    public void dequeue() {
        if (rear == 0)
            System.out.print("\n## Queue kosong");
        else {
            System.out.print("\n##Dequeue Value :" +
                    arr_stack[rear - 1]);
            rear--;
        }
    }

    public void printAll() {
        System.out.print("\n## Queue Size : " + rear);
        for (i = rear - 1; i >= 0; i--)
            System.out.print("\n## No Urut/index : " + i + ", Value :" + arr_stack[i]);
    }

    public void menu() {
        System.out.print("\nMasukkan operasi yang akan dilakukan (1:enqueue, 2:dequeue, 3:print) : ");
        choice = masuk.nextInt();
        switch (choice) {
            case 1: {
                System.out.print("\nMasukkan huruf yang akan di-enqueue : ");
                item = masuk.next().charAt(0);
                enqueue(item);
            }
                break;
            case 2:
                dequeue();
                break;
            case 3:
                printAll();
                break;
            default:
                System.out.print("\n1:enqueue, 2:dequeue, 3:print\n");
                keluar = 1;
                break;
        }
    }

    public static void main(String[] args) {
        queue_array q = new queue_array();
        do {
            q.menu();
        } while (q.keluar == 0);
    }
}