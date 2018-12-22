import java.util.*;

/**
*IdRBTree 
*Manage the RB tree that is sorted by the id of clients
*/

public class IdRBTree
{
    private IdNode root; //root of the tree
    
    //constructor, set the root to null (the tree is now empty)
   public IdRBTree()
   {
       root=null;
    }
    
    //inserts a node and returns the updated root of the tree
   public IdNode insertIdClient(IdNode node)
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
    public IdNode getRoot()
    {
        return root;
    }
    
    //set the root of the tree to the input node
    public void setRoot(IdNode node)
    {
        root=node;
    }
    
    //inorder scan of the tree
    private void inOrder(IdNode root)
    {
        if (root!=null)
        {
            inOrder(root.getLeft());
            System.out.println(root.getId()+" "+root.getName()+" "+root.getCount()+" "+root.getBooks()+" "+root.getColor(root));
            inOrder(root.getRight());
        }
    }
    
    //scan the nodes of the tree and keep the id of each node.
    public ArrayList<String> getIdData(IdNode root)
    {
        ArrayList<String>temp=new ArrayList<String>();
        getIdData(root, temp);
        return temp;
    }
    
    //scan the nodes of the tree by the id.
    private void getIdData(IdNode root, ArrayList<String> temp)
    {
        if (root!=null)
        {
            getIdData(root.getLeft(), temp);
            temp.add(root.getId());
            getIdData(root.getRight(), temp);
        }        
    }
    
    //scan the nodes of the tree by the number of books of each client.
    public ArrayList<Integer> getCountData(IdNode root)
    {
        ArrayList<Integer>temp=new ArrayList<Integer>();
        getCountData(root, temp);
        return temp;
    }
    
    //scan the nodes of the tree by the count of books.
    private void getCountData(IdNode root, ArrayList<Integer>temp)
    {
        if (root!=null)
        {
            getCountData(root.getLeft(), temp);
            temp.add(root.getCount());
            getCountData(root.getRight(), temp);
        }        
    }
}
