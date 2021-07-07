package com.tea.myapplication.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tea.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_avatar)
    public ImageView img_avatar;
    @BindView(R.id.tv_name)
    public TextView tv_name;
    @BindView(R.id.tv_bio)
    public TextView tv_bio;

    private Unbinder unbinder;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }
}
