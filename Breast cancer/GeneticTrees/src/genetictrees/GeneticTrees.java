/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetictrees;

import java.io.File;
import static java.lang.Math.pow;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Thoko
 */

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
    char term[] = new char[]{'a','b','c','d'};
    
    
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
 class population{
     static node [] populate;
      static node [] matingPool ;
      static int populationSize;
      TreeFactory t = new TreeFactory();
      
     public population(int size){
         populationSize = size;
          
         
         populate = new node [size];
         matingPool = new node[size/2];
         for(int i=0; i<size; i++){
            t.initialiseTrees();
            node n = pickTree(t.trees);            
            n.equation = arrangeString(readTree(n));
            populate[i] = n;
            System.out.println(populate[i].equation);
         }
     }
     public static node pickTree(node[] list){
       Random tree = new Random();
       int x=tree.nextInt(6);
       System.out.println("tree"+(x+1));
       return list[x];
   }
     
      public static String readTree(node t){
       StringBuilder s = new StringBuilder();
       if(t != null){
           
           s.append(readTree(t.left));
           s.append(t.data);
           s.append(readTree(t.right));
           
       }
        
       return s.toString() ;
   }
   public static String arrangeString(String s){
       StringBuilder sb = new StringBuilder();
       sb.append('(');
       sb = sb.append(s);
       if(sb.length() == 8){
         sb =  sb.insert(4, ')');
         sb = sb.insert(6, '(');
         sb = sb.insert(10, ')');
       }
       else{
          sb =  sb.insert(4, ')');  
       }
       
       return sb.toString();
   }
    
   
   
   /** Evaluate an expression based on Shunting Yard Algorithm
    
    */
  public static float evaluateExpression(String expression) {
  // Create operandStack to store operands
  Stack<Integer> operandStack = new Stack<>();
 
  // Create operatorStack to store operators
  Stack<Character> operatorStack = new Stack<>();
 
  // Insert blanks around (, ), +, -, /, and *
  expression = insertBlanks(expression);
 
  // Extract operands and operators
  String[] tokens = expression.split(" ");
 
  // Phase 1: Scan tokens
  for (String token: tokens) {
  if (token.length() == 0) // Blank space
  continue; // Back to the while loop to extract the next token
  else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
  // Process all +, -, *, / in the top of the operator stack
  while (!operatorStack.isEmpty() &&
  (operatorStack.peek() == '+' ||
  operatorStack.peek() == '-' ||
  operatorStack.peek() == '*' ||
 operatorStack.peek() == '/')) {
 processAnOperator(operandStack, operatorStack);
 }

 // Push the + or - operator into the operator stack
 operatorStack.push(token.charAt(0));
 }
 else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
 // Process all *, / in the top of the operator stack
 while (!operatorStack.isEmpty() &&
 (operatorStack.peek() == '*' ||
 operatorStack.peek() == '/')) {
 processAnOperator(operandStack, operatorStack);
 }
 // Push the * or / operator into the operator stack
 operatorStack.push(token.charAt(0));
 }
 else if (token.trim().charAt(0) == '(') {
 operatorStack.push('('); // Push '(' to stack
 }
 else if (token.trim().charAt(0) == ')') {
 // Process all the operators in the stack until seeing '('
 while (operatorStack.peek() != '(') {
  processAnOperator(operandStack, operatorStack);
 }

 operatorStack.pop(); // Pop the '(' symbol from the stack
 }
 else { // An operand scanned
 // Push an operand to the stack
 operandStack.push(new Integer(token));
 }
}
 
  while (!operatorStack.isEmpty()) {
 processAnOperator(operandStack, operatorStack);
 }

 
 return operandStack.pop();
 }

 
 public static void processAnOperator(
 Stack<Integer> operandStack, Stack<Character> operatorStack) {
 char op = operatorStack.pop();
 int op1 = operandStack.pop();
 int op2 = operandStack.pop();
 if (op == '+')
 operandStack.push(op2 + op1);
 else if (op == '-')
 operandStack.push(op2 - op1);
 else if (op == '*')
 operandStack.push(op2 * op1);
 else if (op == '/')
 {
     if(op2<op1)
     operandStack.push(0);
     else 
         operandStack.push(op2 / op1);
 }
 
}

public static String insertBlanks(String s) {
String result = "";

 for (int i = 0; i < s.length(); i++) {
 if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
 s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/')
result += " " + s.charAt(i) + " ";
 else
 result += s.charAt(i);
}

return result;
 }
 public static int readFile(String s){
    int count =0; 
    int clas = 0;
    File file = new File("C:\\data.csv");
    try{      
        Scanner read = new Scanner(file);
        while(read.hasNext()){
            String line = read.nextLine();
            line = line.trim();
            String [] token = line.split(",");
            String v = insertValues(token,s); 
            clas  = classify(evaluateExpression(v),Integer.parseInt(token[2]));
            //System.out.println("calclass"+clas);
            //System.out.println("class"+token[5]);
            //System.out.println("count "+count);
            if(clas==Integer.parseInt(token[5]))
            count++;
        }
    }
    catch(Exception e){ }    
   // System.out.println("Masibone iCounter -> " + count);
 return count;  
 } 
 
 public  static int classify(float price, int type){
     int classification =0;
     if(type == 1){
         if(price<279){
         classification = 1;}
         else if(price>590){
             classification = 3;}
         else {classification = 2;}
         
     }
         
     else if(type ==2){
        if(price<518){
         classification = 1;}
         else if(price>880){
             classification = 3;}
         else {classification = 2; }
       
     }
     else if(type ==3){
        if(price<125){
         classification = 1;}
         else if(price>440){
             classification = 3;}
         else {classification = 2; }
        
     }
     else if(type ==4){
        if(price<142){
         classification = 1;}
         else if(price>473){
             classification = 3;}
         else{ classification = 2;}
        
     }
     else if(type ==5){
        if(price<370){
         classification = 1;}
         else if(price>760){
             classification = 3;}
         else {classification = 2;}
        
     }
     else if(type ==6){
        if(price<353){
         classification = 1;}
         else if(price>590){
             classification = 3;}
         else{ classification = 2; } 
        
     }
    return classification; 
 }
