package bbst;
import java.util.Scanner;
public class RedBlackTree {
      
	private Node root;
	RedBlackTree(Scanner kbd){
		int n = kbd.nextInt();
		Node [] nodes = new Node[n];
		for(int i=0;i<n;i++)
		  nodes[i] = new Node(kbd.nextInt(),kbd.nextInt());
		kbd.close();
		root = makeTree(nodes,0,nodes.length-1,0);
	}
	private Node makeTree(Node [] nodes,int start,int end,int level){
		if(start > end)
			return null;
		int mid = (start + end)/2;
		Node newNode = new Node(nodes[mid].id,nodes[mid].count);
		if(level % 2 == 1)
			newNode.color = nodeColor.RED;
		newNode.left = makeTree(nodes,start,mid-1,level+1);
		newNode.right = makeTree(nodes,mid+1,end,level+1);
		return newNode;
		
	}
	private enum nodeColor{RED,BLACK};
	public void increase(int theId, int count){
	    Node x = root;
		while(x != null){
			if(theId == x.id){
				x.count += count;
				break;
			}
			else if(theId < x.id)
				    x = x.left;
			else x = x.right;
		}
		
		if(x == null)
		  {
			 Node z = new Node(theId,count);
			 insert(z);
			 x = z;
		  }
		System.out.println(x.count);			
	}
	private void leftRotate(Node x){
		//assuming x's right child != null
		Node y = x.right;                   //set y to x's right child
		x.right = y.left;                   //set y's left sub-tree to x'right child
		if(y.left != null)                 
			y.left.parent = x;              // set y's left child's parent to x
		x.parent = y.parent;                // set y's parent to x's parent
		if(x.parent == null)                // if x is the root make y the root
			root = y;
		else if(x == x.parent.left)        //if x is left child of it's parent,
			   x.parent.left = y;          // then make y, x's parent's left child
		else x.parent.right = y;           // else make y, x's parent's right child
		y.left = x;                        // set y's left child to x
		x.parent = y;                      // set x's parent to y		
	}
    public void insert(Node z){
    	Node y = null;
    	Node x = root;
        /*  Compare z's id with root's id.
         * 	If z's id < x's id, set x to x's left child 
         *  Else set x to x's right child */
    	while(x != null){               
    		y = x;
    		if(z.id < x.id)               
    			x = x.left;
    		else x = x.right;
    	}
    		z.parent = y;                         //set z's parent to y
    		if(y == null)                         //if y is null,z becomes root
    			  root = z;
    		else if(z.id < y.id)                 //if z's id < y's id,z becomes y's left child
    			    y.left = z; 
    		else y.right = z;                    //if z's id > y's id,z becomes y's right child
    		z.color = nodeColor.RED;              
    		insertFix(z);                    
    		    
    		 
    	
    }
	private void rightRotate(Node x){
		//Assuming x's left child is not null
		
		Node y = x.left;                  //set y to x's left child
		x.left = y.right;                 //set y's right sub-tree to x'left child
		if(y.right != null)               // set y's right child's parent to x  
			y.right.parent = x;
		y.parent = x.parent;              // set y's parent to x's parent
		if(x.parent == null)                // if x is the root make y the root
			root = y;
		else if(x == x.parent.left)        //if x is left child of it's parent,
			   x.parent.left = y;          // then make y, x's parent's left child
		else x.parent.right = y;           // else make y, x's parent's right child
		y.right = x;                        // set y's right child to x
		x.parent = y;                      // set x's parent to y
		
	}
	private class Node{
		private Integer id;
		private Integer count;
		private Node left;
		private Node right;
		private Node parent;
		private nodeColor color;
		public nodeColor getColor(){
			return this.color;
		}
		public Node(Integer id, Integer count){
			this.id = id;
			this.count = count;
			this.color = nodeColor.BLACK;
		}	
	}
   	private void insertFix(Node z){
		Node uncle = null;
		while(z.parent != null && z.parent.color == nodeColor.RED){
			//if z's parent is a left child of it's parent
		    if(z.parent.parent != null && z.parent == z.parent.parent.left){     
			     uncle = z.parent.parent.right;                                 
			     if(uncle.color == nodeColor.RED){     // z's uncle y is red   
			    	 z.parent.color = nodeColor.BLACK;                 
			    	 uncle.color = nodeColor.BLACK;
			    	 z.parent.parent.color = nodeColor.RED;
			    	 z = z.parent.parent;
			     }
			     else {
			    	 if(z == z.parent.right){          // z's uncle is black and z is a right child
			    		 z = z.parent;
			    	     leftRotate(z);
			    	 }
			    	 // z's uncle is black and z is a left child
			    	 z.parent.color = nodeColor.BLACK;      
			    	 z.parent.parent.color = nodeColor.RED;
			    	 rightRotate(z.parent.parent);
			     }			    	   
			 }  
		    // z's parent is a right child of it's parent
		    else{
		    	if(z.parent.parent != null){     
				     uncle = z.parent.parent.left;                                 
				     if(uncle.color == nodeColor.RED){     // z's uncle y is red   
				    	 z.parent.color = nodeColor.BLACK;                 
				    	 uncle.color = nodeColor.BLACK;
				    	 z.parent.parent.color = nodeColor.RED;
				    	 z = z.parent.parent;
				     }
				     else {
				    	 if(z == z.parent.left){          // z's uncle is black and z is a left child
				    		 z = z.parent;
				    	     rightRotate(z);
				    	 }
				    	 // z's uncle is black and z is a right child
				    	 z.parent.color = nodeColor.BLACK;      
				    	 z.parent.parent.color = nodeColor.RED;
				    	 leftRotate(z.parent.parent);
				     }			    	   
				 }		    	
		    }			   
		}
		root.color = nodeColor.BLACK;
	}
	public void count(int theId){
		boolean present = false;
		Node x = root;
		while(x != null){
			if(theId == x.id){
				present = true;
				break;
			}else if(theId > x.id)
				   x = x.right;
			else x = x.left;
		}
		if(!present)
		  System.out.println(0);
	}
	private void transplant(Node u, Node v){
	//replaces node u with node v	
		if(u.parent == null)
			root = v;
		else if(u.parent.left == u)
			    u.parent.left = v;
		else u.parent.right = v;
		if(v != null && u != null)
		   v.parent = u.parent;
	}
    private void delete(Node z){
    	Node y = z;
    	Node x;
    	nodeColor originalColor = y.getColor();
    	if(z.left == null){
    		x = z.right;
    		transplant(z,z.right);
    	}else if(z.right == null){
    		z = z.left;
    		transplant(z,z.left);
    	}else{
    		y = min(z.right);
    		originalColor = y.color;
    		x = y.right;
    		if(y.parent == z){
    			x.parent = y;
    		}
    		else{    			
    			transplant(y,y.right);    			
    			y.right = z.right;
    			y.right.parent = y;
    		}
    		transplant(z,y);
    		y.left = z.left;
    		y.left.parent = y;
    		y.color = z.color;
    		if(originalColor == nodeColor.BLACK)
    			deleteFix(x);
    	}    	
    }
    private Node min(Node z){
   // 	returns node with min value in the subtree rooted at z
    	while(z.left != null)
    		z = z.left;
    	return z;
    }
    private void deleteFix(Node x){
    	while(x != root && x.color == nodeColor.BLACK){
    		Node w = null;
    		if(x == x.parent.left){
    			w = x.parent.right;
    			if(w != null && w.color == nodeColor.RED){
    				w.color = nodeColor.BLACK;
    				x.parent.color = nodeColor.RED;
    				leftRotate(x.parent);
    				w = x.parent.right;
    			}
    			if(w != null && w.left != null && w.right != null
    					&& w.left.color == nodeColor.BLACK && w.right.color == nodeColor.BLACK){
    				w.color = nodeColor.RED;
    				x = x.parent;
    			}
    			else {
    				if(w.right != null && w.right.color == nodeColor.BLACK){
    					w.left.color = nodeColor.BLACK;
    					w.color = nodeColor.RED;
    					rightRotate(w);
    					w = x.parent.right;    				
    				}
    				w.color = x.parent.color;
    				x.parent.color = nodeColor.BLACK;
    				w.right.color = nodeColor.BLACK;
    				leftRotate(x.parent);
    				x = root;
    			}
    				
    		}else{
    			w = x.parent.left;
    			if(w != null && w.color == nodeColor.RED){
    				w.color = nodeColor.BLACK;
    				x.parent.color = nodeColor.RED;
    				rightRotate(x.parent);
    				w = x.parent.left;
    			}
    			if(w != null && w.left != null && w.right != null
    					&& w.left.color == nodeColor.BLACK && w.right.color == nodeColor.BLACK){
    				w.color = nodeColor.RED;
    				x = x.parent;
    			}
    			else {
    				if(w.left != null && w.left.color == nodeColor.BLACK){
    					w.right.color = nodeColor.BLACK;
    					w.color = nodeColor.RED;
    					leftRotate(w);
    					w = x.parent.left;    				
    				}
    				w.color = x.parent.color;
    				x.parent.color = nodeColor.BLACK;
    				w.left.color = nodeColor.BLACK;
    				rightRotate(x.parent);
    				x = root;
    			}
    		}
    	}
    	x.color = nodeColor.BLACK;
    }
    public void reduce(int theId, int count){
    	Node x = root;
     	while(x != null){
    		if(theId == x.id){
    			x.count -= count;
    			if(x.count <= 0){
    				delete(x);
    				x = null;
    			}
    			break;
    		}else if(theId > x.id)
    			    x = x.right;
    		else x = x.left;
    	}
    	if(x == null)
    		System.out.println(0);
    	else System.out.println(x.count);
    }
    public void next(int theId){
       	Node currentNode = root;
    	Node next = null;
    	
    	while(currentNode != null){
    		if(currentNode.id > theId){
    			if(next == null){
    				next = currentNode;    					
    			}
    			else next = next.id < currentNode.id  ? next : currentNode;
    			
    			currentNode = currentNode.left;	
    		}
    		else if(currentNode.id < theId){
    			currentNode = currentNode.right;
    		}
    		else if(currentNode.id == theId){
    			if(currentNode.right != null){
    				next = min(currentNode.right);
    			}
    			break;
    		}
    	}
    	if(next == null)
    		System.out.println(0 + " " + 0);
    	else
    	System.out.println(next.id + " " + next.count);
    }
    private Node max(Node z){
//     	returns node with max value in the subtree rooted at z	
    	while(z.right != null)
    		z = z.right;
    	return z;
    }
    public void previous(int theId){
    		   
    	Node prev = root;
        Node currentNode = root;    	
    	while(currentNode != null){
    		if(currentNode.id > theId){
    			  prev = currentNode;
    			  currentNode = currentNode.left;				  
    		}	
    		else if(currentNode.id < theId){
    			prev = currentNode;
    			currentNode = currentNode.right;
    		}
    		else if(currentNode.id == theId){
    			if(currentNode.right != null){
    				prev = max(currentNode.left);
    			}
    			break;
    		}
    	}
    	if(currentNode == null){
    		while(prev != null && currentNode ==prev.left){
    			currentNode = prev;
    			prev = prev.parent;
    		}
    	}
    	if(prev == null)
    		System.out.println(0 + " " + 0);
    	else
    	System.out.println(prev.id + " " + prev.count);   	
    }
    private int inRange(Node x,int low, int high){
    if(x != null){	
    	if(x.id >= low && x.id <= high){
    		return x.count + inRange(x.left,low,high) + inRange(x.right, low, high);
    	}
    	if(x.id < low){
    		return inRange(x.right,low, high);
    	}
    	if(x.id > high){
    		return inRange(x.left,low,high);
    	}
    }
    return 0;
   }
    public void inRange(int ID1, int ID2){
    	System.out.println(inRange(root,ID1,ID2));
    }
   }
