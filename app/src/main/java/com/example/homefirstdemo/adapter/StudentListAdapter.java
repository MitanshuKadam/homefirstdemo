package com.example.homefirstdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homefirstdemo.Model.StudentModel;
import com.example.homefirstdemo.Model.UserModel;
import com.example.homefirstdemo.R;
import com.example.homefirstdemo.UserListActivity;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    Context mCtx;
    List<StudentModel> userModelArrayList;
    TextView tv_listName,tv_listMobile,tv_sr,tv_course;

    public StudentListAdapter(Context context){
        mCtx=context;
    }

    public void setStudentListAdapter(List<StudentModel> studentModelList){
        this.userModelArrayList = studentModelList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new StudentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, int position) {

        if (userModelArrayList!=null){
            holder.setdata(userModelArrayList.get(position));
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        public StudentViewHolder( View itemView) {
            super(itemView);

            tv_listName = itemView.findViewById(R.id.tv_listName);
            tv_sr = itemView.findViewById(R.id.tv_sr);
            tv_listMobile = itemView.findViewById(R.id.tv_listMobile);
            tv_course = itemView.findViewById(R.id.tv_course);
        }

        public void setdata(StudentModel userModel) {

            tv_sr.setText(""+userModel.getID());
            tv_course.setText("( "+userModel.getStudentCourse()+" )");
            tv_listMobile.setText(userModel.getMobile());
            tv_listName.setText(userModel.getStudentName());

        }
    }
}
