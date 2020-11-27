package org.boisvert.Impl;

import org.boisvert.Exceptions.MauvaisVote;
import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;

import java.util.*;

public class ServiceImpl  implements Service {

    private List<VDQuestion> ListQuestions = new ArrayList<VDQuestion>();

    private List<VDVote> ListVotes = new ArrayList<VDVote>();

    public void ajoutQuestion(VDQuestion question) throws MauvaiseQuestion
    {
        //valider
        if (question.texte == null) throw new MauvaiseQuestion();
        if (question.texte.length()< 5) throw new MauvaiseQuestion();
        if (question.texte.length()>255) throw new MauvaiseQuestion();
        if (question.id != null) throw new MauvaiseQuestion();
        for (VDQuestion Q:ListQuestions) {
            if (question.texte.toUpperCase().equals(Q.texte.toUpperCase())) throw new MauvaiseQuestion();
        }
        if (question.nbVote != null) throw new MauvaiseQuestion();
        //ajouter
        question.id = ListQuestions.size();
        ListQuestions.add(question);
    }

    public void ajoutVote(VDVote vote) throws MauvaisVote
    {
        //valider
        if (vote.valeur == null) throw new MauvaisVote();
        if (vote.valeur > 5) throw new MauvaisVote();
        if (vote.valeur < 0) throw new MauvaisVote();
        if (vote.id != null) throw new MauvaisVote();
        if (vote.Qid == null) throw new MauvaisVote();
        int yn = 0;
        for (int i = 0; i < ListQuestions.size(); i++)
        {
            if (ListQuestions.get(i).id == vote.Qid){yn = 1;}
        }
        if (yn == 0) throw new MauvaisVote();
        if (vote.user == null) throw new MauvaisVote();
        for (VDVote V:ListVotes)
        {
            if (vote.user.toUpperCase() == V.user.toUpperCase()) throw new MauvaisVote();
        }
        //ajouter
        vote.id = ListVotes.size();
        ListVotes.add(vote);
        for (VDQuestion Q:ListQuestions)
        {
            if (Q.id == vote.Qid)
            {
                if (Q.nbVote == null){Q.nbVote = 0;}
            }
            Q.nbVote+=1;
        }
    }

    public List<VDQuestion> questionsParNombreVotes()
    {
        List<VDQuestion> ListQuestionsL = ListQuestions;

        Collections.sort(ListQuestionsL, new Comparator<VDQuestion>() {
            @Override
            public int compare(VDQuestion o1, VDQuestion o2) {
                return Double.compare(o1.nbVote, o2.nbVote);
            }
        });

        return ListQuestionsL;
    }

    public Map<Integer, Integer> distributionPour(VDQuestion question)
    {
        Map<Integer, Integer> Map = new HashMap<Integer, Integer>();

        Map.put(0,0);
        Map.put(1,0);
        Map.put(2,0);
        Map.put(3,0);
        Map.put(4,0);
        Map.put(5,0);

        for (VDVote V:ListVotes)
        {
            if (V.Qid == question.id)
            {
                switch(V.valeur) {
                    case 1:
                        Map.put(1, Map.get(1) + 1);
                        break;
                    case 2:
                        Map.put(2, Map.get(2) + 1);
                        break;
                    case 3:
                        Map.put(3, Map.get(3) + 1);
                        break;
                    case 4:
                        Map.put(4, Map.get(4) + 1);
                        break;
                    case 5:
                        Map.put(5, Map.get(5) + 1);
                        break;
                    default:
                        Map.put(0, Map.get(0) + 1);
                }
            }
        }

        return Map;
    }

    public double moyennePour(VDQuestion question)
    {
        double calcul = 0.0;

        for (VDVote V:ListVotes)
        {
            if (V.Qid == question.id)
            {
                calcul = calcul + V.valeur;
            }
        }

        return calcul/question.nbVote;
    }

    public double ecartTypePour(VDQuestion question)
    {
        double sum = 0;
        int longueur = 0;

        for (VDVote V:ListVotes)
        {
            if (V.Qid == question.id)
            {
                sum += V.valeur;
                longueur++;
            }
        }

        double var = sum/longueur;
        double dev = 0;

        for (VDVote V:ListVotes)
        {
            if (V.Qid == question.id)
            {
                dev = dev + Math.pow(V.valeur - var,2);
            }
        }

        return Math.sqrt(dev/longueur);
    }

    public String nomEtudiant()
    {
        return null;
    }
}
