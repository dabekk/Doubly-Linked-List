import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue(testDLL.isEmpty());
    	testDLL.push(5);
    	assertFalse(testDLL.isEmpty());
    }
    
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(5);
    	testDLL.push(1);
    	testDLL.push(9);
    	testDLL.push(-2);
    	testDLL.toString();
    	assertEquals(-2, (int) testDLL.get(0));
    	assertEquals(5, (int) testDLL.get(3));
    	assertEquals(1, (int) testDLL.get(2));
    	assertEquals(9, (int) testDLL.get(1));
    	
    	assertEquals(null, testDLL.get(-2));
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	assertEquals(null, testDLL.get(1));
    }
    
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,-2);
    	testDLL.insertBefore(1,9);
    	testDLL.insertBefore(2,1);
    	testDLL.insertBefore(10, 5);

    	assertTrue(testDLL.deleteAt(0));
    	assertEquals("9,1,5", testDLL.toString());
    	testDLL.push(-2);
    	
    	assertTrue(testDLL.deleteAt(3));
    	assertEquals("-2,9,1", testDLL.toString());
    	testDLL.insertBefore(10, 5);

    	assertTrue(testDLL.deleteAt(1));
    	assertEquals("-2,1,5", testDLL.toString());
    	
    	testDLL = new DoublyLinkedList<Integer>();
    	assertFalse(testDLL.deleteAt(0));
    	testDLL.push(100);
    	assertFalse(testDLL.deleteAt(1));
    }
    
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,-2);
    	testDLL.insertBefore(1,9);
    	testDLL.insertBefore(2,1);
    	testDLL.insertBefore(10, 5);
    	testDLL.reverse();
    	assertEquals("5,1,9,-2", testDLL.toString()); 
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,800);
    	testDLL.insertBefore(1,900);
    	testDLL.reverse();
    	assertEquals("900,800", testDLL.toString());
    }
    
    @Test
    public void testPush()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(9);
    	testDLL.push(2);
    	testDLL.push(3);
    	testDLL.push(-1);
    	assertEquals("-1,3,2,9", testDLL.toString());
    }
    
    @Test
    public void testPop()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(9);
    	testDLL.push(2);
    	testDLL.push(3);
    	testDLL.push(-1);
    	
    	int testVal = testDLL.pop();
    	
    	assertEquals(testVal, -1);
    	assertEquals("3,2,9", testDLL.toString());
    	testVal = testDLL.pop();
    	assertEquals(testVal, 3);
    	assertEquals("2,9", testDLL.toString());
    	testVal = testDLL.pop();
    	assertEquals(testVal, 2);
    	testVal = testDLL.pop();
    	assertEquals(testDLL.pop(), null);
    }
    
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,-2);
    	testDLL.insertBefore(1,9);
    	testDLL.insertBefore(2,1);
    	testDLL.insertBefore(10, 5);
    	testDLL.enqueue(4);
    	assertEquals("4,-2,9,1,5", testDLL.toString());
    	testDLL.enqueue(7);
    	assertEquals("7,4,-2,9,1,5", testDLL.toString());
    }
    
    @Test
    public void TestDequeue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,-2);
    	testDLL.insertBefore(1,9);
    	testDLL.insertBefore(2,1);
    	testDLL.insertBefore(10, 5);
    	
    	int testVal = testDLL.dequeue();
    	assertEquals(testVal, 5);
    	assertEquals("-2,9,1", testDLL.toString());
    	testVal = testDLL.dequeue();
    	assertEquals(testVal, 1);
    	assertEquals("-2,9", testDLL.toString());
    	
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(1);
    	testVal = testDLL.dequeue();
    	assertEquals(1, testVal);
    	
    }

}
