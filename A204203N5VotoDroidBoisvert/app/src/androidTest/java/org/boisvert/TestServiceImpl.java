package org.boisvert;
import org.boisvert.Exceptions.MauvaisVote;
import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Impl.ServiceImpl;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestServiceImpl
{
    //region AjoutQuestion
    @Test
    public void testAjoutQuestionGood1Q() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);
    }
    @Test
    public void testAjoutQuestionGood2Q() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);
        VDQuestion question2 = new VDQuestion();
        question2.texte = "Le canada a t-il une culture?";
        service.ajoutQuestion(question2);
    }
    @Test
    public void testAjoutQuestionGoodIDIsNull() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        Assert.assertNull (question.id);
        service.ajoutQuestion(question);
        Assert.assertNotNull(question.id);
    }
    @Test
    public void testAjoutQuestionGoodIDNotSame2Q() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);
        VDQuestion question2 = new VDQuestion();
        question2.texte = "Le canada a t-il une culture?";
        service.ajoutQuestion(question2);
        Assert.assertNotEquals(question.id,question2.id);
    }
    @Test (expected = MauvaiseQuestion.class)
    public void testAjoutQuestionBadIDNoneNull() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        question.id = 1995;
        service.ajoutQuestion(question);
    }
    @Test (expected = MauvaiseQuestion.class)
    public void testAjoutQuestionBadTexteSame2Q() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);
        VDQuestion question2 = new VDQuestion();
        question2.texte = "Le quebec est-il un pays?".toUpperCase();
        service.ajoutQuestion(question2);
    }
    @Test (expected = MauvaiseQuestion.class)
    public void testAjoutQuestionBadTexteIsNull() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        service.ajoutQuestion(question);
    }
    @Test (expected = MauvaiseQuestion.class)
    public void testAjoutQuestionBadTexteTooShort() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte ="a";
        service.ajoutQuestion(question);
    }
    @Test (expected = MauvaiseQuestion.class)
    public void testAjoutQuestionBadTexteTooLong() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        service.ajoutQuestion(question);
    }
    @Test (expected = MauvaiseQuestion.class)
    public void testAjoutQuestionBadNbVoteNoneNull() throws MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte ="Le quebec est-il un pays?";
        question.nbVote = 32;
        service.ajoutQuestion(question);
    }
    //endregion
    //region AjoutVote
    @Test
    public void testAjoutVoteGood() throws MauvaisVote, MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);
    }
    @Test
    public void testAjoutVoteGoodUserDiff2V() throws MauvaisVote, MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.user = "PierreEliotTrudeau";
        vote2.valeur = 0;
        vote2.Qid = question.id;
        service.ajoutVote(vote2);
        Assert.assertNotEquals(vote.user,vote2.user);
    }
    @Test
    public void testAjoutVoteGoodIDIsNull() throws MauvaiseQuestion, MauvaisVote
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        Assert.assertNull(vote.id);
        service.ajoutVote(vote);
        Assert.assertNotNull(vote.id);
    }
    @Test (expected = MauvaisVote.class)
    public void testAjoutVoteBadQidNull() throws MauvaisVote
    {
        Service service = new ServiceImpl();
        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        service.ajoutVote(vote);
    }
    @Test (expected = MauvaisVote.class)
    public void testAjoutVoteBadSameGuy2V() throws MauvaiseQuestion, MauvaisVote
    {
        Service service = new ServiceImpl();

        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.user = "JaCqUeSPaRiZeaU";
        vote2.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote2);
    }
    @Test (expected = MauvaisVote.class)
    public void testAjoutVoteBadNegative() throws MauvaiseQuestion, MauvaisVote
    {
        Service service = new ServiceImpl();

        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "VoteEthnique";
        vote.valeur = -1;
        vote.Qid = question.id;
        service.ajoutVote(vote);

    }
    @Test (expected = MauvaisVote.class)
    public void testAjoutVoteBadOver() throws MauvaiseQuestion, MauvaisVote
    {
        Service service = new ServiceImpl();

        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "CharlesDeGaules";
        vote.valeur = 6;
        vote.Qid = question.id;
        service.ajoutVote(vote);

    }
    @Test (expected = MauvaisVote.class)
    public void testAjoutVoteBadIdNotNull() throws MauvaiseQuestion, MauvaisVote
    {
        Service service = new ServiceImpl();

        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "CharlesDeGaules";
        vote.valeur = 6;
        vote.Qid = question.id;
        vote.id = 49;
        service.ajoutVote(vote);

    }
    //endregion
    //region QuestionParNBVote
    @Test
    public void QuestionParNBVoteGood() throws MauvaisVote, MauvaiseQuestion
    {
        List<VDQuestion> ListQuestionsTest = new ArrayList<VDQuestion>();

        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDQuestion question2 = new VDQuestion();
        question2.texte = "Le Canada a t-il une culture ?";
        service.ajoutQuestion(question2);

        VDVote vote3 = new VDVote();
        vote3.user = "JacquesParizeau";
        vote3.valeur = 5;
        vote3.Qid = question2.id;
        service.ajoutVote(vote3);

        VDVote vote2 = new VDVote();
        vote2.user = "PierreElliotTrudeau";
        vote2.valeur = 0;
        vote2.Qid = question2.id;
        service.ajoutVote(vote2);

        VDQuestion question3 = new VDQuestion();
        question3.texte = "insérer un sujet politique totalement pas contreversé";
        service.ajoutQuestion(question3);

        VDVote vote4 = new VDVote();
        vote4.user = "PierreElliotTrudeau";
        vote4.valeur = 0;
        vote4.Qid = question3.id;
        service.ajoutVote(vote4);

        VDVote vote5 = new VDVote();
        vote5.user = "JacquesParizeau";
        vote5.valeur = 5;
        vote5.Qid = question3.id;
        service.ajoutVote(vote5);

        VDVote vote6 = new VDVote();
        vote6.user = "Gilles Duceppe";
        vote6.valeur = 3;
        vote6.Qid = question3.id;
        service.ajoutVote(vote6);

        ListQuestionsTest.add(question3);
        ListQuestionsTest.add(question2);
        ListQuestionsTest.add(question);

        Assert.assertEquals(ListQuestionsTest,service.questionsParNombreVotes());
    }
    @Test (expected = NullPointerException.class)
    public void QuestionParNBVoteBadNoVote() throws MauvaisVote, MauvaiseQuestion, NullPointerException
    {
        Service service = new ServiceImpl();

        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDQuestion question2 = new VDQuestion();
        question2.texte = "Le Canada a t-il une culture ?";
        service.ajoutQuestion(question2);

        service.questionsParNombreVotes();

    }
    @Test (expected = NullPointerException.class)
    public void QuestionParNBVoteBad1Vote() throws MauvaisVote, MauvaiseQuestion, NullPointerException
    {
        Service service = new ServiceImpl();

        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDQuestion question2 = new VDQuestion();
        question2.texte = "Le Canada a t-il une culture ?";
        service.ajoutQuestion(question2);

        service.questionsParNombreVotes();
    }
    //endregion
    //region DistributionPour
    @Test
    public void DistributionPourGood() throws MauvaisVote, MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.user = "PierreEliotTrudeau";
        vote2.valeur = 4;
        vote2.Qid = question.id;
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.user = "JeanCharest";
        vote3.valeur = 3;
        vote3.Qid = question.id;
        service.ajoutVote(vote3);

        VDVote vote4 = new VDVote();
        vote4.user = "GillesDuceppe";
        vote4.valeur = 2;
        vote4.Qid = question.id;
        service.ajoutVote(vote4);

        VDVote vote5 = new VDVote();
        vote5.user = "JeanChretien";
        vote5.valeur = 1;
        vote5.Qid = question.id;
        service.ajoutVote(vote5);

        VDVote vote6 = new VDVote();
        vote6.user = "FrancoisLegault";
        vote6.valeur = 0;
        vote6.Qid = question.id;
        service.ajoutVote(vote6);

        Map<Integer, Integer> Map = new HashMap<Integer, Integer>();

        Map.put(0,1);
        Map.put(1,1);
        Map.put(2,1);
        Map.put(3,1);
        Map.put(4,1);
        Map.put(5,1);

        Assert.assertEquals(Map,service.distributionPour(question));

    }
    //endregion
    //region CalculMoy
    @Test
    public void moyennePourGood() throws MauvaisVote, MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.user = "PierreEliotTrudeau";
        vote2.valeur = 0;
        vote2.Qid = question.id;
        service.ajoutVote(vote2);

        Assert.assertEquals (2.5,service.moyennePour(question),0);
    }
    //endregion
    //region CalculET
    @Test
    public void ecartTypePour() throws MauvaisVote, MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.user = "PierreEliotTrudeau";
        vote2.valeur = 0;
        vote2.Qid = question.id;
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.user = "PierreEliotTrudeau";
        vote3.valeur = 0;
        vote3.Qid = question.id;
        service.ajoutVote(vote3);

        Assert.assertEquals (2.357022603955158,service.ecartTypePour(question),0);
    }
    //endregion
    //region 100pourcentcoverage
    @Test
    public void nomEtudiant() throws MauvaisVote, MauvaiseQuestion
    {
        Service service = new ServiceImpl();
        VDQuestion question = new VDQuestion();
        question.texte = "Le quebec est-il un pays?";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.user = "JacquesParizeau";
        vote.valeur = 5;
        vote.Qid = question.id;
        service.ajoutVote(vote);

        Assert.assertNull(service.nomEtudiant());
    }
    //endregion
}
