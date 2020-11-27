package com.example.fragmentdemo.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.DisplayActivity;
import com.example.fragmentdemo.R;
import com.example.fragmentdemo.SoundActivity;

/**
 * @author JonesYang
 * @Data 2020-11-24
 * @Function
 */
public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View mView;

    private ListView mListView;
    private ArrayAdapter<String> mStringArrayAdapter;
    private String[] menu = {"Sound", "Display"};
    private boolean isTwoPane;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_menu, container, false);
        mListView = mView.findViewById(R.id.menu_list);
        mListView.setAdapter(mStringArrayAdapter);
        mListView.setOnItemClickListener(this);
        return mView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mStringArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.detail_layout) == null) {
            isTwoPane = false;
        } else {
            isTwoPane = true;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (isTwoPane) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new SoundFragment();
            } else {
                fragment = new DisplayFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.detail_layout, fragment).commit();
        } else {
            Intent intent = null;
            if (position == 0) {
                intent = new Intent(getActivity(), SoundActivity.class);
            } else {
                intent = new Intent(getActivity(), DisplayActivity.class);
            }
            startActivity(intent);
        }
    }
}
