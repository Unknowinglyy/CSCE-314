public class Fibonacci {
        public static void main(String[] args) {
            System.out.println("test");
    }
}

class SubsetOutputFib {
    //creating a method for the main logic of the problem
    public static void main(String[] args){
        //making sure the usage is correct
        if(args.length != 2){
            System.out.println("Usage: java SubsetOutputFib {beginning number} {ending number}");
            return;
        }
        //initializing the two range variables
        int be;
        int en;
        //trying to parse the command line arguments to ints
        try{
            be = Integer.parseInt(args[0]);
            en = Integer.parseInt(args[1]);
        }
        //if parsing came up with an error, then exit
        catch(NumberFormatException except){
            System.out.println("Could not parse string inputs into ints");
            return;
        }
        //error checking for zero
        if(be == 0 || en == 0){
            System.out.println("Zero can not be used for either number...");
            return;
        }
        //error checking to see if either of the inputs are negative
        if(be < 0 || en < 0){
            System.out.println("Either one or both of your numbers are negative. Fixing this by converting them to their absolute values.");
            be = Math.abs(be);
            en = Math.abs(en);
            System.out.println("Beginning number is now: " + be);
            System.out.println("Ending number is now: " + en);
            System.out.println();
        }
        //error checking to see if the beginning number is greater than the end
        if(be > en){
            System.out.println("Your beginning number is larger than your ending number. Fixing this by switching them.");
            int temp = be;
            be = en;
            en = temp;
            System.out.println("Beginning number is now: " + be);
            System.out.println("Ending number is now: " + en);
            System.out.println();
        }

        int lo = 1;
        int hi = 1;
        String mark;
        for(int i = 1; i <= en; i++){
            //checking if current fibonacci number is even, if so, then add the mark
            if(hi % 2 == 0){
                mark = " *";
            }
            else{
                mark = "";
            }
            //if the index is equal to or greater than the beginning number, start printing
            if(i >= be){
                System.out.println(i + ": " + hi + mark);
            }
            //don't update if index is at 1 since fibonacci number stays the same
            if(i != 1){
                hi = lo + hi;
                lo = hi - lo;
            }
        }
    }
}

class ImprovedFibonacci {
    static final int MAX_INDEX = 9;
    public static FibonacciObject[] fibonacciArray = new FibonacciObject[MAX_INDEX];
    /**
    * Print out the first few Fibonacci numbers,
    * marking evens with a '*'
    */
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        boolean even;
        FibonacciObject object1 = new FibonacciObject(1, false);
        fibonacciArray[0] = object1;
        for (int i = 2; i <= MAX_INDEX; i++) {
        if (hi % 2 == 0){
            even = true;
        }
        else{
            even = false;
        }
        FibonacciObject object2 = new FibonacciObject(hi, even);
        fibonacciArray[i-1] = object2;
        hi = lo + hi;
        lo = hi - lo;
        }
        //proving the array was created correctly
        System.out.println("Now printing array of Fibonacci numbers");
        System.out.println();
        for(int i = 0; i < fibonacciArray.length; i++){
            System.out.println("fibonacci number " + (i+1) + ": " + fibonacciArray[i].value);
            String answer;
            if(fibonacciArray[i].isEven){
                answer = "Yes";
            }
            else{
                answer = "No";
            }
            System.out.println("is it even? " + answer);
            System.out.println();
        }
    }
}

class FibonacciObject{
    public int value;
    public boolean isEven;

    FibonacciObject(int value, boolean even){
        this.value = value;
        this.isEven = even;
    }
}
