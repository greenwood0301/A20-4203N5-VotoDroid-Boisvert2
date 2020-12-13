package org.boisvert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.boisvert.DAO.BD;
import org.boisvert.Exceptions.MauvaisVote;
import org.boisvert.Exceptions.MauvaiseQuestion;
import org.boisvert.Impl.ServiceImpl;
import org.boisvert.Interfaces.Service;
import org.boisvert.Modele.VDQuestion;
import org.boisvert.Modele.VDVote;
import org.boisvert.databinding.ActivityListeBinding;
import org.boisvert.databinding.ActivityVoteBinding;

import java.util.List;

public class VoteActivity extends AppCompatActivity
{
    private ActivityVoteBinding binding;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        int Id = getIntent().getIntExtra("id",-1);
        List<VDQuestion> gaming = BD.getInstance(this).dao().ALLQ();
        binding.Title.setText(gaming.get(Id).texte);
        BD local = BD.getInstance(this);

        binding.voter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VoteActivity.this,ListeActivity.class);
                try {
                    Service service = new ServiceImpl(getApplicationContext());
                    VDVote vote = new VDVote();
                    vote.Qid = Id;
                    vote.user = binding.tonNom.getText().toString();
                    vote.valeur = (int)binding.ratingBar.getRating();
                    service.ajoutVote(vote);
                    local.dao().creerVote(vote);
                }
                catch (MauvaisVote e)
                {
                    Toast.makeText(getApplicationContext(),"Un problème est survenu pendant la création de la vode. Veuiller réessayer plus tard...", Toast.LENGTH_SHORT).show();
                }
                startActivity(i);
            }
        });

    }
}
