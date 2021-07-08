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

public class ChatInfoHolder extends RecyclerView.ViewHolder {

    Unbinder unbinder;

    @BindView(R.id.img_avatar)
    public ImageView img_avatar;
    @BindView(R.id.tv_name)
    public TextView tv_name;
    @BindView(R.id.tv_last_message)
    public TextView tv_last_message;
    @BindView(R.id.tv_time)
    public TextView tv_time;

    public ChatInfoHolder(@NonNull View itemView) {
        super(itemView);

        unbinder = ButterKnife.bind(this, itemView);
    }
}
