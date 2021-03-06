package com.example.evv.mtsfarm.ui.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;
import com.example.evv.mtsfarm.ui.detail.adapters.MilkingTableAdapter;
import com.example.evv.mtsfarm.ui.detail.adapters.TempTableAdapter;
import com.example.evv.mtsfarm.ui.detail.adapters.WeightTableAdapter;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class FragmentDetail extends Fragment implements ContractDetail.View {

    private final String TAG = this.getClass().getSimpleName();
    private ContractDetail.Presenter mPresenter;
    private List<Milking> mDataMilking;
    private List<Weight> mDataWeight;
    private List<Temperature> mDataTemperature;
    private MilkingTableAdapter mAdapterMilking;
    private WeightTableAdapter mAdapterWeight;
    private TempTableAdapter mAdapterTemperature;

    final static String[] MILKING_HEADERS = {"ДАТА", "ЛИТРЫ"};
    final static String[] WEIGHT_HEADERS = {"ДАТА", "КГ"};
    final static String[] TEMPERATURE_HEADERS = {"ДАТА", "ТЕМПЕРАТУРА"};

    private Button mBtnSaveMilking;
    private Button mBtnSaveWeight;
    private Button mBtnSaveTemperature;

    private TextView mTvId;
    private TextView mTvHerd;
    private TextView mTvFarm;
    private TextView mTvName;

    public FragmentDetail() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.detail));

        mTvId = rootView.findViewById(R.id.tv_id);
        mTvFarm = rootView.findViewById(R.id.tv_farm);
        mTvHerd = rootView.findViewById(R.id.tv_herd);
        mTvName = rootView.findViewById(R.id.tv_name);

        mDataMilking = new ArrayList<>();
        mDataWeight = new ArrayList<>();
        mDataTemperature = new ArrayList<>();

        mAdapterMilking = new MilkingTableAdapter(getActivity(), mDataMilking);
        mAdapterWeight = new WeightTableAdapter(getActivity(), mDataWeight);
        mAdapterTemperature = new TempTableAdapter(getActivity(), mDataTemperature);

        TableView tableMilking = (TableView) rootView.findViewById(R.id.table_milking);
        TableView tableWeight = (TableView) rootView.findViewById(R.id.table_weight);
        TableView tableTemperature = (TableView) rootView.findViewById(R.id.table_temperature);

        LinearLayout layout = rootView.findViewById(R.id.table_milking);
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.width = -1;
        params.height = 2000;
        layout.setLayoutParams(params);

        LinearLayout layout2 = rootView.findViewById(R.id.table_weight);
        layout2.setLayoutParams(params);


        LinearLayout layout3 = rootView.findViewById(R.id.table_temperature);
        layout3.setLayoutParams(params);


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

    @Override
    public void onStart() {
        super.onStart();
        mPresenter = new PresenterDetail();
        mPresenter.attachView(this);
        mPresenter.getData();
        mPresenter.getCow();
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save_milking:
                    mPresenter.saveMilking(mDataMilking);
                    break;
                case R.id.save_weight:
                    mPresenter.saveWeight(mDataWeight);
                    break;
                case R.id.save_temperature:
                    mPresenter.saveTemperature(mDataTemperature);
                    break;
            }
        }
    };

    @Override
    public void setData(Detail detailList) {
        mDataMilking.addAll(detailList.getMilkings());
        mAdapterMilking.notifyDataSetChanged();

        mDataWeight.addAll(detailList.getWeights());
        mAdapterWeight.notifyDataSetChanged();

        mDataTemperature.addAll(detailList.getTemperatures());
        mAdapterTemperature.notifyDataSetChanged();
    }

    @Override
    public void setCow(Cow cow) {
        mTvId.setText(String.valueOf(cow.id));
        mTvHerd.setText(String.valueOf(cow.herd));
        mTvFarm.setText(cow.farm);
        String name = cow.name + "( " + cow.age + " лет, " + +cow.weight + "кг, " + cow.status + " )";
        mTvName.setText(name);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.detouchView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_LONG).show();
    }
}
