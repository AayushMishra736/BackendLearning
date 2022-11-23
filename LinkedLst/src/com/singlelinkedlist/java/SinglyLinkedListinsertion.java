package com.singlelinkedlist.java;

public class SinglyLinkedListinsertion {
	class Node {
		int data;
		Node next;
		Node(int data){
			this.data = data;
			this.next = next;
		}
	}
	private Node head;
	private Node tail;
	
	public void addNode(int data) {
		Node newNode = new Node(data);
		
		
	}
	
	
	public static void main(String[] args) {
	
		SinglyLinkedListinsertion sList = new SinglyLinkedListinsertion();  
         
	        //Add nodes to the list  
	        sList.addNode(1);  
	        sList.addNode(2);  
	        sList.addNode(3);  
	        sList.addNode(4);  
	          
	        //Displays the nodes present in the list  
	        sList.display();  
	}

}
