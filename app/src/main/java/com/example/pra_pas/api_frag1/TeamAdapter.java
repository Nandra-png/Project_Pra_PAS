package com.example.pra_pas.api_frag1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pra_pas.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context context;
    private List<Team> teams;

    public TeamAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_item, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamName.setText("Team Name: " + team.getStrTeam());
        holder.alternateName.setText("Alternate Name: " + team.getStrAlternate());
        holder.stadiumName.setText("Stadium: " + team.getStrStadium());
        holder.teamshort.setText("Singkatan :" + team.getStrTeamShort());

        Picasso.get().load(team.getStrTeamBadge()).into(holder.teamBadge);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamName, alternateName, stadiumName, teamshort;
        ImageView teamBadge;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.teamName);
            teamshort = itemView.findViewById(R.id.teamshort);
            alternateName = itemView.findViewById(R.id.alternateName);
            stadiumName = itemView.findViewById(R.id.stadiumName);
            teamBadge = itemView.findViewById(R.id.teamBadge);
        }
    }
}
