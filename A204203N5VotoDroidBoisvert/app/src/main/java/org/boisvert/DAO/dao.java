package org.boisvert.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.boisvert.Modele.VDQuestion;

import java.util.List;

@Dao
public interface dao {

    @Insert
    Integer creerQuestion(VDQuestion question);

    @Query("SELECT * FROM VDQuestion")
    List<VDQuestion> ALL();
}
