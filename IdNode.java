import java.util.*;
/**
*IdNode
*Represents a client of the library
*/

public class IdNode
{
   private IdNode left; //left son
   private IdNode right; //right son
   private IdNode p; //parent 

   private String name; //name of client
   private String color; //color of node
   private String id; //id of client. The RB tree is sorted by that value.
   private int count; //number of books the client loanded
   private ArrayList<String>books=new ArrayList<String>(); //list of books the client loanded
      
   /**
   * Constructor
   * @patam count - the number of books he has
   * @patambooks- the books he has
   */   
   public IdNode(String name, String id, int count, ArrayList<String>books)
   {
       this.name=name;
       this.id=id;
       this.count=count;
       this.books=books;
       this.left=null;
       this.right=null;
       this.p=null;
       this.color="RED";
    }
    
    
    /**
   * RBinsert - insert a new node to the tree
   * @patam node - a node insert to the RBtree
   */
    public IdNode RBinsert(IdNode root, IdNode node)
    {        
        boolean found=false;
        IdNode x=root;
        IdNode y=null;
        
        while (!found) //while we didn't find the place to put the node
        {
            y=x;
            if (node.id.compareTo(x.id)<0)     //compare the id and choose the right place to go (left or right)    
            {
                if (x.left!=null) x=x.left;          
                else found=true; 
            }
            else 
            {
                if (x.right!=null) x=x.right;           
                else found=true;
            }
        }
        node.p=y; 
        if (node.id.compareTo(x.id)<0)
            y.left=node;
        else y.right=node;
        node.left=null;
        node.right=null;
        node.color="RED";                
        root=RBinsertFix(root, node); //fix the tree after the insertion
        return root; //return the new root of the tree
    }
    
    /**
    * RBinsertFix - fix the tree after an insertion
    * @param node- a node to insert to the RBtree  
    */
    public IdNode RBinsertFix(IdNode root, IdNode node)
    {        
        node.color="RED";
        //Correct double red problems, if they exist
        if (node!=null && node!=root && node.p!=null && node.p.color.equals("RED"))
        {
            if (node.getSilbing(node.p)!=null && node.getSilbing(node.p).color.equals("RED"))
            {//Recolor, and move up to see if more work needed
                node.p.color="BLACK";
                node.getSilbing(node.p).color="BLACK";
                node.getGrandparent(node).color="RED";
                root=RBinsertFix(root, node.getGrandparent(node));
            }
            else if (node.getGrandparent(node)!=null && node.p==node.getGrandparent(node).left)
            { //Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also a left child, or a left-right rotation otherwise.
                if (node==node.p.right)
                    root=leftRotate(root, node=node.p);
                node.p.color="BLACK";
                node.getGrandparent(node).color="RED";
                root=rightRotate(root, node.getGrandparent(node));
            }
            else if (node.getGrandparent(node)!=null && node.p==node.getGrandparent(node).right)
            { //Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also a right child, or a right-left rotation otherwise.
                if (node==node.p.left)
                    rightRotate(root, node=node.p);
                node.p.color="BLACK";
                node.getGrandparent(node).color="RED";
                root=leftRotate(root, node.getGrandparent(node));
            }            
        }
        
        root.color="BLACK";        //color the root in black
        return root; //return the new root of the tree
    }
    
    
    /**
    * getSilbing - return the brother of the input node
    */
    private IdNode getSilbing(IdNode node)
    {
        return (node == null || node.p == null) ? null : (node == node.p.left) ? (IdNode) node.p.right 
            : (IdNode) node.p.left;
    }
    
    /**
    * getGrandparent - return the Grandparent of the input node 
    */
    private IdNode getGrandparent(IdNode node) {
        return (node == null || node.p == null) ? null : (IdNode) node.p.p;
    }
    
    
    /**
    * rightRotate - rotate the tree right 
    * @param node- a node that we rotate right in the RBtree  
    */
    public IdNode rightRotate(IdNode root, IdNode node)
    {
        IdNode y=node.left;
        
        if (y!=null && y.right!=null)
        {
            node.left=y.right;
            y.right.p=node;
        }
        else node.left=null;
        
        if (node.p==null)
        {
            if (y!=null) 
            {
                y.p=null;
                root=y;
            }
        }
        
        else {
            if (y!=null) y.p=node.p;
            if (node==node.p.right)
                node.p.right=y;
            else node.p.left=y;
        }
        if (y!=null) y.right=node;
        node.p=y;
        return root;
    }
    
    /**
    * leftRotate - rotate the tree left
    * @param node- a node that we rotate left in the RBtree  
    */
    public IdNode leftRotate(IdNode root, IdNode node)
    {        
        IdNode y=node.right;
        
        if (y!=null && y.left!=null)        
        {
            node.right=y.left;
            y.left.p=node;          
        }
        else node.right=null; 
        
        if (node.p==null)        
        {
            if (y!=null) 
            {
                y.p=null;
                root=y;    
            }
        }
        else {
            y.p=node.p;
            if (node==node.p.left)
                node.p.left=y;
            else node.p.right=y;
        }
        if (y!=null) y.left=node;
        node.p=y;       
        return root;
    }
    
