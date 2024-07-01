package com.example.laboratorio_07;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterFragment extends Fragment {

    private EditText editTextAuthor, editTextTitle, editTextTechnique, editTextCategory, editTextDescription, editTextYear;
    private Button buttonSave;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        editTextAuthor = view.findViewById(R.id.editTextAuthor);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextTechnique = view.findViewById(R.id.editTextTechnique);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextYear = view.findViewById(R.id.editTextYear);
        buttonSave = view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePaintingData();
                loadFragment(new DisplayFragment());
            }
        });

        return view;
    }

    private void savePaintingData() {
        String author = editTextAuthor.getText().toString();
        String title = editTextTitle.getText().toString();
        String technique = editTextTechnique.getText().toString();
        String category = editTextCategory.getText().toString();
        String description = editTextDescription.getText().toString();
        String year = editTextYear.getText().toString();

        String data = author + "\n" + title + "\n" + technique + "\n" + category + "\n" + description + "\n" + year + "\n";

        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput("registros.txt", getContext().MODE_APPEND);
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
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
