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

public class ChatPictureReceiveHolder extends RecyclerView.ViewHolder {
    private Unbinder unbinder;

    @BindView(R.id.tv_times)
    public TextView tv_times;
    @BindView(R.id.tv_chat_messages)
    public TextView tv_chat_messages;
    @BindView(R.id.img_previews)
    public ImageView img_previews;

    public ChatPictureReceiveHolder(@NonNull View itemView) {
        super(itemView);

        unbinder = ButterKnife.bind(this, itemView);
    }
}
