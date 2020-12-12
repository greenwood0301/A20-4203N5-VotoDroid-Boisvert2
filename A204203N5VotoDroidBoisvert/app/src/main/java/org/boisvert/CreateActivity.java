package org.boisvert;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.boisvert.databinding.ActivityCreateBinding;
import org.boisvert.databinding.ActivityMainBinding;

public class CreateActivity extends AppCompatActivity {
    private ActivityCreateBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
        binding.pose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateActivity.this,ListeActivity.class);

                startActivity(i);
            }
        });
    }
}
