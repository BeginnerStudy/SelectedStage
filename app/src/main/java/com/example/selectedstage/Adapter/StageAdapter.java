package com.example.selectedstage.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selectedstage.MainActivity;
import com.example.selectedstage.Model.StageModel;
import com.example.selectedstage.R;

import java.util.List;

public class StageAdapter extends RecyclerView.Adapter<StageAdapter.ViewHolder> {

    private Context context;
    private List<StageModel> stageModels;
    private int progress ;//關卡進度

    public StageAdapter(Context context, List<StageModel> stageModels,int progress) {
        this.context = context;
        this.stageModels = stageModels;
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    // item可取得焦點(變數&方法) -->
    private int checkPosition;
    private OnItemClickListener onRecyclerViewItemClickListener;

    public void setCheckPosition(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    public int getCheckPosition() {
        return checkPosition;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener){
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }
    //<--item可取得焦點(變數&方法)


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview, parent, false);
        return new StageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        StageModel stageModel = stageModels.get(position);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.in_anim);

        if (position < getProgress()){
            holder.imageView.setImageTintList(null);
        }

        holder.txt_stage.setText(stageModel.getStage());
        holder.txt_name.setText(stageModel.getName());

        if (position == getCheckPosition()){
            holder.relative.setVisibility(View.VISIBLE);
            holder.relative.setAnimation(animation);
            holder.itemView.setBackground(context.getDrawable(R.drawable.item_selector_select));
        }else {
            holder.relative.setVisibility(View.GONE);
            holder.itemView.setBackground(context.getDrawable(R.drawable.item_selector_default));
        }

        // item可取得焦點 回Call方法
        if (onRecyclerViewItemClickListener != null){
            if (position < getProgress()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewItemClickListener.onClick(position);
                    }
                });
            }
        }

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.clear.equals("Clear")) {
                    holder.clear.setText("Clear");
                    holder.clear.setVisibility(View.VISIBLE);
                    Toast.makeText(context, holder.txt_stage.getText().toString() + "通關", Toast.LENGTH_SHORT).show();
                }

                if (position - getProgress() == -1){//每一關過關 變數只會+1次
                    progress++;
                    setProgress(progress);
                    notifyDataSetChanged();
                }

                if (progress>5){
                    MainActivity.imageView.setColorFilter(null);//全部通關後移除黑白濾鏡
                }
            }
        });
        Log.d("Progress",""+progress);
    }

    @Override
    public int getItemCount() {
        return stageModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_stage, txt_name, clear;
        public Button start, memo;
        public RelativeLayout relative;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clear = itemView.findViewById(R.id.clear);
            imageView = itemView.findViewById(R.id.image);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_stage = itemView.findViewById(R.id.txt_stage);
            start = itemView.findViewById(R.id.start);
            memo = itemView.findViewById(R.id.memo);
            relative = itemView.findViewById(R.id.relative);
        }
    }
}
