package com.example.evv.mtsfarm.ui.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.ui.main.ContractMain;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class FragmentDetail extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private ContractMain.Presenter mPresenter;
    private List<Cow> mDataMilking;
    private List<Cow> mDataWeight;
    private List<Cow> mDataTemperature;
    private MyEditTableAdapter mAdapterMilking;
    private MyEditTableAdapter mAdapterWeight;
    private MyEditTableAdapter mAdapterTemperature;

    final static String[] MILKING_HEADERS = {"ДАТА", "ЛИТРЫ"};
    final static String[] WEIGHT_HEADERS = {"ДАТА", "КГ"};
    final static String[] TEMPERATURE_HEADERS = {"ДАТА", "ТЕМПЕРАТУРА"};

    private Button mBtnSaveMilking;
    private Button mBtnSaveWeight;
    private Button mBtnSaveTemperature;

    public FragmentDetail() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mDataMilking = new ArrayList<Cow>();
        mDataWeight = new ArrayList<Cow>();
        mDataTemperature = new ArrayList<Cow>();

        mAdapterMilking = new MyEditTableAdapter(getActivity(), mDataMilking);
        mAdapterWeight = new MyEditTableAdapter(getActivity(), mDataWeight);
        mAdapterTemperature = new MyEditTableAdapter(getActivity(), mDataTemperature);

        TableView tableMilking = (TableView) rootView.findViewById(R.id.table_milking);
        TableView tableWeight = (TableView) rootView.findViewById(R.id.table_weight);
        TableView tableTemperature = (TableView) rootView.findViewById(R.id.table_temperature);

        tableMilking.setDataAdapter(mAdapterMilking);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(2);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        tableMilking.setColumnModel(columnModel);
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getActivity(), MILKING_HEADERS);
        headerAdapter.setTextSize(10);
        tableMilking.setHeaderAdapter(headerAdapter);

        tableWeight.setDataAdapter(mAdapterWeight);
        TableColumnWeightModel columnModelWeight = new TableColumnWeightModel(2);
        columnModelWeight.setColumnWeight(1, 1);
        columnModelWeight.setColumnWeight(2, 1);
        tableWeight.setColumnModel(columnModelWeight);
        SimpleTableHeaderAdapter headerlWeight = new SimpleTableHeaderAdapter(getActivity(), WEIGHT_HEADERS);
        headerlWeight.setTextSize(10);
        tableWeight.setHeaderAdapter(headerlWeight);

        tableTemperature.setDataAdapter(mAdapterTemperature);
        TableColumnWeightModel columnModelTemp = new TableColumnWeightModel(2);
        columnModelTemp.setColumnWeight(1, 1);
        columnModelTemp.setColumnWeight(2, 1);
        tableTemperature.setColumnModel(columnModelTemp);
        SimpleTableHeaderAdapter adapterTemp = new SimpleTableHeaderAdapter(getActivity(), TEMPERATURE_HEADERS);
        adapterTemp.setTextSize(10);
        tableTemperature.setHeaderAdapter(adapterTemp);

        mBtnSaveMilking = rootView.findViewById(R.id.save_milking);
        mBtnSaveWeight = rootView.findViewById(R.id.save_weight);
        mBtnSaveTemperature = rootView.findViewById(R.id.save_temperature);

        mBtnSaveMilking.setOnClickListener(myClick);
        mBtnSaveWeight.setOnClickListener(myClick);
        mBtnSaveTemperature.setOnClickListener(myClick);


        return rootView;
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (Cow cow : mDataMilking) {
                Log.d(TAG, String.valueOf(cow.id));
                Log.d(TAG, String.valueOf(cow.name));
                Log.d(TAG, String.valueOf(cow.herd));
            }

        }
    };


}
