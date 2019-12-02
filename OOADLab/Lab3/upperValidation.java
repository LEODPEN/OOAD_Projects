package OOADLab.Lab3;

public class upperValidation implements Validation {

    @Override
    public boolean validate(String input) {
        for (char c : input.toCharArray()){
//            if (Character.isDigit())
            if (!Character.isUpperCase(c)){
                return false;
            }
        }
        return true;
    }
}
