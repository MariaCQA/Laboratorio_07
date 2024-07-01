package com.example.laboratorio_07;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PaintingAdapter extends RecyclerView.Adapter<PaintingAdapter.PaintingViewHolder> {

    private List<String[]> paintingList;

    public PaintingAdapter(List<String[]> paintingList) {
        this.paintingList = paintingList;
    }

    @NonNull
    @Override
    public PaintingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_painting, parent, false);
        return new PaintingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaintingViewHolder holder, int position) {
        String[] painting = paintingList.get(position);
        holder.textViewAuthor.setText("Autor: " + painting[0]);
        holder.textViewTitle.setText("Título: " + painting[1]);
        holder.textViewTechnique.setText("Técnica: " + painting[2]);
        holder.textViewCategory.setText("Categoría: " + painting[3]);
        holder.textViewDescription.setText("Descripción: " + painting[4]);
        holder.textViewYear.setText("Año: " + painting[5]);
    }

    @Override
    public int getItemCount() {
        return paintingList.size();
    }

    static class PaintingViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAuthor, textViewTitle, textViewTechnique, textViewCategory, textViewDescription, textViewYear;

        public PaintingViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewTechnique = itemView.findViewById(R.id.textViewTechnique);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewYear = itemView.findViewById(R.id.textViewYear);
        }
    }
}
