package com.example.project_countries.adapters;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_countries.R;
import com.example.project_countries.database.entities.User;

import java.util.List;
import java.util.Random;

public class UserScoreHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> users;

    public UserScoreHistoryAdapter(List<User> users) {
        this.users = users;
    }

    public static class UserScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView placeNr;
        private ImageView image;
        private TextView username;
        private TextView score;
        public UserScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            placeNr = itemView.findViewById(R.id.placeNr);
            image = itemView.findViewById(R.id.userImage);
            username = itemView.findViewById(R.id.username);
            score = itemView.findViewById(R.id.score);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserScoreViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        UserScoreViewHolder userScoreViewHolder = (UserScoreViewHolder) holder;

        userScoreViewHolder.placeNr
                .setText(String.format("%s.", position + 1));

        Random rand = new Random();
        userScoreViewHolder.image
                .setColorFilter(new PorterDuffColorFilter(
                        Color.argb(255,
                        rand.nextInt(255),
                        rand.nextInt(255),
                        rand.nextInt(255)), PorterDuff.Mode.SRC_IN));

        userScoreViewHolder.username
                .setText(String.format("%s %s",
                        user.getFirstName(),
                        user.getLastName()));

        userScoreViewHolder.score
                .setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }
}
