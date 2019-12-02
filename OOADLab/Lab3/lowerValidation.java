package OOADLab.Lab3;

public class lowerValidation implements Validation {

    @Override
    public boolean validate(String input) {
        for (char c : input.toCharArray()){
            if (!Character.isLowerCase(c)){
                return false;
            }
        }
        return true;
    }
}
