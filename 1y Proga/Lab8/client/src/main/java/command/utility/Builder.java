package command.utility;



public abstract class Builder {


    /**
     * Abstract class for building strings, longs, ints and floats respectively
     */

    protected Reader scanner;

    public Builder(){
        this.scanner = new InputByHand();
    }

    protected String buildString(String name){
        String word;
        while(true){
            System.out.println("Input " + name);
            word = scanner.nextLine();
            if(word.isBlank()){
                System.err.println("Name cannot be empty");
            }
            else{
                return word;
            }

        }
    }
    protected Long buildLong(String name){
        String input;
        while(true){
            System.out.println("Input " + name);
            input=scanner.nextLine();
            try {
                return Long.parseLong(input);
            }
            catch (NumberFormatException e){
                System.err.println("The specified number must be \"long\"");
            }
        }
    }

    protected Integer buildInt(String name){
        String input;
        while(true){
            System.out.println("Input " + name);
            input=scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e){
                System.err.println("The specified number must be \"int\"");
            }
        }
    }

    protected Float buildFloat(String name){
        String input;
        while(true){
            System.out.println("Input " + name);
            input=scanner.nextLine();
            try {
                return Float.parseFloat(input);
            }
            catch (NumberFormatException e){
                System.err.println("The specified number must be \"float\"");
            }
        }
    }

    protected Float buildFloat(String name, Float min){
        String input;
        while(true){
            System.out.println("Input " + name);
            input=scanner.nextLine();
            if(Float.parseFloat(input) > min) {
                try {
                    return Float.parseFloat(input);
                } catch (NumberFormatException e) {
                    System.err.println("The specified number must be \"float\"");
                }
            }else {
                System.err.println("The specified number must be greater than " + min);
            }
        }
    }

}
