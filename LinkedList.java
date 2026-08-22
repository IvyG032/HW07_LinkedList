public class LinkedList<E> {
    // instance variables
    private Node<E> head;
    private int size; 

    // the node class
    private class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    // size getter
    public int size() {
        return size;
    }
    
    // check if the linked list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // removes all data from linked list
    public void clear() {
        head = null;
        size = 0;
    }

    // add value to the linked list
    public void add(int index, E data) {
        Node<E> node = new Node<E> (data, null);
        Node<E> current = head;

        // check if index is valid 
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        // check if add to front
        if (index == 0) {
            node.next = head;
            head = node;
            size ++;
        }
        else{
            for (int i = 0; i < index -1; i ++) {      // -1 because you can't go pass the node
                current = current.next;
            }
            node.next = current.next;   // point new node to the original next to avoid orphaned node
            current.next = node;        // then point the previous node to the new node
            size ++;
        }
    }

    // add value at the end
    public void add(E data) {
        add(size, data);    // call add(int,E) pass in size
    }

    // check if value is in linked list
    public boolean contains(Object o) {
        Node<E> current = head;
        for (int i = 0; i < size; i ++) {
            if (o == null) {
                if (current.data == null) {
                    return true;
                }
            }
            else if (o.equals(current.data)) {
                return true;
            }
            current = current.next;         // move index and node the same time
        }
        return false;
    }

    // returns the value at the index
    public E get(int index) {
        Node<E> current = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        for (int i = 0; i < index; i ++) {       // keep going as long as i doesn't reach index
                current = current.next;
            }
            return current.data;
    }

    // returns the index of the specified element
    public int indexOf(Object o) {
        Node<E> current = head;
        for (int i = 0; i < size; i ++) {
            if (o == null) {
                if (current.data == null) {
                    return i;
                }
            }
            else if (o.equals(current.data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    // remove and return a value at the index
    public E remove(int index) {
        Node<E> current = head;
        E removedData;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (isEmpty()) {
            return null;
        }

        if (index == 0) {
            removedData = head.data;
            head = head.next;
            size --;
            return removedData;
        }

        for (int i = 0; i < index - 1; i ++) {
            current = current.next;
        }
        removedData = current.next.data;
        current.next = current.next.next;
        size --;
        return removedData;
    }

    // remove the first occurrence of a value in the linked list
    public boolean remove(Object o) {
        int result;
        result = indexOf(o);

        if (result != -1) {
            remove(result);
            return true;
        }
        return false;
    }

    // updates the value at an index and returns the old one
    public E set(int index, E data) {
        Node<E> current = head;
        E oldData;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        for (int i = 0; i < index; i ++) {
            current = current.next;
        }
        oldData = current.data;
        current.data = data;
        return oldData;
    }

    // toString()
    @Override
    public String toString() {
        Node<E> current = head;
        String result = "[";
        
        for (int i = 0; i < size; i ++) {
            result += String.valueOf(current.data);
            if (current.next != null) {
                result += ", ";
            }
            current = current.next;
        }
        return result + "]";
    }

    // optional equals method 
    @Override
    public boolean equals(Object o) {
        // check if they share same reference identity
        if (this == o) {
            return true;
        }

        // check if parameter is a linked list
        if (!(o instanceof LinkedList)) {
            return false;
        }

        // type casting
        LinkedList<?> other = (LinkedList<?>) o;
        // check if same length
        if (this.size != other.size) {
            return false;
        }

        // initiate two pointers
        Node<E> currentThis = this.head;
        LinkedList<?>.Node<?> currentOther = other.head;
        // start loop
        for (int i = 0; i < size; i ++) {
            // catch null
            if (currentThis.data == null) {
                if (currentOther.data != null) {
                    return false;
                }
            }
            else if (!currentThis.data.equals(currentOther.data)) {
                return false;
            }
        currentThis = currentThis.next;
        currentOther = currentOther.next;
        }
        return true;
    }
}

