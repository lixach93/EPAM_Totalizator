package by.training.lihodievski.final_project.dao;

import by.training.lihodievski.final_project.bean.Competition;

public abstract class CompetitionDaoAbstract extends AbstractGenericDao<Competition>{

    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSQL() {
        return null;
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT competition_id, opponent_first,opponent_second,status, opponent_first_result, opponent_second_result FROM totalizator.competition";
    }

    @Override
    protected String getInsertSQL() {
        return null;
    }
}
