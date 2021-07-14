package com.tea.myapplication.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tea.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChatTextReceiveHolder extends RecyclerView.ViewHolder {
    private Unbinder unbinder;

    @BindView(R.id.tv_time)
    public TextView tv_time;
    @BindView(R.id.tv_chat_message)
    public TextView tv_chat_message;

    public ChatTextReceiveHolder(@NonNull View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }
}
