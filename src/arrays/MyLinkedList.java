package arrays;

/**
 * Класс реализация структуры данных двусвязный список.
 * @author Свиридо Роман.
 * @param <T> тип данных элементов списка.
 */
public class MyLinkedList<T> {
	
	/**
	 * Класс содержащий данные элемента MyLinkedList и ссылки на следующий, и предыдущий элементы.
	 */
	private class Element {
		
		/**
		 * Данные элемента.
		 */
		T data;
		
		/**
		 * Ссылка на следующий элемент.
		 */
		Element next;
		
		/**
		 * Ссылка на предыдущий элемент.
		 */
		Element prev;
		
		/**
		 * Конструктор для создания нового элемента.
		 * @param data данные хранящиеся в элементе списке.
		 */
		public Element(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}
	
	/**
	 * Ссылка на первый элемент списка.
	 */
	private Element first;
	
	/**
	 * Ссылка на последний элемент списка.
	 */
	private Element last;
	
	/**
	 * Длина списка.
	 */
	private int totalLength;
	
	/**
	 * Конструктор для списка.
	 */
	public MyLinkedList() {
		first = null;
		last = null;
		int totalLength = 0;
	}
	
	/**
	 * Добавление элемента в список.
	 * @param index Индекс элемента, который добавляется в список.
	 * @param element Данные для добавления в список. 
	 */
	public void add(int index, T element) {
		if (index < 0 || index > totalLength){
            throw new IndexOutOfBoundsException(index);
        }
		
		if (index == 0) {
			addFirst(element);
		}
		else if (index >= totalLength) {
			addLast(element);
		} else {
			Element newElement = new Element(element);
			Element elementAtIndex = first;
			for (int i = 0; i < totalLength - 1; i++) {
				elementAtIndex = elementAtIndex.next;
			}
			Element prevElement = elementAtIndex.prev;
			newElement.prev = prevElement;
			newElement.next = elementAtIndex;
			prevElement.next = newElement;
			elementAtIndex.prev = newElement;
			totalLength++;	
		}
	}
	
	/**
	 * Добавление элемента в конец списка.
	 * @param element Данные для добавления в список. 
	 */
	public void add(T element) {
		addLast(element);
	}
	
	/**
	 * Добавление элемента в начало списка.
	 * @param element Данные для добавления в список. 
	 */
	public void addFirst(T element) {
		Element newElement = new Element(element);

		if (first == null) {
			first = newElement;
		} else {
			newElement.next = first;
			first.prev = newElement;
			
			
			first = newElement;
		}
		
		totalLength++;
		
		if (first.next == null) {
            last = first;
        }
	}
	
	/**
	 * Добавление элемента в конец списка.
	 * @param element Данные для добавления в список. 
	 */
	public void addLast(T element) {
		Element newElement = new Element(element);
		
		if (last != null) {
			newElement.prev = last;
			last.next = newElement;
		}
		
		if (last == null || totalLength == 0) {
			first = newElement;
		}
		
		last = newElement;
		totalLength++;
	}
	
	/**
	 * Возвращает элемент по индексу.
	 * @param index Индекс возвращаемого элемента . 
	 */
	public T get(int index) {
		if (index < 0 || index >= totalLength){
			throw new IndexOutOfBoundsException(index);
        }
		
		Element element = first;
		
		for (int i = 0; i < index; i++) {
			element = element.next;
		}
		
		return element.data;
		
	}
	
	/**
	 * Возвращает первый элемент списка.
	 */
	public T getFirst() {
		return first.data;
	}
	
	/**
	 * Возвращает последний элемент списка.
	 */
	public T getLast() {
		return last.data;
	}
	
	/**
	 * Очистка списка.
	 */
	public void clear() {
		first = null;
		last = null;
		int totalLength = 0;
	}
	
	/**
	 * Удаляет элемент по индексу.
	 * @param index Индекс удаляемого элемента . 
	 */
	public void remove(int index) {
		if (index < 0 || index >= totalLength){
			throw new IndexOutOfBoundsException(index);
        }
		
		if (index == 0) {
			removeFirst();
		} else {
			Element element = first;
			
			for (int i = 0; i < index - 1; i++) {
				element = element.next;
			}
			
			element.next = element.next.next;
			totalLength--;
			
			if (element.next == null) {
				last = element;
			}
		}
	}
	
	/**
	 * Удаляет первый элемент списка.
	 */
	public void removeFirst() {
		first = first.next;
		totalLength--;
		
		if (totalLength == 0) {
			last = null;
		}
	}
	
	/**
	 * Удаляет последний элемент списка. 
	 */
	public void removeLast() {
		last = last.prev;
		totalLength--;
		
		if (totalLength == 0) {
			first = null;
		}
	}
	
	/**
	 * Устанавливает значение для элемента списка.
	 * @param index Индекс элемента в списке.
	 * @param element Данные для добавления в список. 
	 */
	public void set(int index, T element) {
		if (index < 0 || index >= totalLength){
			throw new IndexOutOfBoundsException(index);
        }
		
		if (index == 0) {
			first.data = element;
		} else if (index == totalLength - 1) {
			last.data = element;
		} else {
			Element elementAtIndex = first;
			for (int i = 0; i < index; i++) {
				elementAtIndex = elementAtIndex.next;
			}
			elementAtIndex.data = element;
		}
	}
	
	/**
	 * Возвращает размерность списка. 
	 */
	public int size() {
		return totalLength;
	}
	
	/**
	 * Возвращает элементы существующего списка по указанным индексам.
	 * @param fromIndex Начальный индекс. 
	 * @param toIndex Последний индекс. 
	 */
	public MyLinkedList<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex >= totalLength || toIndex < 0 || toIndex >= totalLength){
			throw new IndexOutOfBoundsException();
        }
		
		MyLinkedList<T> newLinkedList = new MyLinkedList<T>();
		Element element = first;
		
		for (int i = 0; i < fromIndex; i++) {
			element = element.next;
			newLinkedList.totalLength = 1;
		}
		
		newLinkedList.first = element;
		
		for (int i = 0; i < toIndex - fromIndex; i++) {
			element = element.next;
			newLinkedList.totalLength++;
		}
		
		newLinkedList.last = element;
		return newLinkedList;
	}
}
