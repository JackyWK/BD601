package com.beidou.bd601.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beidou.bd601.R;
import com.beidou.bd601.view.BarCharView;

/**
 * Created by wangkuan on 2016/9/28 .
 */
public class HomeFragment extends Fragment {
    private BarCharView mStatscsView;
    private Button mRDSSSignalButton;
    private Button mRNSSSignalButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        mRDSSSignalButton = (Button) view.findViewById(R.id.rdss_signal_button);
        mRNSSSignalButton = (Button) view.findViewById(R.id.rnss_signal_button);

        txt_content.setText("主页");
        mStatscsView = (BarCharView) view.findViewById(R.id.statscsView1);
        int[] lastData0 = new int[] { 1, 2, 3, 4, 1, 2, 3, 1, 2 };
        mStatscsView.updateShowData(lastData0);

        mRDSSSignalButton.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Toast.makeText(getActivity(), "rdss",
                        Toast.LENGTH_LONG).show();
                mStatscsView.setmShowStyle(BarCharView.RDSS_STYLE);
                int[] lastData0 = new int[] { 1, 2, 3, 4, 0, 1, 2, 3, 4,  };
                mStatscsView.updateShowData(lastData0);
                mRNSSSignalButton.setBackgroundColor(Color.GRAY);
                mRDSSSignalButton.setBackgroundColor(Color.BLACK);
            }
        });

        mRNSSSignalButton.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Toast.makeText(getActivity(), "rnss",
                        Toast.LENGTH_LONG).show();
                mStatscsView.setmShowStyle(BarCharView.RNSS_STYLE);
                int[] lastData0 = new int[] { 4, 3, 2, 1, 1, 2, 3, 1, 2};
                mStatscsView.updateShowData(lastData0);

                mRDSSSignalButton.setBackgroundColor(Color.GRAY);
                mRNSSSignalButton.setBackgroundColor(Color.BLACK);
            }
        });
        return view;
    }
}
