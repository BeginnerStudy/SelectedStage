package com.example.selectedstage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.selectedstage.Adapter.StageAdapter;
import com.example.selectedstage.Model.StageModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String imageUri = "http://cloud.culture.tw/e_new_upload/task/ec0c4582-6042-444b-9a5a-3afe8cb1b3ff/19850819000046/8baae94be3640b467d92295269fce5728280f39b.JPG";
    public static ImageView imageView;
    private RecyclerView recyclerView;

    private ArrayList<StageModel> stageModels;
    private StageAdapter stageAdapter;
    private int progress = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.main_image);
        Glide.with(this).load(imageUri).into(imageView);

        //設置Image黑白濾鏡
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f); //飽和度0為黑白畫面
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setStackFromEnd(true);//設定 item 從下往上添加
//        manager.setReverseLayout(true);//反轉整個內容 (搭配 setStackFromEnd 最新的item在最上方)
        recyclerView.setLayoutManager(manager);

        stageModels = new ArrayList<>();
        stageModels.clear();

        stageModels.add(new StageModel(null,"STAGE 1-1","勇者的起點" ));
        stageModels.add(new StageModel(null,"STAGE 1-2","守護聖蹟亭" ));
        stageModels.add(new StageModel(null,"STAGE 1-3","制裁不良觀光客" ));
        stageModels.add(new StageModel(null,"STAGE 1-4","消滅不肖建築商" ));
        stageModels.add(new StageModel(null,"STAGE 1-5","BOSS - 遠熊大股東!!" ));

        stageAdapter = new StageAdapter(MainActivity.this, stageModels, progress);
        stageAdapter.notifyItemRangeRemoved(1,stageModels.size());
        stageAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(stageAdapter);

        stageAdapter.setCheckPosition(-1);//預設焦點
        stageAdapter.setOnRecyclerViewItemClickListener(new StageAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                stageAdapter.setCheckPosition(position);
                stageAdapter.notifyDataSetChanged();
            }
        });

    }
}
