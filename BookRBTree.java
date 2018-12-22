import java.util.*;
/**
*BookRBTree 
*Manage the RB tree that is sorted by the name of each book
*/
public class BookRBTree
{
    private BookNode root; //root of the tree
    
    //constructor, set the root to null (the tree is now empty)
   public BookRBTree()
   {
       root=null;
    }
    
    //inserts a node and returns the updated root of the tree
   public BookNode insertBook(BookNode node)
   {       
       if (root==null) //if the tree is empty
       {
            root=node; //set the root as the node
            root.setColor("BLACK"); //set the color to BLACK
            return root; //return the root
        }
       else //the tree is not empty
       {           
            root=root.RBinsert(root, node); //insert the node to the tree
            return root; //return the root
        }
    }
    
     //inorder scan of the tree
   public void inOrder()
   {
       inOrder(root);
    }
    
    //return the root of the tree
    public BookNode getRoot()
    {
        return root;
    }
    
    //set the root of the tree to the input node
    public void setRoot(BookNode node)
    {
        root=node;
    }
    
    //inorder scan of the tree
    private void inOrder(BookNode root)
    {
        if (root!=null)
        {
            inOrder(root.getLeft());
            System.out.println(root.getBookName()+" "+root.getClientId()+" "+root.getColor(root));
            inOrder(root.getRight());
        }
    }
    
    
}
