package com.ravimishra.toshopchat.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ravimishra.toshopchat.Fragments.ChatFragment;
import com.ravimishra.toshopchat.Fragments.UserListFragment;
import com.ravimishra.toshopchat.R;
import com.ravimishra.toshopchat.UserListModel;

import java.util.ArrayList;

public class UserListRvAdapter extends RecyclerView.Adapter<UserListRvAdapter.UserListViewHolder>{

    private Context context;
    private ArrayList<UserListModel> userListModels=new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public UserListRvAdapter(Context context, ArrayList<UserListModel> userListModels) {
        this.context = context;
        this.userListModels = userListModels;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.user_list_row,null);
        UserListViewHolder userListViewHolder=new UserListViewHolder(view);
        return userListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListViewHolder userListViewHolder, int i) {
      final UserListModel model =userListModels.get(i);
      userListViewHolder.tvUserName.setText(model.getUserName());
      userListViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Bundle bundle = new Bundle();
              bundle.putString("username", model.getUserName());
              bundle.putString("userID", String.valueOf(model.getId()));

              ChatFragment bookFragment = new ChatFragment();
              bookFragment.setArguments(bundle);

              AppCompatActivity activity = (AppCompatActivity) v.getContext();
              activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, bookFragment).addToBackStack(null).commit();

          }
      });

    }


    @Override
    public int getItemCount() {
        return userListModels.size();
    }
    public  class UserListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private LinearLayout linearLayout;
        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName=itemView.findViewById(R.id.tvUserName);
            linearLayout=itemView.findViewById(R.id.usernamelayout);
        }
    }
}
