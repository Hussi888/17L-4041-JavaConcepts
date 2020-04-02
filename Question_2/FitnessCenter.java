package Question_2;

import java.io.FileReader;
import java.util.*;

public class FitnessCenter {
    static int clientCount = 0;
    static Set<Client> allClients;
    static Set<Facility> allFacilities;
    //Singleton Object
    private static FitnessCenter Systems = null;
    private FitnessCenter() {
        //Singleton object of Fitness Center
        //allFacilities.add(new Facility("Gym", 90, 1000, 2200, "Trainers"));
        //We fixed $90 and now adding in for the rest of the facilities
        allClients = new HashSet<Client>();
        allFacilities = new HashSet<Facility>() ;
        allFacilities.add(new Facility("Yoga Center", 50, 1000, 2200, "Trainers"));
        allFacilities.add(new Facility("Swimming Center", 60, 1000, 1700, "Trainers"));
        allFacilities.add(new Facility("Tennis Court", 45, 1000, 1700, "Trainers"));
    }
    public static FitnessCenter getInstance() {
        if (Systems == null)
            Systems = new FitnessCenter();
        return Systems;
    }
    //Functionalities
    public void AddClients()
    {
        Scanner userInput = new Scanner(System.in);
        int select; String checkMember;
        Set<Facility> selectFacility = new HashSet<Facility>();
        System.out.println("-----------------------ADD CLIENT-----------------------");
        System.out.print("Enter name of Client: ");
        String name;
        try {
            name = userInput.nextLine();
        }
        catch (Exception e) {
            System.out.println("Entered Wrong , Exiting.");
            return;
        }
        System.out.print("Enter Age of Client: ");
        int age ;
        try {
            age = Integer.parseInt(userInput.nextLine());
        }
        catch (Exception e) {
            System.out.println("Entered Wrong , Exiting.");
            return;
        }
        System.out.print("Enter Card Number of Client: ");
        int cardNum;
        try {
            cardNum = Integer.parseInt(userInput.nextLine());
        }
        catch (Exception e) {
            System.out.println("Entered Wrong , Exiting.");
            return;
        }
        System.out.println("If you opt to be a member, you would benefit no registeration fee.");
        System.out.println("Do you want to be a member, Press 0 to SKIP or any key to be a member.");
        try {
            checkMember = userInput.nextLine();
        }
        catch (Exception e) {
            System.out.println("Entered Wrong , Exiting.");
            return;
        }
        if (Integer.parseInt(checkMember) == 0)
        {
            Regular regularObj = new Regular(clientCount, name, cardNum, age, selectFacility);
            //Convert to list to get items
            List<Facility> allFacs = new ArrayList<Facility>(allFacilities);
            //Menu to add facilities
            while(true)
            {
                System.out.println("-----------------------ADD From FACILITIES-----------------------");
                regularObj.PrintUnavailedFaculty();
                System.out.println("Press 4 to Exit adding facilities.");
                select = userInput.nextInt();
                if (select == 1)
                {
                    selectFacility.add(allFacs.get(1));
                    regularObj.addFaculty(allFacs.get(1), 1);
                }
                else if (select == 2)
                {
                    selectFacility.add(allFacs.get(2));
                    regularObj.addFaculty(allFacs.get(2), 2);
                }
                else if (select == 3){
                    selectFacility.add(allFacs.get(0));
                    regularObj.addFaculty(allFacs.get(0), 0);
                }
                else if (select == 4) {
                    allClients.add(regularObj);
                    break;
                }
                else
                    System.out.println("Wrong Input, choose again.");
            }
        }
        else {
            allClients.add(new Member(clientCount, name, cardNum, age, allFacilities));
        }
        ArrayList<Client> tempClients = new ArrayList<Client>(allClients);
        System.out.print("Client Added with Credentials: ");
        tempClients.get(clientCount).Print();
        clientCount++;
    }
    int SearchClient()
    {
        int client = -1;
        Scanner userInput = new Scanner(System.in);
        ArrayList<Client> tempClients = new ArrayList<Client>(allClients);
        for (int i = 0; i < tempClients.size(); i++)
            tempClients.get(i).Print();
        while(true)
        {
            System.out.println("Choose your Client Id from above");
            try {
                client = userInput.nextInt();
            }
            catch (Exception e) {
                System.out.println("Entered Wrong , Exiting.");
                return -1;
            }

            if (client < tempClients.size() && client >= 0)
                return client;
        }
    }
    boolean FacultyCheck(int index)
    {
        ArrayList<Client> tempClients = new ArrayList<Client>(allClients);
        if (tempClients.get(index).FacultyCheck())
            return true;
        return false;
    }
    void UserActions()
    {
        Scanner userInput = new Scanner(System.in);
        Set<Facility> selectFacility = new HashSet<Facility>();
        int index = SearchClient(), pressed = 0;
        if (index == -1)
            return;
        ArrayList<Client> tempClients = new ArrayList<Client>(allClients);
        ArrayList<Facility> tempFacs = new ArrayList<Facility>(allFacilities);
        if (!FacultyCheck(index))
        {
            System.out.println("Press 1 to Add Faculty.");
            if (tempClients.get(index) instanceof Regular)
                System.out.println("Press 2 to Upgrade to Member.");
        }
        if (tempClients.get(index) instanceof Member)
            System.out.println("Press 3 to Downgrade to Regular.");
        System.out.println("Press 4 to Check Bill.");
        try {
            pressed = userInput.nextInt();
        }
        catch (Exception e) {
            System.out.println("Entered Wrong , Exiting.");
            return;
        }
        if (pressed == 1)
        {
            while(true)
            {
                System.out.println("-----------------------ADD From FACILITIES-----------------------");
                tempClients.get(index).PrintUnavailedFaculty();
                System.out.println("Press 4 to Exit adding facilities.");
                pressed = userInput.nextInt();
                if (pressed == 1)
                {
                    selectFacility.add(tempFacs.get(1));
                    tempClients.get(index).addFaculty(tempFacs.get(1), 1);
                }
                else if (pressed == 2)
                {
                    selectFacility.add(tempFacs.get(2));
                    tempClients.get(index).addFaculty(tempFacs.get(2), 2);
                }
                else if (pressed == 3){
                    selectFacility.add(tempFacs.get(0));
                    tempClients.get(index).addFaculty(tempFacs.get(0), 0);
                }
                else if (pressed == 4) {
                    break;
                }
                else
                    System.out.println("Wrong Input, choose again.");
            }
            return;
        }
        if (pressed == 2)
        {
            Member upgrade = tempClients.get(index).Upgrade(index);
            FitnessCenter.allClients.remove(tempClients.get(index));
            FitnessCenter.allClients.add(upgrade);
            System.out.println("Upgraded.");
            return;
        }
        if (pressed == 3)
        {
            Regular downgrade = tempClients.get(index).Downgrade(index);
            FitnessCenter.allClients.remove(tempClients.get(index));
            FitnessCenter.allClients.add(downgrade);
            System.out.println("Downgraded.");
            return;
        }
        if (pressed == 4)
        {
            //Check Bill
            if (tempClients.get(index).checkOverdue())
            {
                System.out.println("Your Bill is Overdue, removing Client.");
                FitnessCenter.allClients.remove(tempClients.get(index));
            }
            else
            {
                System.out.println("Your Bill is " + Regular.monthlyFee + " due " + tempClients.get(index).getDueDate() + ".");
                tempClients.get(index).totalFee = Regular.monthlyFee;
            }
            return;
        }
    }
    //Main
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        FitnessCenter fitCenter = getInstance();
        System.out.println("Enter Monthly Fee to be charged.");
        try
        {
            Regular.monthlyFee = userInput.nextInt();
        }
        catch (Exception wrongInput)
        {
            System.out.println("Entered Wrong , Exiting.");
        }
        fitCenter.AddClients();
        while(true)
            fitCenter.UserActions();
    }
}
