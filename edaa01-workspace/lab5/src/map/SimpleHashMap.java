package map;

import java.util.*;

public class SimpleHashMap<K, V> implements Map<K, V> {

	public static class Entry<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

		public String toString() {
			return key + "=" + value;
		}

	}

	private Entry<K, V>[] hashMap;
	private double loadFactor = 0.75;

	public SimpleHashMap() {
		hashMap = (Entry<K, V>[]) new Entry[16];
	}

	public SimpleHashMap(int capacity) {
		hashMap = (Entry<K, V>[]) new Entry[capacity];
	}

	public String show() {
		String s = "";
		int i = 0;
		for (Entry<K, V> e : hashMap) {
			if (e != null) {
				String a = i + "\t" + e.toString();
				while (e.next != null) {
					e = e.next;
					a = a + " " + e.toString();
				}
				s = s + a + "\n";
				i++;
			}
		}
		if (s.equals("")) {
			s = null;
		}
		return s;
	}

	public V get(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> e;
		V value = null;
		;
		for (int i = 0; i < hashMap.length; i++) {
			e = find(i, key);
			if (e != null) {
				value = e.getValue();
				break;
			}
		}
		return value;
	}

	private int index(K key) {
		int index = key.hashCode() % hashMap.length;
		if (index < 0) {
			index = hashMap.length + index;
		}
		return index;
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> e = hashMap[index];
		while (e != null) {
			if (e.getKey().equals(key)) {
				break;
			}
			e = e.next;
		}
		return e;
	}

	@Override
	public boolean isEmpty() {
		boolean empty = true;
		for (Entry<K, V> e : hashMap) {
			if (e != null) {
				empty = false;
				break;
			}
		}
		return empty;
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		V value = null;
		Entry<K, V> find = find(index, arg0);
		if (find != null) {
			value = find.setValue(arg1);
		} else {
			Entry<K, V> temp = hashMap[index];
			hashMap[index] = new Entry(arg0, arg1);
			hashMap[index].next = temp;
			if (temp != null) {
				value = temp.getValue();
			}
		}
		return value;
	}

	private void rehash() {
		int size = 0;
		for (Entry<K, V> e : hashMap) {
			if (e != null) {
				size++;
			}
		}
		int length = hashMap.length;
		if (size >= loadFactor * length) {
			Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[length * 2];
			for (int i = 0; i < length; i++) {
				temp[i] = hashMap[i];
			}
			hashMap = temp;
		}
	}

	@Override
	public V remove(Object arg0) {
		int index = index((K) arg0);
		Entry<K, V> e = hashMap[index];
		Entry<K, V> temp = null;
		V value = null;
		while (e != null) {
			if (e.getKey().equals(arg0)) {
				value = e.getValue();
				if (temp != null) {
					temp.next = e.next;
				} else {
					hashMap[index] = e.next;
				}
			}
			temp = e;
			e = e.next;
		}
		return value;
	}

	@Override
	public int size() {
		int size = 0;
		for (Entry<K, V> e : hashMap) {
			if (e != null) {
				size++;
				while (e.next != null) {
					e = e.next;
					size++;
				}
			}
		}
		return size;
	}

}
