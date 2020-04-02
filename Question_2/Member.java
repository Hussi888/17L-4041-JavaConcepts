package Question_2;

import java.util.List;
import java.util.Set;

public class Member extends Client {
    private int annualFee = 300;
    public Member(int _clientId, String _name, Integer _cardNum, int _age, Set<Facility> Facilities) {
        super(_clientId, _name, _cardNum, _age, Facilities);
        super.totalFee += annualFee;
    }
    @Override
    public void Print() {
        super.Print();
    }
}
