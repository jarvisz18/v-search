package com.ixan.boot.test.juc.collections;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/9 10:57
 * @description 单向链表
 * @version 1.0
 */
public class MyLinkedList<V> {
	public MyLinkedList() {
	}

	/**
	 * 单向链表的特点:
	 * 1.头节点
	 * 2.尾节点 为空
	 * 3.链表大小
	 */
	private Node<V> first;
	private Node<V> NULL = null;
	private int size;

	public void addFirst(V v) {
		Node<V> node = new Node<>();
		node.value = v;
		if (size == 0) {
			node.next = NULL;
		} else {
			node.next = first;
		}
		first = node;
		size++;
	}

	public Node<V> removeFirst() {
		if (size == 0) return NULL;
		Node<V> node = first;
		first = first.next;
		size--;
		return node;
	}

	public static <V> MyLinkedList<V> of(V... collection) {
		MyLinkedList<V> linkedList = new MyLinkedList<>();
		for (V v : collection) {
			linkedList.addFirst(v);
		}
		return linkedList;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(V v) {
		Node<V> node = first;
		while (node != NULL) {
			if (node.value == v) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		} else {
			StringBuilder builder = new StringBuilder("[");
			Node<V> node = first;
			while (node != NULL) {
				builder.append(node.value).append(",");
				node = node.next;
			}
			return builder.replace(builder.length() - 1, builder.length(), "]").toString();
		}
	}

	/**
	 * 链表是由一个一个节点组成
	 * 节点包含两部分内容:
	 * 1.自己本身的值,下一个节点的引用
	 */
	private static class Node<V> {
		V value;
		Node<V> next;
	}

	public static void main(String[] args) {
		MyLinkedList<String> linkedList = MyLinkedList.of("Java", "Go", "Python", "Scala");
		System.out.println(linkedList.size());
		System.out.println(linkedList);
		linkedList.addFirst("C++");
		System.out.println(linkedList.size());
		System.out.println(linkedList);
		System.out.println(linkedList.contains("GoLang"));
		System.out.println(linkedList.contains("Go"));
		System.out.println("-------------------------------------");

		Node<String> node = linkedList.first;
		while (node != linkedList.NULL) {
			System.out.println(linkedList.removeFirst().value);
			node = linkedList.first;
		}
		System.out.println(linkedList.size);
	}
}
