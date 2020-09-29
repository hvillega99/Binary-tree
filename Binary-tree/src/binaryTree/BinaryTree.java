package binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeaves() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeaves();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeaves();
            }
            return leavesLeft + leavesRight;
        }
    }

 

    public BinaryNode<T> search(T content) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (this.root.getContent().equals(content)) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().search(content);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().search(content);
                    }
                }
                return tmp;
            }
        }
    }
    
    
    public void preOrden(){
        
        System.out.println(root.getContent());
        if(root.getLeft()!=null){
            root.getLeft().preOrden();
        }
        
        if(root.getRight()!=null){
            root.getRight().preOrden();
        }
        
        
    }
    
    
    public void inOrden(){
        
        if(root.getLeft()!=null){
            root.getLeft().inOrden();
        }
        
        System.out.println(root.getContent());
        
        if(root.getRight()!=null){
            root.getRight().inOrden();
        }
    }
   
    
    public void postOrden(){
        
        if(root.getLeft()!=null){
            root.getLeft().postOrden();
        }
         
        if(root.getRight()!=null){
            root.getRight().postOrden();
        }
        
        System.out.println(root.getContent());
    }
    
    
    
    public static int getMin(BinaryTree<Integer> tree){
        
        
        PriorityQueue<Integer> cola = new PriorityQueue<>((e1,e2)->{return -e2+e1;});
        cola.add(tree.getRoot().getContent());
        
         if(tree.getLeft()!=null){
            cola.add(BinaryTree.getMin(tree.getLeft()));
        }
         
        if(tree.getRight()!=null){
           cola.add(BinaryTree.getMin(tree.getRight()));
        }
        
        
        return cola.remove();
    }
    
  
    public int countLevels(){
        if(this==null){
            return 0;
        }else if(this.isLeaf()){
            return 1;
        }else{
            int sumLeft=0;
            int sumRight=0;
            
            if(this.getRoot().getLeft()!=null){
           
                sumLeft++;
                sumLeft+=this.getLeft().countLevels();
            }
            
            if(this.getRoot().getRight()!=null){
                
                sumRight++;
                sumRight+=this.getRight().countLevels();

            }
            
            return Math.max(sumRight, sumLeft);
        }
    }
    
    
    public boolean isLefty(){
        if(this==null || this.isLeaf()){
            return true;
        }else{
            boolean comp=true;
            
            if(this.countSubLeftTree()>=(this.countSubLeftTree()+this.countSubRightTree())/2){
                if(root.getLeft()!=null){
                    comp = comp && root.getLeft().isLefty();
                }
                
                if(root.getRight()!=null){
                    comp = comp && root.getRight().isLefty();
                }
              
            }else{
                comp= false;
            }
            
            return comp;
            
        }
       
    }
    
   
    //métodos auxiliares------------------
    public int countSubLeftTree(){
       if(root.getLeft()==null){
            return 0;
        }else{
            int count=0;
            
            Stack<BinaryNode<T>> pila = new Stack<>();
            
            pila.push(root.getLeft().getRoot());
            
            while(!pila.isEmpty()){
                BinaryNode<T> tmp = pila.pop();
               
                count++;
                if(tmp.getLeft()!=null){
                    pila.push(tmp.getLeft().getRoot());
                } 
                
                if(tmp.getRight()!=null){
                    pila.push(tmp.getRight().getRoot());
                }
 
            }
   
            return count;
        }
    }
    
    public int countSubRightTree(){
        
        if(root.getRight()==null){
            return 0;
        }else{
            int count=0;
            
            Stack<BinaryNode<T>> pila = new Stack<>();
            
            pila.push(root.getRight().getRoot());
            
            while(!pila.isEmpty()){
                BinaryNode<T> tmp = pila.pop();
               
                count++;
                if(tmp.getLeft()!=null){
                    pila.push(tmp.getLeft().getRoot());
                } 
                
                if(tmp.getRight()!=null){
                    pila.push(tmp.getRight().getRoot());
                }
 
            }
   
            return count;
        }
    }
    
    //---------------------------------------------
    
   
    public boolean isIdentical(BinaryTree<T> arbol){
        
        if(this.countLevels()!=arbol.countLevels()){
            return false;
        }else{
             boolean comp=true;
             comp= arbol.getRoot().getContent().equals(this.root.getContent());
             
             if(this.getLeft()!=null && arbol.getLeft()!=null){
                 comp= comp && arbol.getLeft().isIdentical(this.getLeft());
             }
             
            
             if(this.getRight()!=null && arbol.getRight()!=null){
                  comp= comp && arbol.getRight().isIdentical(this.getRight());
             }
                
             return comp;
   
        }

   }
    
    public List<T> getNodesByLevel(int level){
        List<T> nodes = new LinkedList<>();
        
        if(level == 1){
            nodes.add(root.getContent());
        }else{
            if(root.getLeft()!=null){
                nodes.addAll(root.getLeft().getNodesByLevel(level-1));
            }
            
            if(root.getRight()!=null){
                nodes.addAll(root.getRight().getNodesByLevel(level-1));
            }
        }
        
        return nodes;
    }
    
}
