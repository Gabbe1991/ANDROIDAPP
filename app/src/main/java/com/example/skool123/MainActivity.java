package com.example.skool123;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


import com.example.skool123.databinding.ActivityMainBinding;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Button rockButton = findViewById(R.id.rock_button5);
        Button paperButton = findViewById(R.id.paper_button5);
        Button scissorsButton = findViewById(R.id.scissors_button5);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChoice(Choices.ROCK);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChoice(Choices.PAPER);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChoice(Choices.SCISSORS);
            }
        });

    }
    public enum Choices {
        ROCK("rock"),
        PAPER("paper"),
        SCISSORS("scissors");

        private String choice;

        Choices(String choice) {
            this.choice = choice;
        }

        public String getChoice() {
            return choice;
        }
    }

    private Choices randomChoice() {
        List<Choices> options = Arrays.asList(Choices.values());
        Random random = new Random();
        return options.get(random.nextInt(options.size()));
    }

    private String checkResult(Choices userChoice, Choices computerChoice) {
        if (userChoice == computerChoice) {
            return "tie";
        } else if (
                (userChoice == Choices.ROCK && computerChoice == Choices.SCISSORS) ||
                        (userChoice == Choices.PAPER && computerChoice == Choices.ROCK) ||
                        (userChoice == Choices.SCISSORS && computerChoice == Choices.PAPER)) {
            return "you win";
        } else {
            return "you lose";
        }
    }

    private void handleChoice(Choices choice) {
        Choices computerChoice = randomChoice();
        TextView userChoiceTextView = findViewById(R.id.user_choice5);
        TextView computerChoiceTextView = findViewById(R.id.computer_choice5);
        TextView resultTextView = findViewById(R.id.result5);

        userChoiceTextView.setText("You chose: " + choice.getChoice());
        computerChoiceTextView.setText("Computer chose: " + computerChoice.getChoice());
        resultTextView.setText("Result: " + checkResult(choice, computerChoice));


    }



}