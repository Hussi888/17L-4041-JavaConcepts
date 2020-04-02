package Question_1;

public interface Character
{
    void Jump();
    void Defend();
    void Speak();
    boolean CheckSameType(Character opponent);
    void GiveDamage();
    //Menu Requirements
    void PrintInfo();
    void TakeAction(boolean attackByType);
}
