package lk.ijse.sgalayeredarchitecture.dao;

import lk.ijse.sgalayeredarchitecture.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CLIENT, LAWYER, CASES, CHARGE, COURT, DEED, JUDGE, SUMMON, PAYMENT, CASECHARGE, DEEDCHARGE
    }

    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){
            case CLIENT:
                return new ClientDAOImpl();
            case LAWYER:
                return new LawyerDAOImpl();
            case CASES:
                return new CasesDAOImpl();
            case CHARGE:
                return new ChargeDAOImpl();
            case COURT:
                return new CourtDAOImpl();
            case DEED:
                return new DeedDAOImpl();
            case JUDGE:
                return new JudgeDAOImpl();
            case SUMMON:
                return new SummonDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case CASECHARGE:
                return new CaseChargeDAOImpl();
            case DEEDCHARGE:
                return new DeedChargeDAOImpl();
            default:
                return null;

        }
    }
}
