package com.example.evv.mtsfarm.ui.detail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.ui.main.ContractMain;
import com.example.evv.mtsfarm.ui.main.MyTableAdapter;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class FragmentDetail extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private ContractMain.Presenter mPresenter;
    private List<Cow> mData;
    private MyEditTableAdapter mAdapter;
    final static String[] TABLE_HEADERS = {"ID", "ИМЯ", "СТАДО"};
    private Button mBtnSave;

    public FragmentDetail() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mData = new ArrayList<Cow>();
        Cow cow = new Cow();
        cow.id = 1;
        cow.name = "Test";
        cow.herd = 100;
        mData.add(cow);
        mAdapter = new MyEditTableAdapter(getActivity(), mData);
        TableView tableView = (TableView) rootView.findViewById(R.id.table_milking);

        tableView.setDataAdapter(mAdapter);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        columnModel.setColumnWeight(3, 1);
        tableView.setColumnModel(columnModel);
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getActivity(), TABLE_HEADERS);
        headerAdapter.setTextSize(10);
        tableView.setHeaderAdapter(headerAdapter);

        mBtnSave = rootView.findViewById(R.id.save);
        mBtnSave.setOnClickListener(myClick);

        return rootView;
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (Cow cow : mData) {
                Log.d(TAG, String.valueOf(cow.id));
                Log.d(TAG, String.valueOf(cow.name));
                Log.d(TAG, String.valueOf(cow.herd));
            }

        }
    };

}
