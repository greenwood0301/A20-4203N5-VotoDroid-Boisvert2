package org.boisvert.DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;

@Database(entities = {VDQuestion.class, VDVote.class},version = 1)

public abstract class BD extends RoomDatabase{

    public abstract dao dao();
}

