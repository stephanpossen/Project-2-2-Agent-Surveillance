import Agent.Individual;

import java.util.Random;
public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        int numberAverage = 1;
        int totNb = 0;
        for(int y = 0; y < numberAverage ; y++) {

        int size = 40;
        Individual[] population = new Individual[size];
        for (int i = 0; i < size; i++) {
            Individual a = new Individual();
            population[i] = a;
        }
        int i = 0;
        int bigFit = 0;
        do {
            System.out.println("step " + i);
            Individual[] child = reproduct(population);
            System.out.println("Child");
            printPopulation(child);
            mutation(child);
            population = child;
            printPopulation(population);
            System.out.println();

            for (int w = 0; w < population.length; w++) {
                if (population[w].getFitness() > bigFit) {
                    bigFit = population[w].getFitness();
                    printIndividual(population[w].getStringArray());
                    System.out.println();
                }
            }
            i++;
        }
        while (bigFit < population[0].getStringArray().length);
        System.out.println("Number of steps used: " + i);
        totNb += i;
    }
    System.out.println("the average number of step is " + totNb/numberAverage);
}
    private static void printIndividual( String [] array){
        for(int i = 0; i < 11; i++)
            System.out.print(array[i]);
        System.out.print(ANSI_RESET);
    }
    private static void printPopulation( Individual[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(ANSI_RED + "Population" + i + " - ");
            printIndividual(a[i].getStringArray());
            System.out.println();
        }
    }
    private static Individual[] selectParents(Individual[] a){
        double goalNb = 0;
        for(int i = 0; i < a.length; i++) {
            if (a[i].getFitness() > goalNb)
                goalNb = a[i].getFitness();
        }
        Individual[] parents = new Individual[2];
        int parentsLength = 0;
        int x = 0;
        while (parentsLength < 2) {
            for (int i = 0; i < a.length; i++) {
                if (a[i].getFitness() == goalNb - x) {
                    if(parentsLength < 2){
                        parents[parentsLength] = a[i];
                        parentsLength++;
                    }
                }
            }
            x++;
        }
        return parents;
    }

    private static Individual[] reproduct(Individual[] a) {
        Random rnd = new Random();
        Individual[] parents = selectParents(a);
        printPopulation(parents);
        System.out.println("Reproduct");
        Individual male = parents[0];
        Individual female = parents[1];
        Individual[] child = new Individual[a.length];
        for (int m = 0; m < a.length; m = m+2) {
            int nbCross = rnd.nextInt(11);
            Individual child1 = male.clone();
            printIndividual(child1.getStringArray());
            System.out.println();
            Individual child2 = female.clone();
            printIndividual(child2.getStringArray());
            System.out.println();

            for (int n = 0; n < nbCross; n++) {
                int Cross = rnd.nextInt(11);
                String str = child1.getStringArray()[Cross];
                child1.getStringArray()[Cross] = child2.getStringArray()[Cross];
                child2.getStringArray()[Cross] = str;
            }
            child1.newFit();
            child2.newFit();
            System.out.print(ANSI_CYAN + "Child1" + " ");
            printIndividual(child1.getStringArray());
            child[m] = child1.clone();
            System.out.println();
            System.out.print(ANSI_PURPLE + "Child2" + " ");
            printIndividual(child2.getStringArray());
            child[m+1] = child2.clone();
            System.out.println();
        }
        return child;
    }

    private static void mutation(Individual[]a){
        int mutationRate = 50;
        System.out.println("MUTATE!");
        Random rnd = new Random();
        for(int i = 0; i < a.length; i++){
            int mutate = rnd.nextInt(100);
            if(mutate >= mutationRate){
                System.out.println("Mutate"+i);
                System.out.print(ANSI_BLUE + "Before: ");
                printIndividual(a[i].getStringArray());
                System.out.println();
                int letter = rnd.nextInt(11);
                a[i].getStringArray()[letter] = a[i].getLetter();
                System.out.print(ANSI_GREEN + "After: ");
                printIndividual(a[i].getStringArray());
                System.out.println();
                a[i].newFit();
            }
        }
    }
}