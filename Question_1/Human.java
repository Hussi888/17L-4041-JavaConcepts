package Question_1;

import java.util.Random;
import java.util.Scanner;

public class Human implements Character
{
    String name;
    int health;
    public Human(String _name) {
        int max = 100, min = 1;
        Random randomHealth = new Random();
        name = _name;
        health = randomHealth.nextInt(((max - min) + 1) + min);
    }
    boolean HealthRange()
    {
        //Health should be greater than 0 and smaller than 100
        try
        {
            if (health <= 0 || health >= 100)
            {
                throw new Exception();
            }
        }
        catch (Exception WastedCharacter)
        {
            System.out.println("Character has been wasted.");
            return false;
        }
        return true;
    }
    @Override
    public void GiveDamage()
    {
        if (Game.damage != 0)
            health -= Game.damage;
        Game.damage = 0;
    }
    @Override
    public void TakeAction(boolean attackByType)
    {
        int pressedInput = 0;
        int max = 5, min = 1;

        //Action select menu
        if (!attackByType) {
            //Random Attack for Opponent
            Random randomHealth = new Random();
            pressedInput = randomHealth.nextInt(((max - min) + 1) + min);
        }
        else {
            Scanner input = new Scanner(System.in);
            System.out.println("-----------------------CHOOSE YOUR ACTION-----------------------");
            System.out.println("Press 1 for Jump");
            System.out.println("Press 2 for Defend");
            System.out.println("Press 3 for Speak");
            System.out.println("Press 4 for Explore");
            System.out.println("Press 5 for Attack");
            try {
                pressedInput = input.nextInt();
            }
            catch (Exception e) {
                System.out.println("Wrong Input, Run Again.");
                return;
            }
        }
        //Carry out Action
        if (pressedInput == 1)
            Jump();
        else if (pressedInput == 2)
            Defend();
        else if (pressedInput == 3)
            Speak();
        else if (pressedInput == 4)
            Explore();
        else if (pressedInput == 5)
            Attack();
        else
            System.out.println("Action MISSED");
        attackByType = false;
    }
    @Override
    public boolean CheckSameType(Character opponent)
    {
        if (opponent instanceof Human)
            if (!(opponent instanceof Wizard))
                if (!(opponent instanceof Warrior))
                    return true;
        return false;
    }
    @Override
    public void PrintInfo() {
        System.out.println("A Human named " + name + " with Health " + health + ".");
    }
    @Override
    public void Jump() {
        if (HealthRange())
            System.out.println("Character Jumps.");
    }
    @Override
    public void Defend() {
        if (HealthRange())
            System.out.println("Character Defends.");
    }
    @Override
    public void Speak() {
        if (HealthRange())
            System.out.println("Character Speaks.");
    }
    void Explore() {
        System.out.println("Human Explores.");
    }
    void Attack() {
        System.out.println("Human Attacks, No damage");
    }
}