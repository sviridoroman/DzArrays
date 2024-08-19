package arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализация структуры данных список.
 * @author Свиридо Роман.
 * @param <T> тип данных элементов списка.
 */
public class MyArrayList<T> implements Iterable<T>{
	
	/**
	 * Массив элементов списка.
	 */
	private T[] list;

	/**
	 * Конструктор для создания нового списка.
	 */
	public MyArrayList() {
		list = (T[])new Object[10];
	}
	
	/**
	 * Конструктор для создания нового списка.
	 * @param size Размерность списка.
	 */
	public MyArrayList(int size) {
		list = (T[])new Object[size];	
	}
	
	/**
	 * Конструктор для создания нового списка.
	 * @param items массив элементов для добавления в список.
	 */
	public MyArrayList(T[] items) {
		list = (T[])new Object[10];
		for(T item: items) {
			add(item);
		}
	}
	
	/**
	 * Метод для увеличения размерности списка.
	 * Размерность увеличивается по формуле: текущая размерность * 1.5 + 1.
	 */
	private void ensureCapacity() {
		T[] newList = (T[])new Object[(int)(list.length * 1.5 + 1)];
		
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		
		list = newList;
	}
	
	/**
	 * Добавление элемента в список.
	 * @param index Индекс элемента, который добавляется в список.
	 * @param element Данные для добавления в список. 
	 */
	public void add(int index, T element) {
		if (index < 0 || index > list.length){
            throw new IndexOutOfBoundsException(index);
        }
		
		if (index == list.length - 1) {
			ensureCapacity();
		}

		T[] newList = (T[])new Object[list.length];
		int j = 0;
		
		for (int i = 0; i < list.length - 1; i++) {
			if( i == index) {
				newList[i] = element;
				j++;
			}
			
			newList[j] = list[i];
			j++;
		}
		
		list = newList;
	}
	
	/**
	 * Добавление элемента в конец списка.
	 * @param element Данные для добавления в список. 
	 */
	public void add(T element) {
		int i = 0;
		
		while(list[i] != null) {
			i++;
		}
		
		add(i, element);
	}
	
	/**
	 * Возвращает элемент по индексу.
	 * @param index Индекс возвращаемого элемента . 
	 */
	public T get(int index) {
		if (index < 0 || index > list.length - 1){
            throw new IndexOutOfBoundsException(index);
        }
		
		return list[index];	
	}
	
	/**
	 * Очистка списка.
	 */
	public void clear() {
		list = (T[])new Object[10];
	}
	
	/**
	 * Удаляет элемент по индексу.
	 * @param index Индекс удаляемого элемента . 
	 */
	public void remove(int index) {
		if (index < 0 || index >= list.length){
			throw new IndexOutOfBoundsException(index);
        }
		
		T[] newList = (T[])new Object[list.length - 1];
		int j = 0;

		for (int i = 0; i < list.length - 1; i++) {
			if( i == index) {
				j++;
			}
			newList[i] = list[j];
			j++;
		}
		
		list = newList;
	}
	
	/**
	 * Устанавливает значение для элемента списка.
	 * @param index Индекс элемента в списке.
	 * @param element Данные для добавления в список. 
	 */
	public void set(int index, T element) {
		if (index < 0 || index >= list.length){
			throw new IndexOutOfBoundsException(index);
        }
		
		list[index] = element;
	}
	
	/**
	 * Возвращает размерность списка. 
	 */
	public int size() {
		return list.length;
	}
	
	/**
	 * Возвращает элементы существующего списка по указанным индексам.
	 * @param fromIndex Начальный индекс. 
	 * @param toIndex Последний индекс. 
	 */
	public MyArrayList<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex >= list.length || toIndex < 0 || toIndex >= list.length){
			throw new IndexOutOfBoundsException();
        }
		
		int newListSize = toIndex - fromIndex;
				
		T[] newList = (T[])new Object[newListSize];
		int j = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (i >= fromIndex && i >= toIndex) {
				newList[j] = list[i];
				j++;
			}
		}
		
		return new MyArrayList(newList);
	}

	@Override
	public Iterator<T> iterator() {
		return new MyArrayListIterator(list);
	}

	/**
	 * класс имплементирующий интрефейс java.util.Iterator<T>.
	 */
	private class MyArrayListIterator implements Iterator<T> {

		/**
		 * Указатель на текущтй элемент списка.
		 */
		int index;

		/**
		 * Конструктор для создания итератора.
		 * @param list массив типа <T>.
		 */
		public MyArrayListIterator(T[] list) {
			index = 0;
		}

		/**
		 * Метод проверяющий наличие элемента в списке.
		 */
		@Override
		public boolean hasNext() {
			return (index < list.length);
		}

		/**
		 * Метод итерирующий следующий элемент.
		 * В случае отсутствия элемента возвращает NoSuchElementException.
		 */
		@Override
		public T next() {			
			T data = list[index];
			if (hasNext()) {
				T element = list[index];
				index++;
				return element;
			} else {
				throw new NoSuchElementException();
			}
		}	
	}
}
