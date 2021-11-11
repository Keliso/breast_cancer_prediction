class population{
     static node [] populate;
      static node [] matingPool ;
      static int populationSize;
     public static double highestFitness=0;
     public static String bestTree= "processing";
     
      TreeFactory t = new TreeFactory();
      
     public population(int size){
         populationSize = size;
         //bestTree.score= 0;
         //bestTree.equation="not yet";
          
         
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
     if(op1==0)
     operandStack.push(0);
     else if (op2<op1)
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
 public static int readFile(String s) throws FileNotFoundException{
    int count =0; 
    int clas = 0;
    File file =  new File("cancer.csv");
    try{      
        //FileReader read =  new FileReader(file);
        Scanner read = new Scanner(file);
        while(read.hasNext()){
            String line = read.nextLine();
            line = line.trim();
            String [] token = line.split(",");
            String v = insertValues(token,s); 
            //System.out.print("Calculated value is"+evaluateExpression(v));
            //System.out.print("");
	    clas  = classify(evaluateExpression(v));
            //System.out.print("Class is"+clas);
            //System.out.print("");
            //System.out.print("Expected class is"+Integer.parseInt(token[9]));
            //System.out.print("");
            //System.out.println("calclass"+clas);
            //System.out.println("class"+token[5]);
            //System.out.println("count "+count);
            if(clas==Integer.parseInt(token[9]))
            count++;
        }
	
        }
    
    catch(Exception e){ 
        //e.printStackTrace();
    }    
   // System.out.println("Masibone iCounter -> " + count);
 return count;  
 } 
 
 public static int readFiletest(String s) throws FileNotFoundException{
    int count =0; 
    int clas = 0;
    File file =  new File("test.csv");
    try{      
       
        Scanner read = new Scanner(file);
        while(read.hasNext()){
            String line = read.nextLine();
            line = line.trim();
            String [] token = line.split(",");
//            for(int i=0;i<10;i++){
//               System.out.print(token[i]+"\t");
//               
//            }
//            System.out.println("");
            String v = insertValues(token,s); 
            
	    clas  = classify(evaluateExpression(v));
            
            if(clas==Integer.parseInt(token[9]))
            count++;
        }
	
        }
    
    catch(Exception e){ 
       
    }    
   
 return count;  
 } 
 
 public  static int classify(float threshold){
     int classification =0;
     if(threshold > 0.5)
     classification = 4;
     else classification = 2;
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
     if(string.contains("e"))
       string = string.replaceAll("e", vals[4]);
    if(string.contains("f"))
       string = string.replaceAll("f", vals[5]);
    if(string.contains("g"))
       string = string.replaceAll("g", vals[6]);
    if(string.contains("h"))
       string = string.replaceAll("h", vals[7]);
    if(string.contains("i"))
       string = string.replaceAll("i", vals[8]);
    
    
    expression = string;
    return expression;
}
public void fitness() throws FileNotFoundException{
         //System.out.println("ave"+average());
    for(node t:populate){
      //  System.out.println("pri"+readFile(t.equation));
               
          t.score =((float)readFile(t.equation) * 100) / (float)numberRecords();
     if(t.score> highestFitness){
         highestFitness =t.score;
         bestTree =t.equation;
     }
      t.percent = t.score;
      System.out.println(t.percent + "%");
    }
     System.out.println( " highestFitness is "+highestFitness+ "%");
 }

public float fitnessTest(String bestEquation)throws FileNotFoundException{
    float score = ((float)readFiletest(bestEquation))*100/(float)numberRecordstest();
    return score;
}
public int numberRecords(){
    int counter =0;
  File file;    
       file = new File("cancer.csv");
 try{      
 Scanner read = new Scanner(file);
 while(read.hasNext()){
    
  read.nextLine();
  counter++;
 }
 }
 catch(FileNotFoundException e){
    //e.printStackTrace();
 }
// System.out.println("number"+counter);
 return counter;
}

public int numberRecordstest(){
    int counter =0;
  File file;    
       file = new File("test.csv");
 try{      
 Scanner read = new Scanner(file);
 while(read.hasNext()){
    
  read.nextLine();
  counter++;
 }
 }
 catch(FileNotFoundException e){
    //e.printStackTrace();
 }
// System.out.println("number"+counter);
 return counter;
}


public Boolean complete () {
    Boolean status = false;
    //int count=0;
    for(node f: populate){
        if(f.percent>=99){            
        status = true;
        System.out.println(">>>>>Equasion producing at least 90%" + f.equation + ">>>>>");
        System.out.println(">>>>>Percentage attained" + f.percent + ">>>>>");
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

