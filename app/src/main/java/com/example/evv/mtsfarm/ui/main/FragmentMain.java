package com.example.evv.mtsfarm.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Cow;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class FragmentMain extends Fragment implements ContractMain.View {
    private ContractMain.Presenter mPresenter;
    private List<Cow> mData;
    private MyTableAdapter mAdapter;
    final static String[] TABLE_HEADERS = {"ID", "ИМЯ", "СТАДО"};

    public FragmentMain() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TableView tableView = (TableView) rootView.findViewById(R.id.tableView);
        //tableView.setColumnCount(3);

        mData = new ArrayList<Cow>();
        mAdapter = new MyTableAdapter(getContext(), mData);
        tableView.setDataAdapter(mAdapter);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        columnModel.setColumnWeight(3, 1);
        tableView.setColumnModel(columnModel);

        // Чтобы задать размер текста в загаловке
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getActivity(), TABLE_HEADERS);
        headerAdapter.setTextSize(10);
        tableView.setHeaderAdapter(headerAdapter);


        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter = new PresenterMain();
        mPresenter.attachView(this);
        mPresenter.getData();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showInternet() {

    }

    @Override
    public void showToast() {

    }

    @Override
    public void getData() {
        mPresenter.getData();
    }

    @Override
    public void refreshData(List<Cow> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }


}