public static String insertValues(String[] vals,String string) {
    String expression;
    if(string.contains("a"))
       string = string.replaceAll("a", vals[0]);
    if(string.contains("b"))
       string = string.replaceAll("b", vals[1]);
    if(string.contains("c"))
       string = string.replaceAll("c", vals[2]);
    if(string.contains("d"))
       string = string.replaceAll("d", vals[3]);
    
    
    
    expression = string;
    return expression;
}
public void fitness(){
         //System.out.println("ave"+average());
    for(node t:populate){
      //  System.out.println("pri"+readFile(t.equation));
               
          t.score =((float)readFile(t.equation) * 100) / (float)numberRecords();
     
      
      t.percent = t.score;
      System.out.println(t.percent + "%");
    }
    
 }

public int numberRecords(){
    int counter =0;
  File file;    
       file = new File("C:\\data"
               + ""
               + ".csv");
 try{      
 Scanner read = new Scanner(file);
 while(read.hasNext()){
    
  read.nextLine();
  counter++;
 }
 }
 catch(Exception e){
     
 }
// System.out.println("number"+counter);
 return counter;
}


public Boolean complete () {
    Boolean status = false;
    //int count=0;
    for(node f: populate){
        if(f.percent>=50){            
        status = true;
        System.out.println(">>>>>" + f.equation + ">>>>>");
        System.out.println(">>>>>" + f.percent + ">>>>>");
        break; }   
    
    }
    
    return status;
    
}
public void naturalSelection(){
    node temp;
    int i,j;
    for( i=0; i< populate.length;i++){
        for(j=i;j< populate.length;j++){
            if(populate[i].percent<populate[j].percent)
            {
                temp= populate[i];
                populate[i]=populate[j];
                populate[j]=temp;
            }
        }
        
    }
    for(int x =0;x< matingPool.length;x++){
        matingPool[x]= populate[x];
    }
}
public int rand(int num){
    Random ran = new Random();
    return ran.nextInt(num);
}
public void printTrees(){
    for(int i=0; i< populate.length;i++){
        System.out.println(populate[i].equation);
    }
}
public void crossover(){
    int x,y;
    int max = matingPool.length + ((int)matingPool.length/2);//probm if not even
   for (int i =0;i<max;i++){
       x = rand(matingPool.length);
       y = rand(matingPool.length);
      //System.out.println(x+"and"+ y);
      String replacement = matingPool[x].equation.substring(0, matingPool[x].equation.length()/2) +matingPool[y].equation.substring( matingPool[y].equation.length()/2,matingPool[y].equation.length()) ;
      replacement =  mutation(replacement,3);
      
      populate[i+(matingPool.length/2)].equation = replacement;
      // z = rand(matingPool[x].equation.length()-1);
       //String c = matingPool[x].equation.substring(matingPool[x].equation.charAt(rand(matingPool[x].equation.length()))); 
      // while ((c=="*")||(c=="/")||(c=="-")||(c=="+")||(c=="(")||(c==")"))
         // c = matingPool[x].equation.substring(matingPool[x].equation.charAt(rand(matingPool[x].equation.length())));  
      
       //String replacement = matingPool[x].equation.replace(c,matingPool[y].equation);
      
      //populate[i].equation = replacement;
      
      
       
       
   } 
   for(node v : populate){
       System.out.println(v.equation);
   }
}
public String mutation(String s, int rate){
 char [] function = new char[]{'*','/','+','-'};
 char [] terminal = new char[]{'a','b','c','d'};
 for(int i=0; i< rate;i++){
     char p = s.charAt(rand(s.length()));
     if (func(p)){
      s = s.replace(p, function[rand(function.length)]);
     }
     else if(term(p)){
     s = s.replace(p, terminal[rand(terminal.length)]);    
     }
    
 }
    
    return s;
}
public Boolean func(char c){
   Boolean state= false;
   if((c == '*')||(c == '/')||(c == '-')||(c == '+'))
       state = true;
   
   return state;
}

public Boolean term(char c){
   Boolean state= false;
   if((c == 'a')||(c == 'b')||(c == 'c')||(c == 'd'))
       state = true;
   
   return state;
}
 }

public class GeneticTrees {
   static int popSize = 1000;
 
  
    public static void main(String[] args) {
     int c=0;
     population p = new population(popSize);
     p.fitness();
      while(!p.complete()){
          c++;
          System.out.println(".............................."+c+"..................................................");
        p.naturalSelection(); 
        p.crossover();
       // p.printTrees();
        p.fitness();
      }
      
     }  // TODO code application logic here
 }
    

