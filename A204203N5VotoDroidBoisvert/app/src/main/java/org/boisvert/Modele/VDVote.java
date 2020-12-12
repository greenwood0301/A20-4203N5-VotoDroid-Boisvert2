package org.boisvert.Modele;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VDVote {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo
    public Integer valeur;

    @ColumnInfo
    public String user;

    @ColumnInfo
    public Integer Qid;

    public VDVote()
    {

    }

    public VDVote(Integer id,Integer valeur, String user, Integer Qid)
    {

    }

}
