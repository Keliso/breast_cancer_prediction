public class Cancer {
    static int popSize = 100;
    static int epochSize = 500;
 
  
    public static void main(String[] args) throws FileNotFoundException{
     int c=0;
     population p = new population(popSize);
     
     
     p.fitness();
     
      //while((c < epochSize)||(!p.complete())){
      while(!p.complete()){
          c++;
          System.out.println(".............................."+c+"..................................................");
        p.naturalSelection(); 
        p.crossover();
       // p.printTrees();
        p.fitness();
      if(c==epochSize){
        System.out.println("Highest Fitness after"+epochSize+" epochs: "+p.highestFitness); 
        break;
      }
      
      }
      
      System.out.println("Performing tests on output eqution.....");
      System.out.println("Performance of equation: "+p.fitnessTest(p.bestTree));
     }  // TODO code application logic here

    /**
     * @param args the command line arguments
     */
   
    
}
