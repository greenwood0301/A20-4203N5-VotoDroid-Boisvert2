package org.boisvert.Modele;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class VDQuestion {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo
    public String texte;

    @ColumnInfo
    public Integer nbVote;

    /*public VDQuestion()
    {

    }

    public VDQuestion(Integer id,String texte, Integer nbVote)
    {

    }*/

}
