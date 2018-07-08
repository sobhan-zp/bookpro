package pro.book.ar.Adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.List;

import pro.book.ar.Model.Main;
import pro.book.ar.R;

public class RecyclerViewAdapter_main extends RecyclerView.Adapter<RecyclerViewAdapter_main.ItemViewHolder> {

    private static final String TAG = RecyclerViewAdapter_main.class.getSimpleName();
 //   private List<Main> itemList;

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        //TextView textView;
        ImageView imgMain;

        ShimmerTextView textView;
        Shimmer shimmer;

        ItemViewHolder(View view) {
            super(view);
            textView = (ShimmerTextView) view.findViewById(R.id.card_view_label);
            shimmer = new Shimmer();
            imgMain = (ImageView) view.findViewById(R.id.img_main);






        }
    }


    private ArrayList<Main> stringArrayList;

    private Context context;

    public RecyclerViewAdapter_main(Context context, ArrayList<Main> stringArrayList) {
        this.stringArrayList = stringArrayList;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_row_main, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(final ItemViewHolder viewHolder, final int i) {
       // final Main Item = itemList.get(i);


        viewHolder.textView.setText(stringArrayList.get(i).getTitle());

        Glide.with(viewHolder.imgMain.getContext())
                .load(stringArrayList.get(i).getImage())
                .fitCenter()
                .into(viewHolder.imgMain);


        viewHolder.shimmer.setRepeatCount(-1)
                .setDuration(2000)
                .setStartDelay(300)
                .setDirection(Shimmer.ANIMATION_DIRECTION_RTL)
                .setAnimatorListener(new Animator.AnimatorListener(){
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

        viewHolder.shimmer.start(viewHolder.textView);

    }






    @Override
    public int getItemCount() {
        return (null != stringArrayList ? stringArrayList.size() : 0);
    }



}