    /**
    * treeSearch - search for a node in the tree. Return the node we are looking for
    * @param id- the id of the client we are looking for
    */
    public IdNode treeSearch(IdNode root, String id)
    {       
        if (root==null)
            return null;        
        if (id.equals(root.id))
            return root;
        if (root.left!=null && id.compareTo(root.id)<0)
        {            
            return treeSearch(root.left, id);
        }
        else if (root.right!=null) 
        {            
            return treeSearch(root.right, id);
        }
        return null;
    }
    
    /**
    * treeMinimum - return the minimum of the tree
    */
    public IdNode treeMinimum(IdNode root)
    {
        IdNode temp=root;
        while (temp.left!=null)
            temp=temp.left;
        return temp;
    }
    
    /**
    * treeSuccessor - return the successor (by value) of the input node
    * @param node- a node that we are looking for its successor
    */
    public IdNode treeSuccessor(IdNode root, IdNode node)
    {
        if (node.right!=null)
            return treeMinimum(node.right);
        if (node==root) //if node=root and there's not a right son, there is no successor
            return null;
        IdNode y=node.p;
        while (y!=null && y.right!=null && node==y.right)
        {
            node=y;
            y=y.p;
        }
        return y;
    }
    
    /**
    * RBdelete - delete a node from the tree. Return the new root of the tree after the delete
    * @param node- a node that we are deleting from the 
    */
    public IdNode RBdelete(IdNode root, IdNode node)
    {
        IdNode x, y, tmp;
        
        if (node==null)
            return root;
        
        if (node.left==null || node.right==null)
            y=node;
        else y=node.treeSuccessor(root, node);
                
        if (y.left!=null)
            x=y.left;
        else x=y.right;
        
        if (x!=null)
            x.p=y.p;
        tmp=y.p;            
        if (y.p==null)
            root=x;
        else {
            if (y==y.p.left)                
                y.p.left=x;            
            else y.p.right=x; 
        }
        
        if (y!=node)
        {
            node.count=y.count;
            node.id=y.id;
            node.name=y.name;            
            node.books=y.books;            
        }
        
        if (y.color.equals("BLACK"))
           root=RBdeleteFix(root, x, tmp);
            
        return root;
    }
    
    /**
    * RBdeleteFix - fix the tree after the delete and return the new root of the tree
    * @param node- where the delete was done
    * @param nodeP - the father of node. we send it to the method so we can know who the father is, if node=null
    */
    public IdNode RBdeleteFix(IdNode root, IdNode node, IdNode nodeP)
    {        
        IdNode w;
        while (node!=root && getColor(node).equals("BLACK"))
        {
            if (node==nodeP.left)
            {
                w=nodeP.right;
                if (getColor(w).equals("RED"))
                {
                    w.color="BLACK";
                    nodeP.color="RED";
                    root=leftRotate(root, nodeP);
                    w=nodeP.right;                    
                }
                
                if (getColor(w.left).equals("BLACK") && getColor(w.right).equals("BLACK"))
                {
                    w.color="RED";
                    node=nodeP;                 
                }
                
                else {
                    if (getColor(w.right).equals("BLACK"))
                    {
                        w.left.color="BLACK";
                        w.color="RED";
                        root=rightRotate(root, w);
                        w=nodeP.right;                        
                    }
                    if (w!=null) w.color=nodeP.color;
                    nodeP.color="BLACK";
                    if (w!=null && w.right!=null) w.right.color="BLACK";
                    root=leftRotate(root, nodeP);
                    node=root;
                }
            }
            
            else {
                w=nodeP.left;
                if (getColor(w).equals("RED"))
                {
                    w.color="BLACK";
                    nodeP.color="RED";
                    root=leftRotate(root, nodeP);
                    w=nodeP.left;
                }
                if (getColor(w.right).equals("BLACK") && getColor(w.left).equals("BLACK"))
                {
                    w.color="RED";
                    node=nodeP;
                }
                else {
                    if (getColor(w.left).equals("BLACK"))
                    {
                        w.right.color="BLACK";
                        w.color="RED";
                        root=leftRotate(root, w);
                        w=nodeP.left;
                    }
                    if (w!=null) w.color=nodeP.color;
                    nodeP.color="BLACK";
                    if (w!=null && w.left!=null) w.left.color="BLACK";
                    root=rightRotate(root, nodeP);
                    node=root;
                }
            }  
            
            nodeP=nodeP.p;
        }     
        
        if (node!=null) node.color="BLACK";
        if (root!=null) root.color="BLACK"; //if the tree is not empty after the removal
        return root;
    }
    
    //return the color of the node. if the node is null, return BLACK
    public String getColor(IdNode node)
    {
        return node==null ? "BLACK" : node.color;
    }
    
    //set the color of the node to the input
    public void setColor(String color)
    {
        this.color=color;
    }
    
    //retutrn the right son
    public IdNode getRight()
    {
        return right;
    }
    
    //return the left son
    public IdNode getLeft()
    {
        return left;
    }
    
    //return the count (number of books) of the client
    public int getCount()
    {
        return count;
    }
    
    //return the list of books the client has
    public ArrayList<String> getBooks()
    {
        return books;
    }
    
    //return the id of client
    public String getId()
    {
        return id;
    }
    
    //return the name of client
    public String getName()
    {
        return name;
    }
}
