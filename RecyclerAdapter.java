package com.skumbam.flickerassignmet;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skumbam.flickerassignmet.databinding.ViewImageDataBinding;

import java.util.List;

/**
 * Created by skumbam on 10/25/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HolderView>{

    private List<ImageResultResponse> imageData;
    private ViewImageDataBinding mBinding;


    RecyclerAdapter( List<ImageResultResponse> imageData) {
            this.imageData = imageData;

    }

    void addImageData(PhotoCollectionResponse imageData) {
        if (this.imageData != null) {
            this.imageData.addAll(imageData.getmPhotoInfo().getPhotoData());
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_image_data,parent,false);
        mBinding = DataBindingUtil.bind(view);
        return new HolderView(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
         mBinding.setImageUrl(imageData.get(position));
    }

    @Override
    public int getItemCount() {
        return imageData.size();
    }


    public class HolderView extends RecyclerView.ViewHolder{

        public HolderView(ViewImageDataBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
