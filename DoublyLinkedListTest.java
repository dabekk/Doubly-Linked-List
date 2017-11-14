
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int dllSize = 0;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     */
    public boolean isEmpty()
    {
      if(dllSize <= 0)
    	  return true;
      else
    	  return false;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     */
    public void insertBefore( int pos, T data ) 
    {
    	//handles first 2 inputs to assign to head and tail
    	if(isEmpty())
    	{
    		DLLNode newNode = new DLLNode(data, null, null);
    		head = newNode;
    		tail = newNode;
    	}
    	else
    	{
    		if(dllSize == 1)	//handles second element entered
        	{
        		//link head and tail
        		
        		if(pos <= 0)
        		{
        			DLLNode newNode = new DLLNode(data, null, head);
        			tail = new DLLNode(head.data, newNode, null);
        			head = newNode;
        		}
        		else
        		{
        			DLLNode newNode = new DLLNode(data, head, null);
        			head = new DLLNode(tail.data, null, newNode);
        			tail = newNode;
        		}
        	}
        	else
        	{
        		if(pos <= 0)
            	{
            		DLLNode newNode = new DLLNode(data, null, head);
            		DLLNode oldNode = head;
            		 oldNode.prev = newNode;
            		 head = newNode;
            	}
            	else
            	{
            		if(pos >= dllSize)
            		{
            			DLLNode newNode = new DLLNode(data, tail, null);
            			DLLNode oldNode = tail;
            			oldNode.next = newNode;
            			tail = newNode;
            		}
            		else
            		{
            			DLLNode iter = head;
            			while(iter != null && pos > 0)	//iterates over nodes until reaches correct one
            			{
            				iter = iter.next;
            				pos--;
            			}
            			if(iter != null)
            			{
            				DLLNode newNode = new DLLNode(data, iter.prev, iter);
            				DLLNode prevNode = iter.prev;
            				iter.prev = newNode;
            				prevNode.next = newNode;
            			}
            		} 
            	}	
        	}
    	}
    	dllSize++;
     }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     */
    public T get(int pos) 
    {
    	if(!isEmpty() && pos >= 0)
    	{
    		DLLNode iter = head;
        	for(int i = 0; i < pos; i++)
        	{
        		if(iter.next != null)
        		{
        			iter = iter.next;
        		}
        		else
        		{
        			return null; //out of bounds
        		}
        	}
          return iter.data;
    	}
    	return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     */
    public boolean deleteAt(int pos) 
    {
    	if(!isEmpty())
    	{
    		DLLNode iter = head;
        	if(pos == 0) //delete first element
        	{
        		if(iter.next == null) //in case only one element in DLL
        		{
        			head = null;
        		}
        		else
        		{
        			DLLNode newNode = iter.next;
        			newNode.prev = null;
        			head = newNode;
        		}
        		dllSize--;
        		return true;
        	}
        	iter = head;
    		while(iter.next != null && pos > 0)
    		{
    			iter = new DLLNode(iter.next.data, iter, iter.next.next); //to fix bug
    			pos--;
    		}
        	if(iter != null && pos == 0)
        	{
        		if(iter.next == null) //iter == tail
        		{
        			DLLNode newNode = tail.prev;
        			newNode.next = null;
        			tail = newNode;
        		}
        		else
        		{
        			
        			DLLNode prevNode = iter.prev;
        			DLLNode nextNode = iter.next;
        			nextNode.prev = prevNode;
        			prevNode.next = nextNode;
        		}
        		dllSize--;
        		return true;
        	}
    	}
      return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     */
    public void reverse()
    {
    	if(!isEmpty() && dllSize > 1)
    	{
    		DLLNode iter = head;
    		DLLNode swap = null;
        	while(iter != null)
        	{
        		swap = iter.next;
        		iter.next = iter.prev;
        		iter.prev = swap;
        		
        		iter = iter.prev;
        	}
        	DLLNode temp = tail;
        	tail = head;
        	head = temp;
        	head.prev = null;
        	tail.next = null;
    	}
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     */
    public void push(T item) 
    {
    	DLLNode newNode = new DLLNode(item, null, head);
//		head.prev = newNode;
		head = newNode;
		dllSize++;
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     */
    public T pop() 
    {
    	if(!isEmpty())
    	{
    		 DLLNode popThis = head;
    	     deleteAt(0);
    	     return popThis.data;
    	}
    	else
    	{
    		return null;
    	}
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     */
    public void enqueue(T item) 
    {
    	insertBefore(0, item);
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     */
    public T dequeue() 
    {
    	if(!isEmpty())
    	{
    		T getThis = get(dllSize-1);
    		deleteAt(dllSize-1);
    		return (T) getThis;
    	}
      return null;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */

    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }
      return s.toString();
    }




}


