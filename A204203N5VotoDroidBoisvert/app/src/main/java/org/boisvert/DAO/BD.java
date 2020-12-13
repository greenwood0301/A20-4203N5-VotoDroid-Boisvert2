package org.boisvert.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;

@Database(entities = {VDQuestion.class, VDVote.class},version = 1)

public abstract class BD extends RoomDatabase{

    public abstract dao dao();

    private static BD instance;

    public static BD getInstance(Context c)
    {
        if (instance == null)
        {
            synchronized (BD.class)
            {
                if (instance == null)
                {
                    instance = Room.databaseBuilder(c.getApplicationContext(),BD.class,"BD_TP").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

}

