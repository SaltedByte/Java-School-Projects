//Andrew Lucas CSC364-001 Assignment 1
import java.util.*;

public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable{
	private Node<E> head = new Node<E>(null);

	/** Create a default list */
	public MyDoublyLinkedList(){
		head.next = head;
		head.previous = head;
	}

	private static class Node<E>{
		E element;
		Node<E> previous;
		Node<E> next;

		public Node(E element){
			this.element = element;
		}
	}

	public String toString(){
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head.next;
		for (int i = 0; i < size; i++){
			result.append(current.element);
			current = current.next;
			if (current != head){
				result.append(", "); // Separate two elements with a comma
			}
		}
		result.append("]"); // Insert the closing ] in the string

		return result.toString();
	}

	@Override
	public Object clone(){
		try{
			MyDoublyLinkedList<E> listClone = (MyDoublyLinkedList<E>) super.clone();
			listClone.size = 0;
			listClone.head = new Node<E>(null);
			listClone.head.next = listClone.head;
			listClone.head.previous = listClone.head;
			ListIterator<E> iter = this.listIterator(0);
			while(iter.hasNext()){
				listClone.add(iter.next());
			}
			return listClone;
		}
		catch (CloneNotSupportedException ex){
			return false;
		}
	}

	private Node<E> getNode(int index){
		Node<E> current = head;
		if (index < size / 2)
			for (int i = -1; i < index; i++)
				current = current.next;
		else
			for (int i = size; i > index; i--)
				current = current.previous;
		return current;
	}

	@Override
	public void add(int index, E e){
		if (index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> prev = getNode(index - 1);
		Node<E> next = prev.next;
		Node<E> newNode = new Node<E>(e);
		prev.next = newNode;
		next.previous = newNode;
		newNode.previous = prev;
		newNode.next = next;
		size++;
	}

	@Override
	public void clear(){
		size = 0;
		head.next = head;
		head.previous = head;
	}

	@Override
	public boolean contains(E o){
		for (Node<E> current = head.next; current != head; current = current.next){
			E e = current.element;
			if (o == null ? e == null : o.equals(e))
				return true;
		}
		return false;
	}

	@Override
	public E get(int index){
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		return getNode(index).element;
	}

	@Override
	public int indexOf(E e){
		Node<E> current = head;
		for (int i = 0; i < size; i++){
			if (current.next.element == e)
				return i;
			current = current.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E e){
		int lastTime = 0;
		for(int i = 0; i < size; i++){
			if(this.get(i) == e){
				lastTime = i;
			}
		}
		return lastTime;
	}

	@Override
	public E remove(int index){
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0){
			return removeFirst();
		}
		else{
			Node<E> current = getNode(index);
			Node<E> next = current.next;
			Node<E> prev = current.previous;
			prev.next=next;
			next.previous = prev;
			size--;
			return current.element;
		}
	}

	@Override
	public Object set(int index, E e){
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Node<E> current = getNode(index);
		current.element = e;
		return current.element;
	}

	@Override
	public E getFirst(){
		return get(0);
	}

	@Override
	public E getLast(){
		if (size != 0){
			return get(size - 1);
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void addFirst(E e){
		Node<E> current = getNode(0);
		Node<E> newNode = new Node<E>(e);
		head.next = newNode;
		current.previous = newNode;
		newNode.previous = head;
		newNode.next = current;
		size++;
	}

	@Override
	public void addLast(E e){
		Node<E> current = getNode(size - 1);
		Node<E> newNode = new Node<E>(e);
		current.next = newNode;
		newNode.next = head;
		head.previous = newNode;
		newNode.previous = current;
		size++;
	}

	@Override
	public E removeFirst(){
		if (size != 0){
			Node<E> current = getNode(0);
			Node<E> next = current.next;
			Node<E> prev = current.previous;
			prev.next = next;
			next.previous = prev;
			size--;
			return current.element;
		}
		else{
			throw new NoSuchElementException();
		}
	}

	@Override
	public E removeLast(){
		if (size != 0){
			Node<E> current = getNode(size - 1);
			Node<E> next = current.next;
			Node<E> prev = current.previous;
			prev.next = next;
			next.previous = prev;
			size--;
			return current.element;
		}
		else{
			throw new NoSuchElementException();
		}
	}

	@Override
	public ListIterator<E> listIterator(int index){
		return new MyDoublyLinkedListIterator(index);
	}

	private static enum ITERATOR_STATE{
		CANNOT_REMOVE, CAN_REMOVE_PREV, CAN_REMOVE_CURRENT
	};

	private class MyDoublyLinkedListIterator implements ListIterator<E>{
		private Node<E> current; // node that holds the next element in the
									// iteration
		private int nextIndex; // index of current
		ITERATOR_STATE iterState = ITERATOR_STATE.CANNOT_REMOVE;

		private MyDoublyLinkedListIterator(int index){
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException("iterator index out of bounds");
			current = getNode(index);
			nextIndex = index;
		}

		@Override
		public void add(E e){
			switch (iterState){
				case CANNOT_REMOVE:
					Node<E> newNode = new Node<E>(e);
					Node<E> prev = head.previous;
					prev.next = newNode;
					head.previous = newNode;
					newNode.next = current;
					newNode.previous = prev;
					nextIndex++;
					size++;
					break;
				case CAN_REMOVE_PREV:
					newNode = new Node<E>(e);
					prev = current.previous;
					prev.next = newNode;
					current.previous = newNode;
					newNode.next = current;
					newNode.previous = prev;
					nextIndex++;
					size++;
					iterState = ITERATOR_STATE.CANNOT_REMOVE;
					break;
				case CAN_REMOVE_CURRENT:
					newNode = new Node<E>(e);
					prev = current.previous;
					prev.next = newNode;
					current.previous = newNode;
					newNode.next = current;
					newNode.previous = prev;
					nextIndex++;
					size++;
					iterState = ITERATOR_STATE.CANNOT_REMOVE;
					break;
			}
			iterState = ITERATOR_STATE.CANNOT_REMOVE;
		}

		@Override
		public boolean hasNext(){
			return nextIndex < size;
		}

		@Override
		public boolean hasPrevious(){
			if (nextIndex > 0){
				iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
				return true;
			}
			return false;
		}

		@Override
		public E next(){
			if (nextIndex >= size)
				throw new NoSuchElementException();
			E value = current.element;
			current = current.next;
			nextIndex++;
			iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
			return value;
		}

		@Override
		public int nextIndex(){
			return nextIndex;
		}

		@Override
		public E previous(){
			if(nextIndex == 0)
				throw new NoSuchElementException();
			current = current.previous;
			E value = current.element;
			nextIndex--;
			iterState = ITERATOR_STATE.CAN_REMOVE_CURRENT;
			return value;
		}

		@Override
		public int previousIndex(){
			return nextIndex - 1;
		}

		@Override
		public void remove(){
			switch (iterState){
			case CANNOT_REMOVE:
				throw new IllegalStateException();
			case CAN_REMOVE_PREV:
				Node<E> next = current;
				Node<E> prev = current.previous.previous;
				prev.next = next;
				next.previous = prev;
				size--;
				iterState = ITERATOR_STATE.CANNOT_REMOVE;
				break;
			case CAN_REMOVE_CURRENT:
				next = current.next;
				prev = current.previous;
				prev.next = next;
				next.previous = prev;
				size--;
				iterState = ITERATOR_STATE.CANNOT_REMOVE;
				break;
			}

		}

		@Override
		public void set(E e){
			switch (iterState){
				case CANNOT_REMOVE:
					throw new IllegalStateException();
				case CAN_REMOVE_PREV:
					current.previous.element = e;
					break;
				case CAN_REMOVE_CURRENT:
					current.previous.element = e;
					break;
			}

		}
	}
	@Override
	public boolean equals(Object o){
		MyDoublyLinkedList<E> list = (MyDoublyLinkedList<E>)o;
		if (this == list){
			return true;
		}
		else if (!(list instanceof MyList)){
			return false;
		}
		else if (list.size != this.size){
			return false;
		}
		else {
			ListIterator<E> listThis = this.listIterator(0);
			ListIterator<E> listList = list.listIterator(0);
			while(listThis.hasNext()){
				if (listThis.next() != listList.next()){
					return false;
				}
			}
			return true;
		}
	}
}