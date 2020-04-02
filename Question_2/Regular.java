package Question_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Regular extends Client {
    static int monthlyFee = 1000;
    public Regular(int _clientId, String _name, Integer _cardNum, int _age, Set<Facility> Facilities) {
        super(_clientId, _name, _cardNum, _age, Facilities);
        ArrayList<Facility> facs= new ArrayList<Facility>(Facilities);
        super.totalFee += monthlyFee;
        for (int i = 0; i < facs.size(); i++) {
            super.totalFee += facs.get(i).registerFee;
        }
    }
    @Override
    public void Print() {
        super.Print();
    }
}
