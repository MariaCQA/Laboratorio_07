package com.example.laboratorio_07;



import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DisplayFragment extends Fragment {

    private RecyclerView recyclerView;
    private PaintingAdapter paintingAdapter;
    private List<String[]> paintings = new ArrayList<>();
    private Button buttonAddPainting;

    public DisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        buttonAddPainting = view.findViewById(R.id.buttonAddPainting);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        paintingAdapter = new PaintingAdapter(paintings);
        recyclerView.setAdapter(paintingAdapter);

        buttonAddPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new RegisterFragment());
            }
        });

        loadPaintingData();

        return view;
    }

    private void loadPaintingData() {
        FileInputStream fis = null;
        try {
            fis = getActivity().openFileInput("registros.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                String author = line;
                String title = br.readLine();
                String technique = br.readLine();
                String category = br.readLine();
                String description = br.readLine();
                String year = br.readLine();
                paintings.add(0, new String[]{author, title, technique, category, description, year});
            }

            paintingAdapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

