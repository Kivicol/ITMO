package command.Utility;


public  abstract class Builder {


    /**
     * Abstract class for building strings, longs, ints and floats respectively
     */

    protected Reader scanner;

    public Builder(){
        this.scanner = (FileUtil.isFileMode) ? new ScriptUtil() : new InputByHand();
    }

    protected String buildString(String name){
        String word;
        while(true){
            System.out.println("Input " + name);
            word = scanner.nextline();
            if(word.isEmpty()){
                return null;
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
            input=scanner.nextline();
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
            input=scanner.nextline();
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
            input=scanner.nextline();
            try {
                return Float.parseFloat(input);
            }
            catch (NumberFormatException e){
                System.err.println("The specified number must be \"float\"");
            }
        }
    }

}
