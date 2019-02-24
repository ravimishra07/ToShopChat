package com.ravimishra.toshopchat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListRvAdapter extends RecyclerView.Adapter<UserListRvAdapter.UserListViewHolder>{

    private Context context;
    private ArrayList<UserListModel> userListModels=new ArrayList<>();

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
    public void onBindViewHolder(@NonNull UserListViewHolder userListViewHolder, int i) {
      UserListModel model =userListModels.get(i);
      userListViewHolder.tvUserName.setText(model.getUserName());

    }


    @Override
    public int getItemCount() {
        return userListModels.size();
    }
    public  class UserListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName=itemView.findViewById(R.id.tvUserName);
        }
    }
}
