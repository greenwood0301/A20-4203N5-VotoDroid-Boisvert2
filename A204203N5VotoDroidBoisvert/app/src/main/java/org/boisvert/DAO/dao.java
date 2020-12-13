package org.boisvert.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;

import java.util.List;

@Dao
public interface dao {

    @Insert
    long creerQuestion(VDQuestion question);

    @Insert
    long creerVote(VDVote vote);

    @Query("SELECT * FROM VDQuestion")
    List<VDQuestion> ALLQ();

    @Query("DELETE FROM VDQuestion")
    void delete();

    @Query("DELETE FROM VDVote")
    void deleteV();

    @Query("SELECT * FROM VDVote")
    List<VDVote> ALLV();

    @Query("DELETE FROM VDQuestion WHERE id = :QuestionId")
    abstract void deleteById(long QuestionId);
}
