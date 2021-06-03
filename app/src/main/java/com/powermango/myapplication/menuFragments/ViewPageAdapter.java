package com.powermango.myapplication.menuFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.powermango.myapplication.R;

public class ViewPageAdapter extends RecyclerView.Adapter<ViewPageAdapter.ViewHolder>{
    private ViewPager2 viewPage;
    private LayoutInflater inflater;
    private NavController navC;
    private int[] sData;

    ViewPageAdapter(Context cont, ViewPager2 vpage, NavController nav){
        this.viewPage = vpage;
        this.inflater = LayoutInflater.from(cont);
        this.navC = nav;
    }

    @NonNull
    @Override
    public ViewPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_lecciones_main_menu_button,parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPageAdapter.ViewHolder holder, int position) {

        switch (position){
            case 0:{
                holder.btn.setImageResource(R.drawable.book);
                holder.tvOption.setText("Lecciones");
                holder.btn.setOnClickListener(viewl -> {
                    navC.navigate(R.id.action_mainMenu_to_leccionesFrag);
                });
                break;
            }
            case 1:{
                holder.btn.setImageResource(R.drawable.training);
                holder.tvOption.setText("Practicar");
                holder.btn.setOnClickListener(viewl -> {
                    navC.navigate(R.id.action_mainMenu_to_menuEjercicios);
                });
                break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btn;
        TextView tvOption;
        ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.LeccImageButton);
            tvOption = itemView.findViewById(R.id.tvOption);

        }
    }
}
