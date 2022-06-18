package com.example.androidprojecta.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidprojecta.R;

import com.example.androidprojecta.Actor;
import com.example.androidprojecta.ActorsAdapter;
import com.example.androidprojecta.databinding.FragmentActorsBinding;

import java.util.ArrayList;
import java.util.List;

public class ActorsFragment extends Fragment {

    private FragmentActorsBinding binding;
    private RecyclerView recyclerView;
    private ActorsAdapter adapter;

    public ActorsFragment() {
        List<Actor> actors = new ArrayList<>();
        actors.add(new
                Actor("Emma Watson", "Emma Charlotte Duerre Watson este o actriță britanică și " +
                "model care a devenit celebră pentru rolul ei, Hermione Granger, " +
                "din seria de succes Harry Potter. Emma Watson a fost aleasă pentru rolul lui Hermione Granger " +
                "la vârsta de 9 ani, deși înainte a jucat doar în piesele de teatru de la școală",
                "https://images.squarespace-cdn.com/content/v1/5e1ecc2150dcaa7892c25ca9/1579883482709-8E5P6UG2E7Y7B13H69HR/original.jpeg?format=1500w"));
        actors.add(new
                Actor("Rupert Grint", "Rupert Alexander Lloyd Grint este un actor" +
                " englez care a devenit celebru pentru rolul lui Ron Weasley, unul dintre cele " +
                "trei personaje principale din seria Harry Potter.",
                "https://cdn.adh.reperio.news/image-9/92b1f7ea-b563-4d0b-9602-2e81bea70cc6/index.jpeg?p=w%3D650%26f%3Dpng%26q%3D91"));
        actors.add(new
                Actor("Daniel Radcliffe", "Daniel Jacob Radcliffe este un actor britanic, cel mai bine cunoscut pentru interpretarea " +
                "personajului Harry Potter din filmele cu același nume, bazate pe bine-cunoscuta serie de cărți. ",
                "https://www.ladbible.com/cdn-cgi/image/width=720,quality=70,format=jpeg,fit=pad,dpr=1/https%3A%2F%2Fs3-images.ladbible.com%2Fs3%2Fcontent%2Ff14fff405aff8154ac50c80c285bd5e2.png"));
        adapter = new ActorsAdapter(actors);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ActorsViewModel actorsViewModel =
                new ViewModelProvider(this).get(ActorsViewModel.class);

        binding = FragmentActorsBinding.inflate(inflater, container, false);
        adapter.setContext(getContext());
        View root = binding.getRoot();
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.getFilter().filter(binding.editTextTextPersonName.getText().toString());
            }
        });

        recyclerView = binding.actorsRecView;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}