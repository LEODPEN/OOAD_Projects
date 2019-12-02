package OOADLab.Lab3;

import java.util.Scanner;

public class DataEntry {

    private Validation validation;

    public void setValidationType( char ch ) {
        switch (ch){
            case 'n':
                validation = new digitValidation();
                break;
            case 'u':
                validation = new upperValidation();
                break;
            case 'l':
                validation = new lowerValidation();
                break;
            default:
                System.out.println("sry, unsupported ! ");
                throw new UnsupportedOperationException("sry, unsupported ! ");
        }
    }

    public void interact(){
        String answer;
        Scanner in = new Scanner(System.in);
        System.out.print("Prompt: ");
        answer = in.next();
        while ( !answer.equals("quit")) {
            if (validation.validate(answer)){
                System.out.println("*** good ***");
            }else {
                System.out.println("*** bad ***");
            }
            System.out.print("Prompt: ");
            answer = in.next();
        }
    }
}
