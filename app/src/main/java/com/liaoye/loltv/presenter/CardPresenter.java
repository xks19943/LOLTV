package com.liaoye.loltv.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.liaoye.loltv.R;
import com.liaoye.loltv.api.Api;
import com.liaoye.loltv.bean.Champion;


/**
 * Created by xiaoming on 2017/7/23.
 */

public class CardPresenter extends Presenter {
    private static int CARD_WIDTH = 313;
    private static int CARD_HEIGHT = 176;

    private Context mContext;
    public CardPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageCardView imageCardView = new ImageCardView(mContext);
        //修改imageCardView的类型  设置信息在主区域的下面  ImageCardView具有imageView作为主要区域，标题和内容文本位于信息区域
        imageCardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        //设置信息的显示方式  当选择条目的时候才显示信息
        imageCardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        imageCardView.setFocusable(true);
        imageCardView.setFocusableInTouchMode(true);
        imageCardView.setBackgroundColor(mContext.getResources().getColor(R.color.default_background));
        return new ImageCardViewHolder(imageCardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Champion champion= (Champion) item;
        ((ImageCardViewHolder) viewHolder).setChampion(champion);
        ImageCardView cardView =  ((ImageCardViewHolder) viewHolder).mCardView;
        cardView.setTitleText(champion.title);
        cardView.setContentText(champion.cname);
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        Glide.with(mContext)
                .load(Api.BaseImageUrl+champion.id+".png")
                .centerCrop()
                .error(((ImageCardViewHolder) viewHolder).getDefaultCardImage())
                .into(cardView.getMainImageView());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    class ImageCardViewHolder extends ViewHolder {
        private Champion champion;
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;

        public ImageCardViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.movie);
        }

        public void setChampion(Champion m) {
            this.champion = m;
        }

        public Champion getChampion() {
            return champion;
        }

        public ImageCardView getCardView() {
            return mCardView;
        }

        public Drawable getDefaultCardImage() {
            return mDefaultCardImage;
        }


    }
}
