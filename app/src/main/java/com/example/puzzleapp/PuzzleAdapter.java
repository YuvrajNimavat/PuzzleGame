package com.example.puzzleapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PuzzleAdapter extends RecyclerView.Adapter<PuzzleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> numbers;
    private PuzzleGame puzzleGame;

    public PuzzleAdapter(Context context, ArrayList<Integer> numbers, PuzzleGame puzzleGame) {
        this.context = context;
        this.numbers = numbers;
        this.puzzleGame = puzzleGame;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.puzzle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int number = numbers.get(position);
        holder.button.setText(number == 0 ? "" : String.valueOf(number));
        holder.button.setEnabled(number != 0);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puzzleGame.move(position);
                notifyDataSetChanged();
                if (puzzleGame.isSolved()) {
                    Toast.makeText(context, "You won!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }
}
