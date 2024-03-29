package com.example.tripvault;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tripvault.UI.createAcc;
import com.example.tripvault.UI.userDrawer;
import com.example.tripvault.data.User;
import com.example.tripvault.data.DatabaseHandler;
 import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "THIS CLASS";
    CardView cardView, brandName;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logIn = findViewById(R.id.login);
        Button createAcc = findViewById(R.id.createAccount);
        cardView = findViewById(R.id.cardView);
        imageView = findViewById(R.id.homedisplay);
        getSupportActionBar().hide();

        anim_Slide_Up();
//		anim_Slide_down();
        createAcc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToacc();
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    goToUserPage();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Picasso.get().load(R.drawable.screennew)
                .resize(1800, 0)
                .centerInside()
                .error(R.drawable.screennew)
                .into(imageView);

    }


    public void goToacc() {
        Intent intent = new Intent(this, createAcc.class);
        startActivity(intent);
    }

    public void goToUserPage() throws Exception {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);


        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.Password);
        String typedName = username.getText().toString().toLowerCase().trim();
        String userPass = password.getText().toString().trim();
        if (!typedName.isEmpty()) {

            User getContact = databaseHandler.getLogInInfo(typedName);
            if (getContact.getUserName().equals(typedName)) {
                Toast.makeText(this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, userDrawer.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();

        }

    }

    private void anim_Slide_Up() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_up);
        cardView.setAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void anim_Slide_down() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_down);
        imageView.setAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

}



