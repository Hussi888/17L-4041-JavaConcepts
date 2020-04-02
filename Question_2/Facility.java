package Question_2;

public class Facility {
    private String name;
    private int startTime;
    int registerFee;
    private int endTime;
    private String requiremtent;
    public Facility(String _name, int _registerFee, int _startTime, int _endTime, String _requiremtent) {
        name = _name;
        registerFee = _registerFee;
        startTime = _startTime;
        endTime = _endTime;
        requiremtent = _requiremtent;
    }
}
