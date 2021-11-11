package cancer;

/**
 *
 * @author Thoko
 * 
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.pow;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileReader;



class node{
public char data;
public node right;
public node left;
public float score;
public float percent;
public String equation;

public node(char val){
    this.data = val;
}
public void setNodeL(char v){
    this.left.data = v;
}
public void setNodeR(char v){
    this.right.data = v;
}
}
class TreeFactory{
    node trees[] = new node[7];
    char func[] = new char[]{'*','/','+','-'};
    char term[] = new char[]{'a','b','c','d','e','f','g','h','i'};
    
    
    public node[] initialiseTrees(){
//          tree1        
//              o
//             / \
//            o   o  
//           / \ / \
//          o  oo   o

    node tree1 = new node(func[function()]);
    tree1.left = new node(func[function()]);
    tree1.right =  new node(func[function()]);
    tree1.left.left = new node(term[terminal()]);
    tree1.left.right = new node(term[terminal()]);
    tree1.right.left = new node(term[terminal()]);
    tree1.right.right = new node(term[terminal()]);
    trees[0]= tree1;
    
//          tree2   
//              o
//             / \
//            o   o  
//           / \ / 
//          o  oo   

    char l,r;
    node tree2 = new node(func[function()]);
    l = func[function()];
    r = func[function()];
    tree2.left = new node(l);
    tree2.right =  new node(r);
    tree2.left.left = new node(term[terminal()]);
    tree2.left.right = new node(term[terminal()]);
    tree2.right.left = new node(term[terminal()]);
    if((r == '*')||(r == '/')){
       tree2.right.right = new node('1'); 
    }
    else{
        tree2.right.right = new node('0');
    }
    
    
    trees[1]=tree2;
    
//          tree3   
//              o
//             / \
//            o   o  
//           / \   \
//          o  o    o

    char a,b;
    node tree3 = new node(func[function()]);
     a = func[function()];
     b = func[function()];
    tree3.left = new node(a);
    tree3.right =  new node(b);
    tree3.left.left = new node(term[terminal()]);
    tree3.left.right = new node(term[terminal()]);
    
    if((b == '*')||(b == '/')){
       tree3.right.right = new node('1'); 
       tree3.right.left = new node(term[terminal()]);
    }
    else{
        tree3.right.left = new node('0');
        tree3.right.right = new node(term[terminal()]);
    }
    
    trees[2]=tree3;
    
//          tree4        
//              o
//             / \
//            o   o  
//           /   / \
//          o   o   o

    char x,y;
    node tree4 = new node(func[function()]);
    x = func[function()];
    y = func[function()];
    tree4.left = new node(x);
    tree4.right =  new node(y);
    tree4.left.left = new node(term[terminal()]);
    tree4.right.left = new node(term[terminal()]);
    tree4.right.right = new node(term[terminal()]);
     if((x == '*')||(x == '/')){
       tree4.left.right = new node('1'); 
    }
    else{
        tree4.left.right = new node('0');
    }
    
    trees[3]=tree4;
    
//          tree5        
//              o
//             / \
//            o   o  
//             \ / \
//             oo   o

    char c,d;
    node tree5 = new node(func[function()]);
    c = func[function()];
    d = func[function()];
    tree5.left = new node(c);
    tree5.right =  new node(d);
    
    tree5.right.left = new node(term[terminal()]);
    tree5.right.right = new node(term[terminal()]);
    if((c == '*')||(c == '/')){
       tree5.left.right = new node('1');
       tree5.left.left = new node(term[terminal()]);
    }
    else{
        tree5.left.left = new node('0');
        tree5.left.right = new node(term[terminal()]);
    }
    
   trees[4]=tree5;
    
//          tree6        
//              o
//             / \
//            o   o  
//           / \ 
//          o   o  

    node tree6 = new node(func[function()]);
    tree6.left = new node(func[function()]);
    tree6.left.left = new node(term[terminal()]);
    tree6.left.right = new node(term[terminal()]);
    tree6.right = new node(term[terminal()]);
    trees[5]=tree6;
//          tree7        
//              o
//             / \
//            o   o  
//               / \
//              o   o

    node tree7 = new node(func[function()]);
    tree7.right =  new node(func[function()]);
    tree7.left = new node(term[terminal()]);
    tree7.right.left = new node(term[terminal()]);
    tree7.right.right = new node(term[terminal()]);
    trees[6]=tree7;
    //System.out.println(trees.length);
    

    return trees;
    }
    
    public int function(){
        Random r = new Random();
        return r.nextInt(func.length);
    }
    
     public int terminal(){
        Random r = new Random();
        return r.nextInt(term.length);
    }

 
}
