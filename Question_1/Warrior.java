package Question_1;

import java.util.Random;
import java.util.Scanner;

public class Warrior extends Human
{
    public Warrior(String _name)
    {
        super(_name);
    }
    @Override
    public boolean CheckSameType(Character opponent)
    {
        if (opponent instanceof Warrior)
            return true;
        return false;
    }
    @Override
    public void GiveDamage()
    {
        if (Game.damage != 0)
            super.health -= Game.damage;
        Game.damage = 0;
    }
    @Override
    public void TakeAction(boolean attackByType)
    {
        int pressedInput = 0;
        int max = 6, min = 1;

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
            System.out.println("Press 6 for Capture");
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
        else if (pressedInput == 6)
            Capture();
        else
            System.out.println("Action MISSED");
        attackByType = false;
    }
    @Override
    public void PrintInfo() {
        System.out.println("A Warrior named " + name + " with Health " + super.health + ".");
    }
    @Override
    void Attack() {
        //Attacks with Swords
        super.health += 15;
        System.out.println("Warrior Attacks");
    }
    void Capture() {
        System.out.println("Warrior Captures.");
    }
}