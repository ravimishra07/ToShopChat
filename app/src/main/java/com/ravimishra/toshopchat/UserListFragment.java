package com.ravimishra.toshopchat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class UserListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvUser;
    private ImageButton btnAddUser;
    private UserListRvAdapter adapter;
    private  EditText editText;
    private ArrayList<UserListModel> userListModels=new ArrayList<>();
    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        rvUser=view.findViewById(R.id.rvUserList);
        btnAddUser=view.findViewById(R.id.imgBtnAddUser);
        
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputDialog();
            }
        });
        adapter=new UserListRvAdapter(getActivity(),userListModels);
        rvUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUser.setAdapter(adapter);
        retrive();
        return view;
    }

    private void userInputDialog() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        final AlertDialog alert = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.username_dialog_layout, null);
        builder.setView(dialogView);
        editText = (EditText) dialogView.findViewById(R.id.etUsername);

        builder.setTitle("Enter name of User");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String username = editText.getText().toString();
                addUser(username);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             alert.cancel();
            }
        });
        builder.show();
    }

    private void addUser(String username) {
        DbAdapter dbAdapter = new DbAdapter(getActivity());

        dbAdapter.openDB();
        long result = dbAdapter.addUser(username);
        if (result > 0) {
            Toast.makeText(getActivity(), "User Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Unable to add User", Toast.LENGTH_SHORT).show();

        }
        dbAdapter.close();
        retrive();
    }

    private void retrive() {

        DbAdapter dbAdapter = new DbAdapter(getActivity());
        dbAdapter.openDB();

        userListModels.clear();
        Cursor c = dbAdapter.getAllData();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String user = c.getString(1);

            UserListModel model = new UserListModel(user,id);
            userListModels.add(model);
        }

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
