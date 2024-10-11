package command.utility;

public class UserForm{
    private final ReaderWriter console;
    private final Reader scanner;
    public UserForm(ReaderWriter console){
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ScriptUtil()
                : new InputByHand();
    }

    public UserData build() {
        ReadManager readManager = new ReadManager(console);
        console.write("Are you registered in the system?");
        while (true){
            console.write("Write Y or N: ");
            String answer = console.readLine().toUpperCase();
            if (!(answer.equals("Y") || answer.equals("N"))) {
                System.out.println("Wrong answer. Write Y, if you are registered, or N, if you are not");
            } else {
                return new UserData(
                        readManager.readLogin(),
                        readManager.readPassword(),
                        answer.equals("Y")
                );
            }
        }
    }
}