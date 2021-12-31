//  SORT. Sort a linear singly-linked list of INTs.

class Sort
{

//  NODE. A node in a linear singly linked list of INTs.

  private static class Node
  {
    private int  number;  //  The INT in the node, duh.
    private Node next;    //  The NODE that follows this one, or NULL.

//  Constructor. Initialize a new NODE with NUMBER and NEXT.

    private Node(int number, Node next)
    {
      this.number = number;
      this.next = next;
    }
  }

//  MAKE NODES. Return a list of NODEs that contains INTs from NUMBERS in order
//  of their appearance.

  private static Node makeNodes(int ... numbers)
  {
    if (numbers.length > 0)
    {
      Node first = new Node(numbers[0], null);
      Node last  = first;
      for (int index = 1; index < numbers.length; index += 1)
      {
        last.next = new Node(numbers[index], null);
        last = last.next;
      }
      return first;
    }
    else
    {
      return null;
    }
  }

//  WRITE NODES. Write the INTs from a list of NODEs in paired square brackets,
//  separated by commas, with a newline at the end.

  private static void writeNodes(Node nodes)
  {
    System.out.print('[');
    if (nodes != null)
    {
      System.out.print(nodes.number);
      nodes = nodes.next;
      while (nodes != null)
      {
        System.out.print(", ");
        System.out.print(nodes.number);
        nodes = nodes.next;
      }
    }
    System.out.println(']');
  }

//  SORT NODES. Sort UNSORTED, a list of NODEs, into nondecreasing order of its
//  NUMBER slots, without making new NODEs.

  private static Node sortNodes(Node unsorted)
  {
    Node left = null;
    Node right = null; //make the left and right lists
    int len = 0;
    
    //TESTING
    if (unsorted == null || unsorted.next == null) //if length is 0 or 1
    {
      return unsorted;
    }
    
    //HALVING
    Node temp = null;
    Node delete = null;
    while (unsorted != null)
    {
      if (len%2 == 0) //if its even add to right
      {
       delete = unsorted;
       unsorted = unsorted.next;
       len++;
       if (right == null) 
       {
        right = delete;
        right.next = null;
       }
       else 
       {
         temp = right;
         right = delete;
         right.next = temp;
         temp = null;  
       }
      }
      else //if its odd add to left
      {
       delete = unsorted;
       unsorted = unsorted.next;
       len++;
       if (left == null) 
       {
          left = delete;
          left.next = null;
       }
       else 
       {
        temp = left;
        left = delete;
        left.next = temp;
        temp = null;
       }
      } 
     }
    
    //SORTING
    
    Node sortedLeft = null;
    Node sortedRight = null; //make sorted lists of left and right
    
    sortedLeft = sortNodes(left); //call sortnodes on the left and right and put the new lists in sortedleft and sortedright
    sortedRight = sortNodes(right);
     
    
    //COMBINING
    
    
    Node sorted = null; //make a final sorted list
    Node a = null;

    if((sortedLeft != null) && (sortedRight != null))
    {
      if(sortedLeft.number < sortedRight.number) //when right is bigger
      {
       sorted = sortedLeft;
       a = sortedLeft;
       temp = sortedLeft.next;
       a.next = null;
       sortedLeft = temp;
      }
      else
      {
       sorted = sortedRight;
       a = sortedRight;
       temp = sortedRight.next;
       a.next = null;
       sortedRight = temp;
      }
    }
   while (sortedLeft!= null && sortedRight!=null)
     {
       if (sortedLeft.number > sortedRight.number) //when left is bigger
       {
       //add left.number to sorted
         a.next = sortedRight;
         a = a.next;
         temp = sortedRight.next;
         sortedRight = temp;
         a.next = null;
       }
       else if (sortedRight.number > sortedLeft.number)
       {
         //add left.number to sorted
         a.next = sortedLeft;
         a = a.next;
         temp = sortedLeft.next;
         sortedLeft = temp;
         a.next = null;
       }
     }
     if (sortedLeft!= null && sortedRight == null)
     {
       //add left to sorted
       a.next = sortedLeft;
     }
     if (sortedRight !=null && sortedLeft == null)
     {
       //add right to sorted
        a.next = sortedRight;
     }
   return sorted;
 }
     
                                   
//  MAIN. Run some examples. The comments show what must be printed.

  public static void main(String [] args)
  {
    writeNodes(sortNodes(makeNodes()));      //  []
    writeNodes(sortNodes(makeNodes(1)));     //  [1]
    writeNodes(sortNodes(makeNodes(1, 2)));  //  [1, 2]
    writeNodes(sortNodes(makeNodes(2, 1)));  //  [1, 2]

    writeNodes(sortNodes(makeNodes(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    //  [1, 2, 3, 4, 5, 6, 7, 8, 9]

    writeNodes(sortNodes(makeNodes(9, 8, 7, 6, 5, 4, 3, 2, 1)));
    //  [1, 2, 3, 4, 5, 6, 7, 8, 9]

    writeNodes(sortNodes(makeNodes(3, 1, 4, 5, 9, 2, 6, 8, 7)));
    //  [1, 2, 3, 4, 5, 6, 7, 8, 9]
  }
  
}