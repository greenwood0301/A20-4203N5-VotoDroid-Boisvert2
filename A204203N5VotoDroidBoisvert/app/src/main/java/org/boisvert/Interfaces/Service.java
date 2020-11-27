package org.boisvert.Interfaces;

import java.util.List;
import java.util.Map;

import org.boisvert.Exceptions.MauvaisVote;
import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;


public interface Service {

    void ajoutQuestion(VDQuestion question) throws MauvaiseQuestion;

    void ajoutVote(VDVote vote) throws MauvaisVote;

    List<VDQuestion> questionsParNombreVotes();

    Map<Integer, Integer> distributionPour(VDQuestion question);

    double moyennePour(VDQuestion question);

    double ecartTypePour(VDQuestion question);

    String nomEtudiant();
}
