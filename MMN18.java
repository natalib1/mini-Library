import java.util.*;
/**
* The main class of the project
*/
public class MMN18
{
   
   public static void main (String[] args)
   {
       Scanner scan=new Scanner(System.in);       
             
       IdRBTree IRBTree=new IdRBTree();       
       BookRBTree BRBTree=new BookRBTree(); 
       
       System.out.println("Enter your commands or questions. enter END when you finish");
       String tmp1=scan.next();    //scan the first word
       
       //temp variables to keep data
       String tmp2, tmp3, tmp4, tmpName, tmpId;
       ArrayList<String>books, tmpArray2;
       ArrayList<Integer>tmpArray1;
       
       while (!tmp1.equals("END")) //while the user didn't enter "END"
       {
            if (tmp1.equals("+")) //new client
            {
                tmp2=scan.next(); //get the name
                tmp3=scan.next(); //get the id
                
                System.out.println("updating IdRBTree");
                IdNode idNode=new IdNode(tmp2, tmp3, 0, null);
                IRBTree.insertIdClient(idNode);                
            }
            
            else if (tmp1.equals("-")) //delete a client
            {
                tmp2=scan.next(); //get the name
                tmp3=scan.next(); //get the id
                
                if (IRBTree.getRoot()==null) //if there are no clients 
                    System.out.println("client "+tmp3+" "+tmp2+" does nos exist");
                    
                else {   //search for the client and delete it from the IdRBTree                                 
                    System.out.println("searching for client "+tmp3+" in IdRBTree");
                    IdNode idNode=IRBTree.getRoot().treeSearch(IRBTree.getRoot(), tmp3);                                       
                    if (idNode!=null)                                           
                    {
                        System.out.println("deleting client "+tmp3+" from IdRBTree");
                        IRBTree.setRoot(IRBTree.getRoot().RBdelete(IRBTree.getRoot(), idNode));  
                    }
                    else System.out.println("client number "+tmp3+" does not exist");
                }              
            }
            
            else if (tmp1.equals("?")) 
            {
                tmp2=scan.next();
                
                if (tmp2.charAt(0)>=48 && tmp2.charAt(0)<=57)  //if it's a number 
                { //we need to find the books that the client own
                    System.out.println("searching for client "+tmp2+" in IdRBTree");      
                    //search for the client
                     IdNode iNode=IRBTree.getRoot().treeSearch(IRBTree.getRoot(), tmp2);
                    if (iNode==null) 
                        System.out.println("client "+tmp2+" does not exist");
                    else {  //print the books of the client                     
                        if (iNode.getBooks()==null)
                            System.out.println("client "+tmp2+" didn't loan any book yet");
                        else if (iNode.getBooks().size()==0)
                            System.out.println("client "+tmp2+" didn't loan any book yet");
                        else System.out.println("client "+tmp2+" loanded the books: "+iNode.getBooks());
                    }
                }
                
                else if ( (tmp2.charAt(0)>=65 && tmp2.charAt(0)<=90) || (tmp2.charAt(0)>=97 && tmp2.charAt(0)<=122) ) 
                { //we need to find the client who own the input book
                    if (BRBTree.getRoot()==null) //if the book tree is empty
                        System.out.println("there aren't any books that were loanded yet");
                    else { //search for the book in the bookRBTree and print the client's data
                        System.out.println("searching for book "+tmp2+" in BookRBTree");
                        BookNode bNode=BRBTree.getRoot().treeSearch(BRBTree.getRoot(), tmp2);
                        if (bNode==null)
                            System.out.println("the book "+tmp2+" was not loanded");
                            else {
                                System.out.println("the book "+tmp2+" is at client: "+bNode.getClientId()+" "+bNode.getClientName());
                            }
                        }
                }
                
                else if (tmp2.equals("!"))
                {
                    if (IRBTree.getRoot()==null)
                        System.out.println("there are no active clients who loanded books yet. Therefore, no one has the biggest number of books.");
                    else {
                        System.out.println("scanning IdRBTree");
                        tmpArray1=IRBTree.getCountData(IRBTree.getRoot()); //scan the tree by the count
                        tmpArray2=IRBTree.getIdData(IRBTree.getRoot()); //scan the tree by the id
                        
                        //find the maximum books that were loanded by the clients
                        int max=0;
                        for (int i=0; i<tmpArray1.size(); i++)
                            if (tmpArray1.get(i)>max)
                                max=tmpArray1.get(i);
                                 //print all the clients who loanded the same number of books
                                System.out.println("the clients who loanded the highest number of books are: ");
                                for (int i=0; i<tmpArray1.size(); i++)
                                {
                                    if (max!=0 && tmpArray1.get(i)==max)
                                        System.out.println(tmpArray2.get(i));
                                }
                            }
                }
            }
            
            else if (!tmp1.equals("?")) //loaning a book or returning a book
            {
                tmp2=scan.next(); //get the id
                tmp3=scan.next(); //get the book number
                tmp4=scan.next(); //get the sign (+ or -)
                
                if (tmp4.equals("+")) //loaning a book
                {
                    System.out.println("searching for client "+tmp2+" in IdRBTree to update the data");
                    //serach for the client
                    IdNode iNode=IRBTree.getRoot().treeSearch(IRBTree.getRoot(), tmp2);
                    int count=iNode.getCount();
                    tmpName=iNode.getName();
                    tmpId=iNode.getId();
                    books=new ArrayList<String>();
                    //update the data of the client
                    if (iNode.getBooks()==null)
                        books.add(tmp3);
                    else { for (int i=0; i<iNode.getBooks().size(); i++)
                        books.add(iNode.getBooks().get(i));
                    books.add(tmp3); }
                    //delete the client from the tree and insert him again, with the new data
                    IRBTree.setRoot(IRBTree.getRoot().RBdelete(IRBTree.getRoot(), iNode));
                    IdNode temp=new IdNode(tmpName, tmpId, count+1, books);
                    IRBTree.setRoot(IRBTree.insertIdClient(temp));
                    
                    System.out.println("updating BookRBTree");
                    //add the loaned book to the bookRBTree
                    BookNode bNode=new BookNode(tmp3, tmpName, tmpId);
                    BRBTree.setRoot(BRBTree.insertBook(bNode));
                }
                
                if (tmp4.equals("-")) //returning a book
                {
                    System.out.println("searching for client "+tmp2+" in IdRBTree to update the data");
                     //serach for the client
                    IdNode iNode=IRBTree.getRoot().treeSearch(IRBTree.getRoot(), tmp2);
                    int count=iNode.getCount();
                    tmpName=iNode.getName();
                    tmpId=iNode.getId();
                    books=new ArrayList<String>();
                    //update the data of the client
                    if (iNode.getBooks()==null)
                        System.out.println("client "+tmpId+" "+tmpName+" has no books");
                    else { for (int i=0; i<iNode.getBooks().size(); i++)
                        books.add(iNode.getBooks().get(i));
                    books.remove(tmp3); }
                    //delete the client from the tree and insert him again, with the new data
                    IRBTree.setRoot(IRBTree.getRoot().RBdelete(IRBTree.getRoot(), iNode));
                    IdNode temp=new IdNode(tmpName, tmpId, count-1, books);
                    IRBTree.setRoot(IRBTree.insertIdClient(temp));
                    //add the loaned book to the bookRBTree
                    System.out.println("updating BookRBTree");
                    BookNode bNode=BRBTree.getRoot().treeSearch(BRBTree.getRoot(), tmp3);
                    BRBTree.setRoot(BRBTree.getRoot().RBdelete(BRBTree.getRoot(), bNode));
                }
            }
              
            tmp1=scan.next(); //scan the next word 
        } //end while
            
    }
}
