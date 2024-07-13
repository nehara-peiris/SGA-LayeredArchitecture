package lk.ijse.sgalayeredarchitecture.bo;

import lk.ijse.sgalayeredarchitecture.bo.custom.impl.*;

public class BOFactory{
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory==null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CLIENT, LAWYER, CASES, CHARGE, COURT, DEED, JUDGE, SUMMON, PAYMENT, LAWCASE, CASECHARGE, DEEDCHARGE, CALCASECHARGE, CALDEEDCHARGE
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CLIENT:
                return new ClientBoImpl();
            case LAWYER:
                return new LawyerBoImpl();
            case CASES:
                return new CasesBoImpl();
            case CHARGE:
                return new ChargeBoImpl();
            case COURT:
                return new CourtBoImpl();
            case DEED:
                return new DeedBoImpl();
            case JUDGE:
                return new JudgeBoImpl();
            case SUMMON:
                return new SummonBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case LAWCASE:
                return new LawCaseBoImpl();
            case DEEDCHARGE:
                return new DeedChargeBoImpl();
            case CASECHARGE:
                return new CaseChargeBoImpl();
            case CALDEEDCHARGE:
                return new CalDeedChargeBoImpl();
            case CALCASECHARGE:
                return new CalCaseChargeBoImpl();
            default:
                return null;
        }
    }
}
