package com.tea.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tea.myapplication.Activity.ChatActivity;
import com.tea.myapplication.Common.Common;
import com.tea.myapplication.Model.UserModel;
import com.tea.myapplication.R;
import com.tea.myapplication.ViewHolders.UserViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PeopleFragment extends Fragment {

    @BindView(R.id.recycler_people)
    RecyclerView recycler_people;

    FirebaseRecyclerAdapter adapter;

    private Unbinder unbinder;
    private PeopleViewModel mViewModel;

    static PeopleFragment instance;

    public static PeopleFragment getInstance() {
        return instance == null ? new PeopleFragment() : instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.people_fragment, container, false);
        initView(itemView);
        loadPeople();

        return itemView;
    }

    private void loadPeople() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(Common.USER_REFERENCE);
        FirebaseRecyclerOptions<UserModel> options = new FirebaseRecyclerOptions
                .Builder<UserModel>()
                .setQuery(query, UserModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<UserModel, UserViewHolder>(options) {
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_people, parent, false);
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull UserModel model) {
                if (!adapter.getRef(position).getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    // Hide yourself
                    ColorGenerator generator = ColorGenerator.MATERIAL;
                    int color = generator.getColor(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    TextDrawable.IBuilder builder = TextDrawable.builder()
                            .beginConfig()
                            .withBorder(4)
                            .endConfig()
                            .round();

                    TextDrawable drawable = builder.build(model.getFirstName().substring(0, 1), color);
                    holder.img_avatar.setImageDrawable(drawable);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(model.getFirstName()).append(" ").append(model.getLastName());
                    holder.tv_name.setText(stringBuilder.toString());
                    holder.tv_bio.setText(model.getBio());

                    // Events
                    holder.itemView.setOnClickListener(v -> {
                        Common.chatUser = model;
                        Common.chatUser.setUid(adapter.getRef(position).getKey());

                        String roomId = Common.generateChatRoomId(FirebaseAuth.getInstance().getCurrentUser().getUid(), Common.chatUser.getUid());
                        Common.roomSelected = roomId;

                        Log.d("ROOM_ID", "ROOM is: " + roomId);

                        // Register topics
                        FirebaseMessaging.getInstance().subscribeToTopic(roomId)
                                .addOnSuccessListener(aVoid -> {
                                    startActivity(new Intent(getContext(), ChatActivity.class));
                                });
                    });

                } else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }
        };

        adapter.startListening();
        recycler_people.setAdapter(adapter);
    }

    private void initView(View itemView) {
        unbinder = ButterKnife.bind(this, itemView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_people.setLayoutManager(layoutManager);
        recycler_people.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) adapter.startListening();
    }

    @Override
    public void onStop() {
        if (adapter != null) adapter.stopListening();
        super.onStop();
    }
}