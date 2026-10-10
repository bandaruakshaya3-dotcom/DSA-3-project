package algorithms;

public class Trie {

    class Node {
        Node[] children = new Node[26];
        boolean end;
    }

    Node root = new Node();

    public void insert(String word) {

        word = word.toLowerCase();

        Node current = root;

        for (char ch : word.toCharArray()) {

            if (ch == ' ') {
                continue;
            }

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new Node();
            }

            current = current.children[index];
        }

        current.end = true;
    }

    public boolean search(String word) {

        word = word.toLowerCase();

        Node current = root;

        for (char ch : word.toCharArray()) {

            if (ch == ' ') {
                continue;
            }

            int index = ch - 'a';

            if (current.children[index] == null) {
                return false;
            }

            current = current.children[index];
        }

        return current.end;
    }
}