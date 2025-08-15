package pratice;

public class ID {
    private boolean isPWD;
    private boolean isSenior;
    private boolean isValid;

    public ID(boolean isPWD, boolean isSenior, boolean isValid) {
        this.isPWD = isPWD;
        this.isSenior = isSenior;
        this.isValid = isValid;   
    }

    public boolean getIsPWD() {
        return isPWD;
    }

    public boolean getIsSenior() {
        return isSenior;
    }

    public boolean getIsValid() {
        return isValid;
    }
}
