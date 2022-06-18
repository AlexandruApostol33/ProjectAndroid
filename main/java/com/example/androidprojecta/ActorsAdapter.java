package com.example.androidprojecta;

import android.content.Context;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolder> implements Filterable {

    List<Actor> actorList;
    List<Actor> actorsAll;
    Context context;

    public ActorsAdapter(List<Actor> actorList) {
        this.actorList = actorList;
        this.actorsAll = new ArrayList<>(actorList);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Filter filter = new Filter() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Actor> filterList = actorsAll.stream().filter(s->s.getName().toLowerCase().contains(charSequence)).collect(Collectors.toList());
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            actorList.clear();
            actorList.addAll((Collection<? extends Actor>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actors_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(actorList.get(position).getName());
        holder.txtEmail.setText(actorList.get(position).getDescription());

        Glide.with(context)
                .asBitmap()
                .load(actorList.get(position).getImage())
                .into(holder.avatarImage);
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtEmail;
        private ImageView avatarImage;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtDescription);
            avatarImage = itemView.findViewById(R.id.imageViewContact);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
