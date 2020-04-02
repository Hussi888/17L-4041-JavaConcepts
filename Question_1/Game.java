package Question_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    //Maintains a list of all characters
    //Maintains selected character & opponent index
    ArrayList<Character> allCharacters = new ArrayList<>();
    int character;
    int opponent;
    public static int damage = 0;

    public void CreateCharacter() {
        Scanner userInput = new Scanner(System.in);
        int pressedNum = 0;
        String enteredName;

        //Check 5 characters limit
        try {
            if (allCharacters.size() >= 5)
                throw new Exception();
        }
        catch (Exception limitCharacters) {
            System.out.println("Cant add, 5 Characters are already present.");
            return;
        }
        //Add Question_1.Character
        System.out.println("-----------------------ADD CHARACTER-----------------------");
        System.out.println("Press 1 to create Simple Human");
        System.out.println("Press 2 to create Alien");
        System.out.println("Press 3 to create Wizard");
        System.out.println("Press 4 to create Warrior");
        try {
            pressedNum = Integer.parseInt(userInput.nextLine());
        }
        catch (Exception e) {
            System.out.println("Wrong Input, Run Again.");
            return;
        }


        if (pressedNum == 2) {
            allCharacters.add(new Alien());
            System.out.println("An Alien is created.");
            return;
        }

        System.out.print("Enter Name of Character: ");
        enteredName = userInput.nextLine();

        if (pressedNum == 1) {
            allCharacters.add(new Human(enteredName));
            System.out.println("A Human named " + enteredName + " is created.");
        }

        else if (pressedNum == 3) {
            allCharacters.add(new Wizard(enteredName));
            System.out.println("A Wizard named " + enteredName + " is created.");
        }
        else if (pressedNum == 4) {
            allCharacters.add(new Warrior(enteredName));
            System.out.println("A Question_1.Warrior named " + enteredName + " is created.");
        }
    }

    public void Play() {
        Scanner userInput = new Scanner(System.in);

        //Check number of current Characters
        if (allCharacters.size() < 2) {
            System.out.println("You cannot play, there are less than 2 characters.");
            return;
        }

        //Choose from current Characters
        System.out.println("-----------------------CHOOSE YOUR CHARACTER-----------------------");
        for (int i = 0; i < allCharacters.size(); i++) {
            System.out.print("Press " + i + " to choose ");
            allCharacters.get(i).PrintInfo();
            System.out.print("\n");
        }
        character = userInput.nextInt();
        System.out.println("-----------------------CHOOSE YOUR OPPONENT-----------------------");
        for (int i = 0; i < allCharacters.size(); i++) {
            //Do not show choosen character in this list now
            if (i != character)
            {
                System.out.print("Press " + i + " to choose ");
                allCharacters.get(i).PrintInfo();
                System.out.print("\n");
            }
        }
        opponent = userInput.nextInt();

        //Check if same (INSTANCE OF)
        try {
            if (allCharacters.get(character).CheckSameType(allCharacters.get(opponent)))
                throw new Exception();
        }
        catch (Exception sameType) {
            System.out.println("Cannot attack same character type");
            return;
        }

        //Now Fight
        System.out.print("You: ");
        allCharacters.get(character).TakeAction(true);
        allCharacters.get(opponent).GiveDamage();
        System.out.print("Opponent: ");
        allCharacters.get(opponent).TakeAction(false);
        allCharacters.get(character).GiveDamage();
    }

    public static void main(String args[]) {

        Scanner userInput = new Scanner(System.in);
        int pressedNum = 0;
        String enteredName;

        //Create Question_1.Game
        Game game = new Game();

        //Start menu
        while(true) {
            System.out.println("-----------------------START MENU-----------------------");
            System.out.println("Press 1 for Creating Characters");
            System.out.println("Press 2 for Playing Game with current Characters");
            System.out.println("Press 3 to Exit Game.");
            try {
                pressedNum = userInput.nextInt();
            }
            catch (Exception e) {
                System.out.println("Wrong Input, Run Again.");
                return;
            }


            //Create Question_1.Character Menu
            if (pressedNum == 1) {
                game.CreateCharacter();
            }

            //Play Question_1.Game Menu
            else if (pressedNum == 2) {
                game.Play();
            }

            //Stop Question_1.Game
            else if (pressedNum == 3) {
                break;
            }

            //Wrong Input
            else
                System.out.println("Wrong Input, Enter Again.");
        }
    }
}
