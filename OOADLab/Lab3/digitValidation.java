package OOADLab.Lab3;

public class digitValidation implements Validation {

    @Override
    public boolean validate(String input) {
        for (char c : input.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
