package com.sudhanshu.tinderswipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.sudhanshu.tinderswipe.tindercard.FlingCardListener;
import com.sudhanshu.tinderswipe.tindercard.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FlingCardListener.ActionDownInterface {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Question> al;
    private SwipeFlingAdapterView flingContainer;
    public String[] answers = new String [5];
    public ArrayList<String> answerlist = new ArrayList<>();
    public int mIndex =0;

    public boolean isLeftCorrect;
    public boolean isRightCorrect;
    public static void removeBackground() {


        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        al = new ArrayList<>();
        al.add(new Question("Which one is the Gateway of India","http://www.tajmahal.org.uk/gifs/taj-mahal.jpeg","http://static.panoramio.com/photos/large/46690325.jpg"));
        al.add(new Question("Which one is the India Gate","http://www.hotelsdelhi.co.in/userfiles/IndiaGate.jpg - Delhi","http://www.tajmahal.org.uk/gifs/taj-mahal.jpeg"));
        al.add(new Question("Which one is TajMAhal","http://www.tajmahal.org.uk/gifs/taj-mahal.jpeg","http://aamhinashikkar.com/wp-content/uploads/2015/09/Hawa-Mahal-1.jpg"));
        al.add(new Question("Which one is Hawa MAhal","http://worldtoptop.com/wp-content/uploads/2011/10/Aerial-View-of-Lotus-Temple.jpg","http://aamhinashikkar.com/wp-content/uploads/2015/09/Hawa-Mahal-1.jpg"));
        al.add(new Question("Which one is Lotus Temple","http://worldtoptop.com/wp-content/uploads/2011/10/Aerial-View-of-Lotus-Temple.jpg","http://www.hotelsdelhi.co.in/userfiles/IndiaGate.jpg - Delhi"));
        myAppAdapter = new MyAppAdapter(al, MainActivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                al.remove(0);
                isLeftCorrect = true;
                isRightCorrect = false;
//
//
                myAppAdapter.notifyDataSetChanged();


            }

            @Override
            public void onRightCardExit(Object dataObject) {
                isRightCorrect = true;
                isLeftCorrect = false;
                String answer = "right";
                al.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                if(isLeftCorrect){
                    answerlist.add("left");
                    isLeftCorrect = false;
                }else if(isRightCorrect) {
                    answerlist.add("right");
                    isRightCorrect = false;
                }

                if(itemsInAdapter==0){
                    Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                    intent.putExtra("answer-key",answerlist);
                    startActivity(intent);
                }

            }

            @Override
            public void onScroll(float scrollProgressPercent) {


                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
            }
        });



        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onActionDownPerform() {
        Log.e("action", "bingo");
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public ImageView image1;
        public TextView questionText;
        public ImageView image2;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Question> questionList;
        public Context context;

        private MyAppAdapter(List<Question> apps, Context context) {
            this.questionList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return questionList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.questionText = (TextView) rowView.findViewById(R.id.questiontext);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.image1 = (ImageView) rowView.findViewById(R.id.leftImage);
                viewHolder.image2 = (ImageView) rowView.findViewById(R.id.rightImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.questionText.setText(questionList.get(position).getQuestion() + "");
            Picasso.with(MainActivity.this).load(questionList.get(position).getImageUrl1()).fit().into(viewHolder.image1);
            Picasso.with(MainActivity.this).load(questionList.get(position).getImageUrl2()).fit().into(viewHolder.image2);

            return rowView;
        }
    }
}
