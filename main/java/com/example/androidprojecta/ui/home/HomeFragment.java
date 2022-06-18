package com.example.androidprojecta.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import com.example.androidprojecta.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidprojecta.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String videoPath = "android.resource://" +getContext().getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        binding.videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());
        binding.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(binding.videoView);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}