package Question_2;
import java.lang.ref.Cleaner;
import java.util.*;

abstract public class Client {
    private Registration registrationDetails = new Registration();
    private int clientId;
    private String name;
    private Integer cardNum;
    private int age;
    protected int totalFee = 0;
    public Client(int _clientId, String _name, Integer _cardNum, int _age, Set<Facility> Facilities) {
        clientId = _clientId;
        name = _name;
        cardNum = _cardNum;
        age = _age;
        registrationDetails.facilityAccessed = new HashSet<Facility>(Facilities);
        registrationDetails.registerDate = new Date(Calendar.getInstance().getTimeInMillis());
        totalFee += registrationDetails.registerFee;
    }
    void Print()
    {
        //Polymorphism
        System.out.println("Client" + clientId + " with name " + name + " aged " + age + " having card number " + cardNum + " and total fee " + totalFee + " on " + registrationDetails.registerDate + ".");
    }
    void PrintUnavailedFaculty()
    {
        int index = 0; boolean flag = true;
        ArrayList<Facility> FacsPersonal = new ArrayList<Facility>(registrationDetails.facilityAccessed);
        ArrayList<Facility> Facs= new ArrayList<Facility>(FitnessCenter.allFacilities);
        if (FacsPersonal.isEmpty()) {
            System.out.println("Press 1 to add Swimming Pool for $60.");
            System.out.println("Press 2 to add Tennis Court for $45.");
            System.out.println("Press 3 to add Yoga Center for $50.");
            return;
        }
        for (int i = 0; i < Facs.size(); i++) {
            flag = true;
            for (int j = 0; j < FacsPersonal.size(); j++) {
                if (FacsPersonal.get(j) == Facs.get(i)) {
                    flag = false;
                    break;
                }
                else
                    index = i;
            }
            if(flag && !FacultyCheck())
            {
                if (index == 1)
                    System.out.println("Press 1 to add Swimming Pool for $60.");
                else if (index == 2)
                    System.out.println("Press 2 to add Tennis Court for $45.");
                else if (index == 0)
                    System.out.println("Press 3 to add Yoga Center for $50.");
                index = -1;
            }
        }
    }
    void addFaculty(Facility Facilities, int facIndex)
    {
        int oldSize, newsize = 0;
        ArrayList<Facility> Facs= new ArrayList<Facility>(FitnessCenter.allFacilities);
        oldSize = registrationDetails.facilityAccessed.size();
        registrationDetails.facilityAccessed.add(Facilities);
        newsize = registrationDetails.facilityAccessed.size();
        if (newsize > oldSize)
            totalFee += Facs.get(facIndex).registerFee;
    }
    boolean FacultyCheck()
    {
        if (registrationDetails.facilityAccessed == FitnessCenter.allFacilities)
            return true;
        return false;
    }
    Member Upgrade(int index)
    {
        Member result = new Member(clientId, name, cardNum, age, registrationDetails.facilityAccessed);
        return result;
    }
    Regular Downgrade(int index)
    {
        Set<Facility> emptyFac = new HashSet<>();
        Regular result = new Regular(clientId, name, cardNum, age, emptyFac);
        return result;
    }
    boolean checkOverdue()
    {
        float diff = Math.abs(registrationDetails.registerDate.getDay() - Calendar.getInstance().getTime().getDay());
        float days = (diff / (1000*60*60*24));
        if (days >= 30)
            return true;
        return false;
    }
    Date getDueDate()
    {
        Date result = new Date();
        result = registrationDetails.registerDate;
        result.setMonth(result.getMonth() + 1);
        return result;
    }
}
