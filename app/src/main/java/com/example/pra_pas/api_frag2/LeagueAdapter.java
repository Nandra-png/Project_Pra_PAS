package com.example.pra_pas.api_frag2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pra_pas.R;

import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>{

    private Context context;
    private List<League> leagues;

    public LeagueAdapter(Context context, List<League> leagues) {
        this.context = context;
        this.leagues = leagues;
    }

    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_item, parent, false);
        return new LeagueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {
        League league = leagues.get(position);
        holder.leagueName.setText("League: " + league.getStrLeague());
        holder.sportName.setText("Sport: " + league.getStrSport());
        holder.leagueAlternate.setText("Alternate Name: " + league.getStrLeagueAlternate());
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public static class LeagueViewHolder extends RecyclerView.ViewHolder {
        TextView leagueName, sportName, leagueAlternate;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueName = itemView.findViewById(R.id.leagueName);
            sportName = itemView.findViewById(R.id.sportName);
            leagueAlternate = itemView.findViewById(R.id.leagueAlternateName);
        }
    }
}
