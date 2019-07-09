package com.example.fragment_4_2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pss on 2019/6/14.
 */

public class RightFragment extends Fragment {
    public static final String TAG="RightFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: 为碎片创建视图--加载布局");
        View view= inflater.inflate(R.layout.right_fragment, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: 碎片和活动建立关联");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 碎片第一次创建，完成加载布局、绑定事件");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated:与碎片相关联的活动已经创建完毕 ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 碎片由不可见变为可见");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 碎片准备好和用户进行交互");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 碎片不可交互，但是依然可见");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 碎片不可见");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: 与碎片相关联的视图被移除");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 碎片被销毁");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: 碎片和活动解除关联");
    }
}
