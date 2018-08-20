package com.example.evv.mtsfarm.ui.detail;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class MyEditTableAdapter extends TableDataAdapter<Cow> {
    private final int TEXT_SIZE = 14;
    Context mContext;
    private final String ID = "id";
    private final String NAME = "name";
    private final String HERD = "herd";

    public MyEditTableAdapter(Context context, List<Cow> data) {
        super(context, data);
        mContext = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Cow cow = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderView(String.valueOf(cow.id), ID, cow);
                break;
            case 1:
                renderedView = renderView(cow.name, NAME, cow);
                break;
            case 2:
                renderedView = renderView(String.valueOf(cow.herd), HERD, cow);
                break;
        }
        return renderedView;
    }

    private EditText renderView(String text, String field, Cow cow) {
        final EditText editText = new EditText(getContext());
        editText.setText(text);
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        switch (field) {
            case ID:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case NAME:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case HERD:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (field) {
                    case ID:
                        String id = s.toString();
                        int dd = Integer.valueOf(id);
                        cow.id = dd;
                        break;
                    case NAME:
                        cow.name = s.toString();
                        break;
                    case HERD:
                        cow.herd = Integer.valueOf(s.toString());
                        break;
                }
            }
        });

        return editText;

    }
}
